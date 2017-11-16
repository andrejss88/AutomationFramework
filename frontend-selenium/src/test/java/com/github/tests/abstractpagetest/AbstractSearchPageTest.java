package com.github.tests.abstractpagetest;

import com.github.pages.search.SearchPage;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;

public abstract class AbstractSearchPageTest extends AbstractPageTest {

    @Resource
    protected SearchPage search;

    @BeforeMethod
    public void searchPageSetup() {
        test.assignCategory("SearchPage");
        search.openPage();
    }
}
