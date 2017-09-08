package com.github.pages.pricing;

import com.github.pages.AbstractGitHubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PricingPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "pricing";

    private PricingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private PricingPage(WebDriver driver, String pageUrl){
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
    }

    public static PricingPage initPageElements(WebDriver driver){
        return new PricingPage(driver);
    }

    public static PricingPage openPage(WebDriver driver){
        return new PricingPage(driver, PAGE_URL);
    }

}
