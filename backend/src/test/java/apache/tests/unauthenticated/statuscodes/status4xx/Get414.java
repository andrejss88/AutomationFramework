package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.unauthenticated.statuscodes.AbstractStatusCodeTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get414 extends AbstractStatusCodeTest{

    private static final int EXPECTED_STATUS = HttpStatus.SC_REQUEST_URI_TOO_LONG;


    @Test
    public void uriTooLong() throws IOException {
        String uri = RandomStringUtils.randomAlphabetic(5000);

        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/" + uri);
        response = client.execute(httpget);

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
