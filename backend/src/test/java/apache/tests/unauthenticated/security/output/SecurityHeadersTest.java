package apache.tests.unauthenticated.security.output;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;
import static com.github.utils.UtilMethods.getValueForHeader;
import static com.github.utils.UtilMethods.getValueForHeaderOldWay;

public class SecurityHeadersTest {

    private CloseableHttpResponse response;


    @BeforeClass
    public void sendAndGetResponse() throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(BASE_API_URL  + RATE_LIMIT);
        response = client.execute(httpget);
    }

    @Test
    public void xssProtectionIsSet(){

        String xssHeader = "X-XSS-Protection";
        String expectedHeaderValue = "1";

        String actualHeaderValue = getValueForHeaderOldWay(response, xssHeader).substring(0,1);
        String actualHeaderValue2 = getValueForHeader(response, xssHeader).substring(0,1);

        Assert.assertEquals(expectedHeaderValue, actualHeaderValue);
        Assert.assertEquals(expectedHeaderValue, actualHeaderValue2);

    }

    @Test
    public void xFrameOptionsIsDenied(){

        String xssHeader = "X-Frame-Options";
        String expectedHeaderValue = "deny";

        String actualHeaderValue = getValueForHeader(response, xssHeader);

        Assert.assertEquals(expectedHeaderValue, actualHeaderValue);
    }

    @Test
    public void contentSecurityPolicyIsDefaultSrc(){

        String header = "Content-Security-Policy";
        String expectedHeaderValue = "default-src 'none'";

        String actualHeaderValue = getValueForHeader(response, header);

        Assert.assertEquals(expectedHeaderValue, actualHeaderValue);
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void xPoweredByIsNotPresent(){

        String header = "X-Powered-By";

        getValueForHeader(response, header);
    }

    @Test
    public void xContentTypeOptionsIsNoSniff(){
        String header = "X-Content-Type-Options";
        String expectedHeaderValue = "nosniff";

        String actualHeaderValue = getValueForHeader(response, header);

        Assert.assertEquals(expectedHeaderValue, actualHeaderValue);
    }

    @Test
    public void hstsHeaderIsSet(){
        String header = "Strict-Transport-Security";

        String expectedHeaderValue = "includeSubdomains; preload";
        String actualHeaderValue = getValueForHeader(response, header);

        boolean headerIsPresent = StringUtils.containsIgnoreCase(actualHeaderValue, expectedHeaderValue);

        Assert.assertTrue(headerIsPresent);
    }
}
