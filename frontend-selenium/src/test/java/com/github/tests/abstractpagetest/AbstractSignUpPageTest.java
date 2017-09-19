package com.github.tests.abstractpagetest;

import com.github.pages.signup.SignUpPage;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractSignUpPageTest extends AbstractPageTest {

    protected SignUpPage signUpPage;

    @BeforeMethod
    public void commonSetup() {
        signUpPage = SignUpPage.openPage(driver);
    }
}
