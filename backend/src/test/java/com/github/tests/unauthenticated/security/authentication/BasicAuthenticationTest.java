package com.github.tests.unauthenticated.security.authentication;

import com.github.factories.ClientFactory;
import com.github.tests.AbstractTest;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

import static com.github.Constants.BASE_API_URL;
import static com.github.factories.ClientFactory.getDefaultClient;
import static java.util.Base64.getEncoder;
import static org.testng.Assert.assertEquals;

/**
 * Usual flow for Basic Auth:
 * 1) Client -> Server (GET a resource)
 * 2) Server -> Client (401 Unauthorized + Header "WWW-Authenticate")
 * 3) Client -> Server (GET a resource with Header "Authorization:" Encode.base64(username + ":" + password)
 * https://stackoverflow.com/questions/3283234/http-basic-authentication-in-java-using-httpclient
 */
public class BasicAuthenticationTest extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED; // 401
    private static final String SECURED_URL = BASE_API_URL + "authorizations";

    private CloseableHttpClient client = getDefaultClient();

    @Test(description = "Incorrect Basic Auth credentials are rejected")
    public void incorrectDetailsFail_UsingCredentialsManager() throws IOException{

        CloseableHttpClient client = ClientFactory.getClientWithCredentials("dummyUser", "dummyPas");

        CloseableHttpResponse response = client.execute(new HttpGet(SECURED_URL));
        int statusCode = rob.getStatusCode(response);

        assertEquals(statusCode, EXPECTED_STATUS);

    }

    @Test(description = "Incorrect Basic Auth credentials are rejected - using headers instead of HttpClient builder API")
    public void incorrectDetailsFail_UsingHeaders() throws IOException {

        HttpGet request = new HttpGet(SECURED_URL);
        String auth = "dummyUser:dummyPass";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("ISO-8859-1")));
        String authHeader = "Basic " + new String(encodedAuth);
        request.setHeader("Authorization", authHeader);

        CloseableHttpResponse response = client.execute(request);

        int statusCode = rob.getStatusCode(response);

        assertEquals(statusCode, EXPECTED_STATUS);

    }

    @Test(description = "Incorrect Basic Auth credentials are rejected - using a Java 8 encoder + Enum for the header")
    public void incorrectDetailsFail_UsingHeaders2() throws IOException {

        String encoding = getEncoder().encodeToString(("dummyUser2:randomPass2").getBytes());
        HttpGet request = new HttpGet(SECURED_URL);
        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encoding);

        CloseableHttpResponse response = client.execute(request);

        int statusCode = rob.getStatusCode(response);

        assertEquals(statusCode, EXPECTED_STATUS);
    }




}


