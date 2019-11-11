package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SphericCoordinateTest {

    SphericCoordinate a;
    SphericCoordinate b;
    SphericCoordinate c;

    @Before
    public void setUp() throws Exception {
        a = new SphericCoordinate(1.1071487177940904, 0.6405223126794247, 0.37416573867739417);
        b = new SphericCoordinate(1.1071487177940904, 0.6405223126794247, 0.37416573867739417);
        c = new SphericCoordinate(1.1071487177940904, 0.8410686705679303, 0.30000000000000004);
    }

    @Test
    public void testSphericCoordinateGetterSetter() {
        double x = 0.4, y = 0.5, z = 0.6;
        c.setPhi(x);
        c.setThata(y);
        c.setRadius(z);

        assertEquals(x, c.getPhi(),1E-5);
        assertEquals(y, c.getThata(),1E-5);
        assertEquals(z, c.getRadius(),1E-5);
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

    @Test
    public void getCentralAngle() {
        //TODO
    }

    @Test
    public void isEqual() {
        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }
}