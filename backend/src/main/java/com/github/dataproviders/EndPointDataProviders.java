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

}
