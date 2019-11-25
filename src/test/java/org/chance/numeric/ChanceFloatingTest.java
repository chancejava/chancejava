package org.chance.numeric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;

import org.chance.Chance;
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
        Float expected = Float.valueOf("1.0");
        Float actual = chance.floating(
            chance.options()
            .option("min", 1)
            .option("max", 1)
        );
       assertEquals(expected, actual); 
    }

    @Test 
    public void obeysMinAndMax() {
        
        Collections.nCopies(5000, null).forEach(x -> {
            Integer min = 1;
            Integer max = 99999;
            Float value = chance.floating(
                chance
                    .options()
                    .option("min", min)
                    .option("max", max)
            );
            assertTrue("value is greater than min", value >= min);
            assertTrue("value is less than max", value <= max);
        });
    }
    
    @Test 
    public void obeysPrecision() {
        
        Collections.nCopies(5000, null).forEach(x -> {
            Integer min = 1;
            Integer max = 99999;
            Integer precision = 5;
            Float value = chance.floating(
                chance
                    .options()
                    .option("precision", precision)
                    .option("min", min)
                    .option("max", max)
            );

            Float actual = Float.valueOf(new DecimalFormat("#.#####").format(value));


            assertEquals(value, actual);
        });
    }
    
    @Test
    public void invalidMin() {
        String min = "min";
        try { 
            chance.floating(
                chance.options()
                .option(min, "1")
                .option("max", 2)
            );
        } catch (IllegalArgumentException e) {
            assertEquals(
                "Chance:"+min+" must be "+ Integer.class.getSimpleName(),
                e.getMessage()
                );        }
    }
    
    @Test
    public void invalidMax() {
        String max = "max";
        try { 
            chance.floating(
                chance.options()
                .option("min", 1)
                .option(max, true)
            );
        } catch (IllegalArgumentException e) {
            assertEquals(
                "Chance:"+max+" must be "+ Integer.class.getSimpleName(),
                e.getMessage()
                );
        }
    }
}