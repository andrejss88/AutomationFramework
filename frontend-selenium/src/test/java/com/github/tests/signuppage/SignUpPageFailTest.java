package com.github.tests.signuppage;

import com.github.dataproviders.UserDetailsProvider;
import com.github.pages.common.CommonActions;
import com.github.tests.abstractpagetest.AbstractSignUpPageTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpPageFailTest extends AbstractSignUpPageTest {

    private CommonActions common;

    @BeforeMethod
    public void setup() {
        common = new CommonActions(driver);
    }

    // With hard-coded values
    @Test(dataProvider = "getUserDetails", dataProviderClass = UserDetailsProvider.class)
    public void signUpStep1_withDataProvider(String userName, String email, String password) {

        common.act()
                .fillInNewUserDetails(userName, email, password);

        signUpPage.act()
                .clickCreateAccount();

        signUpPage.verify()
                .accountCreationFailed();
    }

    // With values fetched from a CSV
    @Test(dataProvider = "loginData", dataProviderClass = UserDetailsProvider.class)
    public void signUpStep1_withCSVDataParser(String userName, String email, String password) {

        common.act()
                .fillInNewUserDetails(userName, email, password);

        signUpPage.act()
                .clickCreateAccount();

        signUpPage.verify()
                .accountCreationFailed();
    }

}
