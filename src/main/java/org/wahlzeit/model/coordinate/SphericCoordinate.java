package org.wahlzeit.model.coordinate;

import org.wahlzeit.utils.DoubleUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {
    private double radius;
    private double theta;
    private double phi;

    private SphericCoordinate() {};

    private SphericCoordinate(double radius, double theta, double phi) throws IllegalArgumentException {
        assertRadiusIsValid(radius);
        assertThetaIsValid(theta);
        assertPhiIsValid(phi);

        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    public static SphericCoordinate getSphericCoordinate(double radius, double theta, double phi) throws IllegalArgumentException {
        int hash = makeHashCode(radius, theta, phi);
        /*if(coordinates.containsKey(hash)) {
            SphericCoordinate sphericCoordinate = (SphericCoordinate) coordinates.get(hash);
            return sphericCoordinate;
        }

        SphericCoordinate sphericCoordinate = (SphericCoordinate) coordinates.put(hash, new SphericCoordinate(radius, theta, phi));
        return sphericCoordinate;*/
        SphericCoordinate coordinate = (SphericCoordinate)coordinates.get(hash);
        if(coordinate == null) {
            coordinate = new SphericCoordinate(radius, theta, phi);
            coordinates.put(hash, coordinate);
        }

        return coordinate;
    }

    public double getRadius() {
        return radius;
    }

    private void setRadius(double radius) throws IllegalArgumentException {
        assertRadiusIsValid(radius);

        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    private void setTheta(double theta) throws IllegalArgumentException {
        assertThetaIsValid(theta);

        this.theta = theta;
    }

    public double getPhi() {
        return phi;
    }

    private void setPhi(double phi) throws IllegalArgumentException {
        assertPhiIsValid(phi);

        this.phi = phi;
    }

    /**
     * Converts the actual Coordinate-object to an CartesianCoordinate-object.
     * @return The actual Coordinate-object as CartesianCoordinate-object.
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalArgumentException {
        assertClassInvariants();

        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        return CartesianCoordinate.getCartesianCoordinate(x, y, z);
    }

    /**
     * Converts the actual Coordinate-object to an SphericCoordinate-object.
     * @return The actual Coordinate-object as SphericCoordinate-object.
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * Calculate the centralAngle between the acutal and another Coordinate-object.
     * @param coordinate another Coordinate-object.
     * @return The central angle between the two Coordinate-objects.
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) throws IllegalArgumentException {
        assertClassInvariants();

        return doGetCentralAngle(coordinate.asSphericCoordinate());
    }

    private double doGetCentralAngle(SphericCoordinate sphericCoordinate) {
        return Math.acos(Math.sin(this.phi) * Math.sin(sphericCoordinate.phi) + Math.cos(this.phi) * Math.cos(sphericCoordinate.phi) * Math.cos(Math.abs(this.radius - sphericCoordinate.radius))); //TODO check physics vs math https://en.wikipedia.org/wiki/Great-circle_distance
    }

    /*@Override
    protected boolean doIsEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();

        return DoubleUtil.compare(sphericCoordinate.radius, radius) &&
                DoubleUtil.compare(sphericCoordinate.theta, theta) &&
                DoubleUtil.compare(sphericCoordinate.phi, phi);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(radius, theta, phi);
    }

    private static int makeHashCode(double radius, double theta, double phi) {
        return Objects.hash(radius, theta, phi);
    }

    private static void assertDoublesAreFinite(double... values) throws NotImplementedException {
        for (double v : values) {
            DoubleUtil.assertIsFinite(v);
        }
    }

    private static void assertRadiusIsValid(double radius) throws IllegalArgumentException {
        assertDoublesAreFinite(radius);
        if (radius < 0) {
            throw new IllegalArgumentException("Radius not greater than 0.");
        }
    }

    private static void assertThetaIsValid(double theta) throws IllegalArgumentException {
        assertDoublesAreFinite(theta);
        if (theta < 0 || theta > Math.PI) {
            throw new IllegalArgumentException("Theta not greater than 0 and equal-smaller than Math.PI.");
        }
    }

    private static void assertPhiIsValid(double phi) throws IllegalArgumentException {
        assertDoublesAreFinite(phi);
        if (phi < 0 && phi >= 2 * Math.PI) {
            throw new IllegalArgumentException("Phi not greater than 0 and smaller than 2*Math.PI.");
        }
    }

    protected void assertClassInvariants() throws IllegalArgumentException {
        assertRadiusIsValid(radius);
        assertThetaIsValid(theta);
        assertPhiIsValid(phi);
    }
}
