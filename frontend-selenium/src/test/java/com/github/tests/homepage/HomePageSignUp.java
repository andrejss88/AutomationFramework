package com.github.tests.homepage;

import com.github.pages.signup.SignUpPage;
import com.github.tests.abstractpagetest.AbstractHomePageTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class HomePageSignUp extends AbstractHomePageTest {

    @Resource
    private SignUpPage signUp;

    private void attemptSignIn(){
        home.act()
                .enterUserName("user")
                .enterEmail("some@email.com")
                .enterPassword("password")
                .clickSignUp();

    }

    @Test(testName = "Sign Up Fails", description = "Invalid credentials")
    public void signUpFails() {

        attemptSignIn();

        signUp.verify()
                .accountCreationFailed();

    }

    @Test // No TestNG name/ desc:
          // method name will be used in report, desc will be ""
    public void signUpFailsNegative() {

        attemptSignIn();

        signUp.verify()
                .accountCreationSuccessful();

    }
}
