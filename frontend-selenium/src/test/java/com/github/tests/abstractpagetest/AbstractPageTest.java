package com.github.tests.abstractpagetest;

import com.github.config.ExtentReportManager;
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

import static com.github.utils.ExtentUtil.writeToReport;

@ContextConfiguration(classes = SpringConfig.class)
public abstract class AbstractPageTest extends AbstractTestNGSpringContextTests {

    protected ExtentReports extent;
    protected String testCaseName;
    protected String testCaseDesc;

    public ExtentTest test;

    @Resource(name = "driver")
    private WebDriver driver;

    @BeforeMethod
    public void globalSetUp() {
        // Driver initialized along with Spring container in SpringConfig.class
        extent = ExtentReportManager.getReporter();
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

    protected void setTestCaseInfo(Method method) {
        Test annotation = method.getAnnotation(Test.class);

        String testNgTestName = annotation.testName();

        if (!testNgTestName.isEmpty()) {
            testCaseName = testNgTestName;
        } else {
            testCaseName = method.getName();
        }

        String testNGTestDesc = annotation.description();

        if (!testNGTestDesc.isEmpty()) {
            testCaseDesc = testNGTestDesc;
        } else {
            testCaseDesc = "N/A";
        }

    }

}
