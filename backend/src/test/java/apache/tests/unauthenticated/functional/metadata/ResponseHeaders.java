package apache.tests.unauthenticated.functional.metadata;

import apache.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.*;

/**
 * Tests Headers and their values that are sent with API response
 */
public class ResponseHeaders extends AbstractTest {

    private static final String VALID_ENDPOINT = "users/andrejss88";
    private static final String URL = BASE_API_URL  + VALID_ENDPOINT;

    private static final String CACHE_HEADER = "Cache-Control";
    private static final String ETAG_HEADER = "ETag";

    @BeforeClass
    public void sendAndGetResponse() throws IOException{ 
        response = clive.sendGet(URL);
    }

    @Test
    public void mediaTypeIsJson() {

        String jsonMimeType = "application/json";
        String actualMimeType = rob.getMimeType(response);

        assertEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void mediaTypeIsNotXml() {

        String jsonMimeType = "application/xml";
        String actualMimeType = rob.getMimeType(response);

        assertNotEquals( jsonMimeType, actualMimeType );
    }

    @Test
    public void charSetIsUtf8() {

        String expectedCharset = "utf-8";
        String actualCharset = rob.getCharSet(response);

        assertTrue(expectedCharset.equalsIgnoreCase(actualCharset), "Returned charset is not utf-8");
    }

    @Test(description = "'Cache-Control' tells clients to reuse some data from local cache" +
                        "this prevents new requests being resent all the way to the server" +
                        "helps both client and API performance")
    public void maxAgeIsOneMinute() {

        String actualHeaderValue = rob.getHeaderValue(response, CACHE_HEADER);

        boolean headerValueIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, "max-age=60");

        assertTrue(headerValueIsPresent);
    }

    @Test
    public void sMaxAgeIsOneMinute() {

        String actualHeaderValue = rob.getHeaderValue(response, CACHE_HEADER);

        boolean headerValueIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, "s-maxage=60");

        assertTrue(headerValueIsPresent);
    }

    @Test
    public void xGitHubRequestIdIsPresent(){

        String header = "X-GitHub-Request-Id";
        assertTrue(rob.headerIsPresent(response, header));
    }

    @Test
    public void eTagIsPresent(){

        assertTrue(rob.headerIsPresent(response, ETAG_HEADER));
    }

    @Test
    public void eTagIsDifferentOnSeparateRequests() throws IOException {

        String eTagValue = rob.getHeaderValue(response, ETAG_HEADER);

        CloseableHttpResponse response2 = clive.sendGet(BASE_API_URL);
        String eTagValue2 = rob.getHeaderValue(response2, ETAG_HEADER);

        assertNotEquals(eTagValue, eTagValue2);
    }
}
