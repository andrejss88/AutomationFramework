package apache.tests.unauthenticated.security.authentication;

import apache.tests.AbstractTest;
import com.github.factories.ClientFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static apache.Constants.BASE_API_URL;
import static java.util.Base64.getEncoder;

public class BasicAuthenticationTest extends AbstractTest {

    // https://stackoverflow.com/questions/3283234/http-basic-authentication-in-java-using-httpclient

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED; // 401
    private static final String SECURED_URL = BASE_API_URL + "authorizations";

    @Test
    public void incorrectDetailsFail_UsingCredentialsManager() throws IOException{

        CloseableHttpClient client = ClientFactory.getClientWithCredentials("dummyUser", "dummyPas");

        HttpResponse response = client.execute(new HttpGet(SECURED_URL));
        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, EXPECTED_STATUS);

    }

    @Test(description = "same as above, but using headers instead of HttpClient builder API")
    public void incorrectDetailsFail_UsingHeaders() throws IOException {

        HttpGet request = new HttpGet(SECURED_URL);
        String auth = "dummyUser:dummyPass";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader("Authorization", authHeader);

        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, EXPECTED_STATUS);

    }

    @Test(description = "same as above, but using a Java 8 encoder + Enum for the header")
    public void incorrectDetailsFail_UsingHeaders2() throws IOException {

        String encoding = getEncoder().encodeToString(("dummyUser2:randomPass2").getBytes());
        HttpGet request = new HttpGet(SECURED_URL);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);

        HttpResponse response = client.execute(request);

        int statusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(statusCode, EXPECTED_STATUS);
    }




}


