package org.wahlzeit.model;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FoodPhotoTest {
    @Test
    public void testCreateFoodPhoto() {
        FoodPhoto foodPhoto = FoodPhotoFactory.getInstance().createPhoto();
    }
}
