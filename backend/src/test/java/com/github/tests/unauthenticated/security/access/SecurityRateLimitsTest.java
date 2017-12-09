package com.github.tests.unauthenticated.security.access;

import com.github.tests.AbstractTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static java.lang.Integer.parseInt;
import static org.testng.Assert.assertTrue;

/**
 * Rate Limits applied to unauthenticated Requests are useful against API abuse
 */
public class SecurityRateLimitsTest extends AbstractTest {

    private static final String LIMIT_REMAINING = "X-RateLimit-Remaining";
    private static final String SEARCH = "search";

    private static final String URL = BASE_API_URL  + SEARCH;

    @Test
    public void xRateLimitDecreases() throws IOException {

        // Send 1st GET
        response = clive.sendGet(URL);
        String hitsRemaining = rob.getHeaderValue(response, LIMIT_REMAINING);

        // Send 2nd GET
        response = clive.sendGet(URL);
        String hitsRemaining2 = rob.getHeaderValue(response, LIMIT_REMAINING);

        int diff = parseInt(hitsRemaining) - parseInt(hitsRemaining2); // should be at least 1

        assertTrue(diff >= 1);
    }
}
