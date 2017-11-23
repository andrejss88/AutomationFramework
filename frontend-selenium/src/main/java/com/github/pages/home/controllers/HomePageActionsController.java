package com.github.pages.home.controllers;

import com.github.pages.AbstractPageController;
import org.openqa.selenium.By;
import org.springframework.stereotype.Controller;

@Controller
public class HomePageActionsController extends AbstractPageController {

    public HomePageActionsController enterUserName(String username) {
        driver.findElement(By.name("user[login]")).sendKeys(username);
        return this;
    }

    public HomePageActionsController enterEmail(String email) {
        driver.findElement(By.name("user[email]")).sendKeys(email);
        return this;
    }

    public HomePageActionsController enterPassword(String password) {
        driver.findElement(By.name("user[password]")).sendKeys(password);
        return this;
    }

    public HomePageActionsController clickSignUp() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return this;
    }

}
