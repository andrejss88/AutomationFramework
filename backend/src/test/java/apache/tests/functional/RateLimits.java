package apache.tests.functional;

import apache.tests.AbstractTest;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;
import static com.github.utils.UtilMethods.getValueForHeaderJava8Way;
import static java.lang.Integer.parseInt;

/**
 * Tests rate limits (a.k.a. how many calls you can make before getting temporarily banned :)
 *
 */
public class RateLimits extends AbstractTest {

    // Docs: https://developer.github.com/v3/rate_limit/

    private static final String LIMIT = "X-RateLimit-Limit";
    private static final String LIMIT_VALUE = "60"; // allowed number of requests per hour for "search"
    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";

    // TODO: Add @BeforeMethod where we check for LIMIT_VALUE, and if it's < 1 then throw new SkipException();
    // UNLESS it's a test testing that the limit has indeed been reached

    @Test(invocationCount = 2)
    public void xRateLimitRemainingRemainsConstant() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + "search");
        response = instance.execute(httpget);

        String actualHeaderValue = getValueForHeaderJava8Way(response, LIMIT);

        System.out.println(actualHeaderValue);

        Assert.assertEquals(LIMIT_VALUE, actualHeaderValue);
    }


    @Test
    public void getRateLimitStatusDoesNotCount() throws IOException {

        String rateLimit = "rate_limit";

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + rateLimit);
        response = instance.execute(httpget);
        String hitsRemaining = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + rateLimit);
        response = instance.execute(httpget2);
        String hitsRemaining2 = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        Assert.assertEquals(hitsRemaining, hitsRemaining2);

    }

    @Test
    public void xRateLimitDecreases() throws IOException {

        String rateLimit = "search";

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + rateLimit);
        response = instance.execute(httpget);
        String hitsRemaining = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + rateLimit);
        response = instance.execute(httpget2);
        String hitsRemaining2 = getValueForHeaderJava8Way(response, LIMIT_REMAINING);

        int diff = parseInt(hitsRemaining) - parseInt(hitsRemaining2); // should be at least 1

        Assert.assertTrue(diff >= 1);
    }
}
