package com.github.pages.signin;

import com.github.pages.AbstractGitHubPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends AbstractGitHubPage {

    private static String PAGE_URL = BASE_URL + "login";
    public static String HEADING = "Sign in to GitHub";

    private SignInPageActionsController signInPageActions;
    private SignInPageVerifierController signInPageVerifier;

    @FindBy(name = "commit")
    private WebElement signInBtn;

    private SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        initControllers();
    }

    /**
     * Instantiate when you want to start the test from this page
     * @param pageUrl : use the PAGE_URL of this class
     */
    private SignInPage(WebDriver driver, String pageUrl){
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        Assert.assertTrue("Could not assert Sign In Page was opened", this.isPageOpened());
        initControllers();
    }

    public static SignInPage initPageElements(WebDriver driver){
        return new SignInPage(driver);
    }

    private void initControllers() {
        signInPageActions = new SignInPageActionsController(driver);
        signInPageVerifier = new SignInPageVerifierController(driver);
    }

    public static SignInPage openPage(WebDriver driver){
        return new SignInPage(driver, PAGE_URL);
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public SignInPageActionsController act(){ return signInPageActions ;
    }

    public SignInPageVerifierController verify() { return signInPageVerifier ;}

}