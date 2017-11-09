package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.AbstractTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get422 extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNPROCESSABLE_ENTITY;

    @Test
    public void missingSearchKeyword() throws IOException {

        HttpGet httpget = new HttpGet(BASE_API_URL  + "search/repositories?q=");
        response = client.execute(httpget);

        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
