package org.chance;

import static org.junit.Assert.assertEquals;

import org.chance.exception.RangeException;
import org.junit.Before;
import org.junit.Test;

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
    public void boolWithOptionsFalseTest() {
        Boolean expected = chance.bool(chance.options().option("likelihood", 0));
        Boolean actual = false;
       assertEquals(expected, actual); 
    }

    @Test(expected = RangeException.class)
    public void testRangeError() {
        chance.bool(chance.options().option("likelihood", 500)); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentType() {
        chance.bool(chance.options().option("likelihood", "500")); 
    }
}