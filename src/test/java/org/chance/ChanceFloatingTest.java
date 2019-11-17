package org.chance;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ChanceFloatingTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void withOptions() {
        Double expected = 1.0;
        Double actual = chance.floating(
            chance.options()
            .option("min", 1)
            .option("max", 1)
            );
       assertEquals(expected, actual); 
    }

    @Test
    public void invalidMin() {
        try { 
            chance.floating(
                chance.options()
                .option("min", "1")
                .option("max", 2)
            );
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Chance: Min must be an integer");
        }
    }
    @Test
    public void invalidMax() {
        try { 
            chance.floating(
                chance.options()
                .option("min", 1L)
                .option("max", true)
            );
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Chance: Max must be an integer");
        }
    }
}