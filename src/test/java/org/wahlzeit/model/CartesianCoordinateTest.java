package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
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
    public void testDefaultConstructor() throws Exception {
        Constructor<CartesianCoordinate> constructor = CartesianCoordinate.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        CartesianCoordinate sphericCoordinate = constructor.newInstance();
    }

    @Test(expected = IllegalStateException.class)
    public void testParameterIsInfinite() {
        new SphericCoordinate(Double.POSITIVE_INFINITY, 0.0, 0.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testParameterIsNan() {
        new SphericCoordinate(0.0, Double.NaN, 0.0);
    }

    @Test
    public void testSetX() {
        a.setX(1.0);

        assertEquals(1.0, a.getX(), 1E-5);
    }

    @Test
    public void testSetY() {
        a.setY(1.0);

        assertEquals(1.0, a.getY(), 1E-5);
    }

    @Test
    public void testSetZ() {
        a.setZ(1.0);

        assertEquals(1.0, a.getZ(), 1E-5);
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
        assertEquals(0.640522, sphericCoordinate.getTheta(), 1E-5);
    }

    /**
     * https://www.wolframalpha.com/input/?i=arccos%28sin%281.1071487177940904%29*sin%281.1071487177940904%29%2Bcos%281.1071487177940904%29*cos%281.1071487177940904%29*cos%280.37416573867739417+-+0.30000000000000004%29%29
     */
    @Test
    public void getCentralAngle() {
        assertEquals(0, a.getCentralAngle(b), 1E-5);
        assertEquals(0.033161844920283, a.getCentralAngle(c), 1E-5);
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
