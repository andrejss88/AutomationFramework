package com.github.pages.search.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.testng.Assert;

import java.util.List;
import java.util.function.Predicate;

import static com.github.pages.search.predicates.SearchPredicates.isSorted;
import static com.github.utils.ConvertUtil.convertToStringList;

@Controller
public class SearchPageVerifierController extends AbstractSearchPageController {

    public SearchPageVerifierController listIsSorted(List<? extends Comparable> list){
        Assert.assertTrue(isSorted(list));
        return this;
    }

    public SearchPageVerifierController languageLabels(Predicate<String> predicate) {

        List<String> labels = getLanguageLabels();

        for (String label : labels) {
            if (!predicate.test(label)) {
                Assert.fail("Filtering by language label '" + label + "' failed");
            }
        }
        return this;
    }

    private List<String> getLanguageLabels() {
        String xpath = "//div[contains(@class, 'repo-list-item')]/div[contains(@class, 'd-table-cell')]";

        List<WebElement> list = driver.findElements(By.xpath(xpath));

        return convertToStringList(list);
    }

    /**
     * If no element is found - method throws exception and test will be considered as failed
     */
    public void elementPresentWithText(String text) {
        driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }
}
