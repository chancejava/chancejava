package org.chance.utils;


import org.chance.exception.RangeException;

public class TestingUtils {
    public static void test(Boolean test, String errorMessage) throws RangeException {
        if (test) {
            throw new RangeException(errorMessage);
        }
    }
}