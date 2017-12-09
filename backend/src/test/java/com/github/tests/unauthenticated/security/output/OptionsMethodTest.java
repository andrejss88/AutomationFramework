package com.github.tests.unauthenticated.security.output;

import com.github.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertTrue;

/**
 * PKPs tell a web client to associate a specific cryptographic public key with a certain web server
 * They are delivered via Headers and help prevent Man-In-The-Middle attack
 * More info @ https://developer.mozilla.org/en-US/docs/Web/HTTP/Public_Key_Pinning
 */
public class OptionsMethodTest extends AbstractTest {

    private String header = "Public-Key-Pins";

    @BeforeClass
    public void sendAndGetResponse() throws IOException {
        response = clive.sendOptions(BASE_API_URL);
    }

    @Test(description = "No exception thrown considered a successful test")
    public void pubicKeyPinsAreUsed() {

        rob.getHeaderValue(response,header);
    }

    @Test
    public void sha256IsUsedInKeyPins() {

        String expectedHeaderValue = "pin-sha256";
        String headerValue = rob.getHeaderValue(response, header);

        boolean valueIsPresent = StringUtils.containsIgnoreCase(headerValue, expectedHeaderValue);

        assertTrue(valueIsPresent);
    }
}
