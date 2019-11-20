package org.chance.utils;

public class StringHelpers { 

    public static  String capitalize(String word) {
        return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
    };
}