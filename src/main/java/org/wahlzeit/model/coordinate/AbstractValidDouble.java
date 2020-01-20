package org.wahlzeit.model.coordinate;

public abstract class AbstractValidDouble implements ValidDouble {
    protected double value;

    public AbstractValidDouble(double value) throws IllegalArgumentException {
        this.value = value;

        assertValidValue();
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) throws IllegalArgumentException {
        this.value = value;
        assertValidValue();
    }

    protected abstract void assertValidValue() throws IllegalArgumentException;
}
