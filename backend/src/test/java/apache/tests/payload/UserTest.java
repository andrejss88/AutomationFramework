package apache.tests.payload;

import apache.tests.AbstractTest;
import com.github.entities.GitHubUser;
import org.apache.http.client.methods.HttpGet;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;
import static com.github.utils.UtilMethods.retrieveResourceFromResponse;

public class UserTest extends AbstractTest{

    @Test
    public void correctUserInfoIsRetrieved() throws IOException{

        String validUser = "andrejss88";
        String userId = "11834443";
        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/"+ validUser);
        response = instance.execute(httpget);

        GitHubUser resource = retrieveResourceFromResponse(
                response, GitHubUser.class);
        Assert.assertEquals(validUser, resource.getLogin());

        Assert.assertEquals(userId, resource.getId());
    }


}
