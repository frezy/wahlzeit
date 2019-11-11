package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
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
        org.wahlzeit.model.LocationTest.class,
})

public class ModelTestSuite {
}
