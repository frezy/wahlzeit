package org.wahlzeit.model;

import java.util.Objects;

public class Coordinate {
    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
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
     * Calculate the euclidean distance between the actual Coordinate-object and another.
     * @param coordinate The other Coordiante-object.
     * @return The distance between the two Coordinate-objects.
     */
    public double getDistance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(coordinate.x - this.x, 2) + Math.pow(coordinate.y - this.y, 2) + Math.pow(coordinate.z - this.z, 2));
    }

    /**
     * Checks if the actual Coordinate-object and the other Coordinate-object is equal.
     * @param coordinate The other Coordinate-object.
     * @return True, if they are the same, false, on the other hand.
     */
    public boolean isEqual(Coordinate coordinate) {
        if (this == coordinate) return true;

        return compareDouble(coordinate.x, x) &&
                compareDouble(coordinate.y, y) &&
                compareDouble(coordinate.z, z);
    }

    private static final double PRECISION = 1E-5;
    private static boolean compareDouble(double x, double y) {
        if(Double.isNaN(x) || Double.isNaN(y)) return false;

        return Math.abs(x - y) < PRECISION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Coordinate coordinate = (Coordinate) o;
        return this.isEqual(coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
