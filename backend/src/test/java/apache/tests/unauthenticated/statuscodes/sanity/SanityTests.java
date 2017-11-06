package apache.tests.unauthenticated.statuscodes.sanity;

import apache.utils.ResponseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class SanityTests {

    private CloseableHttpClient client;

    private CloseableHttpResponse response;

    private HttpGet httpget = new HttpGet(BASE_API_URL);

    @BeforeMethod
    public final void before() {
        client = HttpClientBuilder.create().build();
    }

    @Test
    public void basicGetRequestNoExceptions() throws IOException {
        client.execute(httpget);
    }

    @AfterMethod
    public final void after() throws IllegalStateException, IOException {
        ResponseUtil.closeResponse(response);
    }

}
