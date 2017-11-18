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
import static com.github.utils.RequestHeadersLists.xmlContentList;
import static com.github.utils.RequestHeadersMaps.xmlContentMap;
import static org.testng.Assert.assertEquals;

public class Get415 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE;
    private static final String URL = BASE_API_URL  + "users/andrejss88";

    @Test(description = "Use generic send request and pass in a map")
    public void xmlIsUnsupportedUseMap() throws IOException {

        response = clive.sendRequestWithHeaders(HttpGet.class, URL, xmlContentMap);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test(description = "Use generic send request and pass in a List of BasicHeaders defined by Apache")
    public void xmlIsUnsupportedUseList() throws IOException {

        response = clive.sendRequestWithHeaders(HttpGet.class, URL, xmlContentList);

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test(description = "Same as above, but using a builder, still using the DefaultRequestHandler. Less than ideal.")
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

    @Test(description = "Same as above, but plain Apache Http code. " +
                        "This is the least desirable way, as it's inconsistent with the rest of the client API.")
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
