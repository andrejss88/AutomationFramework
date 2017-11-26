package com.testng.retries;

import com.github.annotations.TestInfo;
import com.github.listeners.Retry;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


@TestInfo(
        testType = TestInfo.TestType.FUNCTIONAL
)
public class FlakyTests3 {

    // Same but using Java instead of XML and
    // using a class implementing IRetryAnalyzer directly, without
    // having to go through a class implementing IAnnotationTransformer

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        for (ITestNGMethod method : context.getAllTestMethods()) {
            method.setRetryAnalyzer(new Retry());
        }
    }


    private static int count = 1;
    @Test(description = "Fails the 1st + 2nd iteration, but passes on 3rd iteration")
    public void smartRetrierTest() {
        if(count <= 1){
            count++;
           Assert.assertEquals(1+1,3,"should be 2!");
        }
        System.out.println("I ran a normal test");
    }

}
