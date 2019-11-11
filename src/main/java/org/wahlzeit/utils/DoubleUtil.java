package org.wahlzeit.utils;

public class DoubleUtil {

    private static final double DELTA = 1E-5;

    public static boolean compare(double x, double y) {
        if(Double.isNaN(x) || Double.isNaN(y)) return false;

        return Math.abs(x - y) < DELTA;
    }
}
