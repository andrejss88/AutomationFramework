package apache.tests.unauthenticated.functional;

import apache.tests.AbstractTest;
import com.github.entities.NotFoundMessage;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;
import static com.github.utils.UtilMethods.retrieveResourceFromResponse;

public class NotFoundMessageTest extends AbstractTest{

    @Test
    public void notFoundMessageIsCorrect() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + "nonExistentEndpoint");
        response = client.execute(httpget);

        NotFoundMessage resource = retrieveResourceFromResponse(response, NotFoundMessage.class);

        Assert.assertEquals(resource.getMessage(), "Not Found");
        Assert.assertEquals(resource.getDocumentationUrl(), "https://developer.github.com/v3");

    }
}
