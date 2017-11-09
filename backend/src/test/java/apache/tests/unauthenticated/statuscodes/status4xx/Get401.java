package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.unauthenticated.statuscodes.AbstractStatusCodeTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get401 extends AbstractStatusCodeTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED;

    @DataProvider
    public static Object[][] endPointsRequiringAuthorization() {
        return new Object[][]{
                {"user"},
                {"authorizations"},
                {"notifications"}
        };
    }

    @Test(dataProvider = "endPointsRequiringAuthorization")
    public void getUserWhenUnauthorized(String endpoint) throws IOException{
        HttpGet httpget = new HttpGet(BASE_API_URL  + endpoint);
        response = client.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }


}
