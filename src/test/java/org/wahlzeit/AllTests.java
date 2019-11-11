package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.CartesianCoordinateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.handlers.TellFriendTest.class,

        org.wahlzeit.model.ModelTestSuite.class,

        org.wahlzeit.services.ServiceTestSuite.class,

        org.wahlzeit.utils.UtilsTestSuite.class
})

public class AllTests {
}
