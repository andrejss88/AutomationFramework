package com.github.pages.signin.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.testng.Assert;

import static com.github.pages.AbstractGitHubPage.BASE_URL;

@Controller
public class SignInPageVerifierController extends AbstractSignInPageController {

    public SignInPageVerifierController signUpFailedMessageShows(){
        Assert.assertTrue(getIncorrectDetailsMsg().isDisplayed(),"Cannot locate the the 'Sign Up Failed' error message ");
        return this;
    }

    public SignInPageVerifierController userRemainsOnSamePage(){
        Assert.assertEquals(driver.getCurrentUrl(),BASE_URL + "session");
        return this;
    }

    private WebElement getIncorrectDetailsMsg(){
        return driver.findElement(By.xpath("//div[contains(@class, 'flash-error') and contains(.//div, 'Incorrect username or password')]"));
    }
}
