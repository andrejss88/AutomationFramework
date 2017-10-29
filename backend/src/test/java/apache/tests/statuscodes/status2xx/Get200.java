package apache.tests.statuscodes.status2xx;

import apache.tests.statuscodes.AbstractStatusCodeTest;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class Get200 extends AbstractStatusCodeTest {

    private static final int EXPECTED_STATUS = 200;

    @Test
    public void baseUrlRequestReturns200() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL);
        response = instance.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void searchRequestReturns200() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL + "search/repositories?q=java");

        response = instance.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }

}
