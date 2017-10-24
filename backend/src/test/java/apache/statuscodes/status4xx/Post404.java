package apache.statuscodes.status4xx;

import apache.statuscodes.AbstractStatusCodeTest;
import org.apache.http.client.methods.HttpPost;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class Post404 extends AbstractStatusCodeTest {

    private static final int EXPECTED_STATUS = 404;

    // TODO: change - posting to baseUrl makes no sense
    @Test
    public void nonAuthenticatedPostReturns404() throws IOException {
        HttpPost httpPost = new HttpPost(BASE_API_URL);
        response = instance.execute(httpPost);
        int actualStatus = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }

}
