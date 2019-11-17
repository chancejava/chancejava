package org.chance;

import java.util.Random;

import org.chance.utils.TestRange;

import static org.chance.Option.chanceOptions;

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
  

    // -- Basics --

    /**
     *  Return a random bool, either true or false
     *
     *  @param Options [Options.likelihood = 50] alter the likelihood of
     *    receiving a true or false value back.
     *  @throws RangeError if the likelihood is out of bounds
     *  @returns either true or false
     */
    public Boolean bool(Option options) {

        Integer likelihood = (Integer)options.getValue("likelihood");

        TestRange.test(
            likelihood < 0 || likelihood > 100,
            "Chance: Likelihood accepts values from 0 to 100."
        );

        return random.nextFloat() * 100 < likelihood;    };

    /**
     *  Return a random bool, either true or false
     *  @return either true or false
     */
    public Boolean bool() {
        return bool(chanceOptions().option("likelihood", 50));
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
        return bool(chanceOptions().option("likelihood", likelihood));
    }

    /**
     *  Return a random character.
     *
     *  @param options [options={}] can specify a character pool or alpha,
     *    numeric, symbols and casing (lower or upper)
     *  @return a single random character
     */
    public String character(Option options) {

        String symbols = "!@#$%^&*()[]";
        String letters;
            
        String casing = (String)options.getValue("casing");
        String pool = (String)options.getOrDefault("pool", "");
        Boolean isAlpha = (Boolean)options.getOrDefault("alpha", true);
        Boolean isNumeric = (Boolean)options.getOrDefault("numeric", true);
        Boolean isSymbols = (Boolean)options.getOrDefault("symbols", true);

        if (casing.equals("lower")) {
            letters = CHARS_LOWER;
        } else if (casing.equals("upper")) {
            letters = CHARS_UPPER;
        } else {
            letters = CHARS_LOWER + CHARS_UPPER;
        }


        if (isAlpha) {
            pool.concat(letters);
        }
        if (isNumeric) {
            pool.concat(NUMBERS);
        }
        if (isSymbols) {
            pool.concat(symbols);
        }
        

        // Options defaults = new Options.Builder().build();
        return String.valueOf(pool.charAt(1));
        // return pool.charAt(this.natural({max: (pool.length() - 1)}));
    };
    /**
     *  Return a random character.
     *
     *  @param pool [pool="abcde"] can specify a character pool 
     *  @return a single random character
     */
    public String character(String pool) {
        return character(chanceOptions().option("pool", pool));
    }

    /**
     *  Return a random character.
     *
     *  @param alpha [alpha=true] can specify a alpha only characters
     *  @return a single random character
     */
    public String character(Boolean alpha) {
        return character(chanceOptions().option("alpha", alpha));
    }
    
    // /**
    //  *  Return a random integer
    //  *
    //  *  NOTE the max and min are INCLUDED in the range. So:
    //  *  chance.integer({min: 1, max: 3});
    //  *  would return either 1, 2, or 3.
    //  *
    //  *  @param {Object} [options={}] can specify a min and/or max
    //  *  @returns {Number} a single random integer number
    //  *  @throws {RangeError} min cannot be greater than max
    //  */
    // public Integer integer(Option options) {

    //     // 9007199254740992 (2^53) is the max integer number in JavaScript
    //     // See: http://vq.io/132sa2j
    //     Option defaults = new Options.Builder().with(b -> {
    //         b.min = MIN_INT;
    //         b.max = MAX_INT;
    //     }).build();

    //     options = initOptions(options, defaults);

    //     TestRange.test(
    //         options.min() > options.max(), 
    //         "Chance: Min cannot be greater than Max."
    //     );

        
    //     return (int)Math.floor(this.random.nextFloat() * (options.max() - options.min() + 1) + options.min());
    // };

}
