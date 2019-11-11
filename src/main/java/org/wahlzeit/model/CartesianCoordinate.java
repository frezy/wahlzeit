package org.wahlzeit.model;

import org.wahlzeit.utils.DoubleUtil;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class CartesianCoordinate implements Coordinate {
    private double x;
    private double y;
    private double z;

    private CartesianCoordinate() {
    }

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Converts the actual Coordinate-object to an CartesianCoordinate-object.
     * @return The actual Coordinate-object as CartesianCoordinate-object.
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    /**
     * Calculate the cartesian distance between the acutal and another Coordinate-object.
     * @param coordinate another Coordinate-object.
     * @return The cartesian distance between the two Coordinate-objects.
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        return doGetCartesianDistance(coordinate.asCartesianCoordinate());
    }

    private double doGetCartesianDistance(CartesianCoordinate cartesianCoordinate) {
        return Math.sqrt(Math.pow(cartesianCoordinate.x - x, 2) + Math.pow(cartesianCoordinate.y - y, 2) + Math.pow(cartesianCoordinate.z - z, 2));
    }

    /**
     * Converts the actual Coordinate-object to an SphericCoordinate-object.
     * @return The actual Coordinate-object as SphericCoordinate-object.
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double p = Math.atan(y / x);
        double t = Math.acos(z / r);

        return new SphericCoordinate(p, t, r);
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

        return doIsEqual(coordinate.asCartesianCoordinate());
    }

    private boolean doIsEqual(CartesianCoordinate cartesianCoordinate) {
        return DoubleUtil.compare(cartesianCoordinate.x, x) &&
                DoubleUtil.compare(cartesianCoordinate.y, y) &&
                DoubleUtil.compare(cartesianCoordinate.z, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) o;

        return isEqual(cartesianCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
