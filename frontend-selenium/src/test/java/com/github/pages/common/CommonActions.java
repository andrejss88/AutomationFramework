package com.github.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonActions {
    WebDriver driver;

    @FindBy(name = "user[login]")
    private WebElement usernameField;

    @FindBy(name = "user[email]")
    private WebElement emailField;

    @FindBy(name = "user[password]")
    private WebElement passwordField;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInNewUserDetails(String username, String email, String password) {
        usernameField.sendKeys(username);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

}
