package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.repackaged.com.google.datastore.v1.client.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.handlers.WebFormHandler;
import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.testEnvironmentProvider.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.Assert.*;

public class PhotoManagerTest {

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
        Photo addedPhoto = new Photo();
        PhotoManager.getInstance().addPhoto(addedPhoto);
        Photo otherPhoto = new Photo();

        assertTrue(PhotoManager.getInstance().hasPhoto(addedPhoto.getId()));
        assertFalse(PhotoManager.getInstance().hasPhoto(otherPhoto.getId()));
    }

    @Test
    public void testGetPhoto() throws IOException {
        Photo addedPhoto = new Photo();
        PhotoManager.getInstance().addPhoto(addedPhoto);
        Photo otherPhoto = new Photo();

        assertEquals(addedPhoto, PhotoManager.getInstance().getPhoto(addedPhoto.id));
        assertNull(PhotoManager.getInstance().getPhoto(otherPhoto.id));
        assertNull(PhotoManager.getInstance().getPhoto((PhotoId)null));
    }

    @Test
    public void testDoAddPhoto() throws Exception {
        Photo addedPhoto = new Photo();
        PhotoManager.getInstance().addPhoto(addedPhoto);

        assertTrue(PhotoManager.getInstance().hasPhoto(addedPhoto.id));
    }

    @Test
    public void testCreatePhoto() throws Exception {
        byte[] addedBytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Photo photo = PhotoManager.getInstance().createPhoto("added", ImagesServiceFactory.makeImage(addedBytes));

        assertTrue(PhotoManager.getInstance().hasPhoto(photo.getId()));
    }

    @Test
    public void testAddPhoto() throws IOException {
        Photo photo = new Photo();
        PhotoManager.getInstance().addPhoto(photo);

        assertTrue(PhotoManager.getInstance().hasPhoto(photo.getId()));
    }

    @Test(expected = IllegalStateException.class)
    public void testAssertIsNewPhoto() throws IOException {
        Photo photo = new Photo();
        PhotoManager.getInstance().addPhoto(photo);

        PhotoManager.getInstance().assertIsNewPhoto(photo.getId());
    }

    @Test
    public void testGetVisiblePhoto() throws IOException {
        /*Photo visiblePhoto = new Photo();
        Tags tags = new Tags();
        tags.tags.add("test");
        visiblePhoto.setTags(tags);
        PhotoManager.getInstance().addPhoto(visiblePhoto);
        Photo hiddenPhoto = new Photo();
        PhotoManager.getInstance().addPhoto(hiddenPhoto);
        PhotoFilter photoFilter = new PhotoFilter();
        photoFilter.setTags(tags);

        assertEquals(visiblePhoto, PhotoManager.getInstance().getVisiblePhoto(photoFilter));*/ //TODO
    }
}