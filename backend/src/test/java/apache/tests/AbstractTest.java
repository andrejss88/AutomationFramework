package apache.tests;

import com.github.utils.ResponseUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.github.factories.ClientFactory.getDefaultClient;

public class AbstractTest {

    protected CloseableHttpClient client;

    protected CloseableHttpResponse response;

    @BeforeClass
    public void before() {
        client = getDefaultClient();
    }

    @AfterClass
    public void after() throws IllegalStateException, IOException {
        ResponseUtils.closeResponse(response);
        client.close();
    }
}
