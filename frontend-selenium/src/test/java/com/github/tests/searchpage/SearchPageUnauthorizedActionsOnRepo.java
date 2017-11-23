package com.github.tests.searchpage;

import com.github.pages.search.enums.Language;
import com.github.pages.signin.SignInPage;
import com.github.tests.abstractpagetest.AbstractSearchPageTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class SearchPageUnauthorizedActionsOnRepo extends AbstractSearchPageTest {

    @Resource
    private SignInPage signInPage;

    @BeforeMethod
    public void searchAndSelectARepo(){
        Language java = Language.JAVA;

        search.act()
                .enterSearchWord(java)
                .clickSearch()
                .selectRepo("hmkcode");
    }

    @Test(description = "Unauthorized user cannot 'Watch' a Repo")
    public void watchNotAllowed() {

        search.act()
                .clickWatch();

        signInPage.verify()
                .isOnPage();
    }

    @Test(description = "Unauthorized user cannot 'Star' a Repo")
    public void starNotAllowed(){

        search.act()
                .clickStar();

        signInPage.verify()
                .isOnPage();
    }

    @Test(description = "Unauthorized user cannot 'Fork' a Repo")
    public void forkNotAllowed(){

        search.act()
                .clickFork();

        signInPage.verify()
                .isOnPage();
    }
}
