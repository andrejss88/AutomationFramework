package com.github.tests.abstractpagetest;

import com.github.pages.home.HomePage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;

public abstract class AbstractHomePageTest extends AbstractPageTest {

    @Resource
    protected HomePage home;

    @BeforeMethod
    public void commonSetup() {
        home.openPage();
    }


}
