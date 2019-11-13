package org.wahlzeit.model;

import org.wahlzeit.utils.DoubleUtil;

import java.util.Objects;

public class SphericCoordinate implements Coordinate {
    private double phi;
    private double theta;
    private double radius;

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Converts the actual Coordinate-object to an CartesianCoordinate-object.
     * @return The actual Coordinate-object as CartesianCoordinate-object.
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(theta) * Math.cos(phi);
        double y = radius * Math.sin(theta) * Math.sin(phi);
        double z = radius * Math.cos(theta);

        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Calculate the cartesian distance between the acutal and another Coordinate-object.
     * @param coordinate another Coordinate-object.
     * @return The cartesian distance between the two Coordinate-objects.
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return asCartesianCoordinate().getCartesianDistance(coordinate);
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
        return doGetCentralAngle(coordinate.asSphericCoordinate());
    }

    private double doGetCentralAngle(SphericCoordinate sphericCoordinate) {
        return Math.acos(Math.sin(this.phi) * Math.sin(sphericCoordinate.phi) + Math.cos(this.phi) * Math.cos(sphericCoordinate.phi) * Math.cos(Math.abs(this.radius - sphericCoordinate.radius))); //TODO check physics vs math https://en.wikipedia.org/wiki/Great-circle_distance
    }

    /**
     * Checks if the actual Coordinate-object and the other Coordinate-object is equal.
     * @param coordinate The other Coordinate-object.
     * @return True, if they are the same, false, on the other hand.
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null) return false;
        assertNotNaN();
        coordinate.assertNotNaN();

        return doIsEqual(coordinate.asSphericCoordinate());
    }

    private boolean doIsEqual(SphericCoordinate sphericCoordinate) {
        return DoubleUtil.compare(sphericCoordinate.radius, radius) &&
                DoubleUtil.compare(sphericCoordinate.theta, theta) &&
                DoubleUtil.compare(sphericCoordinate.phi, phi);
    }

    @Override
    public void assertNotNaN() {
        if(Double.isNaN(phi) || Double.isNaN(theta) || Double.isNaN(radius)) throw new IllegalStateException("Coordinate is NaN");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SphericCoordinate)) return false;

        SphericCoordinate that = (SphericCoordinate) o;

        return isEqual(that);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius);
    }
}
