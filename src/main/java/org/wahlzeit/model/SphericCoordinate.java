package org.wahlzeit.model;

import org.wahlzeit.utils.DoubleUtil;

import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate {
    private double phi;
    private double theta;
    private double radius;

    private SphericCoordinate() {};

    public SphericCoordinate(double phi, double theta, double radius) {
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadius() {
        return radius;
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
        if(coordinate == null) throw new IllegalArgumentException("coordinate should not be null");

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

        return doIsEqual(coordinate);
    }

    protected boolean doIsEqual(Coordinate coordinate) {
        SphericCoordinate sphericCoordinate = coordinate.asSphericCoordinate();

        return DoubleUtil.compare(sphericCoordinate.radius, radius) &&
                DoubleUtil.compare(sphericCoordinate.theta, theta) &&
                DoubleUtil.compare(sphericCoordinate.phi, phi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius);
    }
}
