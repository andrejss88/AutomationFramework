package com.github.jbehave.statuses;

import org.apache.http.HttpStatus;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get200 extends AbstractStory {

    private static final int EXPECTED_STATUS = HttpStatus.SC_OK;

    @Override
    public String storyName() {
        return "get200.story";
    }

    @Override
    public Object stepInstance() {
        return new Get200Steps();
    }

    public class Get200Steps {

        String url;

        @Given("base github endpoint")
        public void givenBaseGitHubEndpoint(){
            url = BASE_API_URL;
        }

        @When("client app sends a request")
        public void clientAppSendsARequest() throws IOException {
            response = clive.sendGet(url);
        }

        @Then("github responds with 200 OK")
        public void thenGitHubRespondsWith200Ok(){
            int code = rob.getStatusCode(response);
            Assert.assertEquals(code, EXPECTED_STATUS);
        }
    }
}
