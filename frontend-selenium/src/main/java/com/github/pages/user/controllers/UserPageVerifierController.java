package com.github.pages.user.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.equalsIgnoreCase;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Controller
public class UserPageVerifierController extends AbstractUserPageController {

    public UserPageVerifierController resultHeaderIs(String expectedHeader) {

        String summary = driver.findElement(By.className("user-repo-search-results-summary")).getText();
        assertTrue(equalsIgnoreCase(summary,expectedHeader));
        return this;
    }

    /**
     * Check shown repositories under "Repositories" tab
     */
    public UserPageVerifierController numberOfReposListed(int repoNumShown) {

        String xpath = "//div[contains(@id, 'user-repositories-list')]/ul/li";
        List<WebElement> list = driver.findElements(By.xpath(xpath));
        assertEquals(list.size(), repoNumShown);

        return this;
    }

    public UserPageVerifierController clearFilterIsDisplayed(boolean clearFilterIsShown) {

        List<WebElement> filterControl = driver.findElements(By.className("issues-reset-query"));

        if(clearFilterIsShown){
            assertEquals(filterControl.size(), 1, "Expected to find exactly ONE Filter control");
        } else {
            assertEquals(filterControl.size(), 0, "Expected NOT to find any filter control");
        }
        return this;
    }
}
