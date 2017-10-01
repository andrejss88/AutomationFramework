package com.github.pages.home;

import com.github.pages.AbstractGitHubPage;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractGitHubPage {

    private static final String PAGE_URL = BASE_URL ;

    private HomePageActionsController homePageActions;

    private HomePage(){}

    private HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        initControllers();
    }

    private HomePage(WebDriver driver, String pageUrl) {
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
        Assert.assertTrue("Could not assert Home Page opened", this.isPageOpened());
    }

    private void initControllers() {
        homePageActions = new HomePageActionsController(driver);
    }

    /**
     * Use to just be able to operate on Page elements
     * @param driver
     * @return
     */

    public static HomePage initPageElements(WebDriver driver){
        return new HomePage(driver);
    }

    /**
     * Opens the page in a new Browser window
     * @param driver
     * @return
     */

    public static HomePage openPage(WebDriver driver){
        return new HomePage(driver, PAGE_URL);
    }

    public HomePageActionsController act(){
        return homePageActions;
    }

}