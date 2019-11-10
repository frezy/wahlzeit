package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;

import java.util.EnumSet;

@Entity
public class FoodPhoto extends Photo {

    private FoodType foodType;
    private EnumSet<FoodAllergens> foodAllergens;

    public FoodPhoto() {
        super();
    }

    public FoodPhoto(PhotoId myId) {
        super(myId);
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public EnumSet<FoodAllergens> getFoodAllergens() {
        return foodAllergens;
    }

    public void setFoodAllergens(EnumSet<FoodAllergens> foodAllergens) {
        this.foodAllergens = foodAllergens;
    }

    /**
     * @methodtype boolean-query
     */
    public boolean hasSameOwner(FoodPhoto foodPhoto) {
        return foodPhoto.getOwnerEmailAddress().equals(ownerEmailAddress);
    }
}
