package com.github.pages.searchpage;

public enum SortOptions {
    MOST_STARS("Most stars"),
    FEWEST_STARS("Fewest stars"),
    MOST_FORKS("Most forks");
    // etc...


    private String value;

    SortOptions(final String value) {
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
