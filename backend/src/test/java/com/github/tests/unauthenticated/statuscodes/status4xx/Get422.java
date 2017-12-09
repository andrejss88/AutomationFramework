package com.github.tests.unauthenticated.statuscodes.status4xx;

import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URLEncoder;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class Get422 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNPROCESSABLE_ENTITY;

    @DataProvider
    public static Object[][] unexpectedData() {
        return new Object[][]{
                {""},
                {" "},
                {"&"}
                // other
        };
    }

    @Test(description = "Unexpected data validation test", dataProvider = "unexpectedData")
    public void invalidDataReturns422(String invalidInput) throws IOException {

        String url = BASE_API_URL  + "search/repositories?q=" + URLEncoder.encode(invalidInput,"UTF-8");
        response = clive.sendGet(url);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
