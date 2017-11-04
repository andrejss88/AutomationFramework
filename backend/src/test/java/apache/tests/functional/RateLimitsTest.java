package apache.tests.functional;

import apache.tests.AbstractTest;
import com.github.entities.RateLimit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;
import static com.github.utils.UtilMethods.getValueForHeaderJava8Way;
import static com.github.utils.UtilMethods.retrieveResourceFromResponse;
import static java.lang.Integer.parseInt;

/**
 * Tests rate limits (a.k.a. how many calls you can make before getting temporarily banned :)
 */
public class RateLimitsTest extends AbstractTest {

    // Docs: https://developer.github.com/v3/rate_limit/

    private static final String LIMIT = "X-RateLimit-Limit";
    private static final String LIMIT_VALUE = "60"; // allowed number of requests per hour for "search"
    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";
    private static final String RATE_LIMIT = "rate_limit";
    private static final String SEARCH = "search";

    @Test(invocationCount = 2)
    public void xRateLimitRemainingRemainsConstant() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = instance.execute(httpget);

        String actualHeaderValue = getValueForHeaderJava8Way(response, LIMIT);

        System.out.println(actualHeaderValue);

        Assert.assertEquals(LIMIT_VALUE, actualHeaderValue);
    }


    @Test
    public void getRateLimitStatusDoesNotCount() throws IOException {

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        CloseableHttpResponse response = instance.execute(httpget);
        String hitsRemaining = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        CloseableHttpResponse response2 = instance.execute(httpget2);
        String hitsRemaining2 = getValueForHeaderJava8Way(response2, LIMIT_REMAINING);

        Assert.assertEquals(hitsRemaining, hitsRemaining2);
    }

    @Test
    public void xRateLimitDecreases() throws IOException {

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + SEARCH);
        response = instance.execute(httpget);
        String hitsRemaining = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + SEARCH);
        response = instance.execute(httpget2);
        String hitsRemaining2 = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        int diff = parseInt(hitsRemaining) - parseInt(hitsRemaining2); // should be at least 1

        Assert.assertTrue(diff >= 1);
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = instance.execute(httpget);

        RateLimit resource = retrieveResourceFromResponse(response, RateLimit.class);

        String actualCoreLimit = resource.getCoreLimit();
        String actualSearchLimit = resource.getSearchLimit();

        Assert.assertEquals(actualCoreLimit, "60");
        Assert.assertEquals(actualSearchLimit, "10");
    }

}
