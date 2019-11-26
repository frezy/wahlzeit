package org.wahlzeit.model;

import org.wahlzeit.utils.DoubleUtil;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {
    private double radius;
    private double theta;
    private double phi;

    private SphericCoordinate() {};

    public SphericCoordinate(double radius, double theta, double phi) {
        assertRadiusIsValid(radius);
        assertThetaIsValid(theta);
        assertPhiIsValid(phi);

        this.radius = radius;
        this.theta = theta;
        this.phi = phi;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        assertRadiusIsValid(radius);

        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        assertThetaIsValid(theta);

        this.theta = theta;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        assertPhiIsValid(phi);

        this.phi = phi;
    }

    /**
     * Converts the actual Coordinate-object to an CartesianCoordinate-object.
     * @return The actual Coordinate-object as CartesianCoordinate-object.
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();

        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        return new CartesianCoordinate(x, y, z);
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
    public double getCentralAngle(Coordinate coordinate) {
        assertClassInvariants();

        return doGetCentralAngle(coordinate.asSphericCoordinate());
    }

    private double doGetCentralAngle(SphericCoordinate sphericCoordinate) {
        return Math.acos(Math.sin(this.phi) * Math.sin(sphericCoordinate.phi) + Math.cos(this.phi) * Math.cos(sphericCoordinate.phi) * Math.cos(Math.abs(this.radius - sphericCoordinate.radius))); //TODO check physics vs math https://en.wikipedia.org/wiki/Great-circle_distance
    }

    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();

        return DoubleUtil.compare(sphericCoordinate.radius, radius) &&
                DoubleUtil.compare(sphericCoordinate.theta, theta) &&
                DoubleUtil.compare(sphericCoordinate.phi, phi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, theta, phi);
    }

    private static void assertDoublesAreFinite(double... values) {
        DoubleUtil.assertIsFinite(values);
    }

    private static void assertRadiusIsValid(double radius) {
        assertDoublesAreFinite(radius);
        assert radius >= 0;
    }

    private static void assertThetaIsValid(double theta) {
        assertDoublesAreFinite(theta);
        assert theta >= 0 && theta <= Math.PI;
    }

    private static void assertPhiIsValid(double phi) {
        assertDoublesAreFinite(phi);
        assert phi >= 0 && phi < 2 * Math.PI;
    }

    protected void assertClassInvariants() {
        assertRadiusIsValid(radius);
        assertThetaIsValid(theta);
        assertPhiIsValid(phi);
    }
}
