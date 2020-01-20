package org.wahlzeit.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FoodTest {
    @Test
    public void testCreateFood() {
        Food food = FoodManager.getInstance().createFood("Soup");
        assertNotNull(food);
        assertEquals(food.getId(), "Soup");
    }
}
