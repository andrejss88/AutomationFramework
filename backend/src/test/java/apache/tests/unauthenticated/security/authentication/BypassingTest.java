package apache.tests.unauthenticated.security.authentication;

import apache.tests.AbstractTest;
import com.github.dataproviders.EndPointDataProviders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpHead;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class BypassingTest extends AbstractTest {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED;

    @Test(dataProvider = "endPointsRequiringAuthorization", dataProviderClass = EndPointDataProviders.class,
          description = "HEAD instead of GET doesn't allow to view content")
    public void headFailsOnSecureEndpoints(String endpoint) throws IOException {

        HttpHead httpHead = new HttpHead(BASE_API_URL  + endpoint);
        response = client.execute(httpHead);
        int actualStatus = rob.getStatusCode(response);

        Assert.assertEquals(actualStatus, EXPECTED_STATUS);
    }
}
