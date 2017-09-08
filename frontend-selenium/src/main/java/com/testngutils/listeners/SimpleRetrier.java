package com.testngutils.listeners;

import com.testngutils.annotations.Flaky;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Simple solution to Flaky tests - if a test is marked with @Flaky annotation, then
 * Increase its invocation count and then see in the report if AT LEAST one run has passed
 */
public class SimpleRetrier implements IAnnotationTransformer {

    private static final int INVOCATION_COUNT = 2;

    @Override
    public void transform(ITestAnnotation annotation, Class aClass, Constructor constructor, Method method) {

        if(testIsFlaky(method)){
            annotation.setInvocationCount(INVOCATION_COUNT);
        }
    }

    private boolean testIsFlaky(Method method) {
        return method.isAnnotationPresent(Flaky.class);
    }
}
