package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import com.github.entities.manuallycreated.NotFoundMessage;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class NotFoundMessageTest extends AbstractTest {

    @Test
    public void notFoundMessageIsCorrect() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + "nonExistentEndpoint");
        response = client.execute(httpget);

        NotFoundMessage resource = rob.retrieveResourceFromResponse(response, NotFoundMessage.class);

        Assert.assertEquals(resource.getMessage(), "Not Found");
        Assert.assertEquals(resource.getDocumentationUrl(), "https://developer.github.com/v3");

    }
}
