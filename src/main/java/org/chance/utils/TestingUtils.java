package org.chance.utils;

import java.util.function.Consumer;

import org.chance.exception.RangeException;

public class TestingUtils {
    public static void test(Boolean test, String errorMessage) throws RangeException {
        if (test) {
            throw new RangeException(errorMessage);
        }
    }
}