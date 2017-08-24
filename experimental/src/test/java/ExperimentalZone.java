import annotations.Flaky;
import annotations.TestInfo;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Class showing various TestNG perks and their functionality
 */
@TestInfo(
        priority =  TestInfo.Priority.HIGH,
        testType = TestInfo.TestType.FUNCTIONAL
)
public class ExperimentalZone  {

    @Flaky
    @Test
    public void testInvocationNumberChange() {
        System.out.println("I ran a flaky test");
    }



    static int count = 0;
    @Test
    public void smartRetryerTest() {
        if(count <= 1){
            count++;
           Assert.assertEquals(2+2,5,"Addition Problem! 2+2 must be 4!\n");
        }
        System.out.println("I ran a normal test");
    }

}
