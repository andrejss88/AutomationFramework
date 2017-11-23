package com.github.pages.common;

import com.github.pages.AbstractPageController;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonActionsController extends AbstractPageController {

    public CommonActionsController(){
        PageFactory.initElements(driver, this);
    }

    public void fillInNewUserDetails(String username, String email, String password) {
        driver.findElement(By.name("user[login]")).sendKeys(username);
        driver.findElement(By.name("user[email]")).sendKeys(email);
        driver.findElement(By.name("user[password]")).sendKeys(password);
    }

}
