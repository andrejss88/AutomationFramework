package com.github.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.function.Predicate;

import static com.github.utils.ConvertUtil.convertToStringList;

public class SearchPageActions {

    private static final String FILTER_ITEM = "//a[contains(@class, 'filter-item')";
    private static final int WAIT_SECONDS = 5;
    private WebDriver driver;

    public SearchPageActions(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPageActions(){}

    public SearchPageActions enterSearchWord(String keyWord) {
        getSearchInput().sendKeys(keyWord);
        return this;
    }

    public SearchPageActions enterSearchWord(Language language) {
        getSearchInput().sendKeys(language.toString());
        return this;
    }

    public SearchPageActions clickSearch() {
        getSearchBtn().click();
        return this;
    }

    public SearchPageActions selectLanguage(Language language) {
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

    public SearchPageActions checkLanguageLabels(Predicate<String> predicate) {

        List<String> labels = getLanguageLabels();

        for (String label : labels) {
            if (!predicate.test(label)) {
                Assert.fail("Filtering by language label " + label + " failed");
            }
        }
        return this;
    }


    private List<String> getLanguageLabels() {
        String xpath = "//div[contains(@class, 'repo-list-item')]//div[2]";

        List<WebElement> list = driver.findElements(By.xpath(xpath));

        return convertToStringList(list);
    }




    public SearchPageActions sortBy(SortOptions option){

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
