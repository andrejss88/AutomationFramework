package apache.tests;

import apache.utils.ResponseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class AbstractTest {

    protected CloseableHttpClient client;

    protected CloseableHttpResponse response;

    @BeforeMethod
    public void before() {
        client = HttpClientBuilder.create().build();
    }

    @AfterMethod
    public void after() throws IllegalStateException, IOException {
        ResponseUtil.closeResponse(response);
    }
}
