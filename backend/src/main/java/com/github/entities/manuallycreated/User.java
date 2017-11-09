package com.github.entities.manuallycreated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String login;

    private String id;

    @JsonProperty("public_repos")
    private String publicRepos;

    public String getLogin(){
        return login;
    }

    public String getId() {
        return id;
    }

    public String getPublicRepos() {
        return publicRepos;
    }
}
