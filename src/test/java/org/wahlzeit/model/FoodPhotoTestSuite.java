package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        org.wahlzeit.model.FoodPhotoTest.class,
        org.wahlzeit.model.FoodPhotoFactoryTest.class,
        org.wahlzeit.model.FoodPhotoManagerTest.class
})

public class FoodPhotoTestSuite {
}
