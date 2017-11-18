package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import com.github.dataproviders.EndPointDataProviders;
import com.github.entities.manuallycreated.RateLimit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

/**
 * Tests rate limits (a.k.a. how many calls you can make before getting temporarily banned :)
 */
public class RateLimitsTest extends AbstractTest {

    // Docs: https://developer.github.com/v3/rate_limit/

    private static final String LIMIT = "X-RateLimit-Limit";
    private static final String LIMIT_VALUE = "60"; // allowed number of requests per hour for queries other than "search"
    private static final String SEARCH_LIMIT_VALUE = "10";
    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";

    private static final String RATE_LIMIT_URL = BASE_API_URL  + RATE_LIMIT;


    @Test
    public void xRateLimitValueIs60() throws IOException{

        response = clive.sendGet(RATE_LIMIT_URL);

        String actualHeaderValue = rob.getHeaderValue(response, LIMIT);

        assertEquals(LIMIT_VALUE, actualHeaderValue);
    }


    @Test
    public void getRateLimitStatusDoesNotCount() throws IOException {

        // Send 1st GET
        CloseableHttpResponse response = clive.sendGet(RATE_LIMIT_URL);
        String hitsRemaining = rob.getHeaderValue(response, LIMIT_REMAINING);

        // Send 2nd GET
        CloseableHttpResponse response2 = clive.sendGet(RATE_LIMIT_URL);
        String hitsRemaining2 = rob.getHeaderValue(response2, LIMIT_REMAINING);

        assertEquals(hitsRemaining, hitsRemaining2);

        rob.closeResponse(response);
        rob.closeResponse(response2);
    }

    @Test(description = "Sending an Options method (over https) to any endpoint does not incur Rate Limit penalty" +
                        "Absence of Header 'X-RateLimit-Limit' is considered a successful test ",

          dataProvider = "endPointsRequiringAuthorization", dataProviderClass = EndPointDataProviders.class )
    public void optionsNoPenalty(String endpoint) throws IOException {

        CloseableHttpResponse response = clive.sendOptions(BASE_API_URL + endpoint);

        assertFalse(rob.headerIsPresent(response,LIMIT));
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException{

        response = clive.sendGet(RATE_LIMIT_URL);

        RateLimit resource = rob.retrieveResourceFromResponse(response, RateLimit.class);

        String actualCoreLimit = resource.getCoreLimit();
        String actualSearchLimit = resource.getSearchLimit();

        assertEquals(actualCoreLimit, LIMIT_VALUE);
        assertEquals(actualSearchLimit, SEARCH_LIMIT_VALUE);
    }

    @Test
    public void searchRateLimitIs10() throws IOException {

        response = clive.sendGet(BASE_API_URL  + "search/repositories?q=");

        String actualHeaderValue = rob.getHeaderValue(response, LIMIT);

        assertEquals(SEARCH_LIMIT_VALUE, actualHeaderValue);
    }

}
