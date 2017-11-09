package apache.tests.unauthenticated.functional.endpoints;

import apache.tests.AbstractTest;
import com.github.entities.generated.Repo;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.MappingUtils.retrieveResourceFromResponse;

public class RepoTest extends AbstractTest {

    private static final String VALID_ENDPOINT = "repos/andrejss88/AutomationFramework";
    private static final String URL = BASE_API_URL  + VALID_ENDPOINT;

    private Repo testRepo;

    @BeforeClass
    public void sendAndGetResponse() throws IOException {

        CloseableHttpClient client = HttpClientBuilder.create().build();

        HttpGet httpget = new HttpGet(URL);
        response = client.execute(httpget);

        testRepo = retrieveResourceFromResponse(response, Repo.class);
    }

    @Test
    public void repoIsNotPrivate(){
        Assert.assertFalse(testRepo.getPrivate());
    }

    @Test
    public void fullNameIsMadeOfLoginAndRepoName(){

        String name = testRepo.getName();
        String owner = testRepo.getOwner().getLogin();

        String expectedFullName =  owner + "/" + name;

        Assert.assertEquals(testRepo.getFullName(), expectedFullName);

    }
}
