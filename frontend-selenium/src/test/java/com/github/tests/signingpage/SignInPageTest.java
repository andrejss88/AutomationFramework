package com.github.tests.signingpage;

import com.github.dataproviders.UserDetailsProvider;
import com.github.tests.abstractpagetest.AbstractSignInPageTest;
import enums.Priority;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.github.utils.ElementUtil.checkElementEnabled;


public class SignInPageTest extends AbstractSignInPageTest {

    @Test(dataProvider = "signUpData", dataProviderClass = UserDetailsProvider.class)
    public void checkSignInFails(String name, String password){

        signInPage.act()
                .fillInUserDetails(name, password)
                .clickSignIn();


        signInPage.verify()
                .signUpFailedMessageShows()
                .userRemainsOnSamePage();
    }

    /**
     * Sign in button is actually always enabled,
     * so these tests are more for the purpose of code demonstration
     */

    @Test(priority = Priority.HIGH)
    public void checkSignBtnEnabled() {
        Assert.assertEquals(signInPage.getSignInBtn().isEnabled(), true, "Sign In button is disabled but should be enabled");
    }

    /**
     * Same, but with the help of a custom Util class.
     * Pro: more concise
     * Con: each additional abstraction = fail message is more vague
     */
    @Test(priority = Priority.HIGH)
    public void checkSignInBtnEnabled_alternativeWay() {
        checkElementEnabled(signInPage.getSignInBtn(), true);
    }

}