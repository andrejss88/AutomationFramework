package com.github.pages.signup.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Controller;
import org.testng.Assert;

import static com.github.utils.ElementUtil.checkElementIsDisplayed;

@Controller
public class SignUpPageVerifierController extends AbstractSignUpPageController {

    public SignUpPageVerifierController accountCreationSuccessful() {
        Assert.assertFalse(checkElementIsDisplayed(getCreateAccountBtn()), "'Create Account' button should not be visible if account creation was successful");
        return this;
    }

    public SignUpPageVerifierController accountCreationFailed() {
        Assert.assertTrue(checkElementIsDisplayed(getCreateAccountBtn()), "'Create Account' button should remain visible if account creation failed");
        Assert.assertTrue(checkElementIsDisplayed(getAccountCreationError()), "Could not find Flash Error message ");
        return this;
    }


    private WebElement getAccountCreationError(){
        return driver.findElement(By.xpath("//div[contains(@class, 'flash-error')  and contains(text(), 'problems creating')]"));
    }


}
