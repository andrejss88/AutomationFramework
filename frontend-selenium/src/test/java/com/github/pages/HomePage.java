package com.github.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractGitHubPage {

    public static String PAGE_URL = BASE_URL ;

    //Locators unique to Homepage
    @FindBy(how = How.CLASS_NAME, using = "display-heading-1")
    private WebElement heading;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    private WebElement signUpBtn;

    @FindBy(name = "user[login]")
    private WebElement usernameField;

    @FindBy(name = "user[email]")
    private WebElement emailField;

    @FindBy(name = "user[password]")
    private WebElement passwordField;

    /**
     * Instantiate for the purpose of initializing page's elements
     * i.e. when you come from a different page
     */

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Instantiate when you want to start the test from this page
     * @param pageUrl : use the PAGE_URL of this class
     */
    public HomePage(WebDriver driver, String pageUrl) {
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        Assert.assertTrue("Could not assert Home Page opened", this.isPageOpened());
    }

    public void clickSignUp() {
        signUpBtn.click();
    }


    /**
     * Below methods do the same as the method "fillInNewUserDetails"
     * but using a Builder pattern
     */
    public HomePage enterUserName(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public HomePage enterEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public HomePage enterPassword(String email) {
        passwordField.sendKeys(email);
        return this;
    }

}