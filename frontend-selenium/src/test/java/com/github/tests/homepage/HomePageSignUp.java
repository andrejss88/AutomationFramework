package com.github.tests.homepage;

import com.github.pages.signup.SignUpPage;
import com.github.tests.abstractpagetest.AbstractHomePageTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class HomePageSignUp extends AbstractHomePageTest {

    @Resource
    private SignUpPage signUp;

    @Test
    public void signUpFails() {

        home.act()
                .enterUserName("user")
                .enterEmail("some@email.com")
                .enterPassword("password")
                .clickSignUp();

        signUp.verify()
                .accountCreationFailed();

    }
}
