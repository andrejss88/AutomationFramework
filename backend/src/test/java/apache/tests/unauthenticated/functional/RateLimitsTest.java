package apache.tests.unauthenticated.functional;

import apache.tests.AbstractTest;
import com.github.entities.RateLimit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;
import static apache.Constants.RATE_LIMIT;
import static com.github.utils.UtilMethods.getValueForHeader;
import static com.github.utils.UtilMethods.retrieveResourceFromResponse;

/**
 * Tests rate limits (a.k.a. how many calls you can make before getting temporarily banned :)
 */
public class RateLimitsTest extends AbstractTest {

    // Docs: https://developer.github.com/v3/rate_limit/

    private static final String LIMIT = "X-RateLimit-Limit";
    private static final String LIMIT_VALUE = "60"; // allowed number of requests per hour for "search"
    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";


    @Test(invocationCount = 2)
    public void xRateLimitRemainingRemainsConstant() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = client.execute(httpget);

        String actualHeaderValue = getValueForHeader(response, LIMIT);

        System.out.println(actualHeaderValue);

        Assert.assertEquals(LIMIT_VALUE, actualHeaderValue);
    }


    @Test
    public void getRateLimitStatusDoesNotCount() throws IOException {

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        CloseableHttpResponse response = client.execute(httpget);
        String hitsRemaining = getValueForHeader(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        CloseableHttpResponse response2 = client.execute(httpget2);
        String hitsRemaining2 = getValueForHeader(response2, LIMIT_REMAINING);

        Assert.assertEquals(hitsRemaining, hitsRemaining2);
    }

    @Test
    public void correctRateLimitsAreSet() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = client.execute(httpget);

        RateLimit resource = retrieveResourceFromResponse(response, RateLimit.class);

        String actualCoreLimit = resource.getCoreLimit();
        String actualSearchLimit = resource.getSearchLimit();

        Assert.assertEquals(actualCoreLimit, "60");
        Assert.assertEquals(actualSearchLimit, "10");
    }

}
