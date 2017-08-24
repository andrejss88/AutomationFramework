package com.github.tests.simple;

import com.github.pages.SignInPage;
import com.github.tests.AbstractPageTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.utils.ElementUtil.checkElementEnabled;


public class SignInPageTest extends AbstractPageTest {

    SignInPage signInPage;

    @BeforeMethod
    public void setup() {
        signInPage = new SignInPage(driver, SignInPage.PAGE_URL);
    }

    /**
     * Sign in button is actually always enabled,
     * so these tests are more for the purpose of code demonstration
     */

    @Test
    public void checkSignBtnEnabled() {
        Assert.assertEquals(signInPage.getSignInBtn().isEnabled(), true, "Sign In button is disabled but should be enabled");
    }

    /**
     * Same, but with the help of a custom Util class.
     * Pro: more concise
     * Con: each additional abstraction = fail message is more vague
     */
    @Test
    public void checkSignInBtnEnabled_alternativeWay() {
        checkElementEnabled(signInPage.getSignInBtn(), true);
    }

}