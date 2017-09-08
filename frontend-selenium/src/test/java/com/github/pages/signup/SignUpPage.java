package com.github.pages.signup;

import com.github.pages.AbstractGitHubPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "join";

    private SignUpPageActions signUpPageActions;

    private SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private SignUpPage(WebDriver driver, String pageUrl) {
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        signUpPageActions = new SignUpPageActions(driver);
    }

    public static SignUpPage initPageElements(WebDriver driver){
        return new SignUpPage(driver);
    }

    public static SignUpPage openPage(WebDriver driver){
        return new SignUpPage(driver, PAGE_URL);
    }

    public SignUpPageActions act(){
        return signUpPageActions;
    }

}
