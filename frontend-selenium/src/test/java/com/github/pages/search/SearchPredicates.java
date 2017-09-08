package com.github.pages.search;

import com.google.common.collect.Ordering;

import java.util.List;
import java.util.function.Predicate;

public class SearchPredicates {

    public static Predicate<String> is(Language lang) {
        return s -> s.equalsIgnoreCase(lang.toString());
    }

    public static Predicate<String> isNot(Language lang) {
        return s -> !s.equalsIgnoreCase(lang.toString());
    }

    public static <T extends Comparable> boolean isSorted(List<T> list){
        boolean isSorted = Ordering.natural().reverse().isOrdered(list);
        return isSorted;
    }
}
