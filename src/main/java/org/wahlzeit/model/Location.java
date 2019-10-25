package org.wahlzeit.model;

import java.util.Objects;

/**
 * A location for a Photo-object.
 */
public class Location {
    public Coordinate coordinate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return coordinate.equals(location.coordinate);
    }
}
