package apache.tests.functional;

import apache.tests.AbstractTest;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class RootUrl extends AbstractTest {


    // TODO: try to get the list of endpoints as described in the API
    //
    @Test
    public void rootUrlReturnsEndPoints() throws IOException {
        HttpGet httpget = new HttpGet(BASE_API_URL);
        response = instance.execute(httpget);
        HttpEntity entity = response.getEntity();

        System.out.println(entity);
    }

}
