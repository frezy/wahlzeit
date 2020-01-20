package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

public class Food {
    protected FoodType type = null;

    public Food() {

    }

    public Food(FoodType foodType) {
        type = foodType;
    }

    public String getId() {
        return type.toString();
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType foodType) {
        this.type = foodType;
    }
}
