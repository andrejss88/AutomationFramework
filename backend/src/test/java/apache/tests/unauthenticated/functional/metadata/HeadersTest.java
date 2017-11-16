package apache.tests.unauthenticated.functional.metadata;

import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class HeadersTest {

    private CloseableHttpResponse response;
    private ResponseHandler rob;

    private static final String VALID_ENDPOINT = "users/andrejss88";
    private static final String URL = BASE_API_URL  + VALID_ENDPOINT;

    @BeforeClass
    public void sendAndGetResponse() throws IOException{

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(URL);
        response = client.execute(httpget);
        rob = new DefaultResponseHandler();
    }

    @Test
    public void mediaTypeIsJson() {

        String jsonMimeType = "application/json";

        String actualMimeType = rob.getMimeType(response);
        Assert.assertEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void mediaTypeIsNotXml() {

        String jsonMimeType = "application/xml";

        String actualMimeType = rob.getMimeType(response);
        Assert.assertNotEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void charSetIsUtf8() {

        String expectedCharset = "utf-8";

        String actualCharset = rob.getCharSet(response);

//        Assert.assertEquals( expectedCharset, actualCharset ); // fails, because case-sensitive

        Assert.assertTrue(expectedCharset.equalsIgnoreCase(actualCharset), "Returned charset is not utf-8");
    }



    @AfterClass
    public void after() throws IllegalStateException, IOException {
        rob.closeResponse(response);
    }
}
