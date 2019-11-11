package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.DoubleUtil;

import java.util.Objects;

import static org.junit.Assert.*;


public class CartesianCoordinateTest {
    private CartesianCoordinate a;
    private CartesianCoordinate b;
    private CartesianCoordinate c;

    @Before
    public void setUp() {
        a = new CartesianCoordinate(0.1, 0.2, 0.3);
        b = new CartesianCoordinate(0.1, 0.2, 0.3);
        c = new CartesianCoordinate(0.1, 0.2, 0.2);
    }

    @Test
    public void testCartesianCoordinateGetterSetter() {
        double x = 0.4, y = 0.5, z = 0.6;
        c.setX(x);
        c.setY(y);
        c.setZ(z);

        assertEquals(x, c.getX(),1E-5);
        assertEquals(y, c.getY(),1E-5);
        assertEquals(z, c.getZ(),1E-5);
    }

    @Test
    public void testAsCartesianCoordinate() {
        assertEquals(a, a.asCartesianCoordinate());
    }

    @Test
    public void testGetCartesianDistance() {
        assertEquals(0, a.getCartesianDistance(b),1E-5);
        assertEquals(0.1, a.getCartesianDistance(c), 1E-5);
    }

    /**
     * r: https://www.wolframalpha.com/input/?i=sqrt%280.1%5E2%2B0.2%5E2%2B0.3%5E2%29
     * p: https://www.wolframalpha.com/input/?i=arctan%280.2%2F0.1%29
     * t: https://www.wolframalpha.com/input/?i=arccos%280.3%2F%28sqrt%280.1%5E2+%2B+0.2%5E2+%2B+0.3%5E2%29%29%29
     */
    @Test
    public void testAsSphericCoordinate() {
        SphericCoordinate sphericCoordinate = a.asSphericCoordinate();

        assertEquals(0.374166, sphericCoordinate.getRadius(), 1E-5);
        assertEquals(1.10715, sphericCoordinate.getPhi(), 1E-5);
        assertEquals(0.640522, sphericCoordinate.getThata(), 1E-5);
    }

    @Test
    public void testGetCentralAngle() {
        //TODO
    }

    @Test
    public void testEquals() {
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    @Test
    public void testHashCode() {
        assertEquals(Objects.hash(a.getX(), a.getY(), a.getZ()), a.hashCode());
    }
}
