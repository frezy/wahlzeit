package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class CoordinateTest {
    private Coordinate a;
    private Coordinate b;
    private Coordinate c;

    @Before
    public void initPhotoFilter() {
        a = new Coordinate(0.1, 0.2, 0.3);
        b = new Coordinate(0.1, 0.2, 0.3);
        c = new Coordinate(0.1, 0.2, 0.2);
    }

    @Test
    public void checkDistance() {
        assertEquals(0, a.getDistance(b),0);
        assertEquals(0.1, a.getDistance(c), 0.01);
    }

    @Test
    public void checkEqual() {
        assertTrue(a.isEqual(b));
        assertFalse(a.isEqual(c));
    }
}
