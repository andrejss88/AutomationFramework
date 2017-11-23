package com.github.pages.user;

import com.github.pages.AbstractGitHubPage;
import com.github.pages.user.controllers.UserPageActionsController;
import com.github.pages.user.controllers.UserPageGettersController;
import com.github.pages.user.controllers.UserPageVerifierController;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Component
public class UserPage extends AbstractGitHubPage {

    @Autowired
    private UserPageActionsController userPageActions;

    @Autowired
    private UserPageGettersController userPageGetters;

    @Autowired
    private UserPageVerifierController userPageVerifierController;

    public void openPage(String user){
        driver.get(BASE_URL + user);
        wait.until(visibilityOfElementLocated(By.className("UnderlineNav-body")));
    }

    public UserPageActionsController act(){
        return userPageActions;
    }

    public UserPageGettersController get() {
        return userPageGetters;
    }

    public UserPageVerifierController verify() {
        return userPageVerifierController;
    }



}
