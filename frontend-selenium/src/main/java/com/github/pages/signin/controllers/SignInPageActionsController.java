package com.github.pages.signin.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.testng.Assert;

@Controller
public class SignInPageActionsController extends AbstractSignInPageController {

    public SignInPageActionsController clickSignIn(){
        getSignInBtn().click();
        return this;
    }

    public SignInPageActionsController enterUserName(String name){
        Assert.assertNotNull(name);
        getNameField().sendKeys(name);
        return this;
    }

    public SignInPageActionsController enterPassword(String pwd){
        Assert.assertNotNull(pwd);
        getPasswordField().sendKeys(pwd);
        return this;
    }

    /**
     * HTML of the User Details fields is different on SignIn page
     * @param name: username
     * @param pwd: password
     * @return the controller for further method chaining
     */

    public SignInPageActionsController fillInUserDetails(String name, String pwd){
        Assert.assertNotNull(name);
        Assert.assertNotNull(pwd);

        getNameField().sendKeys(name);
        getPasswordField().sendKeys(pwd);
        return this;
    }

    private WebElement getSignInBtn(){
        return driver.findElement(By.xpath("//input[@value='Sign in']"));
    }

    private WebElement getNameField(){
        return driver.findElement(By.id("login_field"));
    }

    private WebElement getPasswordField(){
        return driver.findElement(By.id("password"));
    }
}
