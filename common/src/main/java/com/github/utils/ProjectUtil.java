package com.github.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ProjectUtil {

    /**
     * Verifies that all @Test methods are unique
     */
    public static void checkUniqueMethodNames(String baseDir) throws IOException, ClassNotFoundException {

        List<Method> methods = getAllProjectMethods(baseDir);
        List<String> annotatedMethods = getAnnotatedMethodsAsString(methods, Test.class);

        verifyHasNoDuplicates(annotatedMethods);
    }

    /**
     * Verifies that all @Test methods have a description
     * This could be useful to enforce in case it is decided that 'description' is used for reporting
     */
    public static void checkMethodHasDescription(String baseDir) throws IOException, ClassNotFoundException {

        List<Method> methods = getAllProjectMethods(baseDir);
        List<Method> annotatedMethods = getAnnotatedMethods(methods, Test.class);

        verifyHasDescriptions(annotatedMethods);
    }

    private static List<Method> getAllProjectMethods(String baseDir) throws IOException, ClassNotFoundException {
        List<String> files = getAllJavaFilesWithPackage(baseDir);
        List<Class<?>> clazzes = convertToClasses(files);
        return getAllMethods(clazzes);
    }

    private static void verifyHasDescriptions(List<Method> annotatedMethods) {
        Predicate<Method> hasDescription = method -> !method.getAnnotation(Test.class).description().isEmpty();

        Set<String> mehodsWithNoDesc =  annotatedMethods.stream()
                .filter(hasDescription)
                .map(Method::getName)
                .collect(toSet());

        Assert.assertFalse(mehodsWithNoDesc.size() > 0,
                String.format("There are @Test methods without a description names in the project: %s. " +
                        "All @Test method names should have one.", mehodsWithNoDesc));
    }


    private static <T> void verifyHasNoDuplicates(List<T> list){
        Set<T> allItems = new HashSet<>();
        Set<T> duplicates = list.stream()
                .filter(n -> !allItems.add(n))
                .collect(toSet());

        Assert.assertFalse(duplicates.size() > 0,
                String.format("There are duplicate @Test method names in the project: %s. " +
                        "All method names in the project should be unique.", duplicates));
    }

    private static <T extends Annotation> List<String> getAnnotatedMethodsAsString(List<Method> methods, Class<T> annotation) {

        Predicate<Method> annotated = method -> method.getAnnotation(annotation) != null;
        return methods.stream()
                .filter(annotated)
                .map(Method::getName)
                .collect(toList());
    }

    private static <T extends Annotation> List<Method> getAnnotatedMethods(List<Method> methods, Class<T> annotation) {

        Predicate<Method> annotated = method -> method.getAnnotation(annotation) != null;
        return methods.stream()
                .filter(annotated)
                .collect(toList());
    }

    private static List<String> getAllJavaFilesWithPackage(String baseDir) throws IOException {
        BiPredicate<Path, BasicFileAttributes> matcher = (p, bfa) -> bfa.isRegularFile()
                && p.getFileName().toString().matches(".*\\.java");

        int maxDepth = 20;

        return Files.find(Paths.get(baseDir), maxDepth, matcher)
                .map(p-> convertToPackageWithName(p, baseDir))
                .collect(toList());
    }

    private static List<Method> getAllMethods(List<Class<?>> clazzes) {
        List<List<Method>> annotatedMethods = new ArrayList<>();

        for (Class clazz: clazzes){
            annotatedMethods.add(Arrays.asList(clazz.getDeclaredMethods()));
        }
        return annotatedMethods.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Class<?>> convertToClasses(List<String> files) throws ClassNotFoundException {
        List<Class<?>> clazzes = new ArrayList<>();
        for (String file : files){
            clazzes.add(Class.forName(file));
        }
        return clazzes;
    }

    private static String convertToPackageWithName(Path p, String baseDir) {
        String subS2= StringUtils.removeStart(p.toString(), baseDir);
        String subS3 = StringUtils.removeEnd(subS2, ".java");
        String subS4 = subS3.replace(File.separator,".");
        return subS4;
    }
}
