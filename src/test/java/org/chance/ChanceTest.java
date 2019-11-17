package org.chance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChanceTest {

    private Chance classUnderTest;

    @Before
    public void before() {
        this.classUnderTest = new Chance();
    }

    @Test
    public void boolWithOptionsTrueTest() {
        Boolean expected = classUnderTest.bool(new Options.Builder().likelihood(100).build());
        Boolean actual = true;
       assertEquals(expected, actual); 
    }
    @Test
    public void boolWithIntegerTrueTest() {
        Boolean expected = classUnderTest.bool(100);
        Boolean actual = true;
       assertEquals(expected, actual); 
    }
    @Test
    public void boolFalseTest() {
        Boolean expected = classUnderTest.bool(new Options.Builder().likelihood(0).build());
        Boolean actual = false;
       assertEquals(expected, actual); 
    }
}