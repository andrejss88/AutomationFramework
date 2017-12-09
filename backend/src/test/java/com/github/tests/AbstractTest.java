package com.github.tests;

import com.github.handlers.RequestHandler;
import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultRequestHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import com.github.reporting.ExtentReportManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.github.utils.ExtentUtil.*;

public abstract class AbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    private ExtentReports extent;
    public ExtentTest test;

    protected CloseableHttpResponse response;

    protected RequestHandler clive;

    protected ResponseHandler rob;

    @BeforeClass
    public void beforeClassSetup() {

        clive = new DefaultRequestHandler();
        rob = new DefaultResponseHandler();
    }

    @BeforeMethod
    public void beforeMethodSetup(Method method) {
        String testCaseName = getTestName(method);
        String testCaseDesc = getTestDesc(method);

        LOGGER.info(String.format(
                "Running Test '%s'. Description: '%s'",
                testCaseName, testCaseDesc));

        // Driver initialized along with Spring container in SpringConfig.class
        extent = ExtentReportManager.getReporter();
        test = extent.startTest(testCaseName, testCaseDesc);
    }

    @AfterMethod
    public void afterMethodTearDown(ITestResult result){

        // process Test Results
        writeToReport(result, test);
        logUsedParameters(result);

        extent.endTest(test);
        extent.flush(); // write to document



        // There is a limit of concurrent connections set in default client in ClientFactory
        // If that limit is still not enough - an alternative solution is to quietly consume the response
        // -> https://stackoverflow.com/questions/11875015/httpclient-exception-org-apache-http-conn-connectionpooltimeoutexception-timeo
        // EntityUtils.consumeQuietly(response.getEntity());

    }

    @AfterClass
    public void after() throws IllegalStateException, IOException {
        rob.closeResponse(response);
        clive.close();
    }

    @AfterSuite
    public void globalTearDown() {
        extent.close();
    }
}
