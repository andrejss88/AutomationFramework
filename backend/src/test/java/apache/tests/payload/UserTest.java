package apache.tests.payload;

import apache.tests.AbstractTest;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.entities.GitHubUser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class UserTest extends AbstractTest{

    @Test
    public void correctUserInfoIsRetrieved() throws IOException{

        String validUser = "andrejss88";
        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/"+ validUser);
        response = instance.execute(httpget);

        GitHubUser resource = retrieveResourceFromResponse(
                response, GitHubUser.class);
        Assert.assertEquals(validUser, resource.getLogin());
    }

    private static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {

        // Apache Http
        HttpEntity responseEntity = response.getEntity();
        String jsonFromResponse = EntityUtils.toString(responseEntity);

        // Jackson
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonFromResponse, clazz);
    }
}
