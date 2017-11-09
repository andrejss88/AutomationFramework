package apache.tests.unauthenticated.statuscodes.status2xx;

import apache.tests.unauthenticated.statuscodes.AbstractStatusCodeTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get200 extends AbstractStatusCodeTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_OK;

    @Test
    public void baseUrlRequestReturns200() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL);
        response = client.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void searchRequestReturns200() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL + "search/repositories?q=java");

        response = client.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }

    @Test
    public void clientIsRedirected() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL  + "repos/twitter/bootstrap");
        response = client.execute(httpget);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, 200);
    }

}
