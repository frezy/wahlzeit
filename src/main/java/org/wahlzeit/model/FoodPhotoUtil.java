package org.wahlzeit.model;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class FoodPhotoUtil extends PhotoUtil {

    private static final Logger log = Logger.getLogger(FoodPhotoUtil.class.getName());

    /**
     * @methodtype creation
     */
    public static FoodPhoto createPhoto(String filename, PhotoId id, Image uploadedImage) throws Exception {
        FoodPhoto result = FoodPhotoFactory.getInstance().createPhoto(id);
        result.setEnding(filename.substring(filename.lastIndexOf(".") + 1));

        createImageFiles(uploadedImage, result);

        int sourceWidth = uploadedImage.getWidth();
        int sourceHeight = uploadedImage.getHeight();
        result.setWidthAndHeight(sourceWidth, sourceHeight);

        return result;
    }

    /**
     *
     */
    public static void createImageFiles(Image source, FoodPhoto foodPhoto) throws Exception {
        assertIsValidImage(source);

        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();
        assertHasValidSize(sourceWidth, sourceHeight);

        for (PhotoSize size : PhotoSize.values()) {
            if (!size.isWiderAndHigher(sourceWidth, sourceHeight)) {
                scaleImage(ImagesServiceFactory.makeImage(source.getImageData()), size, foodPhoto);
            }
        }
    }

    /**
     * @methodtype command Scale the source picture to the given size, store it in the datastore and reference it in the
     * photo.
     */
    protected static void scaleImage(Image source, PhotoSize size, FoodPhoto foodPhoto) throws Exception {
        int sourceWidth = source.getWidth();
        int sourceHeight = source.getHeight();

        int targetWidth = size.calcAdjustedWidth(sourceWidth, sourceHeight);
        int targetHeight = size.calcAdjustedHeight(sourceWidth, sourceHeight);

        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        Transform resize = ImagesServiceFactory.makeResize(targetWidth, targetHeight);
        Image newImage = imagesService.applyTransform(resize, source);

        foodPhoto.setImage(size, newImage);

        log.config(LogBuilder.createSystemMessage().addParameter("Scaled image to size", size.asString()).toString());
    }
}
