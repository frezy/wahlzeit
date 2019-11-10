package org.wahlzeit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FoodPhotoFactoryTest {

    @Test(expected = IllegalStateException.class)
    public void testSetInstanceAlreadySet() {
        FoodPhotoFactory.setInstance(new FoodPhotoFactory());
    }

    @Test
    public void testCreatePhoto() {
        FoodPhoto foodPhoto = FoodPhotoFactory.getInstance().createPhoto();

        assertNotNull(foodPhoto);
        assertEquals(FoodPhoto.class, foodPhoto.getClass());
    }

    @Test
    public void testCreatePhotoWithId() {
        PhotoId photoId = PhotoId.getRandomId();
        FoodPhoto foodPhoto = FoodPhotoFactory.getInstance().createPhoto(photoId);

        assertNotNull(foodPhoto);
        assertEquals(photoId, foodPhoto.getId());
        assertEquals(FoodPhoto.class, foodPhoto.getClass());
    }

    @Test
    public void testLoadPhoto() {
        assertNull(FoodPhotoFactory.getInstance().loadPhoto(PhotoId.getIdFromString("noID")));
    }
}