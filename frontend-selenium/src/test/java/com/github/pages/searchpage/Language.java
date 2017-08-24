package com.github.pages.searchpage;

/**
 * Represents languages shown on the
 * right side to filter repos
 */
public enum Language {
    JAVA("Java"),
    HTML("HTML"),
    PYTHON("Python"),
    JAVASCRIPT("JavaScript");


    private String value;

    Language(final String value) {
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
