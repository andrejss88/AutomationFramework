package com.github.pages.search.enums;

/**
 * Represents languages shown on the
 * right side to filter repos
 */
public enum SearchTab {
    REPOSITORIES("Repositories"),
    CODE("Code"),
    COMMITS("Commits"),
    ISSUES("Issues"),
    WIKIS("Wikis"),
    USERS("Users");


    private String value;

    SearchTab(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
