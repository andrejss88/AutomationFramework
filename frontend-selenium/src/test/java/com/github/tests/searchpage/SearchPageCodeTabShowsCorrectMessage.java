package com.github.tests.searchpage;

import com.github.pages.search.enums.Language;
import com.github.pages.search.enums.SearchTab;
import com.github.tests.abstractpagetest.AbstractSearchPageTest;
import org.testng.annotations.Test;


public class SearchPageCodeTabShowsCorrectMessage extends AbstractSearchPageTest {

    @Test
    public void codeTabShowsCorrectMessage() {

        Language java = Language.JAVA;

        search.act()
                .enterSearchWord(java)
                .clickSearch()
                .selectTab(SearchTab.CODE);

        search.verify()
                .elementPresentWithText("We could not perform this search");

    }
}
