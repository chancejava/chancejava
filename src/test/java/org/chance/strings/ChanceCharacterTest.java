package org.chance.strings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.chance.Chance;
import org.junit.Before;
import org.junit.Test;

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
    
    @Test
    public void testCharacter2() {
        String pool = "abcdef";
        String actual = chance.character(chance.options().option("pool", pool));
        Boolean expected = pool.contains(actual);
        Date b = chance.birthday(chance.options().option("type", "child"));
        assertTrue("actual should be a value in the pool", expected); 

    }
}