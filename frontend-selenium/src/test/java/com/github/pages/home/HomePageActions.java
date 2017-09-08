package com.github.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageActions  {

    private WebDriver driver;

    public HomePageActions(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageActions(){}

    public HomePageActions enterUserName(String username) {
        driver.findElement(By.name("user[login]")).sendKeys(username);
        return this;
    }

    public HomePageActions enterEmail(String email) {
        driver.findElement(By.name("user[email]")).sendKeys(email);
        return this;
    }

    public HomePageActions enterPassword(String password) {
        driver.findElement(By.name("user[password]")).sendKeys(password);
        return this;
    }

    public HomePageActions clickSignUp() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return this;
    }

}
