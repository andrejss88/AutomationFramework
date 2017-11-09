package com.github.entities.manuallycreated;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RootUrl {

    @JsonProperty("current_user_url")
    private String currentUserUrl;

    @JsonProperty("emails_url")
    private String emailsUrl;

    @JsonProperty("emojis_url")
    private String emojisUrl;

    @JsonProperty("events_url")
    private String eventsUrl;

    @JsonProperty("hub_url")
    private String hubUrl;

    // etc...

    public String getCurrentUserUrl(){
        return currentUserUrl;
    }

    public String getEmailsUrl() {
        return emailsUrl;
    }

    public String getEmojisUrl() {
        return emojisUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getHubUrl() {
        return hubUrl;
    }
}
