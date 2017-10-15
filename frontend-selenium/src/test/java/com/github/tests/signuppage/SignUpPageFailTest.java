package com.github.tests.signuppage;

import com.github.dataproviders.UserDetailsProvider;
import com.github.pages.common.CommonActions;
import com.github.tests.abstractpagetest.AbstractSignUpPageTest;
import org.testng.annotations.Test;

import javax.annotation.Resource;

public class SignUpPageFailTest extends AbstractSignUpPageTest {

    @Resource
    private CommonActions common;

    @Test(dataProvider = "getUserDetails", dataProviderClass = UserDetailsProvider.class)
    public void signUpStep1_withDataProvider(String userName, String email, String password) {

        common.act()
                .fillInNewUserDetails(userName, email, password);

        signUpPage.act()
                .clickCreateAccount();

        signUpPage.verify()
                .accountCreationFailed();
    }

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
