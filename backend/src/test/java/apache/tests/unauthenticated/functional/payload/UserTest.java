package apache.tests.unauthenticated.functional.payload;

import apache.tests.AbstractTest;
import com.github.entities.manuallycreated.User;
import com.github.utils.HttpHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static com.github.utils.HttpHelper.valueIsPresent;
import static org.testng.Assert.*;

public class UserTest extends AbstractTest{

    private static final String VALID_USER = "andrejss88";
    private static final String USER_URL = BASE_API_URL  + "users/" + VALID_USER;

    private User testUser;

    @BeforeClass
    public void sendAndGetResponse() throws IOException {
        
        response = clive.sendGet(USER_URL);
        testUser = rob.retrieveResourceFromResponse(response, User.class);
    }

    @Test
    public void userLoginIsCorrect(){
        
        assertEquals(testUser.getLogin(), VALID_USER);
    }

    @Test
    public void userIdIsCorrect(){

        String expectedUserId = "11834443";

        assertEquals(testUser.getId(), expectedUserId);
    }

    @Test(description = "this is an interesting one," +
            "we compare 'public_repos' value to number of ids found when querying for all repos of that user" +
            "through a different endpoint")
    public void publicRepoCountIsCorrect(){

        int publicRepoCount = Integer.valueOf(testUser.getPublicRepos());
        int repoIdCount = HttpHelper.getReposForUser(VALID_USER).length();

        assertEquals(publicRepoCount, repoIdCount);

    }

    @Test
    public void userHasRepoWithName() throws JSONException {

        JSONArray repos = HttpHelper.getReposForUser(VALID_USER);
        boolean repoExists = valueIsPresent(repos,"name", "LQuiz");

        assertTrue(repoExists);

    }

    @Test
    public void userHasNoPrivateRepos() throws JSONException {

        JSONArray repos = HttpHelper.getReposForUser(VALID_USER);
        boolean hasPrivateRepos = valueIsPresent(repos,"private", "true");

        assertFalse(hasPrivateRepos);
    }
}
