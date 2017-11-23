package com.github.pages.signup.controllers;

import com.github.pages.AbstractPageController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract class AbstractSignUpPageController extends AbstractPageController {

    WebElement getCreateAccountBtn(){
        return driver.findElement(By.id("signup_button"));
    }
}
