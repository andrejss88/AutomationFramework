package apache.tests.statuscodes.unauthenticated.status4xx;

import apache.tests.statuscodes.AbstractStatusCodeTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class Get414 extends AbstractStatusCodeTest{

    private static final int EXPECTED_STATUS = 414;


    @Test
    public void uriTooLong() throws IOException {
        String uri = RandomStringUtils.randomAlphabetic(5000);

        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/" + uri);
        response = instance.execute(httpget);

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
