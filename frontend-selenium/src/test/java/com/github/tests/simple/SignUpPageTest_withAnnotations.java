package com.github.tests.simple;

import com.github.dataproviders.UserDetailsProvider;
import com.github.pages.SignUpPage;
import com.github.pages.common.CommonActions;
import com.github.tests.AbstractPageTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Class showing various TestNG annotations and their functionality
 */
public class SignUpPageTest_withAnnotations extends AbstractPageTest {

    SignUpPage signUpPage;
    CommonActions common;

    @BeforeMethod
    public void setup() {
        signUpPage = new SignUpPage(driver, SignUpPage.PAGE_URL);
        common = new CommonActions(driver);
    }

    /**
     * If the test is flaky for whatever reason,
     * invoke it several times
     */
    @Test(invocationCount = 2)
    public void checkSignUpPageStarts() {
        SignUpPage signInPage = new SignUpPage(driver, SignUpPage.PAGE_URL);
        Assert.assertTrue(signInPage.isPageOpened(), "Could not assert Sign Up Page opened");
    }

    // Will run if above passes
    @Test(dependsOnMethods = {"checkSignUpPageStarts"})
    public void signUpStep1() {
        common.fillInNewUserDetails("test", "test", "test");
        signUpPage.clickCreateAccount();
        signUpPage.checkAccountCreationSuccessful();
    }

    @Test(dependsOnMethods = {"signUpStep1"})
    public void fillInStep2() {
        // Some methods to test Step 2 of the registration
        System.out.println("But this will simply not run because signUpStep1 will fail");
    }

    @Test(dataProvider = "getUserDetails", dataProviderClass = UserDetailsProvider.class)
    public void signUpStep1_withDataProvider(String userName, String email, String password) {

        common.fillInNewUserDetails(userName, email, password);

        signUpPage.clickCreateAccount();
        signUpPage.checkAccountCreationFailed();
    }

    @Test(dataProvider = "loginData", dataProviderClass = UserDetailsProvider.class)
    public void signUpStep1_withCSVDataParse(String userName, String email, String password) {

        common.fillInNewUserDetails(userName, email, password);

        signUpPage.clickCreateAccount();
        signUpPage.checkAccountCreationFailed();
    }

}
