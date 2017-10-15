package com.github.pages.home;

import com.github.pages.AbstractGitHubPage;
import com.github.pages.home.controllers.HomePageActionsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL ;

    @Autowired
    private HomePageActionsController homePageActions;

    public void openPage(){
        driver.get(PAGE_URL);
    }

    public HomePageActionsController act(){
        return homePageActions;
    }

}