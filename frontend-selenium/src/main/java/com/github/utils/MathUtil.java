package com.github.utils;

public class MathUtil {

    private static final double MIN_PRECISION = 0.0001;

    /**
     * Provides a safer way to compare double to avoid potential
     * floating point arithmetic errors. If the differences is minimal,
     * i.e. less than specified precision - then the values are considered equal
     * @param val1
     * @param val2
     * @return
     */
    public static boolean valuesAreEqual(double val1, double val2){
        return Math.abs(val1 - val2) < MIN_PRECISION;
    }
}
