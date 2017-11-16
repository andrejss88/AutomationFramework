package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URLEncoder;

import static com.github.Constants.BASE_API_URL;

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

        HttpGet httpget = new HttpGet(BASE_API_URL  + "search/repositories?q=" + URLEncoder.encode(invalidInput,"UTF-8"));
        response = client.execute(httpget);

        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
