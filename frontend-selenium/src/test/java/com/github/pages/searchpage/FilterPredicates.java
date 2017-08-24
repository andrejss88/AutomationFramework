package com.github.pages.searchpage;

import java.util.function.Predicate;

public class FilterPredicates {

    public static Predicate<String> is(Language lang) {
        return s -> s.equalsIgnoreCase(lang.toString());
    }

    public static Predicate<String> isNot(Language lang) {
        return s -> !s.equalsIgnoreCase(lang.toString());
    }
}
