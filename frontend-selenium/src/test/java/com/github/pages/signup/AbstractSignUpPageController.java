package com.github.pages.signup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AbstractSignUpPageController {

    protected WebDriver driver;

    // Package-private access modifier on purpose

    WebElement getCreateAccountBtn(){
        return driver.findElement(By.id("signup_button"));
    }
}
