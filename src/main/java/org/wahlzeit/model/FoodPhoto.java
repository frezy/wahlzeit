package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;

import java.util.EnumSet;

@Entity
public class FoodPhoto extends Photo {

    //private Food food;

    public FoodPhoto() {
        super();
    }

    public FoodPhoto(PhotoId myId) {
        super(myId);
    }

    /*public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }*/

    /*public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }*/

    /**
     * @methodtype boolean-query
     */
    public boolean hasSameOwner(FoodPhoto foodPhoto) {
        return foodPhoto.getOwnerEmailAddress().equals(ownerEmailAddress);
    }
}
