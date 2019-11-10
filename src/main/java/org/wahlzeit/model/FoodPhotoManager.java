package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.wahlzeit.model.persistence.ImageStorage;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.Persistent;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;

public class FoodPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final FoodPhotoManager instance = new FoodPhotoManager();

    private static final Logger log = Logger.getLogger(FoodPhotoManager.class.getName());

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, FoodPhoto> photoCache = new HashMap<PhotoId, FoodPhoto>();

    public FoodPhotoManager() {
        super();
    }

    /**
     *
     */
    public static FoodPhotoManager getInstance() {
        return instance;
    }

    /**
     *
     */
    public boolean hasPhoto(String id) {
        return hasPhoto(PhotoId.getIdFromString(id));
    }

    /**
     *
     */
    public boolean hasPhoto(PhotoId id) {
        return getPhoto(id) != null;
    }

    /**
     *
     */
    public Photo getPhoto(PhotoId id) {
        return instance.getPhotoFromId(id);
    }

    @Override
    public FoodPhoto getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        FoodPhoto result = doGetPhotoFromId(id);

        if (result == null) {
            result = FoodPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    @Override
    protected FoodPhoto doGetPhotoFromId(PhotoId id) {
        return photoCache.get(id);
    }

    protected void doAddPhoto(FoodPhoto myPhoto) {
        photoCache.put(myPhoto.getId(), myPhoto);
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void loadPhotos() {
        Collection<FoodPhoto> existingPhotos = ObjectifyService.run(new Work<Collection<FoodPhoto>>() {
            @Override
            public Collection<FoodPhoto> run() {
                Collection<FoodPhoto> existingPhotos = new ArrayList<FoodPhoto>();
                readObjects(existingPhotos, FoodPhoto.class);
                return existingPhotos;
            }
        });

        for (Photo photo : existingPhotos) {
            if (!doHasPhoto(photo.getId())) {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Load Photo with ID", photo.getIdAsString()).toString());
                loadScaledImages(photo);
                doAddPhoto(photo);
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Already loaded Photo", photo.getIdAsString()).toString());
            }
        }

        log.info(LogBuilder.createSystemMessage().addMessage("All photos loaded.").toString());
    }

    @Override
    protected boolean doHasPhoto(PhotoId id) {
        return photoCache.containsKey(id);
    }

    protected void loadScaledImages(FoodPhoto foodPhoto) {
        String photoIdAsString = foodPhoto.getId().asString();
        ImageStorage imageStorage = ImageStorage.getInstance();

        for (PhotoSize photoSize : PhotoSize.values()) {
            log.config(LogBuilder.createSystemMessage().
                    addAction("loading image").
                    addParameter("image size", photoSize.asString()).
                    addParameter("photo ID", photoIdAsString).toString());
            if (imageStorage.doesImageExist(photoIdAsString, photoSize.asInt())) {
                try {
                    Serializable rawImage = imageStorage.readImage(photoIdAsString, photoSize.asInt());
                    if (rawImage != null && rawImage instanceof Image) {
                        foodPhoto.setImage(photoSize, (Image) rawImage);
                    }
                } catch (IOException e) {
                    log.warning(LogBuilder.createSystemMessage().
                            addParameter("size", photoSize.asString()).
                            addParameter("photo ID", photoIdAsString).
                            addException("Could not load image although it exists", e).toString());
                }
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("Size does not exist", photoSize.asString()).toString());
            }
        }
    }

    public void savePhoto(FoodPhoto foodPhoto) {
        updateObject(foodPhoto);
    }

    @Override
    protected void updateDependents(Persistent obj) {
        if (obj instanceof FoodPhoto) {
            FoodPhoto foodPhoto = (FoodPhoto) obj;
            saveScaledImages(foodPhoto);
            updateTags(foodPhoto);
            UserManager userManager = UserManager.getInstance();
            Client owner = userManager.getClientById(foodPhoto.getOwnerId());
            userManager.saveClient(owner);
        }
    }

    protected void saveScaledImages(FoodPhoto foodPhoto) {
        String photoIdAsString = foodPhoto.getId().asString();
        ImageStorage imageStorage = ImageStorage.getInstance();
        PhotoSize photoSize;
        int it = 0;
        boolean moreSizesExist = true;
        do{
            photoSize = PhotoSize.values()[it];
            it++;
            Image image = foodPhoto.getImage(photoSize);
            if (image != null) {
                try {
                    if (!imageStorage.doesImageExist(photoIdAsString, photoSize.asInt())) {
                        imageStorage.writeImage(image, photoIdAsString, photoSize.asInt());
                    }
                } catch (Exception e) {
                    log.warning(LogBuilder.createSystemMessage().
                            addException("Problem when storing image", e).toString());
                    moreSizesExist = false;
                }
            } else {
                log.config(LogBuilder.createSystemMessage().
                        addParameter("No image for size", photoSize.asString()).toString());
                moreSizesExist = false;
            }
        } while (it < PhotoSize.values().length && moreSizesExist);
    }

    protected void updateTags(FoodPhoto foodPhoto) {
        // delete all existing tags, for the case that some have been removed
        deleteObjects(Tag.class, Tag.PHOTO_ID, foodPhoto.getId().asString());

        // add all current tags to the datastore
        Set<String> tags = new HashSet<String>();
        photoTagCollector.collect(tags, foodPhoto);
        for (Iterator<String> i = tags.iterator(); i.hasNext(); ) {
            Tag tag = new Tag(i.next(), foodPhoto.getId().asString());
            log.config(LogBuilder.createSystemMessage().addParameter("Writing Tag", tag.asString()).toString());
            writeObject(tag);
        }
    }

    @Override
    public void savePhotos() throws IOException {
        updateObjects(photoCache.values());
    }

    @Override
    public Map<PhotoId, FoodPhoto> getPhotoCache() {
        return photoCache;
    }

    @Override
    public Set<FoodPhoto> findPhotosByOwner(String ownerName) {
        Set<FoodPhoto> result = new HashSet<FoodPhoto>();
        readObjects(result, FoodPhoto.class, Photo.OWNER_ID, ownerName);

        for (Iterator<FoodPhoto> i = result.iterator(); i.hasNext(); ) {
            doAddPhoto(i.next());
        }

        return result;
    }

    @Override
    public FoodPhoto getVisiblePhoto(PhotoFilter filter) {
        filter.generateDisplayablePhotoIds();
        return getPhotoFromId(filter.getRandomDisplayablePhotoId());
    }

    @Override
    public FoodPhoto createPhoto(String filename, Image uploadedImage) throws Exception {
        PhotoId id = PhotoId.getNextId();
        FoodPhoto result = FoodPhotoUtil.createPhoto(filename, id, uploadedImage);
        addPhoto(result);
        return result;
    }

    public void addPhoto(FoodPhoto foodPhoto) throws IOException {
        PhotoId id = foodPhoto.getId();
        assertIsNewPhoto(id);
        doAddPhoto(foodPhoto);

        GlobalsManager.getInstance().saveGlobals();
    }
}
