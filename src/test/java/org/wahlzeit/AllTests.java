package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.CartesianCoordinateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.handlers.TellFriendTest.class,

        org.wahlzeit.model.AccessRightsTest.class,
        org.wahlzeit.model.CoordinateTestSuite.class,
        org.wahlzeit.model.FlagReasonTest.class,
        org.wahlzeit.model.GenderTest.class,
        org.wahlzeit.model.GuestTest.class,
        org.wahlzeit.model.PhotoFilterTest.class,
        org.wahlzeit.model.TagsTest.class,
        org.wahlzeit.model.UserStatusTest.class,
        org.wahlzeit.model.ValueTest.class,

        org.wahlzeit.model.PhotoTestSuite.class,
        org.wahlzeit.model.FoodPhotoTestSuite.class,

        org.wahlzeit.services.EmailTestSuite.class,
        org.wahlzeit.services.LogBuilderTest.class,

        org.wahlzeit.utils.StringUtilTest.class,
        org.wahlzeit.utils.VersionTest.class
})

public class AllTests {
}
