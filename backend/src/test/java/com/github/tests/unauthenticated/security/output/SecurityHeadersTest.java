package com.github.tests.unauthenticated.security.output;

import com.github.tests.AbstractTest;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;
import static org.testng.Assert.*;

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

    @Test(description = "Disallow rendering a page in a <frame>, <iframe> or <object>." +
                        "Protects against clickjacking attacks.")
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

    @Test(description = "'X-Powered-By' is information about the host server" +
                        " and may be useful for an attacker during reconnaissance")
    public void xPoweredByIsNotPresent(){

        String header = "X-Powered-By";

        assertFalse(rob.headerIsPresent(response, header));
    }

    @Test(description = "Without 'nosniff' clients (browsers) can ignore 'Content-Type' headers " +
                        "to guess and process the data using an implicit content type."+
                        "Getting user-created content could mean pulling in and executing malicious javascript injected by an attacker")
    public void xContentTypeOptionsIsNoSniff(){
        String header = "X-Content-Type-Options";
        String expectedHeaderValue = "nosniff";

        String actualHeaderValue = rob.getHeaderValue(response, header);

        assertEquals(actualHeaderValue, expectedHeaderValue);
    }

    @Test(description = "'HSTS' header prevents any communications from being sent over HTTP to the specified domain" +
                        "see https://www.owasp.org/index.php/HTTP_Strict_Transport_Security_Cheat_Sheet")
    public void hstsHeaderHasSubDomainsAndPreload(){
        String header = "Strict-Transport-Security";

        String expectedHeaderValue = "includeSubdomains; preload";
        String actualHeaderValue = rob.getHeaderValue(response, header);

        boolean headerValueIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, expectedHeaderValue);

        assertTrue(headerValueIsPresent);
    }

    @Test
    public void hstsHeaderMaxAgeIsOneYear() {
        String header = "Strict-Transport-Security";

        String oneYear = "max-age=31536000";
        String actualHeaderValue = rob.getHeaderValue(response, header);

        boolean headerValueIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, oneYear);

        assertTrue(headerValueIsPresent);
    }
}
