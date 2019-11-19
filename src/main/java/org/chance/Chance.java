package org.chance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.MersenneTwister;
import org.chance.utils.TestingUtils;

public class Chance {

    Integer MAX_INT = 2147483647;
    Integer MIN_INT = -MAX_INT;
    String NUMBERS = "0123456789";
    String CHARS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    String CHARS_UPPER = CHARS_LOWER.toUpperCase();
    String HEX_POOL  = NUMBERS + "abcdef";
    String SYMBOLS = "!@#$%^&*()[]";

    private MersenneTwister random;

    public Chance() {
        this.random = new MersenneTwister();

    }

    public Chance(String seed) {
 
        this.random = new MersenneTwister(seed.hashCode());

    }

    /**
     *  Return the chance options builder.
     * Ex: 
     * <pre>
     * {@code 
     * Options options = chance.this.options()
     *   .option("min", 1)
     *   .option("max", 5);
     * 
     * Integer randomInt = chance.integer(options)
     * }
     * </pre>
     * 
     * @see Options
     *  @return Options - Builder for providing options to various methods
     */
    public Options options() {
        return new Options();
    }

    // -- Basics --

    /**
     *  Return a random bool, either true or false
     *  Example: 
     *  <pre> 
     * {@code 
     * Boolean randBool = chance.bool(
     *   chance.this.options()
     *         .option("likelihood", 35)
     * );
     * }
     * </pre>
     * @param options  valid options: 
     * <pre>
     * </br>
     * Likelihood: Integer between 0 and 100
     * </pre>
     * @see Options
     * @throws RangeException if the likelihood is out of bounds
     * @return either true or false
     */
    public Boolean bool(Options options) {
        Integer likelihood = options.getOrDefault("likelihood", 50, Integer.class);

        TestingUtils.test(
            likelihood < 0 || likelihood > 100,
            "Chance: Likelihood accepts values from 0 to 100."
        );
        
        return random.nextFloat() * 100 < likelihood; 

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

    // 
    // STRINGS
    // 
    /////////////////////////////////////////////

    /**
     *  Return a random character.
     *  Example: 
     *  <pre> 
     * {@code 
     * Options options = chance.this.options()
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
    public String character(Options options) {

    
        String letters;
        String casing = options.getOrDefault("casing", "any", String.class);
        String pool = options.getOrDefault("pool", "", String.class);
        Boolean isAlpha = options.getOrDefault("alpha", true, Boolean.class);
        Boolean isNumeric = options.getOrDefault("numeric", true, Boolean.class);
        Boolean isSymbols = options.getOrDefault("symbols", true, Boolean.class);

        if (casing.equals("lower")) {
            letters = CHARS_LOWER;
        } else if (casing.equals("upper")) {
            letters = CHARS_UPPER;
        } else {
            letters = CHARS_LOWER + CHARS_UPPER;
        }

        if(!pool.equals("")) {

            return String.valueOf(
                pool.charAt(
                    this.natural(this.options().option("max", pool.length() - 1))
                )
            ); 

        } else {

            if (isAlpha) {
                pool += letters;
            }
            if (isNumeric) {
                pool += NUMBERS;
            }
            if (isSymbols) {
                pool += SYMBOLS;
            }
            
            return String.valueOf(
                pool.charAt(
                    this.natural(this.options().option("max", pool.length() - 1))
                )
            );
        }


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
     *  Return a random letter.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.this.options()
     *   .option("pool", "abcd")
     *   .option("casing", "lower");
     * 
     *String randomLetter = chance.character(options)
     * }
     * </pre>
     * randomLetter would be one of "a","b","c","d"

     *  @param options can specify a character pool, and casing (lower | upper)
     * <pre>
     * {@code
     * pool: string;
     *casing: "lower" | "upper"
     * }
     *  
     *  @return a single random letter
    */
    public String letter(Options options) {

        String pool = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUWXYZ";
        String letter = this.character(this.options().option("pool", pool));
        String casing = options.getOrDefault("casing", "any", String.class);
        if (casing.equals("upper")) {
            letter = letter.toUpperCase();
        } else if (casing.equals("lower")) {
            letter = letter.toLowerCase();
        }

        return letter;
    }
    
    /**
     *  Return a random string
     *
     *  @param options [options={}] can specify a length or min and max
     *  @returns {String} a string of random length
     *  @throws {RangeError} length cannot be less than zero
    */
    public String string(Options options) {
        Integer min = options.getOrDefault("min", 5, Integer.class);
        Integer max = options.getOrDefault("max", 20, Integer.class);
        String defaultPool = CHARS_LOWER + CHARS_UPPER + NUMBERS + SYMBOLS;
        String pool = options.getOrDefault("pool", defaultPool, String.class);

        Integer defaultLength = this.natural(this.options().option("min", min).option("max", max));

        Integer length = options.getOrDefault("length", defaultLength, Integer.class);

        TestingUtils.test(length < 0, "Chance: Length cannot be less than zero.");
            
        Options characterOptions = this.options()
            .option("min", min)
            .option("max", max)
            .option("pool", pool);
        
        return this.n(this::character, length, characterOptions)
            .stream()
            .collect(Collectors.joining(""));
    }

    
    // 
    // NUMERIC
    // 
    /////////////////////////////////////////////

    /**
     *  Return a random double number
     * <pre>
     * {@code  
     * chance.doub(chance.this.options()
     *   .option("precision", 3)
     *   .option("min", 1)
     *   .option("max", 3)
     * );
     * }
     * </pre>     
     *  @param options can specify a fixed precision, min, max
     *  @return a single double number
     *  @throws RangeException 
     *    min cannot be greater than max
    */
    public Double doub(Options options) {

        Integer min = options.getOrDefault("min", MIN_INT, Integer.class);
        Integer max = options.getOrDefault("max", MAX_INT, Integer.class);
        Integer precision = options.getOrDefault("precision", 15, Integer.class);

        TestingUtils.test(
            min > max, 
            "Chance: Min cannot be greater than Max."
        );

        return new BigDecimal(min + (max - min ) * random.nextDouble())
            .setScale(precision, RoundingMode.FLOOR)
            .doubleValue();
    }

    /**
     *  Return a random double number
     * <pre>
     * {@code  
     * Double rand = chance.doub();
     * }
     * </pre>     
     *  @return  a single double number
    */
    public Double doub() {
        return doub(this.options()
            .option("precision", 15)
            .option("min", MIN_INT)
            .option("max", MAX_INT)
        );
    }

    /**
     *  Return a random integer
     *
     *  NOTE the max and min are INCLUDED in the range. So:
     * <pre>
     * {@code  
     * chance.integer(chance.this.options()
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
     *  @throws RangeException min cannot be greater than max
    */
    public Integer integer(Options options) {
        Integer min = options.getOrDefault("min", MIN_INT, Integer.class);
        Integer max = options.getOrDefault("max", MAX_INT, Integer.class);

        TestingUtils.test(
            min > max, 
            "Chance: Min cannot be greater than Max."
        );

        return (int)Math.floor(this.random.nextFloat() * (max - min + 1) + min);
    };

    /**
     *  Return a random integer
     *  @return a single random integer number
     *  @throws RangeException min cannot be greater than max
    */
    public Integer integer() {
        return integer(this.options().option("min", MIN_INT).option("max", MAX_INT));
    }

    /**
     *  Return a natural integer 0 - 2147483647
     *
     *  NOTE the max and min are INCLUDED in the range. So:
     * <pre>
     * {@code  
     * chance.integer(chance.this.options()
     *   .option("min",1)
     *   .option("max", 3)
     * );
     * }
     * </pre>
     * 
     *  would return either 1, 2, or 3.
     *
     *  @param options can specify a min and/or max
     *  @return a single random natural number
     *  @throws RangeException min cannot be greater than max
    */
    public Integer natural(Options options) {

        // TODO add excludes
        Integer min = options.getOrDefault("min", 0, Integer.class);
        Integer max = options.getOrDefault("max", MAX_INT, Integer.class);
        
        TestingUtils.test(min < 0, "Chance: Min cannot be less than zero.");


        return this.integer(this.options().option("min", min).option("max", max));
    };

    /**
     *  Return a natural integer 0 - 2147483647
     *  @return a single random natural number
    */
    public Integer natural() {
        return this.natural(this.options().option("min", 0).option("max", MAX_INT));
    }

     /**
     *  Gives a collection of n random terms
     *  @param <T> Desired collection type
     *  @param fn fn the function that generates something random
     *  @param n n number of terms to generate
     *  @param options options to be passed to fn
     *  @return Collection of length `n` with items generated by `fn`
    */
    public <T> Collection<T> n(Function<Options,T> fn, Integer n, Options options) {

        return Collections
            .nCopies(n, options)
            .stream()
            .map(fn)
            .collect(Collectors.toList());

    };
    
}
