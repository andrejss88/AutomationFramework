package com.github.tests.signingpage;

import com.github.dataproviders.UserDetailsProvider;
import com.github.tests.abstractpagetest.AbstractSignInPageTest;
import com.github.enums.Priority;
import org.testng.annotations.Test;


public class SignInPageTest extends AbstractSignInPageTest {

    @Test(dataProvider = "signUpData", dataProviderClass = UserDetailsProvider.class, priority = Priority.HIGH)
    public void checkSignInFails(String name, String password){

        signInPage.act()
                .fillInUserDetails(name, password)
                .clickSignIn();


        signInPage.verify()
                .signUpFailedMessageShows()
                .userRemainsOnSamePage();
    }

}