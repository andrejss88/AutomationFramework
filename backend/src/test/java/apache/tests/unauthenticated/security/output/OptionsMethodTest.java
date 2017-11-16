package apache.tests.unauthenticated.security.output;

import apache.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertTrue;

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
