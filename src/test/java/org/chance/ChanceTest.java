package org.chance;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ChanceTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void streetSuffixTest() {
        Map<String,String> streetSuffix = chance.streetSuffix();
        assertTrue("street name should come from streetSuffixes", chance.streetSuffixes().get("us").contains(streetSuffix));
    }
    
    @Test
    public void streetSuffixTestWithOptions() {
        Map<String,String> streetSuffix = chance.streetSuffix(chance.options().option("country", "it"));
        assertTrue("street name should come from streetSuffixes", chance.streetSuffixes().get("it").contains(streetSuffix));
    }
}