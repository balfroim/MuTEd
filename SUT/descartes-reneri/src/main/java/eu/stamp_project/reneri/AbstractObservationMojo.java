package eu.stamp_project.reneri;

import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.element;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executeMojo;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executionEnvironment;
import static org.twdata.maven.mojoexecutor.MojoExecutor.goal;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.MavenPluginManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.version.PluginVersionResolutionException;
import org.apache.maven.plugin.version.PluginVersionResolver;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.reloc.asm.ClassReader;
import org.pitest.reloc.asm.ClassWriter;

import eu.stamp_project.mutationtest.descartes.codegeneration.MutationClassAdapter;
import eu.stamp_project.reneri.diff.ObservedValueMap;
import eu.stamp_project.reneri.utils.FileUtils;
import spoon.MavenLauncher;
import spoon.reflect.declaration.CtClass;

public abstract class AbstractObservationMojo extends AbstractDiffMojo {

    @Parameter(defaultValue = "${session}", readonly = true)
    private MavenSession mavenSession;

    @Component
    private BuildPluginManager pluginManager;

    @Component
    private MavenPluginManager mavenPluginManager;

    @Component
    private PluginVersionResolver versionResolver;

    /**
     * Number of times the tests should be executed.
     */
    @Parameter(property = "testTimes", defaultValue = "10")
    private int testTimes;

    public int getTestTimes() {
        return testTimes;
    }

    public void setTestTimes(int testTimes) {
        this.testTimes = testTimes;
    }

    /**
     * Name of the JSON file where stack traces will be stored.
     */
    @Parameter(property = "stackTraces", defaultValue = "${project.build.directory}/stack-traces.json")
    private File stackTracesFile;

    public File getStackTracesFile() {
        return stackTracesFile;
    }

    public void setStackTracesFile(File stackTraces) {
        this.stackTracesFile = stackTraces;
    }

    /**
     * Test names that will be ignored.
     */
    @Parameter(property = "excludedTests")
    private Set<String> excludedTests;

    public Set<String> getExcludedTests() {
        if (excludedTests == null) {
            return Collections.emptySet();
        }
        return excludedTests;
    }

    public void setExcludedTests(Set<String> excludedTests) {
        if (excludedTests == null) {
            excludedTests = Collections.emptySet();
        }
        this.excludedTests = excludedTests;
    }

    @Parameter(property = "computeDiff", defaultValue = "true")
    private boolean computeDiff;

    public boolean shouldComputeDiff() {
        return computeDiff;
    }

    public void setComputeDiff(boolean computeDiff) {
        this.computeDiff = computeDiff;
    }

    @Parameter(property = "keepObservations", defaultValue = "false")
    private boolean keepObservations;

    public boolean shouldKeepObservations() {
        return keepObservations;
    }

    public void setKeepObservations(boolean keepObservations) {
        this.keepObservations = keepObservations;
    }

    private MavenPluginResolver pluginResolver;

    protected MavenPluginResolver getPluginResolver() {
        if (pluginResolver == null) {
            pluginResolver = new MavenPluginResolver(getProject(), mavenSession, versionResolver);
        }
        return pluginResolver;
    }

    private MethodTracesEntry[] methodStackTraces;

