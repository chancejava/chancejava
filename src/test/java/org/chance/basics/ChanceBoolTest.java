package org.chance.basics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.chance.Chance;
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
    public void returnsRandomBoolean() {
        Boolean bool = chance.bool();
        assertEquals(bool.getClass(), Boolean.class);
    }

    @Test
    public void boolWithOptionsTrueTest() {
        Collections.nCopies(1000, null).forEach(x -> {
            Boolean expected = chance.bool(chance.options().option("likelihood", 100));
            Boolean actual = true;
            assertEquals(expected, actual); 
        });


    }

    @Test
    public void obeysLikelihood() {
        Integer trueCount = Collections.nCopies(1000, null).stream()
            .map( x-> chance.bool(chance.options().option("likelihood", 30)) ? 1 : 0 )
            .reduce((count, value) -> {
                count += value;
                return count;
            }).get();
            
        assertTrue("expect average to be around 300", trueCount > 200 & trueCount < 400);
    
        trueCount = Collections.nCopies(1000, null).stream()
            .map( x-> chance.bool(chance.options().option("likelihood", 99)) ? 1 : 0 )
            .reduce((count, value) -> {
                count += value;
                return count;
            }).get();
    
        // Expect it to average at 990
        assertTrue("expect average to be around 990", trueCount > 900);
    }
    
    @Test
    public void boolWithOptionsFalseTest() {
        Collections.nCopies(1000, null).forEach(x -> {
            Boolean expected = chance.bool(chance.options().option("likelihood", 0));
            Boolean actual = false;
            assertEquals(expected, actual); 
        });
    }

    @Test(expected = RangeException.class)
    public void testRangeErrorMin() {
        chance.bool(chance.options().option("likelihood", -1)); 
    }
    @Test(expected = RangeException.class)
    public void testRangeErrorMax() {
        chance.bool(chance.options().option("likelihood", 500)); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testWrongArgumentType() {
        chance.bool(chance.options().option("likelihood", "500")); 
    }
}