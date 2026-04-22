package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import utilities.Foods;

/**
 * Test cases for Foods class.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

class FoodsTest
{
  @Test
  void testFoods()
  {
    Foods foods = new Foods();
    foods.getDefaultFoods();

    assertEquals(foods.getSize(), foods.getSize());

    String[] foodNames = foods.getFoodNames();
    assertEquals(foodNames.length, foodNames.length);

    assertEquals("apple juice", foodNames[5]);

    assertEquals(foods.getFoods().size(), foods.getFoods().size());

    double density = foods.getDensity("apple");

    assertEquals(0.56, density);

    assertEquals(true, foods.addFoods("Test", new double[] {0.00, 0.00}));
    assertEquals(true, foods.addFoods("lalala", new double[] {0.00, 0.00}));
    assertEquals(false, foods.addFoods("Test", new double[] {0.00, 0.00}));

    assertEquals(true, foods.containsFood("Test"));
  }

}
