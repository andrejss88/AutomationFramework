package com.github.tests.searchpage;

import com.github.pages.search.enums.Language;
import com.github.tests.abstractpagetest.AbstractSearchPageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.github.pages.search.SearchPredicates.is;
import static com.github.pages.search.SearchPredicates.isNot;

/**
 * Tests here use a different design method: a simple Fluent Interface,
 * where methods are chained together to form a readable sequence of actions
 */
public class SearchPageFilters extends AbstractSearchPageTest {

    @Test
    public void checkLanguageFilterWorks() {

        Language java = Language.JAVA;

        search.act()
                .enterSearchWord(java)
                .clickSearch()
                .selectLanguage(java);

        search.verify()
                .languageLabels(is(java));
    }

    @Test
    public void checkLanguageFilterWorksNegativeTest(){

        Language html = Language.HTML;
        Language python = Language.PYTHON;

        search.act()
                .enterSearchWord(html)
                .clickSearch()
                .selectLanguage(html);

        search.verify()
                .languageLabels(isNot(python));
    }

    @Test
    public void checkFilterTotalRepoCount(){

        Language js = Language.JAVASCRIPT;

        search.act()
                .enterSearchWord(js)
                .clickSearch();

        int repoCount = search.get().repoCountFor(js);

        search.act()
                .selectLanguage(js);

        int totalCount = search.get().totalRepoCount();

        // For some reason filter results count may not match
        // depending on searched keyword
        Assert.assertEquals(repoCount, totalCount);
    }

}
