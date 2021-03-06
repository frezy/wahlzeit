package org.wahlzeit.model;

import org.wahlzeit.model.coordinate.Coordinate;

import java.util.Objects;

/**
 * A location for a Photo-object.
 */
public class Location {
    private Coordinate coordinate;

    private Location() {}

    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;
        return coordinate.equals(location.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
