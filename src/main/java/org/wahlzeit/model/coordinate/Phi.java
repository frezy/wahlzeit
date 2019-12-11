package org.wahlzeit.model.coordinate;

import org.wahlzeit.utils.DoubleUtil;

public class Phi extends AbstractValidDouble {

    public Phi(double value) {
        super(value);
    }

    @Override
    protected void assertValidValue() throws IllegalArgumentException {
        DoubleUtil.assertIsFinite(value);
        if (value < 0 || value > Math.PI) {
            throw new IllegalArgumentException("Theta not greater than 0 and equal-smaller than Math.PI.");
        }
    }
}
