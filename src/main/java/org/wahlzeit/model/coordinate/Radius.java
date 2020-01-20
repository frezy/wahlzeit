package org.wahlzeit.model.coordinate;

import org.wahlzeit.utils.DoubleUtil;

public class Radius extends AbstractValidDouble {

    public Radius(double value) {
        super(value);
    }

    protected void assertValidValue() throws IllegalArgumentException {
        DoubleUtil.assertIsFinite(value);
        if (value < 0) {
            throw new IllegalArgumentException("Radius not greater than 0.");
        }
    }
}
