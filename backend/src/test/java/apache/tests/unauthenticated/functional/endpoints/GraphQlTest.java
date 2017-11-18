package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GraphQlTest extends AbstractTest {

    @BeforeClass
    public void sendAndGetResponse() throws IOException {

        response = clive.sendGet(BASE_API_URL  + "graphql");
    }

    @Test
    public void endpointReturns401(){

        int actualStatus = rob.getStatusCode(response);

       assertEquals(actualStatus, HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void gitHubMediaTypeIsV4(){

        String header = "X-GitHub-Media-Type";

        String expectedHeaderValue = "github.v4";
        String actualHeaderValue = rob.getHeaderValue(response, header);

        boolean headerValueIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, expectedHeaderValue);

       assertTrue(headerValueIsPresent);
    }

    @Test
    public void xRateLimitIsZero(){
        String actualHeaderValue = rob.getHeaderValue(response, "X-RateLimit-Limit");

       assertEquals(actualHeaderValue, "0");
    }

    @Test
    public void xRateLimitRemainingIsZero(){
        String actualHeaderValue = rob.getHeaderValue(response, "X-RateLimit-Remaining");

       assertEquals("0", actualHeaderValue);
    }

}
