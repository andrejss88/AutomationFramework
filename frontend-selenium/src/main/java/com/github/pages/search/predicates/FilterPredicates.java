package com.github.pages.search.predicates;

import com.github.pages.search.enums.Language;

import java.util.function.Predicate;

public class FilterPredicates {

    public static Predicate<String> is(Language lang) {
        return s -> s.equalsIgnoreCase(lang.toString());
    }

    public static Predicate<String> isNot(Language lang) {
        return s -> !s.equalsIgnoreCase(lang.toString());
    }
}
