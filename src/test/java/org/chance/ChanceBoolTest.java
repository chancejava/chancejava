package org.chance;

import static org.junit.Assert.assertEquals;

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
        Boolean expected = chance.bool(new Options.Builder().likelihood(100).build());
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
        Boolean expected = chance.bool(new Options.Builder().likelihood(0).build());
        Boolean actual = false;
       assertEquals(expected, actual); 
    }

    @Test
    public void boolWithIntegerFalseTest() {
        Boolean expected = chance.bool(0);
        Boolean actual = false;
       assertEquals(expected, actual); 
    }
}