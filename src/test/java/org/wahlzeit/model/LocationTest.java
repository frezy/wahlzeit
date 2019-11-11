package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class LocationTest {

    private Location a;
    private Location b;
    private Location c;

    @Before
    public void setUp() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0.1, 0.2, 0.3);
        a = new Location(cartesianCoordinate);
        b = new Location(cartesianCoordinate);
        c = new Location(new CartesianCoordinate(0.4, 0.5, 0.6));
    }

    @Test
    public void testLocationGetterSetter() {
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(0.7, 0.8, 0.9);
        a.setCoordinate(cartesianCoordinate);

        assertEquals(cartesianCoordinate, a.getCoordinate());
    }

    @Test
    public void testEquals() {
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    public void testHashCode() {
        assertEquals(Objects.hash(a.getCoordinate()), a.hashCode());
    }
}