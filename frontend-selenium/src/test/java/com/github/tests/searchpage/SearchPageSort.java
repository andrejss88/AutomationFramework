package com.github.tests.searchpage;

import com.github.tests.abstractpagetest.AbstractSearchPageTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.github.pages.search.SearchPredicates.isSorted;
import static com.github.pages.search.SortOptions.MOST_STARS;

public class SearchPageSort extends AbstractSearchPageTest {


    @Test
    public void checkSortBy(){

        search.act()
                .enterSearchWord("Java")
                .clickSearch()
                .sortBy(MOST_STARS);

        List<Double> ratingList = search.get().starRatings();

        Assert.assertTrue(isSorted(ratingList));

    }
}
