package com.github.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends AbstractGitHubPage{

    public static String PAGE_URL = BASE_URL + "login";
    public static String HEADING = "Sign in to GitHub";

    @FindBy(name = "commit")
    private WebElement signInBtn;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Instantiate when you want to start the test from this page
     * @param pageUrl : use the PAGE_URL of this class
     */
    public SignInPage(WebDriver driver, String pageUrl){
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        Assert.assertTrue("Could not assert Sign In Page was opened", this.isPageOpened());
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

}