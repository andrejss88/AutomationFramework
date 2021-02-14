package com;

import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest {

    @Test
    public void hamcrestExample() {

        assertThat(10, lessThan(11));
        assertThat(10, greaterThanOrEqualTo(10));

        assertThat("hello", containsStringIgnoringCase("eLLo"));

        assertThat(asList(1, 2, 3), containsInAnyOrder(2, 1, 3));

    }
}
