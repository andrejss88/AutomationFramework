package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.factories.ClientFactory.getDefaultClient;
import static com.github.utils.RequestHeaders.xmlContent;
import static org.testng.Assert.assertEquals;

public class Get415 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;

    @Test
    public void xmlIsUnsupported() throws IOException {

        response = clive.sendGetWithHeaders(BASE_API_URL  + "users/andrejss88", xmlContent);
        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void xmlIsUnsupportedWithPlainHttpClient() throws IOException {
        CloseableHttpClient client = getDefaultClient();
        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/andrejss88");

        httpget.setHeader(HttpHeaders.ACCEPT, "application/xml");
        httpget.setHeader(HttpHeaders.CONTENT_TYPE, "application/xml");

        response = client.execute(httpget);
        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }
}
