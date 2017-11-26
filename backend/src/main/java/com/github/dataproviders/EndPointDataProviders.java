package com.github.dataproviders;

import org.testng.annotations.DataProvider;

public class EndPointDataProviders {

    @DataProvider
    public static Object[][] endPointsRequiringAuthorization() {
        return new Object[][]{
                {"user"},
                {"authorizations"},
                {"notifications"}
                // etc
        };
    }

    @DataProvider
    public static Object[][] endPointsNotRequiringAuthorization() {
        return new Object[][]{
                {""}, // concatenates with base api url in the test
                {"users/andrejss88"},
                {"search/repositories?q=java"}
                // etc
        };
    }

}
