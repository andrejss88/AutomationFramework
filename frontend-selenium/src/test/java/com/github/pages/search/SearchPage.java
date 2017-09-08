package com.github.pages.search;

import com.github.pages.AbstractGitHubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "search";

    private SearchPageActions searchPageActions;

    private SearchPageGetters searchPageGetters;

    private SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private SearchPage(WebDriver driver, String pageUrl){
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        searchPageActions = new SearchPageActions(driver);
        searchPageGetters = new SearchPageGetters(driver);
    }

    public static SearchPage initPageElements(WebDriver driver){
        return new SearchPage(driver);
    }

    public static SearchPage openPage(WebDriver driver){
        return new SearchPage(driver, PAGE_URL);
    }

    public SearchPageActions act(){
        return searchPageActions;
    }

    public SearchPageGetters get() {
        return searchPageGetters;
    }

}
