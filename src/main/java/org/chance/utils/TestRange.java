package org.chance.utils;

public class TestRange {
    public static void test(Boolean test, String errorMessage) throws IllegalArgumentException {
        if (test) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}