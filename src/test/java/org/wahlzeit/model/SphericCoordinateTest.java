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
        a = new SphericCoordinate(1.1071487177940904, 0.6405223126794247, 0.37416573867739417);
        b = new SphericCoordinate(1.1071487177940904, 0.6405223126794247, 0.37416573867739417);
        c = new SphericCoordinate(1.1071487177940904, 0.8410686705679303, 0.30000000000000004);
    }

    @Test
    public void asCartesianCoordinate() {
        CartesianCoordinate cartesianCoordinate = a.asCartesianCoordinate();

        assertEquals(0.1, cartesianCoordinate.getX(), 1E-5);
        assertEquals(0.2, cartesianCoordinate.getY(), 1E-5);
        assertEquals(0.3, cartesianCoordinate.getZ(), 1E-5);
    }

    @Test
    public void getCartesianDistance() {
        assertEquals(0, a.getCartesianDistance(b),1E-5);
        assertEquals(0.1, a.getCartesianDistance(c), 1E-5);
    }

    @Test
    public void asSphericCoordinate() {
        assertEquals(a, a.asSphericCoordinate());
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
    public void isEqual() {
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }


    @Test
    public void testHashCode() {
        assertEquals(Objects.hash(a.getPhi(), a.getTheta(), a.getRadius()), a.hashCode());
    }
}