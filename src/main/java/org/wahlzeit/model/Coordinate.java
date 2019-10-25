package org.wahlzeit.model;

public class Coordinate {
    private double x;
    private double y;
    private double z;

    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
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
        return coordinate.x == this.x && coordinate.y == this.y && coordinate.z == this.z;
    }
}
