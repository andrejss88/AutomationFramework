package com.github.tests.searchpage;

import com.github.pages.search.enums.Language;
import com.github.pages.search.enums.SearchTab;
import com.github.pages.signin.SignInPage;
import com.github.tests.abstractpagetest.AbstractSearchPageTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;


public class SearchPageFollowLeadsToSignUp extends AbstractSearchPageTest {

    @Resource
    private SignInPage signInPage;

    @Test(testName = "Unauthorized Follow", description = "When not signed in user clicks follow - he is directed to Sign Up Page")
    public void unauthorizedFollowIsRedirected() {

        Language java = Language.JAVA;

        search.act()
                .enterSearchWord(java)
                .clickSearch()
                .selectTab(SearchTab.USERS)
                .clickFollow(1);


        signInPage.verify()
                .isOnPage();

    }
}
