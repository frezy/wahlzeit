package org.wahlzeit.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoFactoryTest {

    @Test(expected = IllegalStateException.class)
    public void testSetInstanceException() {
        PhotoFactory.setInstance(new PhotoFactory());
    }

    @Test
    public void testCreatePhoto() {
        Photo photo = PhotoFactory.getInstance().createPhoto();

        assertNotNull(photo);
        assertEquals(Photo.class, photo.getClass());
    }

    @Test
    public void testCreatePhotoWithId() {
        PhotoId photoId = PhotoId.getRandomId();
        Photo photo = PhotoFactory.getInstance().createPhoto(photoId);

        assertNotNull(photo);
        assertEquals(photoId, photo.getId());
        assertEquals(Photo.class, photo.getClass());
    }
}