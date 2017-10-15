package com.github.pages.search;

import com.github.pages.AbstractGitHubPage;
import com.github.pages.search.controllers.SearchPageActionsController;
import com.github.pages.search.controllers.SearchPageGettersController;
import com.github.pages.search.controllers.SearchPageVerifierController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SearchPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "search";

    @Autowired
    private SearchPageActionsController searchPageActions;

    @Autowired
    private SearchPageGettersController searchPageGetters;

    @Autowired
    private SearchPageVerifierController searchPageVerifierController;

    public void openPage(){
        driver.get(PAGE_URL);
    }

    public SearchPageActionsController act(){
        return searchPageActions;
    }

    public SearchPageGettersController get() {
        return searchPageGetters;
    }

    public SearchPageVerifierController verify() {
        return searchPageVerifierController;
    }



}
