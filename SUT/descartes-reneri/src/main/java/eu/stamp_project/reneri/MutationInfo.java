package eu.stamp_project.reneri;

import com.google.gson.JsonObject;
import org.pitest.classinfo.ClassName;
import org.pitest.mutationtest.engine.Location;
import org.pitest.mutationtest.engine.MethodName;
import org.pitest.mutationtest.engine.MutationIdentifier;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MutationInfo {

    public static final Pattern TEST_CASE_NAME = Pattern.compile("^(?<class>.+)(\\.)(((?<method>[^\\[]+)(?<params>\\[.*\\])?\\(\\k<class>\\))|(\\k<class>))$");

    private String mutator;

    private String className;

    private String packageName;

    private String methodName;

    private String methodDescription;

    private Set<String> testClasses;

    public String getMutator() {
        return mutator;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public Set<String> getTestClasses() {
        return testClasses;
    }

    public void setTestClasses(Collection<String> testClasses) {
        this.testClasses = new HashSet<>(testClasses);
    }

    public MutationInfo(String mutator, String className, String packageName, String methodName, String methodDescription) {
        this.mutator = mutator;
        this.className = className;
        this.packageName = packageName;
        this.methodName = methodName;
        this.methodDescription = methodDescription;

        this.testClasses = Collections.emptySet();
    }

    public MutationInfo(JsonObject obj) {

        mutator = obj.getAsJsonPrimitive("mutator").getAsString();

        JsonObject methodInformation = obj.getAsJsonObject("method");
        className = methodInformation.getAsJsonPrimitive("class").getAsString();
        packageName = methodInformation.getAsJsonPrimitive("package").getAsString();
        methodName = methodInformation.getAsJsonPrimitive("name").getAsString();
        methodDescription = methodInformation.getAsJsonPrimitive("description").getAsString();

    }

    public String getClassQualifiedName() {
        return packageName + "." + className;
    }

    public String getInternalClassName() { return  getClassQualifiedName().replace('.', '/'); }

    public String getMethodInternalFullName() {
        return String.format("%s/%s%s", getInternalClassName(), methodName, methodDescription);
    }

    public boolean matches(String name, String desc) {
        return methodName.equals(name) && methodDescription.equals(desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutationInfo that = (MutationInfo) o;
        return Objects.equals(mutator, that.mutator) &&
                Objects.equals(className, that.className) &&
                Objects.equals(packageName, that.packageName) &&
                Objects.equals(methodName, that.methodName) &&
                Objects.equals(methodDescription, that.methodDescription);
    }

    public MutationIdentifier toMutationIdentifier() {
        return new MutationIdentifier(
                new Location(
                        ClassName.fromString(getClassQualifiedName()),
                        MethodName.fromString(methodName),
                        methodDescription
                ),
                0, mutator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mutator, className, packageName, methodName, methodDescription);
    }

    public static Set<String> guessTestClasses(Stream<String> testCaseNames) {
        return testCaseNames
                .map(TEST_CASE_NAME::matcher)
                .filter(Matcher::matches)
                .map((m) -> m.group("class"))
                .collect(Collectors.toSet());
    }

    public static Set<String> guessTestClasses(Collection<String> testCaseNames) {
        return guessTestClasses(testCaseNames.stream());
    }
}
