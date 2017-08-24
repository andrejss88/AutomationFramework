package com.github.tests.builderstyle;

import com.github.pages.searchpage.Language;
import com.github.pages.searchpage.SearchPage;
import com.github.tests.AbstractPageTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.pages.searchpage.FilterPredicates.is;
import static com.github.pages.searchpage.FilterPredicates.isNot;
import static com.github.pages.searchpage.SearchPage.isSorted;
import static com.github.pages.searchpage.SortOptions.MOST_STARS;

/**
 * Tests here use a different design method: a simple Fluent Interface,
 * where methods are chained together to form a readable sequence of actions
 */
public class SearchPageTest extends AbstractPageTest {

    SearchPage search;

    @BeforeMethod
    public void setup() {
        search = new SearchPage(driver, SearchPage.PAGE_URL);
    }

    @Test
    public void checkLanguageFilterWorks() {

        Language java = Language.JAVA;

        search.enterSearchWord(java)
                .clickSearch()
                .selectLanguage(java)
                .checkLanguageLabels(is(java));
    }

    @Test
    public void checkLanguageFilterWorksNegativeTest(){

        Language html = Language.HTML;
        Language python = Language.PYTHON;

        search.selectLanguage(html)
                .checkLanguageLabels(isNot(python));
    }

    @Test
    public void checkFilterTotalRepoCount(){

        Language js = Language.JAVASCRIPT;

        search.enterSearchWord(js)
                .clickSearch();

        int repoCount = search.getRepoCountFor(js);

        search.selectLanguage(js);

        int totalCount = search.getTotalRepoCount();

        // For some reason filter results count may not match
        // depending on searched keyword
        Assert.assertEquals(repoCount, totalCount);
    }

    @Test
    public void checkSortBy(){

        List<Double> ratingList = search.enterSearchWord("Java")
                .clickSearch()
                .sortBy(MOST_STARS)
                .getStarRatings();

        Assert.assertTrue(isSorted(ratingList));

    }
}
