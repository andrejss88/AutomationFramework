package com.github.pages.search.controllers;

import com.github.pages.search.enums.Language;
import com.github.pages.search.enums.SearchTab;
import com.github.pages.search.enums.SortOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.github.utils.XpathUtil.sideNav;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Controller
public class SearchPageActionsController extends AbstractSearchPageController {

    public SearchPageActionsController enterSearchWord(String keyWord) {
        getSearchInput().sendKeys(keyWord);
        return this;
    }

    public SearchPageActionsController enterSearchWord(Language language) {
        getSearchInput().sendKeys(language.toString());
        return this;
    }

    public SearchPageActionsController clickSearch() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        wait.until(visibilityOfElementLocated(By.xpath("//nav[contains(@class, 'menu')]")));
        return this;
    }

    public SearchPageActionsController selectTab(SearchTab searchTab){
        driver.findElement(By.xpath(sideNav(searchTab.toString()))).click();
        return this;
    }

    public SearchPageActionsController clickFollow(int btnPosition){
        List<WebElement> buttons = driver.findElements(By.className("follow"));
        buttons.get(btnPosition-1).click();
        return this;
    }

    public SearchPageActionsController selectLanguage(Language language) {
        String languageItem = FILTER_ITEM +
                " and text()[normalize-space() = '" + language + "']]";

        WebElement li = driver.findElement(By.xpath(languageItem));
        li.click();

        wait.until(visibilityOfElementLocated(By
                        .xpath(FILTER_ITEM + " and contains(@class, 'selected')]")));
        return this;

    }


    public SearchPageActionsController sortBy(SortOptions option){

        driver.findElement(By.className("select-menu-button")).click();

        String optionText = "//span[contains(@class, 'select-menu-item-text')" +
                "  and text()[normalize-space() = '" + option + "']]";

        driver.findElement(By.xpath(optionText)).click();
        wait.until(visibilityOfElementLocated(By.className("repo-list")));

        return this;
    }

    public WebElement getFilterList() {
        return driver.findElement(By.className("filter-list"));
    }

    private WebElement getSearchInput() {
        return driver.findElement(By.className("input-block"));
    }

    /**
     * Selects the chosen repo
     * @param repoName: repo name WITHOUT "/<language>" postfix
     */
    public SearchPageActionsController selectRepo(String repoName) {

        driver.findElement(By.xpath("//a[contains(text(), '" + repoName + "')]")).click();
        wait.until(visibilityOfElementLocated(By.className("author")));
        return this;
    }

    public void clickWatch() {
        getPageHeadActions().findElement(By.linkText("Watch")).click();
    }

    public void clickStar() {
        getPageHeadActions().findElement(By.linkText("Star")).click();
    }

    public void clickFork() {
        getPageHeadActions().findElement(By.linkText("Fork")).click();
    }

    private WebElement getPageHeadActions(){
        return driver.findElement(By.className("pagehead-actions"));
    }
}
