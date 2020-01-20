package org.wahlzeit.model.coordinate;

import org.wahlzeit.utils.DoubleUtil;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class CartesianCoordinate extends AbstractCoordinate {
    private double x;
    private double y;
    private double z;

    private static Map<Integer, CartesianCoordinate> coordinates = new ConcurrentHashMap<>();

    //private Map<Integer, CartesianCoordinate> coordinateMap = new ConcurrentHashMap<>();

    private CartesianCoordinate() {};

    private CartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
        assertDoublesAreFinite(x, y, z);

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static CartesianCoordinate getCartesianCoordinate(double x, double y, double z) throws IllegalArgumentException {
        int hash = makeHashCode(x, y, z);
        /*if(coordinates.containsKey(hash)) {
            CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)coordinates.get(hash);
            return cartesianCoordinate;
        }

        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)coordinates.put(hash, new CartesianCoordinate(x, y, z));
        return cartesianCoordinate;*/
        CartesianCoordinate coordinate = coordinates.get(hash);
        if(coordinate == null) {
            coordinate = new CartesianCoordinate(x, y, z);
            coordinates.put(hash, coordinate);
        }

        return coordinate;
    }

    public double getX() {
        return x;
    }

    private void setX(double x) throws IllegalArgumentException {
        assertDoublesAreFinite(x);

        this.x = x;
    }

    public double getY() {
        return y;
    }

    private void setY(double y) throws IllegalArgumentException {
        assertDoublesAreFinite(y);

        this.y = y;
    }

    public double getZ() {
        return z;
    }

    private void setZ(double z) throws IllegalArgumentException {
        assertDoublesAreFinite(z);

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
    public double getCartesianDistance(Coordinate coordinate) throws IllegalArgumentException {
        assertClassInvariants();

        return doGetCartesianDistance(coordinate.asCartesianCoordinate());
    }

    private double doGetCartesianDistance(CartesianCoordinate cartesianCoordinate) {
        return Math.sqrt(Math.pow(cartesianCoordinate.x - x, 2) +
                Math.pow(cartesianCoordinate.y - y, 2) +
                Math.pow(cartesianCoordinate.z - z, 2));
    }

    /**
     * Converts the actual Coordinate-object to an SphericCoordinate-object.
     * @return The actual Coordinate-object as SphericCoordinate-object.
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws IllegalArgumentException {
        assertClassInvariants();

        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double t = Math.acos(z / r);
        double p = Math.atan(y / x);

        return SphericCoordinate.getSphericCoordinate(r, t, p);
    }

    /*@Override
    protected boolean doIsEqual(Coordinate coordinate) {
        CartesianCoordinate cartesianCoordinate = coordinate.asCartesianCoordinate();

        return DoubleUtil.compare(cartesianCoordinate.x, x) &&
                DoubleUtil.compare(cartesianCoordinate.y, y) &&
                DoubleUtil.compare(cartesianCoordinate.z, z);
    }*/

    @Override
    public int hashCode() {
        return makeHashCode(x, y, z);
    }

    private static int makeHashCode(double x, double y, double z) {
        return Objects.hash(x, y, z);
    }

    private static void assertDoublesAreFinite(double... values) throws IllegalArgumentException {
        for(double v : values) {
            DoubleUtil.assertIsFinite(v);
        }
    }

    protected void assertClassInvariants() throws IllegalArgumentException {
        assertDoublesAreFinite(x, y, z);
    }

    private void assertMapIncrease() throws IllegalArgumentException {

    }
}
