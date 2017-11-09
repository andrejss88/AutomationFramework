package com.github.entities.manuallycreated;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class RateLimit {

    private String coreLimit;
    private String searchLimit;

    public String getCoreLimit(){
        return coreLimit;
    }

    public String getSearchLimit(){
        return searchLimit;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("resources")
    private void unpackNested(Map<String,Object> resources) {
        Map<String,String> core = (Map<String,String>)resources.get("core");
        this.coreLimit = String.valueOf(core.get("limit"));

        Map<String,String> search = (Map<String,String>)resources.get("search");
        this.searchLimit = String.valueOf(search.get("limit"));
    }
}

