package org.wahlzeit.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.services.EmailTestSuite.class,
        org.wahlzeit.services.LogBuilderTest.class
})

public class ServiceTestSuite {
}
