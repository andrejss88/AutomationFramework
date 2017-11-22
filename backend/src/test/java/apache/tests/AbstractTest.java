package apache.tests;

import com.github.handlers.RequestHandler;
import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultRequestHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import com.github.reporting.ExtentReportManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.github.utils.ExtentUtil.getTestDesc;
import static com.github.utils.ExtentUtil.getTestName;
import static com.github.utils.ExtentUtil.writeToReport;

public abstract class AbstractTest {

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
