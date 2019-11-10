package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.model.PhotoTest.class,
        org.wahlzeit.model.PhotoFactoryTest.class,
        org.wahlzeit.model.PhotoManagerTest.class
})

public class PhotoTestSuite {
}
