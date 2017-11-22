package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.factories.ClientFactory.getDefaultClient;
import static org.testng.Assert.assertEquals;

/**
 * Test class showing native Apache HttpClient API
 * Solely for informational/demo purposes
 */
public class Get415PlainCode extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;
    private static final String URL = BASE_API_URL  + "users/andrejss88";

    @Test(description = "Same as in class Get415, but using a builder, still using the DefaultRequestHandler. Less than ideal.")
    public void xmlIsUnsupported2() throws IOException {

        HttpUriRequest request = clive.buildCustomRequest("GET")
                .setUri(URL)
                .setHeader(HttpHeaders.ACCEPT, "application/xml")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/xml")
                .build();

        response = clive.send(request);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test(description = "Same as in class Get415, but plain Apache Http code. " +
                        "This is the least desirable way, as it's verbose inconsistent with the rest of the client API.")
    public void xmlIsUnsupportedWithPlainHttpClient() throws IOException {
        CloseableHttpClient client = getDefaultClient();
        HttpGet httpget = new HttpGet(URL);

        httpget.setHeader(HttpHeaders.ACCEPT, "application/xml");
        httpget.setHeader(HttpHeaders.CONTENT_TYPE, "application/xml");

        response = client.execute(httpget);
        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
