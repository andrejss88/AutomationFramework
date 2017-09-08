package com.github.pages.signup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.github.utils.ElementUtil.checkElementIsDisplayed;

public class SignUpPageActions {

    private WebDriver driver;

    public SignUpPageActions(){}

    public SignUpPageActions(WebDriver driver){
        this.driver = driver;
    }

    private WebElement createAccountBtn;

    private WebElement getCreateAccountBtn(){
        return driver.findElement(By.id("signup_button"));
    }

    public SignUpPageActions checkAccountCreationSuccessful() {
        Assert.assertFalse(checkElementIsDisplayed(getCreateAccountBtn()), "'Create Account' button should not be visible if account creation was successful");
        return this;
    }

    public SignUpPageActions checkAccountCreationFailed() {
        Assert.assertTrue(checkElementIsDisplayed(getCreateAccountBtn()), "'Create Account' button should remain visible if account creation failed");
        return this;
    }


    public SignUpPageActions clickCreateAccount() {
        getCreateAccountBtn().click();
        return this;
    }


}
