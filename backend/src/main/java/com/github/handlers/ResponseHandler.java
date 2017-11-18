package com.github.handlers;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;

public interface ResponseHandler {

    String getProtocolVersion(CloseableHttpResponse response);

    int getStatusCode(CloseableHttpResponse response);

    String getMimeType(CloseableHttpResponse response);

    String getCharSet(CloseableHttpResponse response);

    /**
     * Find a header from the entire response entity.
     * Search is case-insensitive, see https://stackoverflow.com/questions/5258977/are-http-headers-case-sensitive
     * @param response: the response object to be parsed
     * @param headerName: the header to search for
     * @return the value of the header
     */

    String getHeaderValue(CloseableHttpResponse response, String headerName);

    boolean headerIsPresent(CloseableHttpResponse response, String headerName);

    /**
     * Use default (strict) ObjectMapper (that fails if something's wrong) to verify entity schema hasn't changed
     * @param response: response received from the API
     * @param clazz: The POJO to which to unmarshall Json
     * @return POJO filled with content from Json contained in the passed response
     * @throws IOException or UnrecognizedPropertyException
     */

    <T> T validateSchema(HttpResponse response, Class<T> clazz) throws IOException;

    /**
     * As above, but ignores unknown properties (they will be null in POJO) and carries on
     */

    <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException;

    void closeResponse(CloseableHttpResponse response) throws IOException;
}
