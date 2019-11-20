package org.chance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.random.MersenneTwister;
import org.chance.types.Month;
import org.chance.utils.StringHelpers;
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

        text = StringHelpers.capitalize(text);

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
                chr = this.character(this.options().option("pool", all));
            } else if (consonants.indexOf(chr) == -1) {
                // Last character was a vowel, now we want a consonant
                chr = this.character(this.options().option("pool", consonants));
            } else {
                // Last character was a consonant, now we want a vowel
                chr = this.character(this.options().option("pool", vowels));
            }

            text += chr;
        }

        if (capitalize != null) {
            text = StringHelpers.capitalize(text);
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
            text = StringHelpers.capitalize(text);
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
    
    // 
    // NUMERIC
    // 
    /////////////////////////////////////////////

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

    // -- Time

    private String ampm() {
        return this.bool() ? "am" : "pm";
    };

    public Date date(Options options) {
        String date_string;
        Date date;
        Boolean isAmerican = options.getOrDefault("american", true, Boolean.class);
        Boolean isString = options.getOrDefault("string", false, Boolean.class);

        Date min = options.getOrDefault("min", null, Date.class);
        Date max = options.getOrDefault("max", null, Date.class);
        // If interval is specified we ignore preset
        if(min != null || max != null) {
            date = new Date(
                this.integer(this.options()
                    .option("min", min.getTime())
                    .option("max", max.getTime())
                )
            );
        } else {
            Month m = this.monthRaw();
            Integer daysInMonth = m.getDays();

            if(options && options.month) {
                // Mod 12 to allow months outside range of 0-11 (not encouraged, but also not prevented).
                daysInMonth = this.get('months')[((options.month % 12) + 12) % 12].days;
            }

            options = initOptions(options, {
                year: parseInt(this.year(), 10),
                // Necessary to subtract 1 because Date() 0-indexes month but not day or year
                // for some reason.
                month: m.numeric - 1,
                day: this.natural({min: 1, max: daysInMonth}),
                hour: this.hour({twentyfour: true}),
                minute: this.minute(),
                second: this.second(),
                millisecond: this.millisecond(),
                american: true,
                string: false
            });

            date = new Date(options.year, options.month, options.day, options.hour, options.minute, options.second, options.millisecond);
        }

        if (options.american) {
            // Adding 1 to the month is necessary because Date() 0-indexes
            // months but not day for some odd reason.
            date_string = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
        } else {
            date_string = date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
        }

        // return options.string ? date_string : date;
        return null;
    }

    public Date date() {
        return this.date(this.options());
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
    
    // -- Data 

    private Collection<Month> months() {

        return Stream.of(
            new Month("January", "Jan", "01", 31),
            new Month("February", "Feb", "02", 28),
            new Month("March", "Mar", "03", 31),
            new Month("April", "Apr", "04", 30),
            new Month("May", "May", "05", 31),
            new Month("June", "Jun", "06", 30),
            new Month("July", "Jul", "07", 31),
            new Month("August", "Aug", "08", 31),
            new Month("September", "Sep", "09", 30),
            new Month("October", "Oct", "10", 31),
            new Month("November", "Nov", "11", 30),
            new Month("December", "Dec", "12", 31)
        ).collect(Collectors.toList());

    }
}
