package org.chance.utils;

import java.util.Random;

import org.chance.Options;

public class Bool {
    public static Boolean bool(Options options, Random random) {
        TestRange.test(
            options.likelihood() < 0 || options.likelihood() > 100,
            "Chance: Likelihood accepts values from 0 to 100."
        );

        return random.nextFloat() * 100 < options.likelihood();
    };
}