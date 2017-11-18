package apache.tests.unauthenticated.functional.metadata;

import apache.tests.AbstractTest;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class RequestHeaders extends AbstractTest {

    @Test
    public void invalidContentEncodingDoesNotBreakApi() throws IOException {

        Map<String, String> contentEncoding = new HashMap<>();
        contentEncoding.put(HttpHeaders.CONTENT_ENCODING, "my own very special encoding");

        CloseableHttpResponse response = clive.sendGetWithHeaders(BASE_API_URL, contentEncoding);

        assertEquals(rob.getStatusCode(response), HttpStatus.SC_OK);
    }

}
