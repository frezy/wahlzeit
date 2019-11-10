package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import org.junit.*;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class PhotoTest {
    User user;
    User otherUser;

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

        /*User user = new User("user", "User", "usermail");
        User otherUser = new User("otheruser", "otherUser", "otherusermail");*/
    }

    @Test
    public void testGetImage() throws IOException {
        byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        assertEquals(image, photo.getImage(PhotoSize.EXTRA_LARGE));
    }

    @Test
    public void testSetImage() throws IOException {
        byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        byte[] otherBytes = Files.readAllBytes(new File("src/main/resources/pictures/test2.jpg").toPath());
        Image otherImage = ImagesServiceFactory.makeImage(otherBytes);
        Photo otherPhoto = new Photo();
        otherPhoto.setImage(PhotoSize.EXTRA_LARGE, otherImage);

        assertEquals(image, photo.getImage(PhotoSize.EXTRA_LARGE));
        assertNotEquals(otherPhoto, photo.getImage(PhotoSize.EXTRA_LARGE));
    }

    @Test
    public void testGetIdAsString() throws IOException {
        byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        byte[] otherBytes = Files.readAllBytes(new File("src/main/resources/pictures/test2.jpg").toPath());
        Image otherImage = ImagesServiceFactory.makeImage(otherBytes);
        Photo otherPhoto = new Photo();
        otherPhoto.setImage(PhotoSize.EXTRA_LARGE, otherImage);

        assertEquals(photo.id.asString(), photo.getIdAsString());
        assertNotEquals(otherPhoto.id.asString(), photo.getIdAsString());
    }

    @Test
    public void testGetId() throws IOException {
        byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        byte[] otherBytes = Files.readAllBytes(new File("src/main/resources/pictures/test2.jpg").toPath());
        Image otherImage = ImagesServiceFactory.makeImage(otherBytes);
        Photo otherPhoto = new Photo();
        otherPhoto.setImage(PhotoSize.EXTRA_LARGE, otherImage);

        assertEquals(photo.id, photo.getId());
        assertNotEquals(otherPhoto.id, photo.getId());
    }

    @Test
    public void testGetOwnerId() throws IOException {
        /*byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        byte[] otherBytes = Files.readAllBytes(new File("src/main/resources/pictures/test2.jpg").toPath());
        Image otherImage = ImagesServiceFactory.makeImage(otherBytes);
        Photo otherPhoto = new Photo();
        otherPhoto.setImage(PhotoSize.EXTRA_LARGE, otherImage);

        assertEquals(user.getId(), photo.getOwnerId());
        assertNotEquals(user.getId(), otherPhoto.getOwnerId());*/ //TODO
    }

    @Test
    public void testSetOwnerId() throws IOException {
        /*byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        photo.setOwnerId(user.getId());

        assertEquals(user.getId(), photo.getOwnerId());
        assertNotEquals(otherUser.getId(), photo.getOwnerId());*/ //TODO
    }

    @Test
    public void hasSameOwner() throws IOException {
        /*byte[] bytes = Files.readAllBytes(new File("src/main/resources/pictures/test1.jpg").toPath());
        Image image = ImagesServiceFactory.makeImage(bytes);
        Photo photo = new Photo();
        photo.setImage(PhotoSize.EXTRA_LARGE, image);

        byte[] otherBytes = Files.readAllBytes(new File("src/main/resources/pictures/test2.jpg").toPath());
        Image otherImage = ImagesServiceFactory.makeImage(otherBytes);
        Photo otherPhoto = new Photo();
        otherPhoto.setImage(PhotoSize.EXTRA_LARGE, otherImage);

        photo.setOwnerId(user.getId());

        assertFalse(photo.hasSameOwner(otherPhoto));

        otherPhoto.setOwnerId(user.getId());

        assertTrue(photo.hasSameOwner(otherPhoto));*/ //TODO
    }
}
