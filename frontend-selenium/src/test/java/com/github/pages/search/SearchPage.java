package com.github.pages.search;

import com.github.pages.AbstractGitHubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "search";

    private SearchPageActionsController searchPageActions;

    private SearchPageGettersController searchPageGetters;

    private SearchPageVerifierController searchPageVerifierController;

    private SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        initControllers();
    }

    private SearchPage(WebDriver driver, String pageUrl){
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
    }

    private void initControllers() {
        searchPageActions = new SearchPageActionsController(driver);
        searchPageGetters = new SearchPageGettersController(driver);
        searchPageVerifierController = new SearchPageVerifierController(driver);
    }

    public static SearchPage initPageElements(WebDriver driver){
        return new SearchPage(driver);
    }

    public static SearchPage openPage(WebDriver driver){
        return new SearchPage(driver, PAGE_URL);
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
