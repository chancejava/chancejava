package org.chance;

import java.util.Map;

public class Chance {

    long MAX_INT = 9007199254740992L;
    long MIN_INT = -MAX_INT;
    String NUMBERS = "0123456789";
    String CHARS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    String CHARS_UPPER = CHARS_LOWER.toUpperCase();
    String HEX_POOL  = NUMBERS + "abcdef";

    private RandomGenerator randomGenerator;
    private String seed; 

    public Chance() {

    }

    public Chance(String seed) {
        this.seed = seed;

    }

    public Chance(RandomGenerator randomGenerator) {
        this.randomGenerator = randomGenerator; 
    }
    
    private Long random() {
        return randomGenerator.generate();
    }

    private Options initOptions(Options options, Options defaults) {

        if(options == null) {
            return new Options(defaults);
        }

        else  {
            return new Options(options, defaults); 

        }
    }
    private void testRange(Boolean test, String errorMessage) throws IllegalArgumentException {
        if (test) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
        // -- Basics --

    /**
     *  Return a random bool, either true or false
     *
     *  @param {Object} [options={ likelihood: 50 }] alter the likelihood of
     *    receiving a true or false value back.
     *  @throws {RangeError} if the likelihood is out of bounds
     *  @returns {Bool} either true or false
     */
    public Boolean bool(Options options) {
        Options defaults = new Options.Builder().likelihood(50).build();
        options = initOptions(options, defaults);

        // Note, we could get some minor perf optimizations by checking range
        // prior to initializing defaults, but that makes code a bit messier
        // and the check more complicated as we have to check existence of
        // the object then existence of the key before checking constraints.
        // Since the options initialization should be minor computationally,
        // decision made for code cleanliness intentionally. This is mentioned
        // here as it's the first occurrence, will not be mentioned again.
        testRange(
            options.likelihood() < 0 || options.likelihood() > 100,
            "Chance: Likelihood accepts values from 0 to 100."
        );

        return this.random() * 100 < options.likelihood();
    };
}
