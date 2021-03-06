package org.chance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.math3.random.MersenneTwister;
import org.chance.types.Month;
import org.chance.utils.StringHelpers;
import org.chance.utils.TestingUtils;

public class Chance extends ChanceData {

    Integer MAX_INT = 2147483647;
    Integer MIN_INT = -MAX_INT;
    String NUMBERS = "0123456789";
    String CHARS_LOWER = "abcdefghijklmnopqrstuvwxyz";
    String CHARS_UPPER = CHARS_LOWER.toUpperCase();
    String HEX_POOL  = NUMBERS + "abcdef";
    String SYMBOLS = "!@#$%^&*()[]";

    private Supplier<Double> random;
    
    public Chance() {
        MersenneTwister mt = new MersenneTwister();

        this.random = () -> mt.nextDouble();
        
    }

    public Chance(String seed) {

        MersenneTwister mt = new MersenneTwister(seed.hashCode());
        this.random = () -> mt.nextDouble();

    }

    public Chance(Supplier<Double> fn) {

        this.random = fn;
    }

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
     * @see Options
     * @return Options - Builder for providing options to various methods
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
     *   chance.options()
     *         .option("likelihood", 35)
     * );
     * }
     * </pre>
     * @param options  valid options: Likelihood: Integer between 0 and 100
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
        
        return this.random.get() * 100 < likelihood; 

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

    // -- Text

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
     * Character randomCharacter = chance.character(options)
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
    public Character character(Options options) {

    
        String letters;
        String casing = options.getOrDefault("casing", "any", String.class);
        String pool = options.getOrDefault("pool", "", String.class);
        Boolean isAlpha = options.getOrDefault("alpha", false, Boolean.class);
        Boolean isNumeric = options.getOrDefault("numeric", false, Boolean.class);
        Boolean isSymbols = options.getOrDefault("symbols", false, Boolean.class);

        if (casing.equals("lower")) {
            letters = CHARS_LOWER;
        } else if (casing.equals("upper")) {
            letters = CHARS_UPPER;
        } else {
            letters = CHARS_LOWER + CHARS_UPPER;
        }

        if(!pool.isEmpty()) {

            
            return pool.charAt(
                this.natural(this.options().option("max", pool.length() - 1))
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

            if(pool.isEmpty()) {
                pool = letters + NUMBERS + SYMBOLS;

            }
            
            return pool.charAt(
                this.natural(this.options().option("max", pool.length() - 1))
            );
                
        }


    };
    
    /**
     *  Return a random character.
     *  @return a single random character
     *  
    */
    public Character character() {
        return character(this.options());
    }
    
    /**
     *  Return a random letter.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.options()
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
        String letter = this.character(this.options().option("pool", pool)).toString();
        String casing = options.getOrDefault("casing", "any", String.class);
        if (casing.equals("upper")) {
            letter = letter.toUpperCase();
        } else if (casing.equals("lower")) {
            letter = letter.toLowerCase();
        }

        return letter;
    }
    
    /**
     *  Return a random string.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.options()
     *   .option("length", 10)
     *   .option("pool", "abcdef");
     * 
     *String randomString = chance.string(options)
     * }
     * </pre>
     *  @param options can specify a character pool, length, or min, and/or max
     *  @return a random string
     *  @throws RangeException length cannot be less than zero
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
            .map(c -> c.toString())
            .collect(Collectors.joining(""));
    }

    /**
     *  Return a random string
     *  @return a string of random length
    */
    public String string() {
        return this.string(this.options());
    }
    
    /**
     *  Return a random paragraph.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.options()
     *   .option("sentences", 5);
     * 
     *String randomParagraph = chance.paragraph(options)
     * }
     * </pre>
     *  @param options can specify a number of sentences
     *  @return a random paragraph
    */
    public String paragraph(Options options) {

        Integer sentences = options.getOrDefault("sentences", null, Integer.class);

        if(sentences == null) {
            sentences = this.natural(this.options().option("min", 3).option("max", 7));
        }

        return this.n(this::sentence, sentences, this.options())
            .stream()
            .collect(Collectors.joining(" "));
    };

    /**
     *  Return a random paragraph.
     *  @return a random paragraph
    */
    public String paragraph() {
        
        return this.paragraph(this.options());
    }

    /**
     *  Return a random sentence.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.options()
     *   .option("words", 5);
     * 
     *String randomSentence = chance.sentence(options)
     * }
     * </pre>
     *  @param options can specify a number of words
     *  @return a random sentence
    */
    public String sentence(Options options) {

        Integer defaultWordCount = this.natural(this.options().option("min", 12).option("max", 18));
        Integer wordCount = options.getOrDefault("words", defaultWordCount, Integer.class);

        Collection<String> word_array = this.n(this::word, wordCount, this.options());

        String text = word_array.stream().collect(Collectors.joining(" "));

        text = this.capitalize(text);

        return text += ".";
    }

    /**
     * Returns a random sentence. 
     *  Example: 
     *  <pre> 
     * {@code 
     * String randomSentence = chance.sentence()
     * }
     * </pre>
     *  @return a random sentence
    */
    public String sentence() {

        return this.sentence(this.options());
    }

    /**
     *  Return a random syllable.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.options()
     *   .option("length", 5)
     *   .option("capitalize", true);
     * 
     *String randomSyllable = chance.syllable(options)
     * }
     * </pre>
     *  @param options can specify a length, or choose to capitalize the word
     *  @return a random syllable
    */
    public String syllable(Options options) {

        Integer length = options.getOrDefault("length", null, Integer.class);
        Boolean capitalize = options.getOrDefault("capitalize", null, Boolean.class);
        String consonants = "bcdfghjklmnprstvwz"; // consonants except hard to speak ones
        String vowels = "aeiou"; // vowels
        String all = consonants + vowels; // all
        String text = "";
        String chr = "";

        if(length == null) {
            length = this.natural(this.options().option("min", 2).option("max", 3));
        }

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                // First character can be anything
                chr = this.character(this.options().option("pool", all)).toString();
            } else if (consonants.indexOf(chr) == -1) {
                // Last character was a vowel, now we want a consonant
                chr = this.character(this.options().option("pool", consonants)).toString();
            } else {
                // Last character was a consonant, now we want a vowel
                chr = this.character(this.options().option("pool", vowels)).toString();
            }

            text += chr;
        }

        if (capitalize != null) {
            text = this.capitalize(text);
        }

        return text;
    }

    /**
     *  Return a random syllable.
     *  Example: 
     *  <pre> 
     * {@code 
     *String randomSyllable = chance.syllable()
     * }
     * </pre>
     *  @return a random syllable
    */
    public String syllable() {

        return this.syllable(this.options());
    }

   /**
     *  Return a random word.
     *  Example: 
     *  <pre> 
     * {@code 
     * Optiona options = chance.options()
     *   .option("syllables", 5)
     *   .option("capitalize", true);
     * 
     *String randomWord = chance.word(options)
     * }
     * </pre>
     *  @param options can specify number of syllables or length, but not both. or choose to capitalize the word
     *  @return a random word
    */
    public String word(Options options) {

        Integer syllables = options.getOrDefault("syllables", null, Integer.class);
        Integer length = options.getOrDefault("length", null, Integer.class);
        Boolean capitalize = options.getOrDefault("capitalize", null, Boolean.class);

        TestingUtils.test(
            syllables != null && length != null,
            "Chance: Cannot specify both syllables AND length."
        );

        if(syllables == null) {
            syllables = this.natural(this.options().option("min", 1).option("max", 3));
        }

        String text = "";

        if (length != null) {
            // Either bound word by length
            do {
                text += this.syllable();
            } while (text.length() < length);

            text = text.substring(0, length);
            
        } else {
            // Or by number of syllables
            for (int i = 0; i < syllables; i++) {
                text += this.syllable();
            }
        }

        if (capitalize != null) {
            text = this.capitalize(text);
        }

        return text;

    }

   /**
     *  Return a random word.
     *  Example: 
     *  <pre> 
     * {@code 
     *String randomWord = chance.word()
     * }
     * </pre>
     *  @return a random word
    */
    public String word() {
        return this.word(this.options());
    }
    
    // -- Numerics

    /**
     *  Return a random double number
     * <pre>
     * {@code  
     * chance.doub(chance.options()
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

        return new BigDecimal(min + (max - min ) * this.random.get())
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
     *  @throws RangeException min cannot be greater than max
    */
    public Integer integer(Options options) {
        Integer min = options.getOrDefault("min", MIN_INT, Integer.class);
        Integer max = options.getOrDefault("max", MAX_INT, Integer.class);

        TestingUtils.test(
            min > max, 
            "Chance: Min cannot be greater than Max."
        );

        return (int)Math.floor(this.random.get() * (max - min + 1) + min);
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
     *  Return a random floating number
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
     *  @return a single floating number
     *  @throws RangeException 
     *    min cannot be greater than max
    */
    public Float floating(Options options) {

        Integer min = options.getOrDefault("min", MIN_INT, Integer.class);
        Integer max = options.getOrDefault("max", MAX_INT, Integer.class);
        Integer precision = options.getOrDefault("precision", 7, Integer.class);

        TestingUtils.test(
            min > max, 
            "Chance: Min cannot be greater than Max."
        );

        return new BigDecimal(min + (max - min ) * this.random.get())
            .setScale(precision, RoundingMode.FLOOR)
            .floatValue();
    }

    /**
     *  Return a random floating number
     * <pre>
     * {@code  
     * Float rand = chance.floating();
     * }
     * </pre>     
     *  @return  a single floating number
    */
    public Float floating() {

        return this.floating(this.options());
    }

    /**
     *  Return a natural integer 0 - 2147483647
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
     *  Return a random prime number
     *
     *  NOTE the max and min are INCLUDED in the range.
     *
     *  @param options can specify a min and/or max
     *  @return Integer a single random prime number
     *  @throws RangeError min cannot be greater than max nor negative
     */
    public Integer prime(Options options) {

        Integer min = options.getOrDefault("min", 0, Integer.class);
        Integer max = options.getOrDefault("max", 10000, Integer.class);
        TestingUtils.test(min < 0, "Chance: Min cannot be less than zero.");
        TestingUtils.test(min > max, "Chance: Min cannot be greater than Max.");

        List<Integer> primes = this.primes().stream().collect(Collectors.toList());
        Integer lastPrime = primes.get(primes.size() -1 );

        if (max > lastPrime) {
            for (int i = lastPrime + 2; i <= max; ++i) {
                if (this.isPrime(i)) {
                    primes.add(i);
                }
            }
        }

        Collection<Integer> targetPrimes = primes
            .stream()
            .filter(p ->  p >= min && p <= max)
            .collect(Collectors.toList());

        return this.pickone(targetPrimes);
    }

    /**
     *  Return a random prime number
     *
     *  @return Integer a single random prime number
     */
    public Integer prime() {
        return this.prime(this.options());
    }
    
    // -- Location --
    public String address(Options options) {
        Options naturalOptions = this.options().option("min", 5).option("max", 2000);
        return this.natural(naturalOptions) + " " + this.street(options);
    }

    public String address() {

        return this.address(this.options());
    } 

    public Double altitude(Options options) {
        
        Integer fixed = options.getOrDefault("fixed", 5, Integer.class);
        Integer min = options.getOrDefault("min", 0, Integer.class);
        Integer max = options.getOrDefault("max", 8848, Integer.class);
        
        return this.doub(
            this.options()
            .option("fixed", fixed)
            .option("min", min)
            .option("max", max)
        );
    
    }

    public Double altitude() {
        return this.altitude(this.options());
    }

    public String street(Options options) {

        Boolean shortSuffix = options.getOrDefault("short_suffix", false, Boolean.class);
        String country = options.getOrDefault("country", "us", String.class);
        Integer syllables = options.getOrDefault("syllables", 2, Integer.class);

        String street = "";
        // options = initOptions(options, { country: 'us', syllables: 2 });
        // var     street;

        switch (country.toLowerCase()) {
            case "us":
                street = this.word(this.options().option("syllables", syllables));
                street = this.capitalize(street);
                street += " ";
                street += shortSuffix ?
                    this.streetSuffix(options).get("abbreviation") :
                    this.streetSuffix(options).get("name");
                break;
            case "it":
                street = this.word(this.options().option("syllables", syllables));
                street = this.capitalize(street);
                street = (shortSuffix ?
                    this.streetSuffix(options).get("abbreviation") :
                    this.streetSuffix(options).get("name")) + " " + street;
                break;
        }
        return street;
    }

    public String street() {
        return this.street(this.options());
    }

    public Map<String,String> streetSuffix(Options options) {

        String country = options.getOrDefault("country", "us", String.class);
        return this.pickone(this.streetSuffixes().get(country));
    }

    public Map<String,String> streetSuffix() {

        return this.streetSuffix(this.options());
    }  


    // -- Person --

    /**
     *  Return a random age.  Example:
     * <pre>
     * {@code  
     * Options options = chance.options()
     *   .option("type", "child");
     * 
     * Integer randomAge = chance.age(options);
     * }
     * </pre>
     *
     *  @param options can specify type (child, teen, adult, senior)
     *  @return a random age
    */
    public Integer age(Options options) {
        String type = options.getOrDefault("type", "", String.class);

        switch (type) {
            case "child":
                return this.natural(this.options().option("min", 0).option("max", 12));
            case "teen":
                 return this.natural(this.options().option("min", 13).option("max", 19));
            case "adult":
                return this.natural(this.options().option("min", 18).option("max", 65));
            case "senior":
                return this.natural(this.options().option("min", 65).option("max", 100));
            case "all":
                return this.natural(this.options().option("min", 0).option("max", 100));
            default:
                return this.natural(this.options().option("min", 18).option("max", 65));
        }

    };

    /**
     *  Return a random age.  Example:
     * <pre>
     * {@code  
     * Integer randomAge = chance.age();
     * }
     * </pre>
     *
     *  @return a random age
    */
    public Integer age() {

        return this.age(this.options());
    }

    /**
     *  Return a birthday.  Example:
     * <pre>
     * {@code  
     * Options options = chance.options()
     *   .option("type", "child");
     * 
     * Date randomBirthday = chance.birthday(options);
     * }
     * </pre>
     * Date would be a date between now and 18 years ago
     *  @param options can specify type (child, teen, adult, senior)
     *  @return a random birthday
    */
    public Date birthday(Options options) {

        Calendar cal = Calendar.getInstance();

        Integer age = this.age(options);

        String type = options.getOrDefault("type", null, String.class);
        
        cal.setTime(new Date());
        
        Integer currentYear = cal.get(Calendar.YEAR);

        if(type != null) {
            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(new Date());
            max.setTime(new Date());
            min.set(currentYear - age - 1, min.get(Calendar.MONTH), min.get(Calendar.DATE));
            max.set(currentYear - age, min.get(Calendar.MONTH), min.get(Calendar.DATE));
            Integer minTime = new BigDecimal(min.getTime().getTime()).intValue();
            Integer maxTime = new BigDecimal(max.getTime().getTime()).intValue();
            options = options.option("min", minTime).option("max", maxTime);
        }

        return this.date(options);
    }

    public Date birthday() {

        return this.birthday(this.options());
    }

    public String first(Options options) {

        String gender = options.getOrDefault("gender", this.gender(), String.class).toLowerCase();
        String nationality = options.getOrDefault("nationality", "en", String.class).toLowerCase();

        Map<String, Map<String, Collection<String>>> firstNames = this.firstNames();

        Collection<String> names = firstNames.get(gender).get(nationality);

        return this.pickone(names);
    };

    public String first() {

        return this.first(this.options());
    }
    
    public String gender() {

        return this.pickone(Arrays.asList("Male", "Female"));
    }

    public String last(Options options) {

        String nationality = options.getOrDefault("nationality", "en", String.class);
        return this.pickone(this.lastNames().get(nationality));
    }

    public String last() {
        
        return this.last(this.options());
    }

    
    
    public String name(Options options) {

        String name;
        String first = this.first(options);
        String last = this.last(options);

        Boolean hasMiddle = options.getOrDefault("middle", false, Boolean.class);
        Boolean hasMiddleInitial = options.getOrDefault("middle_initial", false, Boolean.class);

        if(hasMiddle) {
            name = first + " " + this.first(options) + " " + last;
        } else if(hasMiddleInitial) {
            String middleInitial = this.character(
                this.options()
                    .option("alpha", true)
                    .option("casing", "upper")
            ).toString();
            name = first + " " + middleInitial + ". " + last;
        } else { 
            name = first + " " + last;
        }

        return name;
    }

    public String name() {

        return this.name(this.options());
    }

    // -- Time

    public String ampm() {
        return this.bool() ? "am" : "pm";
    };

    public Date date(Options options) {

        Calendar calendar = Calendar.getInstance();

        Integer min = options.getOrDefault("min", null, Integer.class);
        Integer max = options.getOrDefault("max", null, Integer.class);

        if(min != null || max != null) {
            calendar.setTime(new Date(
                this.integer(this.options()
                    .option("min", min)
                    .option("max", max)
                )
            ));

            return calendar.getTime();

        } else {

            Integer month = options.getOrDefault("month", null, Integer.class);

            Month m = this.monthRaw();

            Integer daysInMonth = m.getDays();

            if (month != null) {

                daysInMonth = this.months()
                    .stream()
                    .collect(Collectors.toList())
                    .get(month)
                    .getDays();
            } 

            Integer dateYear = Integer.valueOf(this.year());
            Integer dateMonth = Integer.valueOf(m.getNumeric());
            Integer dateDay = this.natural(this.options().option("min", 1).option("max", daysInMonth));
            Integer dateHour = this.hour(this.options().option("twentyfour", true));
            Integer dateMinute = this.minute();
            Integer dateSecond = this.second();


            calendar = Calendar.getInstance();
            calendar.set(dateYear, dateMonth, dateDay, dateHour, dateMinute, dateSecond);
            
            return calendar.getTime();

        }
    }

    public Date date() {
        return this.date(this.options());
    }

    public Long hammertime() {
        return this.date().getTime();
    }

    public Integer hour(Options options) {

        Boolean isTwentyFour = options.getOrDefault("twentyfour", false, Boolean.class);

        Integer min = options.getOrDefault("min", isTwentyFour ? 0 : 1, Integer.class);
        Integer max = options.getOrDefault("max", isTwentyFour ? 23 : 12, Integer.class);

        TestingUtils.test(min < 0, "Chance: Min cannot be less than 0.");
        TestingUtils.test(isTwentyFour && max > 23, "Chance: Max cannot be greater than 23 for twentyfour option.");
        TestingUtils.test(!isTwentyFour && max > 12, "Chance: Max cannot be greater than 12.");
        TestingUtils.test(min > max, "Chance: Min cannot be greater than Max.");

        return this.natural(this.options().option("min", min).option("max", max));
    };

    public Integer hour() {
        return this.hour(this.options());
    }

    public Integer minute(Options options) {

        Integer min = options.getOrDefault("min", 1, Integer.class);
        Integer max = options.getOrDefault("max", 59, Integer.class);

        TestingUtils.test(min < 0, "Chance: Min cannot be less than 0.");
        TestingUtils.test(max > 59, "Chance: Max cannot be greater than 59.");
        TestingUtils.test(min > max, "Chance: Min cannot be greater than Max.");

        return this.natural(this.options().option("min", min).option("max", max));    
    }

    public Integer minute() {

        return this.minute(this.options());
    }

    public Month monthRaw(Options options) {
        Integer min = options.getOrDefault("min", 1, Integer.class);
        Integer max = options.getOrDefault("max", 12, Integer.class);

        TestingUtils.test(min < 1, "Chance: Min cannot be less than 1.");
        TestingUtils.test(max > 12, "Chance: Max cannot be greater than 12.");
        TestingUtils.test(min > max, "Chance: Min cannot be greater than Max.");

        return this.pickone(this.months());
        
    }

    public Month monthRaw() {
        
        return this.monthRaw(this.options());
    }

    public String month(Options options) {
        Integer min = options.getOrDefault("min", 1, Integer.class);
        Integer max = options.getOrDefault("max", 12, Integer.class);

        TestingUtils.test(min < 1, "Chance: Min cannot be less than 1.");
        TestingUtils.test(max > 12, "Chance: Max cannot be greater than 12.");
        TestingUtils.test(min > max, "Chance: Min cannot be greater than Max.");

        Month month = this.pickone(this.months());

        return month.getName();
        
    }

    public String month() {
        return this.month(this.options());
    }

    public Integer millisecond() {
        return this.natural(this.options().option("max", 999));
    }

    public Integer second() {
        return this.natural(this.options().option("max", 59));
    }

    public Integer timestamp() {

        Integer max = new BigDecimal(new Date().getTime() / 1000)
            .setScale(10, RoundingMode.FLOOR)
            .intValue();

        return this.natural(this.options().option("min", 1).option("max", max));

    }

    public TimeZone timezone() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(this.date());

        return cal.getTimeZone();
    }

    public String weekday(Options options) {

        Boolean isWeekdayOnly = options.getOrDefault("weekday_only", false, Boolean.class);
        Collection<String> days = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
        
        if(!isWeekdayOnly) {
            days.addAll(Arrays.asList("Saturday", "Sunday"));
        }

        return this.pickone(days);
    }

    public String weekday() {
        return this.weekday(this.options());
    }

    public String year(Options options) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Integer currentYear = calendar.get(Calendar.YEAR);

        Integer min = options.getOrDefault("min", currentYear, Integer.class);
        Integer max = options.getOrDefault("max", currentYear + 100, Integer.class); 
        
        return this.natural(this.options().option("min", min).option("max", max)).toString();
    }

    public String year() {
        return this.year(this.options());
    }

    // -- Helpers 


    public <T> T pickone(Collection<T> coll) {

        TestingUtils.test(
            coll.isEmpty(), 
            "Chance: Cannot pickone() from an empty collection"
        );
        Integer randInt = this.natural(this.options().option("max", coll.size() - 1));
        
        return new ArrayList<>(coll).get(randInt);   
    }


    // 
    // 
    // UTILS
    // 
    // 
    public String capitalize(String word) {
        return StringHelpers.capitalize(word);
    }
    
    private Boolean isPrime(Integer n) {
        if (n % 1  != 0 || n < 2) {
            return false;
        }
        if (n % 2 == 0) {
            return n == 2;
        }
        if (n % 3 == 0) {
            return n == 3;
        }
        double m = Math.sqrt(Double.valueOf(n));
        for (int i = 5; i <= m; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    };

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
