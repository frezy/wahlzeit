package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    /**
     * Converts the actual Coordinate-object to an CartesianCoordinate-object.
     * @return The actual Coordinate-object as CartesianCoordinate-object.
     */
    @Override
    public abstract CartesianCoordinate asCartesianCoordinate();

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
    public abstract SphericCoordinate asSphericCoordinate();

    /**
     * Calculate the centralAngle between the acutal and another Coordinate-object.
     * @param coordinate another Coordinate-object.
     * @return The central angle between the two Coordinate-objects.
     */
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        return asSphericCoordinate().getCentralAngle(coordinate);
    }

    /**
     * Checks if the actual Coordinate-object and the other Coordinate-object is equal.
     * @param coordinate The other Coordinate-object.
     * @return True, if they are the same, false, on the other hand.
     */
    @Override
    public abstract boolean isEqual(Coordinate coordinate);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractCoordinate)) return false;

        AbstractCoordinate that = (AbstractCoordinate) o;

        return isEqual(that);
    }
}