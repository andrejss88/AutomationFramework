package com.github.tests.abstractpagetest;

import com.github.pages.search.SearchPage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;
import java.lang.reflect.Method;

public abstract class AbstractSearchPageTest extends AbstractPageTest {

    @Resource
    protected SearchPage search;

    @BeforeMethod
    public void commonSetup(Method method) {

        setTestCaseInfo(method);

        test = extent.startTest(testCaseName, testCaseDesc);
        test.assignCategory("SearchPage");
        search.openPage();
    }
}
