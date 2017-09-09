package com.github.pages.search;

import com.github.pages.search.enums.Language;
import com.github.pages.search.enums.SortOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageActionsController extends AbstractSearchPageController{

    private static final int WAIT_SECONDS = 5;

    public SearchPageActionsController(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPageActionsController(){}

    public SearchPageActionsController enterSearchWord(String keyWord) {
        getSearchInput().sendKeys(keyWord);
        return this;
    }

    public SearchPageActionsController enterSearchWord(Language language) {
        getSearchInput().sendKeys(language.toString());
        return this;
    }

    public SearchPageActionsController clickSearch() {
        getSearchBtn().click();
        return this;
    }

    public SearchPageActionsController selectLanguage(Language language) {
        String languageItem = FILTER_ITEM +
                " and text()[normalize-space() = '" + language + "']]";

        WebElement li = driver.findElement(By.xpath(languageItem));
        li.click();

        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath(FILTER_ITEM + " and contains(@class, 'selected')]")));
        return this;

    }


    public SearchPageActionsController sortBy(SortOptions option){

        getSortByBtn().click();

        String optionText = "//span[contains(@class, 'select-menu-item-text')" +
                "  and text()[normalize-space() = '" + option + "']]";

        driver.findElement(By.xpath(optionText)).click();

        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("repo-list")));

        return this;
    }

    private WebElement getSearchBtn() {
        return driver.findElement(By.xpath("//button[@type='submit']"));
    }

    public WebElement getFilterList() {
        return driver.findElement(By.className("filter-list"));
    }

    private WebElement getSearchInput() {
        return driver.findElement(By.className("input-block"));
    }

    private WebElement getSortByBtn() {
        return driver.findElement(By.className("select-menu-button"));
    }

}
