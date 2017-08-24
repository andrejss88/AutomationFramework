package com.github.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import static com.github.utils.ElementUtil.checkElementIsDisplayed;

public class SignUpPage extends AbstractGitHubPage{

    public static String PAGE_URL = BASE_URL + "join";

    @FindBy(className = "flash-error")
    private WebElement errorToolTip;

    @FindBy(id = "signup_button")
    private WebElement createAccountBtn;


    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage(WebDriver driver, String pageUrl) {
        this(driver);
        driver.get(pageUrl);
        PageFactory.initElements(driver, this);
    }

    public void softCheckSignUpFailed() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(this.isPageOpened(), "Could not assert Sign Up page is opened");
        softAssert.assertTrue(checkElementIsDisplayed(errorToolTip), "Could not assert that element: " + errorToolTip + " is displayed");
    }


    public void checkAccountCreationSuccessful() {
        Assert.assertFalse(checkElementIsDisplayed(createAccountBtn), "'Create Account' button should not be visible if account creation was successful");
    }

    public void checkAccountCreationFailed() {
        Assert.assertTrue(checkElementIsDisplayed(createAccountBtn), "'Create Account' button should remain visible if account creation failed");
    }


    public void clickCreateAccount() {
        createAccountBtn.click();
    }


}
