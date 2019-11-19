package org.chance;


import org.apache.commons.math3.random.MersenneTwister;
import org.chance.utils.TestingUtils;


import java.math.BigDecimal;
import java.math.RoundingMode;

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
     *  Return a random letter.
     *  Example: 
     *  <pre> 
     * {@code 
     * Option options = chance.options()
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
    public String letter(Option options) {

        String pool = "abcdefghijklmnopqrstuvwxyz";
        String letter = this.character(options().option("pool", pool));
        if (options.getValue("casing").equals("upper")) {
            letter = letter.toUpperCase();
        }
        return letter;
    }



    
    // 
    // NUMERIC
    // 
    /////////////////////////////////////////////


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
     *  @throws RangeError min cannot be greater than max
     */
    public Integer integer() {
        return integer(this.options().option("min", MIN_INT).option("max", MAX_INT));
    }

    /**
     *  Return a random floating point number
     * <pre>
     * {@code  
     * chance.floating(chance.options()
     *   .option("precision", 3)
     *   .option("min", 1)
     *   .option("max", 3)
     * );
     * }
     * </pre>     
     *  @param options can specify a fixed precision, min, max
     *  @return a single floating point number
     *  @throws RangeError 
     *    min cannot be greater than max
     */
    public Double floating(Option options) {

        Integer min = options.getOrDefault("min", MIN_INT, Integer.class);
        Integer max = options.getOrDefault("max", MAX_INT, Integer.class);
        Integer precision = options.getOrDefault("precision", 15, Integer.class);

        TestingUtils.test(
            min > max, 
            "Chance: Min cannot be greater than Max."
        );

        return  new BigDecimal(min + (max - min ) * random.nextDouble())
            .setScale(precision, RoundingMode.FLOOR)
            .doubleValue();
    }

    /**
     *  Return a random floating point number
     * <pre>
     * {@code  
     * Double rand = chance.floating();
     * }
     * </pre>     
     *  @return  a single floating point number
     *  @throws RangeError min must be greater than max
     */
    public Double floating() {
        return floating(options()
            .option("precision", 15)
            .option("min", MIN_INT)
            .option("max", MAX_INT)
        );
    }

}
