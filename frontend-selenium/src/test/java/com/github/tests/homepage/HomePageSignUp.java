package com.github.tests.homepage;

import com.github.pages.signup.SignUpPage;
import com.github.tests.abstractpagetest.AbstractHomePageTest;
import org.testng.annotations.Test;

public class HomePageSignUp extends AbstractHomePageTest {

    @Test
    public void signUpFails() {

        home.act()
                .enterUserName("user")
                .enterEmail("some@email.com")
                .enterPassword("password")
                .clickSignUp();

        SignUpPage signUpPage = SignUpPage.initPageElements(driver);

        signUpPage.verify()
                .accountCreationFailed();

    }
}
