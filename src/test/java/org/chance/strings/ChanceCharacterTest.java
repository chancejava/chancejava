package org.chance.strings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.regex.Pattern;

import org.chance.Chance;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;

public class ChanceCharacterTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void returnsCharacter() {
        assertEquals(chance.character().getClass(), Character.class);
    }

    @Test
    public void usesPoolWhenProvided() {
        String pool = "abcdefg";

        Collections.nCopies(1000, null).forEach(x -> {
            Character character = chance.character(chance.options().option("pool", pool));
    
            assertThat("character is from pool", pool, StringContains.containsString(character.toString()));
        });

    }
    @Test
    public void allowsAlphaOnly() {

        Collections.nCopies(5000, null).forEach(x -> {
            Character character = chance.character(chance.options().option("alpha", true));
            Boolean match = Pattern.compile("[a-zA-Z]").matcher(character.toString()).matches();
            assertTrue("character is alpha only", match);
        });
    }

    @Test
    public void allowsAlphaNumericOnly() {

        Collections.nCopies(5000, null).forEach(x -> {
            Character character = chance.character(chance.options().option("alpha", true).option("numeric", true));
            Boolean match = Pattern.compile("[a-zA-Z0-9]").matcher(character.toString()).matches();
            assertTrue("character is alpha only", match);
        });
    }

    @Test
    public void obeysUpperCase() {

        Collections.nCopies(5000, null).forEach(x -> {
            Character character = chance.character(chance.options().option("alpha", true).option("casing", "upper"));
            Boolean match = Pattern.compile("[A-Z]").matcher(character.toString()).matches();
            assertTrue("character is alpha only", match);
        });
    }
    
    @Test
    public void obeysLowerCase() {

        Collections.nCopies(5000, null).forEach(x -> {
            Character character = chance.character(chance.options().option("alpha", true).option("casing", "lower"));
            Boolean match = Pattern.compile("[a-z]").matcher(character.toString()).matches();
            assertTrue("character is alpha only", match);
        });
    }
}
