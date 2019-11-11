package org.wahlzeit.model;

import java.util.Objects;

/**
 * A location for a Photo-object.
 */
public class Location {
    public CartesianCoordinate cartesianCoordinate;

    public Location(CartesianCoordinate cartesianCoordinate) {
        this.cartesianCoordinate = cartesianCoordinate;
    }

    public CartesianCoordinate getCartesianCoordinate() {
        return cartesianCoordinate;
    }

    public void setCartesianCoordinate(CartesianCoordinate cartesianCoordinate) {
        this.cartesianCoordinate = cartesianCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;
        return cartesianCoordinate.equals(location.cartesianCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartesianCoordinate);
    }
}
