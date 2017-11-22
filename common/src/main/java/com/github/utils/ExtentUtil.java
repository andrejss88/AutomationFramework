package com.github.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class ExtentUtil {

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

    private static Test getTestAnnotation(Method method){
        return method.getAnnotation(Test.class);
    }
}
