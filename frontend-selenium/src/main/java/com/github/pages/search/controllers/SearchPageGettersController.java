package com.github.pages.search.controllers;

import com.github.pages.search.enums.Language;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;

import java.util.List;

import static com.github.utils.ConvertUtil.convertToDoubleList;
import static com.github.utils.ConvertUtil.stringToNumber;

@Controller
public class SearchPageGettersController extends AbstractSearchPageController {

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
