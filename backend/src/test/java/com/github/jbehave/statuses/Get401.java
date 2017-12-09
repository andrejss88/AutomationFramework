package com.github.jbehave.statuses;

import org.apache.http.HttpStatus;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testng.Assert;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;

public class Get401 extends AbstractStory {

    private static final int EXPECTED_STATUS = HttpStatus.SC_UNAUTHORIZED;

    @Override
    public String storyName() {
        return "get401.story";
    }

    @Override
    public Object stepInstance() {
        return new Get401Steps();    }


    public class Get401Steps {

        String url;

        @Given("an endpoint requiring authentication")
        public void givenBaseGitHubEndpoint(){
            url = BASE_API_URL + "user";
        }

        @When("client app sends a GET request")
        public void clientAppSendsARequest() throws IOException {
            response = clive.sendGet(url);
        }

        @Then("github responds with 401 Error")
        public void thenGitHubRespondsWith401Error(){
            int code = rob.getStatusCode(response);
            Assert.assertEquals(code, EXPECTED_STATUS);
        }
    }
}
