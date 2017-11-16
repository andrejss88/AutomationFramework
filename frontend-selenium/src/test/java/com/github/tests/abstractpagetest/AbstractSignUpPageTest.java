package com.github.tests.abstractpagetest;

import com.github.pages.signup.SignUpPage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;

public abstract class AbstractSignUpPageTest extends AbstractPageTest {

    @Resource
    protected SignUpPage signUpPage;

    @BeforeMethod
    public void signUpPageSetup() {
        test.assignCategory("SignUpPage");
        signUpPage.openPage();
    }
}
