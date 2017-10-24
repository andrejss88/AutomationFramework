package apache.statuscodes.status4xx;

import apache.statuscodes.AbstractStatusCodeTest;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class Get404 extends AbstractStatusCodeTest {

    private static final int EXPECTED_STATUS = 404;

    @Test
    public void getTeamsWhenUnauthorized() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL  + "teams");
        response = instance.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }
}
