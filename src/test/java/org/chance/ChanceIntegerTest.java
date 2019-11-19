package org.chance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChanceIntegerTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void withOptions() {
        Integer expected = 1;
        Integer actual = chance.integer(
            chance.options()
            .option("min", 1)
            .option("max", 1)
            );
       assertEquals(expected, actual); 
    }

    @Test
    public void invalidMin() {
        String min = "min";
        try { 
            chance.integer(
                chance.options()
                .option(min, "1")
                .option("max", 2)
            );
        } catch (IllegalArgumentException e) {
            assertEquals(
                "Chance:"+min+" must be " + Integer.class.getSimpleName(),
                e.getMessage()
            );
        }
    }
    @Test
    public void invalidMax() {
        String max = "max";
        try { 
            chance.integer(
                chance.options()
                .option("min", 1)
                .option(max, true)
            );
        } catch (IllegalArgumentException e) {
            assertEquals(
                "Chance:"+max+" must be " + Integer.class.getSimpleName(),
                e.getMessage()
            );        }
    }
}