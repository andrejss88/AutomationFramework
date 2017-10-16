package com.github.tests.abstractpagetest;

import com.github.pages.home.HomePage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;
import java.lang.reflect.Method;

public abstract class AbstractHomePageTest extends AbstractPageTest {

    @Resource
    protected HomePage home;

    @BeforeMethod
    public void commonSetup(Method method) {

        setTestCaseInfo(method);

        test = extent.startTest(testCaseName, testCaseDesc);
        test.assignCategory("HomePage");
        home.openPage();
    }

}
