package com.github.pages.common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Header  {
    private WebDriver driver ;
    /**
     * Common header elements found on various pages
     * Keeping them in a separate class prevents declaring them
     * multiple times in many Page Objects that share the header
     *
     * This keeps things DRY
     */

    @FindBy(how = How.LINK_TEXT, using = "Sign in")
    private WebElement signInBtn;

    @FindBy(how = How.LINK_TEXT, using = "Sign up")
    private WebElement signUpBtn;

    @FindBy(className = "header-search-input")
    private WebElement searchGitHubField;

    @FindBy(how = How.CLASS_NAME, using = "octicon-mark-github")
    private WebElement logoIcon;


    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnHeaderSignIn() {
        signInBtn.click();
    }

    public void clickOnHeaderSignUp() {
        signUpBtn.click();
    }

    public void search(String searchText) {
        searchGitHubField.sendKeys(searchText);
        searchGitHubField.sendKeys(Keys.RETURN);
    }

    public void clickLogo() {
        logoIcon.click();
    }
}
