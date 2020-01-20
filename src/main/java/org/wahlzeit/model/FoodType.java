package org.wahlzeit.model;

/*public enum FoodType {
    Vegan,
    Vegetarian,
    Regular
}*/

import org.wahlzeit.services.DataObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FoodType {
    private String type;
    protected FoodType superType = null;
    protected Set<FoodType> subTypes = new HashSet<FoodType>();

    public FoodType(String foodType) {
        type = foodType;
    }

    public Food createInstance() {
        return new Food(this);
    }

    public FoodType getSuperType() {
        return superType;
    }

    public void setSuperType(FoodType superType) {
        this.superType = superType;
    }

    public Iterator<FoodType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void addSubType(FoodType foodType) {
        foodType.setSuperType(this);
        subTypes.add(foodType);
    }

    public boolean hasInstance(Food food) {
        if(food.getType() == this) {
            return true;
        }

        for(FoodType foodType : subTypes) {
            if(foodType.hasInstance(food)) {
                return true;
            }
        }

        return false;
    }
}
