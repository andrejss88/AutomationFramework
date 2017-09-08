package com.testngutils.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * If a test fails - retry up to a defined maximum
 */
public class Retry implements IRetryAnalyzer {

    private static final Logger log = LoggerFactory.getLogger(Retry.class);

    private int count = 0;
    private static int maxTry = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        log.info("Retrying");
        if (!iTestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;                                 //re-run the test
            } else {
                log.info(String.format("Maximum number of tries reached ('%s'). Marking the test as failed.", maxTry));
                iTestResult.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }

}
