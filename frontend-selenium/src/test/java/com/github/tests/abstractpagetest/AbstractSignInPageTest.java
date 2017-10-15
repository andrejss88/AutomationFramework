package com.github.tests.abstractpagetest;

import com.github.pages.signin.SignInPage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;

public abstract class AbstractSignInPageTest extends AbstractPageTest {

    @Resource
    protected SignInPage signInPage;

    @BeforeMethod
    public void commonSetup() {
        signInPage.openPage();
    }
}
