package org.chance;

import java.util.Random;
import java.util.stream.Collectors;

import org.chance.utils.Bool;
import org.chance.utils.TestRange;

public class Chance {

    long MAX_INT = 9007199254740992L;
    long MIN_INT = -MAX_INT;
    String NUMBERS = "0123456789";
    String CHARS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    String CHARS_UPPER = CHARS_LOWER.toUpperCase();
    String HEX_POOL  = NUMBERS + "abcdef";

    private Random random;

    public Chance() {
        this.random = new Random();

    }

    public Chance(String seed) {
        this.random = new Random(seed.hashCode());

    }

    public Chance(Random random) {
        this.random = random; 
    }
  

    private Options initOptions(Options options, Options defaults) {

        if(options == null) {
            return defaults;
        }

        else  {
            return options;

        }
    }

    // -- Basics --

    /**
     *  Return a random bool, either true or false
     *
     *  @param Options [Options.likelihood = 50] alter the likelihood of
     *    receiving a true or false value back.
     *  @throws RangeError if the likelihood is out of bounds
     *  @returns either true or false
     */
    public Boolean bool(Options options) {
        return Bool.bool(options, random);
    };

    /**
     *  Return a random bool, either true or false
     *  @return either true or false
     */
    public Boolean bool() {
        return bool(new Options.Builder().likelihood(50).build());
    }

    /**
     *  Return a random bool, either true or false
     *
     *  @param likelihood (0-100) alter the likelihood of
     *    receiving a true or false value back.
     *  @throws RangeError if the likelihood is out of bounds
     *  @return either true or false
     */
    public Boolean bool(Integer likelihood) {
        return bool(new Options.Builder().likelihood(likelihood).build());
    }

    /**
     *  Return a random character.
     *
     *  @param options [options={}] can specify a character pool or alpha,
     *    numeric, symbols and casing (lower or upper)
     *  @return a single random character
     */
    public String character(Options options) {

        String symbols = "!@#$%^&*()[]";
        String letters;
        String pool = "";
            
        
        if (options.casing() ==  Options.Casing.LOWER.value()) {
            letters = CHARS_LOWER;
        } else if (options.casing() == Options.Casing.UPPER.value()) {
            letters = CHARS_UPPER;
        } else {
            letters = CHARS_LOWER + CHARS_UPPER;
        }

        if (options.pool() != null) {
            pool = options.pool().stream().map(p -> p.toString()).collect(Collectors.joining());
        } else {
            if (options.alpha() != null) {
                pool.concat(letters);
            }
            if (options.numeric() != null) {
                pool.concat(NUMBERS);
            }
            if (options.symbols() != null) {
                pool.concat(symbols);
            }
        }

        // Options defaults = new Options.Builder().build();
        return String.valueOf(pool.charAt(1));
        // return pool.charAt(this.natural({max: (pool.length() - 1)}));
    };
        /**
     *  Return a random character.
     *
     *  @param options [options={}] can specify a character pool or alpha,
     *    numeric, symbols and casing (lower or upper)
     *  @return a single random character
     */

    /**
     *  Return a random integer
     *
     *  NOTE the max and min are INCLUDED in the range. So:
     *  chance.integer({min: 1, max: 3});
     *  would return either 1, 2, or 3.
     *
     *  @param {Object} [options={}] can specify a min and/or max
     *  @returns {Number} a single random integer number
     *  @throws {RangeError} min cannot be greater than max
     */
    public Integer integer(Options options) {

        // 9007199254740992 (2^53) is the max integer number in JavaScript
        // See: http://vq.io/132sa2j
        Options defaults = new Options.Builder().with(b -> {
            b.min = MIN_INT;
            b.max = MAX_INT;
        }).build();

        options = initOptions(options, defaults);

        TestRange.test(
            options.min() > options.max(), 
            "Chance: Min cannot be greater than Max."
        );

        
        return (int)Math.floor(this.random.nextFloat() * (options.max() - options.min() + 1) + options.min());
    };

}
