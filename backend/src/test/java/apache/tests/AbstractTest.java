package apache.tests;

import com.github.handlers.RequestHandler;
import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultRequestHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public abstract class AbstractTest {

    protected CloseableHttpResponse response;

    protected RequestHandler clive;

    protected ResponseHandler rob;

    @BeforeClass
    public void before() {

        clive = new DefaultRequestHandler();
        rob = new DefaultResponseHandler();
    }

    @AfterClass
    public void after() throws IllegalStateException, IOException {
        rob.closeResponse(response);
        clive.close();
    }
}
