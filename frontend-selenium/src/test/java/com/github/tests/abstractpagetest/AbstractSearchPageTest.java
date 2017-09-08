package com.github.tests.abstractpagetest;

import com.github.pages.search.SearchPage;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractSearchPageTest extends AbstractPageTest {

    protected SearchPage search;

    @BeforeMethod
    public void commonSetup() {
        search = SearchPage.openPage(driver);
    }
}
