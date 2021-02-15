package com.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public String login;
    public int id;

//    public int publicRepos; // works
    @JsonProperty("public_repos")
    public int publicRepos; // works only with the annotation

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

//    public int getPublicRepos() {
//        return publicRepos;
//    }
}
