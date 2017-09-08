package com.github.pages.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.github.utils.ConvertUtil.convertToDoubleList;
import static com.github.utils.ConvertUtil.stringToNumber;

public class SearchPageGetters {

    private static final String FILTER_ITEM = "//a[contains(@class, 'filter-item')";

    private WebDriver driver;

    public SearchPageGetters(WebDriver driver) {
        this.driver = driver;
    }


    public int repoCountFor(Language language){
        String repoCountXpath = FILTER_ITEM +
                " and text()[normalize-space() = '" + language + "']]//span";
        String repoCount = driver.findElement(By.xpath(repoCountXpath)).getText();

        return stringToNumber(repoCount);
    }


    public int totalRepoCount(){
        String totalRepoXpath = "//h3[contains(text(),'repository results')]";
        String repoCount = driver.findElement(By.xpath(totalRepoXpath)).getText();

        return stringToNumber(repoCount);
    }

    public List<Double> starRatings() {

        String xpath = "//div[contains(@class, 'repo-list')]//a[contains(@class, 'muted-link')]";

        List<WebElement> list = driver.findElements(By.xpath(xpath));

        return convertToDoubleList(list);
    }

}
