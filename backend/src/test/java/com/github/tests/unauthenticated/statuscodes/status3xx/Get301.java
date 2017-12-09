package com.github.tests.unauthenticated.statuscodes.status3xx;

import com.github.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.Constants.RATE_LIMIT;
import static org.testng.Assert.assertEquals;

public class Get301 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_MOVED_PERMANENTLY;
    private static final String OLD_URL = BASE_API_URL  + "repos/twitter/bootstrap";

    private CloseableHttpClient client;

    @BeforeMethod
    public void setUpClientWithDisabledRedirects(){
        client = getClient();
    }

    @Test
    public void permanentlyMovedRepo() throws IOException {

        response = client.execute(new HttpGet(OLD_URL));

        int actualStatus = rob.getStatusCode(response);

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    @Test
    public void plainHttpIsRedirected() throws IOException {

        response = client.execute( new HttpGet("http://api.github.com/" + RATE_LIMIT));

        int actualStatus = response.getStatusLine().getStatusCode();

        assertEquals(actualStatus, EXPECTED_STATUS);
    }

    private CloseableHttpClient getClient() {
        return HttpClientBuilder.create().disableRedirectHandling().build();
    }
}
