package com.flaky;

import com.testngutils.annotations.Flaky;
import com.testngutils.annotations.TestInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.testngutils.annotations.TestInfo.Priority;
import static com.testngutils.annotations.TestInfo.TestType;

/**
 * Not Selenium: just shows how annotations and TestNG listeners work
 * These could then be applied to actual Selenium tests
 */
@TestInfo(
        priority =  Priority.HIGH,
        testType = TestType.FUNCTIONAL
)
public class FlakyTests {

    @Flaky
    @Test
    public void testInvocationNumberChange() {
        System.out.println("I ran a flaky test");
    }



    private static int count = 0;
    @Test
    public void smartRetrierTest() {
        if(count <= 1){
            count++;
           Assert.assertEquals(1+1,3,"should be 2!");
        }
        System.out.println("I ran a normal test");
    }

}
