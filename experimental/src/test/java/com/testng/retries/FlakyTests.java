package com.testng.retries;

import com.github.annotations.Flaky;
import com.github.annotations.TestInfo;
import org.testng.annotations.Test;

@TestInfo(
        testType = TestInfo.TestType.FUNCTIONAL
)
public class FlakyTests {

    // Plain and simple: run @Flaky tests a number of times specified in SimpleRetrier
    @Flaky
    @Test
    public void testInvocationNumberChange() {
        System.out.println("I ran a flaky test");
    }

}
