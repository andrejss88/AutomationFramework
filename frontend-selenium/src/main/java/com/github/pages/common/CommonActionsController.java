package com.github.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CommonActionsController {

    @Resource(name= "driver")
    protected WebDriver driver;

    public CommonActionsController(){
        PageFactory.initElements(driver, this);
    }

    public void fillInNewUserDetails(String username, String email, String password) {
        driver.findElement(By.name("user[login]")).sendKeys(username);
        driver.findElement(By.name("user[email]")).sendKeys(email);
        driver.findElement(By.name("user[password]")).sendKeys(password);
    }

}
