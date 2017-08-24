package com.github.pages.searchpage;

import com.github.pages.AbstractGitHubPage;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.function.Predicate;

import static com.github.utils.ConvertUtil.*;

public class SearchPage extends AbstractGitHubPage {

    public static String PAGE_URL = BASE_URL + "search";

    private static final String FILTER_ITEM = "//a[contains(@class, 'filter-item')";
    private static final int WAIT_SECONDS = 5;

    @FindBy(how = How.CLASS_NAME, using = "input-block")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchBtn;

    @FindBy(how = How.CLASS_NAME, using = "filter-list")
    private WebElement filterList;

    @FindBy(how = How.CLASS_NAME, using = "select-menu-button")
    private WebElement sortByBtn;



    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SearchPage(WebDriver driver, String pageUrl){
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
    }

    public SearchPage enterSearchWord(String keyWord) {
        searchInput.sendKeys(keyWord);
        return this;
    }

    public SearchPage enterSearchWord(Language language) {
        searchInput.sendKeys(language.toString());
        return this;
    }

    public SearchPage clickSearch() {
        searchBtn.click();
        return this;
    }

    public SearchPage selectLanguage(Language language) {
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

    public SearchPage checkLanguageLabels(Predicate<String> predicate) {

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

    public List<Double> getStarRatings() {

        String xpath = "//div[contains(@class, 'repo-list')]//a[contains(@class, 'muted-link')]";

        List<WebElement> list = driver.findElements(By.xpath(xpath));

        return convertToDoubleList(list);
    }

    public static <T extends Comparable> boolean isSorted(List<T> list){
        boolean isSorted = Ordering.natural().reverse().isOrdered(list);
        return isSorted;
    }

    public int getRepoCountFor(Language language){
        String repoCountXpath = FILTER_ITEM +
                " and text()[normalize-space() = '" + language + "']]//span";
        String repoCount = driver.findElement(By.xpath(repoCountXpath)).getText();

        return stringToNumber(repoCount);
    }

    public int getTotalRepoCount(){
        String totalRepoXpath = "//h3[contains(text(),'repository results')]";
        String repoCount = driver.findElement(By.xpath(totalRepoXpath)).getText();

        return stringToNumber(repoCount);
    }

    public SearchPage sortBy(SortOptions option){

        sortByBtn.click();

        String optionText = "//span[contains(@class, 'select-menu-item-text')" +
                "  and text()[normalize-space() = '" + option + "']]";


        driver.findElement(By.xpath(optionText)).click();

        WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("repo-list")));

        return this;
    }

}
