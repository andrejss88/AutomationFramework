package apache.tests.unauthenticated.statuscodes.status4xx;

import apache.tests.unauthenticated.statuscodes.AbstractStatusCodeTest;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get404 extends AbstractStatusCodeTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_NOT_FOUND;

    @Test
    public void getTeamsWhenUnauthorized() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL  + "teams");
        response = client.execute(httpget);
        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);

    }
}
