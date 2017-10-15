package com.github.pages.signin;

import com.github.pages.AbstractGitHubPage;
import com.github.pages.signin.controllers.SignInPageActionsController;
import com.github.pages.signin.controllers.SignInPageVerifierController;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignInPage extends AbstractGitHubPage {

    private static String PAGE_URL = BASE_URL + "login";
    public static String HEADING = "Sign in to GitHub";

    @Autowired
    private SignInPageActionsController signInPageActions;

    @Autowired
    private SignInPageVerifierController signInPageVerifier;

    @FindBy(name = "commit")
    private WebElement signInBtn;


    public void openPage(){
        driver.get(PAGE_URL);
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public SignInPageActionsController act(){ return signInPageActions ;
    }

    public SignInPageVerifierController verify() { return signInPageVerifier ;}

}