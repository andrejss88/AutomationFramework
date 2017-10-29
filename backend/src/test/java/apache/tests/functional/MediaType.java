package apache.tests.functional;

import apache.tests.AbstractTest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static apache.Constants.BASE_API_URL;

public class MediaType extends AbstractTest{

    private final String jsonMimeType = "application/json";

    @Test
    public void mediaTypeIsJson() throws IOException{

        HttpGet httpget = new HttpGet(BASE_API_URL  + "users/andrejss88");
        response = instance.execute(httpget);

        String actualMimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        Assert.assertEquals( jsonMimeType, actualMimeType );

    }
}
