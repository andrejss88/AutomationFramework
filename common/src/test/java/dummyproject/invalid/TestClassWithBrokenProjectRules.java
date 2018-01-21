package dummyproject.invalid;

import org.testng.annotations.Test;

public class TestClassWithBrokenProjectRules {

    @Test
    public void testNoDescription(){
        // no-op
    }

    @Test(description = "something")
    public void testHasDescription(){

    }


}
