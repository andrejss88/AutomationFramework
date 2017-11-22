import com.github.annotations.Flaky;
import com.github.annotations.TestInfo;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Not Selenium: just shows how com.github.annotations and TestNG com.github.listeners work
 * These could then be applied to actual Selenium tests
 */
@TestInfo(
        testType = TestInfo.TestType.FUNCTIONAL
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
