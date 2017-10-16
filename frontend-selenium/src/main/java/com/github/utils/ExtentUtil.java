package com.github.utils;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestResult;

public class ExtentUtil {

    public static void writeToReport(ITestResult result, ExtentTest test){
        if(result.getStatus() == ITestResult.SUCCESS){
            test.log(LogStatus.PASS, "Passed");
        }

        if(result.getStatus() == ITestResult.FAILURE){
            test.log(LogStatus.FAIL, "Reason: " + result.getThrowable());
        }

    }
}
