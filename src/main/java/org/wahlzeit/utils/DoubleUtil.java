package org.wahlzeit.utils;

public class DoubleUtil {

    private static final double DELTA = 1E-5;

    public static boolean compare(double x, double y) {
        if(Double.isNaN(x) || Double.isNaN(y)) return false;

        return Math.abs(x - y) < DELTA;
    }

    public static void assertIsNotNaN(double... values) {
        for (double v : values) {
            if (Double.isNaN(v)) throw new IllegalStateException("value should be not NaN");
        }
    }

    public static void assertIsNotInfinite(double... values) {
        for(double v : values) {
            if (Double.isInfinite(v)) throw new IllegalStateException("value should be not infinite");
        }
    }

    public static void assertIsFinite(double... values) {
        for(double v : values) {
            assert Double.isFinite(v);
        }
    }
}
