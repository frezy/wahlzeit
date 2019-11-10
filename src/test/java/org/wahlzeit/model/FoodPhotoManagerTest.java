package org.wahlzeit.model;

import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.handlers.WebFormHandler;
import org.wahlzeit.testEnvironmentProvider.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class FoodPhotoManagerTest {

    @ClassRule
    public static SysConfigProvider sysConfigProvider = new SysConfigProvider();
    @ClassRule
    public static WebFormHandlerProvider webFormHandlerProvider = new WebFormHandlerProvider();

    @Rule
    public TestRule chain = RuleChain.
            outerRule(new LocalDatastoreServiceTestConfigProvider()).
            around(new RegisteredOfyEnvironmentProvider()).
            around(new UserSessionProvider());

    @Before
    public void setUp() throws Exception {
        ObjectifyService.begin();
    }

    @Test
    public void testHasPhoto() throws IOException {
        FoodPhoto addedPhoto = new FoodPhoto();
        FoodPhotoManager.getInstance().addPhoto(addedPhoto);
        FoodPhoto otherPhoto = new FoodPhoto();

        assertTrue(FoodPhotoManager.getInstance().hasPhoto(addedPhoto.getId()));
        assertFalse(FoodPhotoManager.getInstance().hasPhoto(otherPhoto.getId()));
    }

    @Test
    public void testGetPhoto() throws IOException {
        FoodPhoto addedPhoto = new FoodPhoto();
        FoodPhotoManager.getInstance().addPhoto(addedPhoto);
        FoodPhoto otherPhoto = new FoodPhoto();

        assertEquals(addedPhoto, FoodPhotoManager.getInstance().getPhoto(addedPhoto.id));
        assertNull(FoodPhotoManager.getInstance().getPhoto(otherPhoto.id));
        assertNull(FoodPhotoManager.getInstance().getPhoto((PhotoId)null));
    }

    @Test
    public void testDoAddPhoto() throws Exception {
        FoodPhoto addedPhoto = new FoodPhoto();
        FoodPhotoManager.getInstance().addPhoto(addedPhoto);

        assertTrue(FoodPhotoManager.getInstance().hasPhoto(addedPhoto.id));
    }

    @Test
    public void testCreatePhoto() throws Exception {
        byte[] addedBytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        FoodPhoto photo = FoodPhotoManager.getInstance().createPhoto("added", ImagesServiceFactory.makeImage(addedBytes));

        assertTrue(FoodPhotoManager.getInstance().hasPhoto(photo.getId()));
    }

    @Test
    public void testAddPhoto() throws IOException {
        FoodPhoto photo = new FoodPhoto();
        FoodPhotoManager.getInstance().addPhoto(photo);

        assertTrue(FoodPhotoManager.getInstance().hasPhoto(photo.getId()));
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertIsNewPhoto() throws IOException {
        FoodPhoto photo = new FoodPhoto();
        FoodPhotoManager.getInstance().addPhoto(photo);

        FoodPhotoManager.getInstance().assertIsNewPhoto(photo.getId());
    }

    @Test
    public void testGetVisiblePhoto() throws IOException {
        /*Photo visiblePhoto = new Photo();
        Tags tags = new Tags();
        tags.tags.add("test");
        visiblePhoto.setTags(tags);
        FoodPhotoManager.getInstance().addPhoto(visiblePhoto);
        Photo hiddenPhoto = new Photo();
        FoodPhotoManager.getInstance().addPhoto(hiddenPhoto);
        PhotoFilter photoFilter = new PhotoFilter();
        photoFilter.setTags(tags);

        assertEquals(visiblePhoto, FoodPhotoManager.getInstance().getVisiblePhoto(photoFilter));*/ //TODO
    }
}
