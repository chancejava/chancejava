package org.chance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.chance.Option.chanceOptions;

public class ChanceCharacterTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void testCharacter() {
        String expected = "a";
        String actual = chance.character(chance.options().option("pool", "a"));
        assertEquals(expected, actual); 

    }
}