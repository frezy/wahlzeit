package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.model.CartesianCoordinateTest.class,
        org.wahlzeit.model.SphericCoordinateTest.class
})

public class CoordinateTestSuite {
}
