package com.github.pages.user.enums;

/**
 * Represents languages shown on the
 * right side to filter repos
 */
public enum UserTab {
    OVERVIEW("Overview"),
    REPOSITORIES("Repositories"),
    STARS("Stars"),
    FOLLOWERS("Followers"),
    FOLLOWING("Following");

    private String value;

    UserTab(final String value) {
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
