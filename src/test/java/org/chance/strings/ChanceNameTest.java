package org.chance.strings;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.chance.Chance;
import org.junit.Before;
import org.junit.Test;

public class ChanceNameTest {

    private Chance chance;

    @Before
    public void before() {
        this.chance = new Chance();
    }

    @Test
    public void testNameTakesInOptions() {

        assertEquals(chance.name(chance.options()).getClass(), String.class);

    }

    @Test
    public void testNameDefaultHasFirstAndLastName() {

        Collections.nCopies(5000, null).forEach(x -> {
            String name = chance.name();
            String first = name.split(" ")[0];
            String last = name.split(" ")[1];

            assertTrue(name.split(" ").length == 2);
            assertEquals(first.getClass(), String.class);
            assertTrue(first.length() > 0);
            assertEquals(last.getClass(), String.class);
            assertTrue(last.length() > 0);

        });
    }

    @Test
    public void testNameHasMiddleName() {

        Collections.nCopies(5000, null).forEach(x -> {
            String name = chance.name(chance.options().option("middle", true));
            String first = name.split(" ")[0];
            String middle = name.split(" ")[1];
            String last = name.split(" ")[2];

            assertTrue(name.split(" ").length == 3);
            assertEquals(first.getClass(), String.class);
            assertTrue(first.length() > 0);
            assertEquals(middle.getClass(), String.class);
            assertTrue(middle.length() > 0);

            assertEquals(last.getClass(), String.class);
            assertTrue(last.length() > 0);

        });
    }

    @Test
    public void testNameHasMiddleInitial() {

        Collections.nCopies(5000, null).forEach(x -> {
            String name = chance.name(chance.options().option("middle_initial", true));
            String first = name.split(" ")[0];
            String middle = name.split(" ")[1];
            String last = name.split(" ")[2];

            assertTrue(name.split(" ").length == 3);
            assertEquals(first.getClass(), String.class);
            assertTrue(first.length() > 0);
            assertEquals(middle.getClass(), String.class);
            assertTrue(middle.length() > 0);

            assertEquals(last.getClass(), String.class);
            assertTrue(last.length() > 0);

        });
    }

}