package apache.tests;

import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import static com.github.factories.ClientFactory.getDefaultClient;

public class AbstractTest {

    protected CloseableHttpClient client;

    protected CloseableHttpResponse response;

    protected ResponseHandler rob;

    @BeforeClass
    public void before() {
        client = getDefaultClient();

        rob = new DefaultResponseHandler();
    }

    @AfterClass
    public void after() throws IllegalStateException, IOException {
        rob.closeResponse(response);
        client.close();
    }
}
