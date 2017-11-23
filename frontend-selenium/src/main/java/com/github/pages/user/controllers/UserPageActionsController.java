package com.github.pages.user.controllers;

import com.github.pages.user.enums.UserTab;
import org.openqa.selenium.By;
import org.springframework.stereotype.Controller;

import static com.github.utils.XpathUtil.tabXpath;

@Controller
public class UserPageActionsController extends AbstractUserPageController {

    public UserPageActionsController selectTab(UserTab tab) {
            driver.findElement(By.xpath(tabXpath(tab.toString()))).click();
            return this;
    }

    public UserPageActionsController enterSearchWord(String keyWord) {
        driver.findElement(By.cssSelector("input[type='search']")).sendKeys(keyWord);
        return this;
    }
}
