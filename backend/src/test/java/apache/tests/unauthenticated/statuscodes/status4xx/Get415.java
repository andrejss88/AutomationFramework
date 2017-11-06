package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class Get415 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;

    @Test
    public void xmlIsUnsupported() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/andrejss88");

        httpget.setHeader("Accept", "application/xml");
        httpget.setHeader("Content-type", "application/xml");

        response = client.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }
}
