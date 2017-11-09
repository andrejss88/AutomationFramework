package apache.tests.unauthenticated.security.access;

import apache.tests.AbstractTest;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.UtilMethods.getValueForHeader;
import static java.lang.Integer.parseInt;

public class SecurityRateLimitsTest extends AbstractTest {

    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";
    private static final String SEARCH = "search";

    @Test
    public void xRateLimitDecreases() throws IOException {

        // Send 1st GET
        HttpGet httpget = new HttpGet(BASE_API_URL  + SEARCH);
        response = client.execute(httpget);
        String hitsRemaining = getValueForHeader(response, LIMIT_REMAINING);

        // Send 2nd GET
        HttpGet httpget2 = new HttpGet(BASE_API_URL  + SEARCH);
        response = client.execute(httpget2);
        String hitsRemaining2 = getValueForHeader(response, LIMIT_REMAINING);

        int diff = parseInt(hitsRemaining) - parseInt(hitsRemaining2); // should be at least 1

        Assert.assertTrue(diff >= 1);
    }
}
