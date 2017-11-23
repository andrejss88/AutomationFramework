package com.github.tests.abstractpagetest;

import com.github.pages.user.UserPage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;

public abstract class AbstractUserPageTest extends AbstractPageTest {

    @Resource
    protected UserPage user;

    @BeforeMethod
    public void searchPageSetup() {
        test.assignCategory("UserPage");
    }
}
