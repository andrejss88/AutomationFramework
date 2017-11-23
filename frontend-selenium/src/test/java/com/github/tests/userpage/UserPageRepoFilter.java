package com.github.tests.userpage;

import com.github.pages.user.enums.UserTab;
import com.github.tests.abstractpagetest.AbstractUserPageTest;
import org.testng.annotations.Test;

public class UserPageRepoFilter extends AbstractUserPageTest {

    @Test(description = "Repo filter lists correct set of repos")
    public void filterWorksCorrectly() {

        String userName = "andrejss88";
        String searchWord = "Lquiz";
        user.openPage(userName);

        user.act()
                .selectTab(UserTab.REPOSITORIES);

        user.verify()
                .numberOfReposListed(8)
                .clearFilterIsDisplayed(false);

        user.act()
                .enterSearchWord(searchWord);

        user.verify()
                .resultHeaderIs("1 result for repositories matching " + searchWord)
                .numberOfReposListed(1)
                .clearFilterIsDisplayed(true);

    }
}
