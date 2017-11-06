package apache.tests;

import apache.utils.ResponseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import static com.github.factories.ClientFactory.getDefaultClient;

public class AbstractTest {

    protected CloseableHttpClient client;

    protected CloseableHttpResponse response;

    @BeforeMethod
    public void before() {
        client = getDefaultClient();
    }

    @AfterMethod
    public void after() throws IllegalStateException, IOException {
        ResponseUtil.closeResponse(response);
        client.close();
    }
}
