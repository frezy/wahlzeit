package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.utils.DoubleUtil;

import java.lang.reflect.Constructor;
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
    public void testDefaultConstructor() throws Exception {
        Constructor<SphericCoordinate> constructor = SphericCoordinate.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        SphericCoordinate sphericCoordinate = constructor.newInstance();
    }

    @Test(expected = IllegalStateException.class)
    public void testParameterIsInfinite() {
        new SphericCoordinate(Double.POSITIVE_INFINITY, 0.0, 0.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testParameterIsNan() {
        new SphericCoordinate(Double.NaN, 0.0, 0.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testRadiusException() {
        a.setRadius(-1.0);
    }

    @Test
    public void testSetRadius() {
        a.setRadius(1.5);

        assertEquals(1.5, a.getRadius(), 1E-5);
    }

    @Test(expected = IllegalStateException.class)
    public void testThetaExceptionLow() {
        a.setTheta(-1.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testThetaExceptionHigh() {
        a.setTheta(Math.PI);
    }

    @Test
    public void testSetTheta() {
        a.setTheta(1.5);

        assertEquals(1.5, a.getTheta(), 1E-5);
    }

    @Test(expected = IllegalStateException.class)
    public void testPhiExceptionLow() {
        a.setTheta(-1.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testPhiExceptionHigh() {
        a.setTheta(2 * Math.PI);
    }

    @Test
    public void testSetPhi() {
        a.setPhi(1.5);

        assertEquals(1.5, a.getPhi(), 1E-5);
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
        assertEquals(Objects.hash(a.getRadius(), a.getTheta(), a.getPhi()), a.hashCode());
    }
}