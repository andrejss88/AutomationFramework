package com.github.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotFoundMessage {

    private String message;

    @JsonProperty("documentation_url")
    private String documentatioUrl;

    public String getMessage(){
        return message;
    }

    public String getDocumentationUrl(){
        return documentatioUrl;
    }

    // Jackson uses reflection to map Object fields <-> Json entities
    // -> https://stackoverflow.com/questions/22162916/how-does-the-jackson-mapper-know-what-field-in-each-json-object-to-assign-to-a-c
    // If it doesn't find an exact match with a getter method or the field (using Java convention), then it gives up - either throws an Exception or silently puts a null (if you instruct the ObjectMapper() to do so

    // so you can use @JsonProperty to tell the mapper what to look for exactly
    // -> https://stackoverflow.com/questions/29746303/how-to-map-json-fields-to-custom-object-properties

}

