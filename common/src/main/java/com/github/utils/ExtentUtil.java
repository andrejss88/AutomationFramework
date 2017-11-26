package com.github.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ExtentUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExtentUtil.class);

    public static void writeToReport(ITestResult result, ExtentTest test){
        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(LogStatus.PASS, "Passed");
        }

        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Reason: " + result.getThrowable());
        }

    }

    public static String getTestName(Method method) {

        String testNgTestName = getTestAnnotation(method).testName();

        if (!testNgTestName.isEmpty()) {
            return testNgTestName;
        } else {
            return method.getName();
        }
    }

    public static String getTestDesc(Method method) {
        String testNGTestDesc = getTestAnnotation(method).description();

        if (!testNGTestDesc.isEmpty()) {
            return testNGTestDesc;
        } else {
            return "N/A";
        }
    }

    public static void logUsedParameters(ITestResult result){
        Object[] params = result.getParameters();

        if (testHadParameters(params)) {
            LOGGER.info(String.format(
                    "Test ran with parameter(s): '%s'.",
                    Arrays.toString(params)));
        }
    }

    private static boolean testHadParameters(Object[] params) {
        return params.length != 0;
    }

    private static Test getTestAnnotation(Method method){
        return method.getAnnotation(Test.class);
    }
}
