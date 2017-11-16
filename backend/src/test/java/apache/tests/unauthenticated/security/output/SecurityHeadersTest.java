package apache.tests.unauthenticated.security.output;

import apache.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SecurityHeadersTest extends AbstractTest {

    @BeforeClass
    public void sendAndGetResponse() throws IOException {
        response = clive.sendGet(BASE_API_URL  + RATE_LIMIT);
    }

    @Test
    public void xssProtectionIsSet(){

        String xssHeader = "X-XSS-Protection";
        String expectedHeaderValue = "1";

        String actualHeaderValue = rob.getHeaderValue(response, xssHeader).substring(0,1);
        String actualHeaderValue2 = rob.getHeaderValue(response, xssHeader).substring(0,1);

        assertEquals(actualHeaderValue, expectedHeaderValue);
        assertEquals(actualHeaderValue2, expectedHeaderValue);

    }

    @Test
    public void xFrameOptionsIsDenied(){

        String xssHeader = "X-Frame-Options";
        String expectedHeaderValue = "deny";

        String actualHeaderValue = rob.getHeaderValue(response, xssHeader);

        assertEquals(actualHeaderValue, expectedHeaderValue);
    }

    @Test
    public void contentSecurityPolicyIsDefaultSrc(){

        String header = "Content-Security-Policy";
        String expectedHeaderValue = "default-src 'none'";

        String actualHeaderValue = rob.getHeaderValue(response, header);

        assertEquals(actualHeaderValue, expectedHeaderValue);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void xPoweredByIsNotPresent(){

        String header = "X-Powered-By";

        rob.getHeaderValue(response, header);
    }

    @Test
    public void xContentTypeOptionsIsNoSniff(){
        String header = "X-Content-Type-Options";
        String expectedHeaderValue = "nosniff";

        String actualHeaderValue = rob.getHeaderValue(response, header);

        assertEquals(actualHeaderValue, expectedHeaderValue);
    }

    @Test
    public void hstsHeaderIsSet(){
        String header = "Strict-Transport-Security";

        String expectedHeaderValue = "includeSubdomains; preload";
        String actualHeaderValue = rob.getHeaderValue(response, header);

        boolean headerIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, expectedHeaderValue);

        assertTrue(headerIsPresent);
    }
}
