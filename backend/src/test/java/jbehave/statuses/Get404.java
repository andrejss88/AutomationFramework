package jbehave.statuses;

import jbehave.AbstractStory;
import org.apache.http.HttpStatus;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get404 extends AbstractStory {

    private static final int EXPECTED_STATUS = HttpStatus.SC_NOT_FOUND;

    @Override
    public String storyName() {
        return "get404.story";
    }

    @Override
    public Object stepInstance() {
        return new Get404Steps();    }

    public class Get404Steps {

        String url;

        @Given("an endpoint that does not exist")
        public void givenBaseGitHubEndpoint(){
            url = BASE_API_URL + "teams";
        }

        @When("client app sends a GET request")
        public void clientAppSendsARequest() throws IOException {
            response = clive.sendGet(url);
        }

        @Then("github responds with 404 Error")
        public void thenGitHubRespondsWith401Error(){
            int code = rob.getStatusCode(response);
            Assert.assertEquals(code, EXPECTED_STATUS);
        }
    }
}
