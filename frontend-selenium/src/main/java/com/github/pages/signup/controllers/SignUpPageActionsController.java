package com.github.pages.signup.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class SignUpPageActionsController extends AbstractSignUpPageController {

    public SignUpPageActionsController clickCreateAccount() {
        getCreateAccountBtn().click();
        return this;
    }


}
