package apache.tests.unauthenticated.functional.endpoints;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.HeaderUtils.getValueForHeader;

public class GraphQlTest {

    private CloseableHttpResponse response;


    @BeforeClass
    public void sendAndGetResponse() throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(BASE_API_URL  + "graphql");
        response = client.execute(httpget);
    }

    @Test
    public void endpointReturns401(){

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void gitHubMediaTypeIsV4(){

        String header = "X-GitHub-Media-Type";

        String expectedHeaderValue = "github.v4";
        String actualHeaderValue = getValueForHeader(response, header);

        boolean headerIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, expectedHeaderValue);

        Assert.assertTrue(headerIsPresent);
    }

    @Test
    public void xRateLimitIsZero(){
        String actualHeaderValue = getValueForHeader(response, "X-RateLimit-Limit");

        Assert.assertEquals("0", actualHeaderValue);
    }

    @Test
    public void xRateLimitRemainingIsZero(){
        String actualHeaderValue = getValueForHeader(response, "X-RateLimit-Remaining");

        Assert.assertEquals("0", actualHeaderValue);
    }

}
