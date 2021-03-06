package eu.stamp_project.reneri;

import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.element;
import static org.twdata.maven.mojoexecutor.MojoExecutor.name;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import eu.stamp_project.reneri.diff.ObservedValueMap;
import eu.stamp_project.reneri.instrumentation.ObserverClassProcessor;
import eu.stamp_project.reneri.instrumentation.PointcutLocator;
import eu.stamp_project.reneri.instrumentation.StateObserver;
import eu.stamp_project.reneri.utils.FileUtils;
import spoon.MavenLauncher;
import spoon.reflect.CtModel;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtClass;

@Mojo(name = "observeTests", requiresDependencyResolution = ResolutionScope.TEST)
@Execute(phase = LifecyclePhase.COMPILE)
public class TestObservationMojo extends AbstractObservationMojo {

    @Parameter(property = "transformations", defaultValue = "${project.build.directory/mutations.json}")
    private File transformations;

    public boolean shoulCheckInstrumentationOnly() {
        return checkInstrumentationOnly;
    }

    @Parameter(property = "checkInstrumentationOnly", defaultValue = "false")
    private boolean checkInstrumentationOnly;

    /*
     * This parameter instructs the goal to search for the result of observedMethods
     * and will execute only the transformations for which a diff was previously
     * detected
     */
    @Parameter(property = "infectedOnly", defaultValue = "true")
    private boolean infectedOnly;

    public boolean shouldCheckInfectedOnly() {
        return infectedOnly;
    }

    public File getTransformations() {
        return transformations;
    }

    public void setTransformations(File transformations) {
        this.transformations = transformations;
    }

