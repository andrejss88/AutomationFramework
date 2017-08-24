package com.github.tests.simple;

import com.github.pages.HomePage;
import com.github.pages.SignInPage;
import com.github.pages.SignUpPage;
import com.github.pages.common.CommonActions;
import com.github.pages.common.CommonHeaderElements;
import com.github.pages.searchpage.SearchPage;
import com.github.tests.AbstractPageTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageTest extends AbstractPageTest {

    CommonHeaderElements header;
    HomePage home;

    @BeforeMethod
    public void setup() {

        header = new CommonHeaderElements(driver);

        home = new HomePage(driver, HomePage.PAGE_URL);
    }

    @Test
    public void checkSignInBtnWorks() {
        // Arrange

        SignInPage signInPage = new SignInPage(driver);

        //Act
        header.clickOnHeaderSignIn();

        // Assert
        Assert.assertTrue(signInPage.isPageOpened(), "Could not assert Sign In Page opened");
    }

    @Test
    public void checkSignUpBtnWorks() {
        // Arrange
        SignUpPage signUpPage = new SignUpPage(driver);

        // Act
        header.clickOnHeaderSignUp();

        // Assert
        Assert.assertTrue(signUpPage.isPageOpened(), "Could not assert Sign Up Page opened");

    }

    @Test
    public void checkSearchWorks() {

        SearchPage searchPage = new SearchPage(driver);

        header.search("test repo");

        Assert.assertTrue(searchPage.isPageOpened(), "Could not assert Search Page opened");
    }

    @Test
    public void negativeSignUpTest() {

        SoftAssert soft = new SoftAssert();
        SignUpPage signUpPage = new SignUpPage(driver);
        CommonActions common = new CommonActions(driver);

        common.fillInNewUserDetails("", "email@email.com", "pass");
        home.clickSignUp();
        signUpPage.softCheckSignUpFailed();
        header.clickLogo();


        common.fillInNewUserDetails("name", "", "pass");
        home.clickSignUp();
        signUpPage.softCheckSignUpFailed();
        header.clickLogo();

        common.fillInNewUserDetails("name", "email", "");
        home.clickSignUp();
        signUpPage.softCheckSignUpFailed();

        // Will display the same fail assert message 3 times, separated by comma
        soft.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
