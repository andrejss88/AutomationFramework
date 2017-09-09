package com.github.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.function.Predicate;

import static com.github.pages.search.SearchPredicates.isSorted;
import static com.github.utils.ConvertUtil.convertToStringList;

public class SearchPageVerifierController extends AbstractSearchPageController {

    public SearchPageVerifierController(){}
    public SearchPageVerifierController(WebDriver driver) {
        this.driver = driver;
    }

    public SearchPageVerifierController listIsSorted(List<? extends Comparable> list){
        Assert.assertTrue(isSorted(list));
        return this;
    }

    public SearchPageVerifierController languageLabels(Predicate<String> predicate) {

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
}