    protected MethodTracesEntry[] getMethodStackTraces() throws MojoExecutionException {
        if (methodStackTraces != null) {
            return methodStackTraces;
        }

        if (!stackTracesFile.exists()) {
            getLog().warn("There are no stack traces record. Only test cases reported by PIT are going to be considered.");
            return methodStackTraces = new MethodTracesEntry[0];
        }

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(stackTracesFile)) {
            return methodStackTraces = gson.fromJson(reader, MethodTracesEntry[].class);
        } catch (Exception exc) {
            throw new MojoExecutionException("An error occurred while loading the stack traces file.", exc);
        }
    }

    protected void setUserPropertyInSession(String property, String value) {
        mavenSession.getUserProperties().setProperty(property, value);
    }

    protected void executePlugin(String groupId, String artifactId, String version, Xpp3Dom configuration, String goalToExecute) throws MojoExecutionException {
        try {
            Plugin plugin = getPluginResolver().resolve(groupId, artifactId, version, configuration);
            executeMojo(plugin, goal(goalToExecute),
                    (Xpp3Dom) plugin.getConfiguration(),
                    executionEnvironment(getProject(), mavenSession, pluginManager));
        } catch (PluginVersionResolutionException exc) {
            throw new MojoExecutionException(String.format("Could not find %s:%s:%s", groupId, artifactId, version), exc);
        }
    }

    private Set<String> testClasses;

    private InheritanceGraph graph;

    protected Set<String> getTestClasses() {
        return testClasses;
    }

    protected Set<String> getInheritanceClousure(Set<String> classes) {
        return graph.getInheritanceClousure(classes);
    }

    protected void setTestClasses(Set<CtClass<?>> testClasses) {
        if (testClasses == null) {
            this.testClasses = Collections.emptySet();
            graph = null;
            return;
        }
        this.testClasses = testClasses.stream().map(CtClass::getQualifiedName).collect(Collectors.toSet());
        graph = new InheritanceGraph(testClasses);
    }

    // protected Set<String> getInvolvedTestsFor(MutationInfo mutation) throws MojoExecutionException {
    //     HashSet<String> result = new HashSet<>();
    //     for(MethodTracesEntry entry : getMethodStackTraces()) {
    //         Set<String> closestTests = entry.getClosestClassesTo(mutation, testClasses);
    //         result.addAll(getInheritanceClousure(closestTests)); // Include derived classes
    //     }
    //     return result;
    // }

    protected void complementTests(MutationInfo mutation) throws MojoExecutionException {
        HashSet<String> tests = new HashSet<>();
        tests.addAll(mutation.getTestClasses());
        for (MethodTracesEntry entry : getMethodStackTraces()) {
            tests.addAll(entry.getClosestClassesTo(mutation, testClasses));
        }
        mutation.setTestClasses(getInheritanceClousure(tests));
    }

    protected Set<String> getTestsToExecute(Collection<MutationInfo> mutations) throws MojoExecutionException {
        HashSet<String> result = new HashSet<>();
        for (MutationInfo mut : mutations) {
            result.addAll(mut.getTestClasses());
        }
        result.removeAll(getExcludedTests());
        return result;
    }

    protected Set<String> getTestsToExecute(MutationInfo mutation) throws MojoExecutionException {
        return getTestsToExecute(Collections.singleton(mutation));
    }

    protected Path getClassFilePath(MutationInfo mutation) {
        return Paths.get(getProjectBuild().getOutputDirectory(), mutation.getInternalClassName() + ".class");
    }

    protected Path getClassFilePath(MethodRecord method) {
        return getClassFilePath(method.getClassQualifiedName());
    }

    protected Path getClassFilePath(String className) {
        String[] parts = className.split("\\.");
        parts[parts.length - 1] += ".class";
        return Paths.get(getProjectBuild().getOutputDirectory(), parts);
    }

    protected MavenLauncher getLauncherForProject() {
        return new MavenLauncher(getAbsolutePathToProject(), MavenLauncher.SOURCE_TYPE.ALL_SOURCE);
    }

    protected byte[] mutate(byte[] originalClass, MutationIdentifier mutation) throws MojoExecutionException {
        List<String> classpath;

        try {
            classpath = getProject().getTestClasspathElements();
        } catch (DependencyResolutionRequiredException exc) {
            throw new MojoExecutionException("Could not get test class path for project", exc);
        }

        ClassReader reader = new ClassReader(originalClass);
        ClassWriter writer = new ContextAwareClassWriter(reader, ClassWriter.COMPUTE_FRAMES, classpath);
        MutationClassAdapter adapter = new MutationClassAdapter(mutation, writer);
        reader.accept(adapter, ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }

    protected void executeTests(Path resultFolder, Set<String> classes) throws MojoExecutionException {
        for (int iteration = 0; iteration < testTimes; iteration++) {
            executeTestsOnce(resultFolder, classes);
        }
    }

    protected void executeTestsOnce(Path resultFolder, Set<String> classes) throws MojoExecutionException {
        getLog().info("Executing tests.");
        String testsToRun = classes.stream().collect(Collectors.joining(", "));
        getLog().debug("Test classes to execute: " + testsToRun);

        try {
            Path executionOutputFolder = resultFolder.resolve(Long.toString(System.currentTimeMillis()));

            setUserPropertyInSession("stamp.reneri.folder", executionOutputFolder.toAbsolutePath().toString());
            executePlugin("org.apache.maven.plugins",
                    "maven-surefire-plugin",
                    "2.22.2",
                    configuration(
                            element("reportsDirectory", executionOutputFolder.resolve("surefire-reports").toAbsolutePath().toString()),
                            element("redirectTestOutputToFile", "true"),
                            element("test", testsToRun)),
                    "test");
        } catch (MojoExecutionException exc) {
            getLog().warn("Issues while executing the tests ", exc);
        }
    }

    protected void saveMutationInfo(Path folder, MutationInfo mutation, Set<String> executedTests) throws MojoExecutionException {
        JsonObject obj = new JsonObject();
        obj.addProperty("mutator", mutation.getMutator());
        obj.addProperty("class", mutation.getClassName());
        obj.addProperty("package", mutation.getPackageName());
        obj.addProperty("method", mutation.getMethodName());
        obj.addProperty("description", mutation.getMethodDescription());

        if (!executedTests.isEmpty()) {
            JsonArray testArray = new JsonArray();
            executedTests.forEach(testArray::add);
            obj.add("tests", testArray);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(folder.resolve("mutation.json").toFile())) {
            gson.toJson(obj, writer);
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not save mutation details " + mutation.toString(), exc);
        }
    }

    protected byte[] readBytes(Path path) throws MojoExecutionException {
        try {
            return Files.readAllBytes(path);
        } catch (IOException exc) {
            throw new MojoExecutionException("Error while reading file " + path.toString());
        }
    }

    protected void writeBytes(Path path, byte[] bytes) throws MojoExecutionException {
        try {
            FileUtils.write(path, bytes);
        } catch (IOException exc) {
            throw new MojoExecutionException("Error while writing file " + path.toString());
        }
    }

    protected void ensureObservationFolderIsEmpty(String name) throws MojoExecutionException {
        try {
            Path testObservationDirectory = getPathTo("observations", name);
            getLog().info("Cleaning folder " + testObservationDirectory);
            FileUtils.createEmptyDirectory(testObservationDirectory);
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not clean observation output folder " + name, exc);
        }
    }

    protected void removeObservations(Path directory) throws MojoExecutionException {
        try {
            for (File child : FileUtils.getChildrenDirectories(directory)) {
                FileUtils.deleteDirectory(child.toPath());
            }
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not delete observations from " + directory, exc);
        }
    }

    protected void generateDiffReportFor(Path pathToObservations, ObservedValueMap originalValues) throws MojoExecutionException {
        if (!shouldComputeDiff()) {
            getLog().info("Skipping diff on " + pathToObservations);
            return;
        }

        computeDiffOnFolder(pathToObservations, originalValues);

        if (shouldKeepObservations()) {
            return;
        }

        removeObservations(pathToObservations);
    }

    protected void removeOriginalObservationsIfNeeded(Path pathToResults) throws MojoExecutionException {
        if (shouldKeepObservations()) {
            return;
        }
        try {
            FileUtils.deleteDirectory(pathToResults.resolve("original"));
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not remove original observations at " + pathToResults, exc);
        }
    }

    protected MutationInfo getMutationFromJsonReport(JsonObject mutationJsonObject) {
        JsonObject method = mutationJsonObject.getAsJsonObject("method");
        MutationInfo info = new MutationInfo(
                mutationJsonObject.getAsJsonPrimitive("mutator").getAsString(),
                method.getAsJsonPrimitive("class").getAsString(),
                method.getAsJsonPrimitive("package").getAsString(),
                method.getAsJsonPrimitive("name").getAsString(),
                method.getAsJsonPrimitive("description").getAsString());
        info.setTestClasses(testClassesFromJsonArray(mutationJsonObject.getAsJsonObject("tests").getAsJsonArray("ordered")));
        return info;
    }

    protected Set<String> testClassesFromJsonArray(JsonArray elements) {
        return MutationInfo.guessTestClasses(
                StreamSupport.stream(elements.spliterator(), false)
                        .map(JsonElement::getAsString));
    }
}
