package org.chance;


import org.apache.commons.math3.random.MersenneTwister;
import org.chance.utils.TestingUtils;

import static org.chance.Option.chanceOptions;

import java.util.stream.Stream;

public class Chance {

    long MAX_INT = 9007199254740992L;
    long MIN_INT = -MAX_INT;
    String NUMBERS = "0123456789";
    String CHARS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    String CHARS_UPPER = CHARS_LOWER.toUpperCase();
    String HEX_POOL  = NUMBERS + "abcdef";

    private MersenneTwister random;

    public Chance() {
        this.random = new MersenneTwister();

    }

    public Chance(String seed) {
 
        this.random = new MersenneTwister(seed.hashCode());

    }


 
    // TODO revisit Options class.
    /**
     *  Return the chance options builder.
     * Ex: 
     * <pre>
     * {@code 
     * Options options = chance.options()
     *   .option("min", 1)
     *   .option("max", 5);
     * 
     * Integer randomInt = chance.integer(options)
     * }
     * </pre>
     * 
     * @see Option
     *  @return Option - Builder for providing options to various methods
     */
    public Option options() {
        return new Option();
    }
    // -- Basics --

    /**
     *  Return a random bool, either true or false
     *  Example: 
     *  <pre> 
     * {@code 
     * Boolean randBool = chance.bool(
     *   chance.options()
     *         .option("likelihood", 35)
     * );
     * }
     * </pre>
     * @param options  valid options: 
     * <pre>
     * </br>
     * Likelihood: Integer between 0 and 100
     * </pre>
     * @see Option
     * @throws RangeError if the likelihood is out of bounds
     * @return either true or false
     */
    public Boolean bool(Option options) {

        try { 

            Integer likelihood = options.getValueAsInt("likelihood");
            TestingUtils.test(
                likelihood < 0 || likelihood > 100,
                "Chance: Likelihood accepts values from 0 to 100."
            );
            
            return random.nextFloat() * 100 < likelihood; 

        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Chance: Likelihood must be an integer");
        }
    };

    /**
     *  Return a random bool, either true or false
     *  Example: 
     *  <pre> 
     * {@code 
     * Boolean randBool = chance.bool()
     * }
     * </pre>
     *  @return either true or false
     */
    public Boolean bool() {
        return bool(this.options().option("likelihood", 50));
    }
    /**
     *  Return a random character.
     *  Example: 
     *  <pre> 
     * {@code 
     * Options options = chance.options()
     *   .options("casing", "lower")
     *   .option("alpha", true)
     *   .option("symbols", true);
     * 
     * String randomCharacter = chance.character(options)
     * }
     * </pre>
     *  @param options can specify a character pool or alpha,
     *    numeric, symbols and casing
     * <pre>
     * {@code
     * pool: string;
     * alpha: true | false;
     * numeric: true | false;
     * casing: "lower" | "upper"
     * }
     *  
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
     *  @return a single random character
     *  
     */
    public String character() {
        return character(this.options());
    }
    
    /**
     *  Return a random integer
     *
     *  NOTE the max and min are INCLUDED in the range. So:
     * <pre>
     * {@code  
     * chance.integer(chance.options()
     *   .option("min",1)
     *   .option("max", 3)
     * );
     * }
     * </pre>
     * 
     *  would return either 1, 2, or 3.
     *
     *  @param options can specify a min and/or max
     *  @return a single random integer number]
     *  @throws {RangeError} min cannot be greater than max
     */
    public Integer integer(Option options) {
        Integer min;
        Integer max;

        try {
            min = options.getValueAsInt("min");
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Chance: Min must be an integer");
        }

        try {
            max = options.getValueAsInt("max");
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Chance: Max must be an integer");
        }

        TestingUtils.test(
            min > max, 
            "Chance: Min cannot be greater than Max."
        );

        return (int)Math.floor(this.random.nextFloat() * (max - min + 1) + min);

    
    };
    /**
     *  Return a random integer
     *  @return a single random integer number
     *  @throws RangeError min cannot be greater than max
     */
    public Integer integer() {
        return integer(this.options().option("min", MIN_INT).option("max", MAX_INT));
    }


}
