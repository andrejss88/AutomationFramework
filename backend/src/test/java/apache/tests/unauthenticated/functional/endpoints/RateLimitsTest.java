package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import com.github.entities.manuallycreated.RateLimit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;
import static com.github.factories.ClientFactory.getDefaultClient;

/**
 * Tests rate limits (a.k.a. how many calls you can make before getting temporarily banned :)
 */
public class RateLimitsTest extends AbstractTest {

    // Docs: https://developer.github.com/v3/rate_limit/

    private static final String LIMIT = "X-RateLimit-Limit";
    private static final String LIMIT_VALUE = "60"; // allowed number of requests per hour for queries other than "search"
    private static final String SEARCH_LIMIT_VALUE = "10";
    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";


    @Test
    public void xRateLimitValueIs60() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = client.execute(httpget);

        String actualHeaderValue = rob.getValueForHeader(response, LIMIT);

        System.out.println(actualHeaderValue);

        Assert.assertEquals(LIMIT_VALUE, actualHeaderValue);
    }


    @Test
    public void getRateLimitStatusDoesNotCount() throws IOException {

        CloseableHttpClient client = getDefaultClient();

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        CloseableHttpResponse response = client.execute(httpget);
        String hitsRemaining = rob.getValueForHeader(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        CloseableHttpResponse response2 = client.execute(httpget2);
        String hitsRemaining2 = rob.getValueForHeader(response2, LIMIT_REMAINING);

        Assert.assertEquals(hitsRemaining, hitsRemaining2);

        rob.closeResponse(response);
        rob.closeResponse(response2);
        client.close();
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = client.execute(httpget);

        RateLimit resource = rob.retrieveResourceFromResponse(response, RateLimit.class);

        String actualCoreLimit = resource.getCoreLimit();
        String actualSearchLimit = resource.getSearchLimit();

        Assert.assertEquals(actualCoreLimit, LIMIT_VALUE);
        Assert.assertEquals(actualSearchLimit, SEARCH_LIMIT_VALUE);
    }

    @Test
    public void searchRateLimitIs10() throws IOException {

        HttpGet httpget = new HttpGet(BASE_API_URL  + "search/repositories?q=");
        response = client.execute(httpget);

        String actualHeaderValue = rob.getValueForHeader(response, LIMIT);

        Assert.assertEquals(SEARCH_LIMIT_VALUE, actualHeaderValue);
    }

}
