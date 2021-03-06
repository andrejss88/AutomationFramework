package com.github.tests.unauthenticated.functional.endpoints;

import com.github.entities.generated.Repo;
import com.github.tests.AbstractTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.Constants.BASE_API_URL;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class RepoTest extends AbstractTest {

    private static final String VALID_ENDPOINT = "repos/andrejss88/AutomationFramework";
    private static final String URL = BASE_API_URL  + VALID_ENDPOINT;

    private Repo testRepo;

    @BeforeClass
    public void sendAndGetResponse() throws IOException {
        response = clive.sendGet(URL);
        testRepo = rob.retrieveResourceFromResponse(response, Repo.class);
    }

    @Test
    public void repoIsNotPrivate(){
        assertFalse(testRepo.getPrivate());
    }

    @Test
    public void fullNameIsMadeOfLoginAndRepoName(){

        String name = testRepo.getName();
        String owner = testRepo.getOwner().getLogin();

        String expectedFullName =  owner + "/" + name;

        assertEquals(testRepo.getFullName(), expectedFullName);

    }
}
