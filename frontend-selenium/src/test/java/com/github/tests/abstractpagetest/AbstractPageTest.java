package com.github.tests.abstractpagetest;

import com.github.reporting.ExtentReportManager;
import com.github.config.SpringConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.annotation.Resource;

import java.lang.reflect.Method;

import static com.github.utils.ExtentUtil.getTestDesc;
import static com.github.utils.ExtentUtil.getTestName;
import static com.github.utils.ExtentUtil.writeToReport;

@ContextConfiguration(classes = SpringConfig.class)
public abstract class AbstractPageTest extends AbstractTestNGSpringContextTests {

    private ExtentReports extent;

    public ExtentTest test;

    @Resource(name = "driver")
    private WebDriver driver;

    @BeforeMethod
    public void globalSetUp(Method method) {
        String testCaseName = getTestName(method);
        String testCaseDesc = getTestDesc(method);

        // Driver initialized along with Spring container in SpringConfig.class
        extent = ExtentReportManager.getReporter();
        test = extent.startTest(testCaseName, testCaseDesc);
    }

    @AfterMethod
    public void processTestResults(ITestResult result){

        writeToReport(result, test);

        extent.endTest(test);
        extent.flush(); // write to document
    }

    @AfterSuite
    public void globalTearDown() {
        driver.close();
        extent.close();
    }
}
