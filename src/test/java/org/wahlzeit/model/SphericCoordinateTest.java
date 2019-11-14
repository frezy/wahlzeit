package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.DoubleUtil;

import java.util.Objects;

import static org.junit.Assert.*;

public class SphericCoordinateTest {

    private SphericCoordinate a, b, c;

    @Before
    public void setUp() {
        a = new SphericCoordinate(0.37416573867739417, 0.6405223126794247, 1.1071487177940904);
        b = new SphericCoordinate(0.37416573867739417, 0.6405223126794247, 1.1071487177940904);
        c = new SphericCoordinate(0.30000000000000004, 0.8410686705679303, 1.1071487177940904);
    }

    @Test
    public void testAsCartesianCoordinate() {
        CartesianCoordinate cartesianCoordinate = a.asCartesianCoordinate();

        assertEquals(0.1, cartesianCoordinate.getX(), 1E-5);
        assertEquals(0.2, cartesianCoordinate.getY(), 1E-5);
        assertEquals(0.3, cartesianCoordinate.getZ(), 1E-5);
    }

    @Test
    public void testGetCartesianDistance() {
        assertEquals(0, a.getCartesianDistance(b),1E-5);
        assertEquals(0.1, a.getCartesianDistance(c), 1E-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCartesianDistanceIllegalArgumentException() {
        a.getCartesianDistance(null);
    }

    @Test
    public void testAsSphericCoordinate() {
        assertEquals(a, a.asSphericCoordinate());
    }

    /**
     * https://www.wolframalpha.com/input/?i=arccos%28sin%281.1071487177940904%29*sin%281.1071487177940904%29%2Bcos%281.1071487177940904%29*cos%281.1071487177940904%29*cos%280.37416573867739417+-+0.30000000000000004%29%29
     */
    @Test
    public void testGetCentralAngle() {
        assertEquals(0, a.getCentralAngle(b), 1E-5);
        assertEquals(0.033161844920283, a.getCentralAngle(c), 1E-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCentralAngleIllegalArgumentException() {
        a.getCentralAngle(null);
    }

    @Test
    public void testIsEqual() {
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }


    @Test
    public void testHashCode() {
        assertEquals(Objects.hash(a.getRadius(), a.getTheta(), a.getPhi()), a.hashCode());
    }
}