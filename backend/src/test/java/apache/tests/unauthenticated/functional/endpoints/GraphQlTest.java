package apache.tests.unauthenticated.functional.endpoints;

import com.github.handlers.ResponseHandler;
import com.github.handlers.impl.DefaultResponseHandler;
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

public class GraphQlTest {

    private CloseableHttpResponse response;

    private ResponseHandler rob;


    @BeforeClass
    public void sendAndGetResponse() throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(BASE_API_URL  + "graphql");
        response = client.execute(httpget);

        rob = new DefaultResponseHandler();
    }

    @Test
    public void endpointReturns401(){

        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void gitHubMediaTypeIsV4(){

        String header = "X-GitHub-Media-Type";

        String expectedHeaderValue = "github.v4";
        String actualHeaderValue = rob.getValueForHeader(response, header);

        boolean headerIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, expectedHeaderValue);

        Assert.assertTrue(headerIsPresent);
    }

    @Test
    public void xRateLimitIsZero(){
        String actualHeaderValue = rob.getValueForHeader(response, "X-RateLimit-Limit");

        Assert.assertEquals(actualHeaderValue, "0");
    }

    @Test
    public void xRateLimitRemainingIsZero(){
        String actualHeaderValue = rob.getValueForHeader(response, "X-RateLimit-Remaining");

        Assert.assertEquals("0", actualHeaderValue);
    }

}
