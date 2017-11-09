package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import com.github.entities.manuallycreated.RootUrl;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.MappingUtils.retrieveResourceFromResponse;

public class RootUrlTest extends AbstractTest {

    private RootUrl resource;

    @BeforeMethod
    public void getResponse() throws IOException{
        HttpGet httpget = new HttpGet(BASE_API_URL);
        response = client.execute(httpget);
        resource = retrieveResourceFromResponse(response, RootUrl.class);
    }

    @Test
    public void rootUserUrlIsCorrect(){
        Assert.assertEquals(resource.getCurrentUserUrl(), BASE_API_URL + "user");
    }

    @Test
    public void rootEmailsUrlIsCorrect(){
        Assert.assertEquals(resource.getEmailsUrl(), BASE_API_URL + "user/emails");
    }

    @Test
    public void rootEmojisUrlIsCorrect(){
        Assert.assertEquals(resource.getEmojisUrl(), BASE_API_URL + "emojis");
    }

    @Test
    public void rootEventsUrlIsCorrect(){
        Assert.assertEquals(resource.getEventsUrl(), BASE_API_URL + "events");
    }

    @Test
    public void rootHubUrlIsCorrect(){
        Assert.assertEquals(resource.getHubUrl(), BASE_API_URL + "hub");
    }


}
