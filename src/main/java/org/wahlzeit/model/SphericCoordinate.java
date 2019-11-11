package org.wahlzeit.model;

import org.wahlzeit.utils.DoubleUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class SphericCoordinate implements Coordinate {
    private double phi;
    private double thata;
    private double radius;

    private SphericCoordinate() {
    }

    public SphericCoordinate(double phi, double thata, double radius) {
        this.phi = phi;
        this.thata = thata;
        this.radius = radius;
    }

    public double getPhi() {
        return phi;
    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public double getThata() {
        return thata;
    }

    public void setThata(double thata) {
        this.thata = thata;
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
        double x = radius * Math.sin(thata) * Math.cos(phi);
        double y = radius * Math.sin(thata) * Math.sin(phi);
        double z = radius * Math.cos(thata);

        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Calculate the cartesian distance between the acutal and another Coordinate-object.
     * @param coordinate another Coordinate-object.
     * @return The cartesian distance between the two Coordinate-objects.
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return this.asCartesianCoordinate().getCartesianDistance(coordinate);
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
        throw new NotImplementedException(); //TODO
    }

    /**
     * Checks if the actual Coordinate-object and the other Coordinate-object is equal.
     * @param coordinate The other Coordinate-object.
     * @return True, if they are the same, false, on the other hand.
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        if(coordinate == null) return false;

        return doIsEqual(coordinate.asSphericCoordinate());
    }

    private boolean doIsEqual(SphericCoordinate sphericCoordinate) {
        return DoubleUtil.compare(sphericCoordinate.radius, radius) &&
                DoubleUtil.compare(sphericCoordinate.thata, thata) &&
                DoubleUtil.compare(sphericCoordinate.phi, phi);
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
        return Objects.hash(phi, thata, radius);
    }
}
