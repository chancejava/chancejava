package org.chance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static org.chance.Option.chanceOptions;

public class ChanceBoolTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void boolWithOptionsTrueTest() {
        Boolean expected = chance.bool(chance.options().option("likelihood", 100));
        Boolean actual = true;
       assertEquals(expected, actual); 
    }

    @Test
    public void boolWithIntegerTrueTest() {
        Boolean expected = chance.bool(100);
        Boolean actual = true;
       assertEquals(expected, actual); 
    }

    @Test
    public void boolWithOptionsFalseTest() {
        Boolean expected = chance.bool(chance.options().option("likelihood", 0));
        Boolean actual = false;
       assertEquals(expected, actual); 
    }

    @Test
    public void boolWithIntegerFalseTest() {
        Boolean expected = chance.bool(0);
        Boolean actual = false;
       assertEquals(expected, actual); 
    }

    @Test
    public void testCharacter() {
        String expected = "a";
        String actual = chance.character(chance.options().option("pool", "a"));
        assertEquals(expected, actual); 

    }
}