    private List<MutationInfo> undetectedMutations;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {

            ensureObservationFolderIsEmpty("tests");

            instrumentTestClasses();

            if (noClassWasInstrumented()) {
                getLog().info("Stopping analysis as no test class was found or instrumented");
                return;
            }

            compileInstrumentedTestClasses();

            loadTransformations();

            if (undetectedMutations.isEmpty()) {
                getLog().warn("No undetected transformation found");
                return;
            }

            if (shoulCheckInstrumentationOnly()) {
                setTestTimes(1);
            }

            observeOriginalValues();

            if (shoulCheckInstrumentationOnly()) {
                return;
            }

            loadOriginalObservations();

            observeTransformations();

            removeOriginalObservations();

        } catch (Throwable exc) {
            throw new MojoExecutionException("Process failed", exc);
        }
    }

    private boolean noClassWasInstrumented() {
        Set<String> classes = getTestClasses();
        return classes == null || classes.isEmpty();
    }

    private void instrumentTestClasses() throws MojoExecutionException {
        getLog().info("Instrumenting test classes");
        try {
            Path testSourceOutputPath = getGeneratedTestsPath();
            FileUtils.createEmptyDirectory(testSourceOutputPath);
            setTestClasses(instrumentTestClasses(testSourceOutputPath.toString()));
            FileUtils.copyDirectory(Paths.get(getProject().getBuild().getTestSourceDirectory()), testSourceOutputPath);
        } catch (IOException exc) {
            throw new MojoExecutionException(
                    "An error occurred while creating the instrumented test output folder or while copying the non-instrumented test classes",
                    exc);
        }
    }

    private Path getGeneratedTestsPath() throws IOException {
        return getPathTo("generated");
    }

    private Set<CtClass<?>> instrumentTestClasses(String folder) throws MojoExecutionException {
        MavenLauncher launcher = getLauncherForProject();

        // Adding the observer to the path so it can be compiled at the same time as the
        // project
        launcher.addInputResource(new ResourceJavaFile("utils/StateObserver.java"));
        CtModel model = launcher.buildModel();

        Set<CtClass<?>> testClassesFound = TestClassFinder.findTestClasses(model);
        removeExcludedTestClassesFrom(testClassesFound);

        if (getLog().isDebugEnabled()) {

            getLog().debug("Excluded test classes");
            for (String name : getExcludedTests()) {
                getLog().debug(name);
            }

            getLog().debug("Test classes found: " + testClassesFound.size());
            for (CtClass type : testClassesFound) {
                getLog().debug(type.getQualifiedName());
            }
        }

        getLog().info("Saving observation point locations");
        savePointcutLocations(testClassesFound);

        launcher.addProcessor(new ObserverClassProcessor(testClassesFound));
        launcher.process();

        getLog().debug("Saving instrumented classes");
        launcher.setSourceOutputDirectory(folder);

        // Not too pretty :(
        CtClass<?> observerClass = (CtClass<?>) model.getRootPackage().getFactory().Type().get(StateObserver.class);
        testClassesFound.add(observerClass);
        launcher.setOutputFilter(testClassesFound::contains);
        launcher.prettyprint();
        testClassesFound.remove(observerClass);

        return testClassesFound;
    }

    private void removeExcludedTestClassesFrom(Set<CtClass<?>> classes) {
        Set<String> excludedClasses = getExcludedTests();
        classes.removeAll(
                classes.stream()
                        .filter(aClass -> excludedClasses.contains(aClass.getQualifiedName()))
                        .collect(Collectors.toSet()));
    }

    private void savePointcutLocations(Set<CtClass<?>> testClasses) throws MojoExecutionException {
        // TODO: Save the expression too
        PointcutLocator locator = new PointcutLocator();

        for (CtClass<?> testClass : testClasses) {
            locator.setFactory(testClass.getFactory());
            locator.process(testClass);
        }

        try (FileWriter writer = new FileWriter(
                getPathTo("observations", "tests").resolve("locations.json").toFile())) {
            JsonWriter jsonWriter = new JsonWriter(writer);

            Map<String, SourcePosition> locations = locator.getLocations();
            Map<String, String> types = locator.getTypes();

            jsonWriter.beginArray();

            for (String point : locations.keySet()) {
                SourcePosition position = locations.get(point);
                if (!position.isValidPosition()) {
                    continue;
                }
                jsonWriter.beginObject()
                        .name("point").value(point)
                        .name("type").value(types.get(point))
                        .name("from")
                        .beginObject()
                        .name("line").value(position.getLine())
                        .name("column").value(position.getColumn())
                        .endObject()
                        .name("to")
                        .beginObject()
                        .name("line").value(position.getEndLine())
                        .name("column").value(position.getEndColumn())
                        .endObject()
                        .name("file").value(position.getFile().toString())
                        .endObject();
            }

            jsonWriter.endArray();
            jsonWriter.close();
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not save observation point locations", exc);
        }
    }

    private void compileInstrumentedTestClasses() throws MojoExecutionException {

        getLog().info("Compiling instrumented test classes");

        String instrumentedTestsPath = "";
        try {
            instrumentedTestsPath = getGeneratedTestsPath().toString();
        } catch (IOException exc) {
            throw new MojoExecutionException("An error occurred while accessing the instrumented tests folder", exc);
        }

        executePlugin("org.apache.maven.plugins",
                "maven-compiler-plugin",
                "3.10.0",
                configuration(
                        element(name("compileSourceRoots"),
                                element("value", instrumentedTestsPath)),
                        element("release", "11")), // TODO: Maybe a parameter with the language version
                "testCompile");
    }

    private void loadTransformations() throws MojoExecutionException {
        getLog().info("Loading undetected transformations");

        try {
            undetectedMutations = loadUndetectedMutations();

            if (shouldCheckInfectedOnly()) {
                undetectedMutations = keepInfectedOnly(undetectedMutations);
            }
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not load transformations from " + transformations.getAbsolutePath(),
                    exc);
        }
    }

    protected Set<MutationInfo> getInfectedOnly() throws MojoExecutionException {
        try {
            HashSet<MutationInfo> result = new HashSet<>();

            Gson gson = new GsonBuilder().create();

            File[] methodDirectories = FileUtils.getChildrenDirectories(getPathTo("observations", "methods"));

            for (File methodDir : methodDirectories) {
                for (File mutationDir : FileUtils.getChildrenDirectories(methodDir)) {

                    Path pathToMutationDir = mutationDir.toPath();
                    if (FileUtils.exists(pathToMutationDir.resolve("mutation.json"))
                            && FileUtils.exists(pathToMutationDir.resolve("diff.json"))) {
                        result.add(loadMutationFromDir(gson, pathToMutationDir));
                    }
                }
            }

            return result;
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not read the method observation folder", exc);
        }

    }

    protected List<MutationInfo> keepInfectedOnly(List<MutationInfo> mutations) throws MojoExecutionException {
        Set<MutationInfo> infectedMutations = getInfectedOnly();
        getLog().debug("Found " + infectedMutations.size() + " mutations with reported infection");
        return mutations.stream().filter(infectedMutations::contains).collect(Collectors.toList());
    }

    private List<MutationInfo> loadUndetectedMutations() throws IOException, MojoExecutionException {
        Gson gson = new GsonBuilder().create();
        FileReader fileReader = new FileReader(transformations);
        JsonObject document = gson.fromJson(fileReader, JsonObject.class);
        List<MutationInfo> result = new ArrayList<>();
        for (JsonElement mutationElement : document.getAsJsonArray("mutations")) {
            JsonObject mutation = mutationElement.getAsJsonObject();
            if (!mutation.getAsJsonPrimitive("status").getAsString().equals("SURVIVED")) {
                continue;
            }
            MutationInfo mutationInfo = getMutationFromJsonReport(mutation);
            complementTests(mutationInfo);
            result.add(mutationInfo);
        }
        return result;
    }

    private void observeOriginalValues() throws MojoExecutionException {
        getLog().info("Executing test classes to observe original values");

        try {
            executeTests(getPathTo("observations", "tests", "original"), getTestsToExecute(undetectedMutations));
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not create directory to store original observations", exc);
        }
    }

    private void observeTransformations() throws MojoExecutionException {
        getLog().info("Observing transformation effects");

        try {
            List<MutationInfo> mutations = loadUndetectedMutations();

            getLog().debug(String.format("Found %d undetected transformations", mutations.size()));

            for (int index = 0; index < mutations.size(); index++) {

                getLog().debug("Executing transformation " + index);
                try {

                    Path currentObservationsDirectory = getPathTo("observations", "tests", Integer.toString(index));
                    executeMutation(currentObservationsDirectory, mutations.get(index));
                    generateDiffReportFor(currentObservationsDirectory);
                } catch (Exception exc) {
                    getLog().error("Error executing transformation " + index, exc);
                }
            }
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not read mutation file", exc);
        }
    }

    private void executeMutation(Path resultFolderPath, MutationInfo mutation)
            throws IOException, MojoExecutionException {
        // Select the tests to execute
        Set<String> tests = getTestsToExecute(mutation);
        // Save the information
        saveMutationInfo(resultFolderPath, mutation, tests);

        if (tests.isEmpty()) {
            getLog().warn("No test found in recorded stack traces for " + mutation);
            return;
        }

        // Mutate the source code
        Path pathToClass = getClassFilePath(mutation);
        byte[] originalClass = Files.readAllBytes(pathToClass);
        FileUtils.write(pathToClass, mutate(originalClass, mutation.toMutationIdentifier()));

        // Execute the tests
        executeTests(resultFolderPath, tests);
        // Restore the original code
        FileUtils.write(pathToClass, originalClass);
    }

    private ObservedValueMap originalValues;

    private void loadOriginalObservations() throws MojoExecutionException {
        try {
            originalValues = loadOriginalObservations(getPathTo("observations", "tests"));
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not read original observation files", exc);
        }
    }

    private void generateDiffReportFor(Path directory) throws MojoExecutionException {
        if (!shouldComputeDiff()) {
            getLog().info("Skipping diff report generation");
            return;
        }

        computeDiffOnFolder(directory, originalValues);

        if (shouldKeepObservations()) {
            return;
        }
        removeObservations(directory);
    }

    private void removeOriginalObservations() throws MojoExecutionException {
        try {
            removeOriginalObservationsIfNeeded(getPathTo("observations", "tests"));
        } catch (IOException exc) {
            throw new MojoExecutionException("Could not read original test observations", exc);
        }
    }

}
