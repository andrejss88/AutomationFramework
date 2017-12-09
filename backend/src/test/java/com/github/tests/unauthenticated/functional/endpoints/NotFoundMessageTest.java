package com.github.tests.unauthenticated.functional.endpoints;

import com.github.entities.manuallycreated.NotFoundMessage;
import com.github.tests.AbstractTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;

public class NotFoundMessageTest extends AbstractTest {

    @Test
    public void notFoundMessageIsCorrect() throws IOException{

        response = clive.sendGet(BASE_API_URL  + "nonExistentEndpoint");

        NotFoundMessage resource = rob.retrieveResourceFromResponse(response, NotFoundMessage.class);

        assertEquals(resource.getMessage(), "Not Found");
        assertEquals(resource.getDocumentationUrl(), "https://developer.github.com/v3");
    }
}
