package apache.tests.unauthenticated.functional;

import apache.utils.ResponseUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class HeadersTest {

    private CloseableHttpResponse response;

    private static final String VALID_ENDPOINT = "users/andrejss88";
    private static final String URL = BASE_API_URL  + VALID_ENDPOINT;

    @BeforeClass
    public void sendAndGetResponse() throws IOException{

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(URL);
        response = client.execute(httpget);
    }

    @Test
    public void mediaTypeIsJson() {

        String jsonMimeType = "application/json";

        String actualMimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assert.assertEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void mediaTypeIsNotXml() {

        String jsonMimeType = "application/xml";

        String actualMimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assert.assertNotEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void charSetIsUtf8() {

        String expectedCharset = "utf-8";

        String actualCharset = ContentType.getOrDefault(response.getEntity()).getCharset().toString();

//        Assert.assertEquals( expectedCharset, actualCharset ); // fails, because case-sensitive

        Assert.assertTrue(expectedCharset.equalsIgnoreCase(actualCharset), "Returned charset is not utf-8");
    }


    @AfterClass
    public void after() throws IllegalStateException, IOException {
        ResponseUtil.closeResponse(response);
    }
}
