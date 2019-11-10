package org.wahlzeit.model;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.services.OfyService;

import java.util.logging.Logger;

public class FoodPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());
    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static FoodPhotoFactory instance = null;

    /**
     *
     */
    protected FoodPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized FoodPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic FoodPhotoFactory").toString());
            setInstance(new FoodPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(FoodPhotoFactory foodPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize FoodPhotoFactory twice");
        }

        instance = foodPhotoFactory;
    }


    @Override
    public FoodPhoto createPhoto() {
        return new FoodPhoto();
    }

    @Override
    public FoodPhoto createPhoto(PhotoId id) {
        return new FoodPhoto(id);
    }

    /**
     * Loads a photo. The Java object is loaded from the Google Datastore, the Images in all sizes are loaded from the
     * Google Cloud storage.
     */
    @Override
    public FoodPhoto loadPhoto(PhotoId id) {
	   /*Photo result =
                OfyService.ofy().load().type(Photo.class).ancestor(KeyFactory.createKey("Application", "Wahlzeit")).filter(Photo.ID, id).first().now();
        for (PhotoSize size : PhotoSize.values()) {
            GcsFilename gcsFilename = new GcsFilename("picturebucket", filename);



        }*/
        return null;
    }
}
