package apache.tests.unauthenticated.security.output;

import apache.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class OptionsMethodTest extends AbstractTest{

    private String header = "Public-Key-Pins";

    @BeforeClass
    public void sendAndGetResponse() throws IOException {
        HttpOptions httpOptions = new HttpOptions(BASE_API_URL);
        response = client.execute(httpOptions);
    }

    @Test(description = "No exception thrown considered a successful test")
    public void pubicKeyPinsAreUsed() {

        rob.getValueForHeader(response,header);
    }

    @Test
    public void sha256IsUsedInKeyPins() {

        String expectedHeaderValue = "pin-sha256";
        String headerValue = rob.getValueForHeader(response, header);

        boolean valueIsPresent = StringUtils.containsIgnoreCase(headerValue, expectedHeaderValue);

        Assert.assertTrue(valueIsPresent);
    }
}
