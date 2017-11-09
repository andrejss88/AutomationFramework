package com.github.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

public class MappingUtils {

    /**
     * Use default (strict) ObjectMapper (that fails if something's wrong) to verify entity schema hasn't changed
     * @param response: response received from the API
     * @param clazz: The POJO to which to unmarshall Json
     * @return POJO filled with content from Json contained in the passed response
     * @throws IOException or UnrecognizedPropertyException
     */
    public static <T> T validateSchema(HttpResponse response, Class<T> clazz) throws IOException {

        String jsonFromResponse = getJson(response);

        return new ObjectMapper().readValue(jsonFromResponse, clazz);
    }

    /**
     * As above, but ignores unknown properties (they will be null in POJO) and carries on
     */
    public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {

        String jsonFromResponse = getJson(response);

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(jsonFromResponse, clazz);
    }

    private static String getJson(HttpResponse response) {
        Objects.requireNonNull(response); // Fail fast
        String jsonFromResponse = null;

        try {
            jsonFromResponse = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFromResponse;
    }
}
