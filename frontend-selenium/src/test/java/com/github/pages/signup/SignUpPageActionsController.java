package com.github.pages.signup;

import org.openqa.selenium.WebDriver;

public class SignUpPageActionsController extends AbstractSignUpPageController {

    public SignUpPageActionsController(){}

    public SignUpPageActionsController(WebDriver driver){
        this.driver = driver;
    }


    public SignUpPageActionsController clickCreateAccount() {
        getCreateAccountBtn().click();
        return this;
    }


}
