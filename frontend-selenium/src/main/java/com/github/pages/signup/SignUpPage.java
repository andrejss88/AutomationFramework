package com.github.pages.signup;

import com.github.pages.AbstractGitHubPage;
import com.github.pages.signup.controllers.SignUpPageActionsController;
import com.github.pages.signup.controllers.SignUpPageVerifierController;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpPage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL + "join";

    @Autowired
    private SignUpPageActionsController signUpPageActions;

    @Autowired
    private SignUpPageVerifierController signUpPageVerifier;

    private SignUpPage() {
        PageFactory.initElements(driver, this);
    }

    public static SignUpPage initPageElements(){
        return new SignUpPage();
    }

    public void openPage(){
        driver.get(PAGE_URL);
    }

    public SignUpPageActionsController act(){
        return signUpPageActions ;
    }

    public SignUpPageVerifierController verify() { return signUpPageVerifier ;}

}
