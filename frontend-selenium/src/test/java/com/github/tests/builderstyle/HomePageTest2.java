package com.github.tests.builderstyle;

import com.github.pages.HomePage;
import com.github.pages.SignUpPage;
import com.github.pages.common.CommonHeaderElements;
import com.github.tests.AbstractPageTest;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest2 extends AbstractPageTest {

    CommonHeaderElements header;
    HomePage home;

    @BeforeMethod
    public void setup() {

        header = new CommonHeaderElements(driver);

        home = new HomePage(driver, HomePage.PAGE_URL);
    }

    // Using the Builder Pattern
    @Test
    public void signIn_fails_using_BuilderPattern() {

        // Arrange
        SignUpPage signUpPage = new SignUpPage(driver);

        // Act
        home.enterUserName("user")
                .enterEmail("some@email.com")
                .enterPassword("password")
                .clickSignUp();

        // Assert
        Assert.assertTrue(signUpPage.isPageOpened(), "Could not assert Sign Up Page opened");

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
