package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import org.wahlzeit.services.DataObject;

@Entity
public class Food extends DataObject {
    @Id
    Long idLong;

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
