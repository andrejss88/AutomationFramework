package com.github.dataproviders;

import org.testng.annotations.DataProvider;

import static com.dataprovider.CSVProvider.getCSVData;

public class UserDetailsProvider {

    @DataProvider
    public static Object[][] getUserDetails() {
        return new Object[][]{
                {"user1", "email1", "pass1"},
                {"user2", "email2", "pass2"}
        };
    }

    @DataProvider
    public static Object[][] loginData() {
        return getCSVData("loginDetails.csv");
    }

    @DataProvider
    public static Object[][] signUpData() {
        return getCSVData("signUpData.csv");
    }



}
