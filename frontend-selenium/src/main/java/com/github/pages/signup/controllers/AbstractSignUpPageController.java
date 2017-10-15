package com.github.pages.signup.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.annotation.Resource;

public class AbstractSignUpPageController {

    @Resource(name = "driver")
    protected WebDriver driver;

    WebElement getCreateAccountBtn(){
        return driver.findElement(By.id("signup_button"));
    }
}
