package com.testng.retries;

import com.github.annotations.TestInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

@TestInfo(
        testType = TestInfo.TestType.FUNCTIONAL
)
public class FlakyTests2 {

    // Using the smart retrier - it implements IAnnotationTransformer

    private static int count = 0;
    @Test(description = "Fails the 1st + 2nd iteration, but passes on 3rd iteration")
    public void smartRetrierTest() {
        if(count <= 1){
            count++;
           Assert.assertEquals(1+1,3,"should be 2!");
        }
        System.out.println("I ran a normal test");
    }

}
