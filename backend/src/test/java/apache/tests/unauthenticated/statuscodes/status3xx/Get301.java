package apache.tests.unauthenticated.statuscodes.status3xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;

public class Get301 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_MOVED_PERMANENTLY;
    private static final String OLD_URL = BASE_API_URL  + "repos/twitter/bootstrap";

    @Test
    public void permanentlyMovedRepo() throws IOException {

        CloseableHttpClient client = getClient();
        CloseableHttpResponse response = client.execute(new HttpGet(OLD_URL));

        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void plainHttpIsRedirected() throws IOException {

        CloseableHttpClient client = getClient();
        HttpGet httpGet = new HttpGet("http://api.github.com/" + RATE_LIMIT);
        CloseableHttpResponse response = client.execute(httpGet);

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }

    private CloseableHttpClient getClient() {
        return HttpClientBuilder.create().disableRedirectHandling().build();
    }
}
