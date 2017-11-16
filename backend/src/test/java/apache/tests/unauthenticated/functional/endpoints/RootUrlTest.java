package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import com.github.entities.manuallycreated.RootUrl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class RootUrlTest extends AbstractTest {

    private RootUrl resource;

    @BeforeMethod
    public void getResponse() throws IOException{
        response = clive.sendGet(BASE_API_URL);
        resource = rob.retrieveResourceFromResponse(response, RootUrl.class);
    }

    @Test
    public void rootUserUrlIsCorrect(){
        assertEquals(resource.getCurrentUserUrl(), BASE_API_URL + "user");
    }

    @Test
    public void rootEmailsUrlIsCorrect(){
        assertEquals(resource.getEmailsUrl(), BASE_API_URL + "user/emails");
    }

    @Test
    public void rootEmojisUrlIsCorrect(){
        assertEquals(resource.getEmojisUrl(), BASE_API_URL + "emojis");
    }

    @Test
    public void rootEventsUrlIsCorrect(){
        assertEquals(resource.getEventsUrl(), BASE_API_URL + "events");
    }

    @Test
    public void rootHubUrlIsCorrect(){
        assertEquals(resource.getHubUrl(), BASE_API_URL + "hub");
    }


}
