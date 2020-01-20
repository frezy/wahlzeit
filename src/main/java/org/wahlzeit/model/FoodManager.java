package org.wahlzeit.model;

import org.wahlzeit.services.ObjectManager;

import java.util.HashMap;
import java.util.Map;

public class FoodManager {
    private static final FoodManager instance = new FoodManager();

    public static FoodManager getInstance() {
        return instance;
    }

    private static final Map<String, Food> foods = new HashMap<>();
    private static final Map<String, FoodType> foodTypes = new HashMap<>();

    public Food createFood(String typeName) {
        assertIsValidTypeName(typeName);
        FoodType foodType = getFoodType(typeName);
        Food food = foodType.createInstance();
        foods.put(typeName, food);
        return food;
    }

    private FoodType getFoodType(String foodType) {
        if(foodTypes.containsKey(foodType)) {
            return foodTypes.get(foodType);
        } else {
            foodTypes.put(foodType, new FoodType(foodType));
            return foodTypes.get(foodType);
        }
    }

    private void assertIsValidTypeName(String typeName) {
        assert typeName != null;
        assert !typeName.isEmpty();
        //assert foodTypes.containsKey(typeName);
    }
}
