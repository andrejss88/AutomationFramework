package com.github.pages.signup;

import com.github.pages.AbstractGitHubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "join";

    private SignUpPageActionsController signUpPageActions;

    private SignUpPageVerifierController signUpPageVerifier;

    private SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        initControllers();
    }

    private SignUpPage(WebDriver driver, String pageUrl) {
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        initControllers();
    }

    public static SignUpPage initPageElements(WebDriver driver){
        return new SignUpPage(driver);
    }

    private void initControllers() {
        signUpPageActions = new SignUpPageActionsController(driver);
        signUpPageVerifier = new SignUpPageVerifierController(driver);
    }

    public static SignUpPage openPage(WebDriver driver){
        return new SignUpPage(driver, PAGE_URL);
    }

    public SignUpPageActionsController act(){
        return signUpPageActions ;
    }

    public SignUpPageVerifierController verify() { return signUpPageVerifier ;}

}
