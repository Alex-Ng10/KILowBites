package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cooking.Meal;
import cooking.Recipe;

import java.util.List;

class MealTest
{

  private Meal meal;
  private Recipe recipe1;
  private Recipe recipe2;

  @BeforeEach
  void setUp()
  {
    meal = new Meal();
    recipe1 = new Recipe();
    recipe1.setName("Recipe 1");

    recipe2 = new Recipe();
    recipe2.setName("Recipe 2");
  }

  @Test
  void testAddRecipe()
  {
    // Add the first recipe and check if it is added
    boolean addedFirst = meal.addRecipe(recipe1);
    List<Recipe> recipes = meal.getRecipes();

    assertFalse(addedFirst); // Should return false as it was not a duplicate
    assertEquals(1, recipes.size()); // One recipe should be in the meal
    assertEquals("Recipe 1", recipes.get(0).getName());

    // Try adding the same recipe again
    boolean addedDuplicate = meal.addRecipe(recipe1);
    assertTrue(addedDuplicate); // Should return true because it's a duplicate
    assertEquals(1, recipes.size()); // Still one recipe in the meal

    // Add a different recipe
    boolean addedSecond = meal.addRecipe(recipe2);
    assertFalse(addedSecond); // Should return false as it's not a duplicate
    assertEquals(2, recipes.size()); // Two recipes should be in the meal
  }

  @Test
  void testRemoveRecipe()
  {
    meal.addRecipe(recipe1);
    meal.addRecipe(recipe2);
    List<Recipe> recipes = meal.getRecipes();
    assertEquals(2, recipes.size());

    // Remove the first recipe
    meal.removeRecipe("Recipe 1");
    assertEquals(1, recipes.size());
    assertEquals("Recipe 2", recipes.get(0).getName());

    // Try to remove a recipe that doesn't exist
    meal.removeRecipe("Non-existing Recipe");
    assertEquals(1, recipes.size()); // Size should remain the same
  }

  @Test
  void testGetNameAndSetName()
  {
    meal.setName("My Meal");
    assertEquals("My Meal", meal.getName());
  }

  @Test
  void testGetRecipes()
  {
    assertTrue(meal.getRecipes().isEmpty()); // Initially empty

    meal.addRecipe(recipe1);
    meal.addRecipe(recipe2);

    List<Recipe> recipes = meal.getRecipes();
    assertEquals(2, recipes.size()); // Should have two recipes

    assertEquals("Recipe 1", recipes.get(0).getName());
    assertEquals("Recipe 2", recipes.get(1).getName());
  }

  @Test
  void testPrintRecipes()
  {
    meal.addRecipe(recipe1);
    meal.addRecipe(recipe2);

    assertDoesNotThrow(() -> meal.printRecipes()); // This method prints to the console, so just
                                                   // check that no exceptions are thrown
  }
}
