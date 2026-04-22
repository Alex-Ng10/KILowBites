package testing;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;

/**
 * Test cases for Cooking package.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

class CookingTests
{
  @Test
  void testIngredients()
  {
    Ingredients ing = new Ingredients();
    assertEquals(0.0, ing.getAmount());
    assertEquals("", ing.getUnit());
    assertEquals(" ", ing.getDetails());
    assertEquals("", ing.getName());
  }

  @Test
  void testIngredients1()
  {
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    assertEquals(3.0, ing.getAmount());
    assertEquals("individual", ing.getUnit());
    assertEquals(" (sliced ripe)", ing.getDetails());
    assertEquals("bananas", ing.getName());
  }

  @Test
  void testIngredients2()
  {
    Ingredients ing = new Ingredients();
    ing.setAmount(3.0);
    ing.setUnit("individual");
    ing.setDetails("(sliced ripe)");
    ing.setName("bananas");
    assertEquals(3.0, ing.getAmount());
    assertEquals("individual", ing.getUnit());
    assertEquals(" (sliced ripe)", ing.getDetails());
    assertEquals("bananas", ing.getName());

//    assertEquals("3.0 individual of(sliced ripe) bananas", ing.toString());
  }

  @Test
  void testUtensils()
  {
    Utensils uten = new Utensils();
    assertEquals("", uten.getDetails());
    assertEquals("", uten.getName());
  }

  @Test
  void testUtensils1()
  {
    Utensils uten = new Utensils("Skillet", "large");
    assertEquals("large", uten.getDetails());
    assertEquals("Skillet", uten.getName());
  }

  @Test
  void testUtensils2()
  {
    Utensils uten = new Utensils("Skillet", "large");
    uten.setDetails("small");
    uten.setName("pan");
    assertEquals("small", uten.getDetails());
    assertEquals("pan", uten.getName());

    assertEquals("small pan", uten.toString());
  }

  @Test
  void testSteps()
  {
    Steps steps = new Steps();
    assertEquals("", steps.getAction());
    assertEquals(null, steps.getSource());
    assertEquals(null, steps.getDestination());
    assertEquals("", steps.getDetails());
  }

  @Test
  void testSteps1()
  {
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    Utensils uten = new Utensils("Skillet", "large");
    Steps steps = new Steps("simmer", ing, uten, "for 2 minutes");
    assertEquals("simmer", steps.getAction());
    assertEquals(ing, steps.getSource());
    assertEquals(uten, steps.getDestination());
    assertEquals("for 2 minutes", steps.getDetails());
  }

  @Test
  void testSteps2()
  {
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    Utensils uten = new Utensils("Skillet", "large");
    Steps steps = new Steps("", null, null, "");
    steps.setAction("simmer");
    steps.setSource(ing);
    steps.setDestination(uten);
    steps.setDetails("for 2 minutes");
    assertEquals("simmer", steps.getAction());
    assertEquals(ing, steps.getSource());
    assertEquals(uten, steps.getDestination());
    assertEquals("for 2 minutes", steps.getDetails());
//    assertEquals("simmer the bananas in the Skillet for 2 minutes", steps.toString());
  }

  @Test
  void testRecipe()
  {
    Recipe recipe = new Recipe();
    assertEquals("", recipe.getName());
    assertEquals(0, recipe.getServes());
    assertEquals(new ArrayList<>(), recipe.getIngredients());
    assertEquals(new ArrayList<>(), recipe.getUtensils());
    assertEquals(new ArrayList<>(), recipe.getSteps());
  }

  @Test
  void testRecipe1()
  {
    Recipe recipe = new Recipe("Pasta", 4);
    assertEquals("Pasta", recipe.getName());
    assertEquals(4, recipe.getServes());
    assertEquals(new ArrayList<>(), recipe.getIngredients());
    assertEquals(new ArrayList<>(), recipe.getUtensils());
    assertEquals(new ArrayList<>(), recipe.getSteps());
  }

  @Test
  void testRecipe2()
  {
    Recipe recipe = new Recipe("Pasta", 4);
    recipe.setName("Candy");
    recipe.setServes(5);
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    Utensils uten = new Utensils("Skillet", "large");
    Steps steps = new Steps("", null, null, "");
    recipe.addIngredient(ing);
    recipe.addUtensils(uten);
    recipe.addStep(steps);
    assertEquals("Candy", recipe.getName());
    assertEquals(5, recipe.getServes());
    assertEquals(ing, recipe.getIngredients().getFirst());
    assertEquals(uten, recipe.getUtensils().getFirst());
    assertEquals(steps, recipe.getSteps().getFirst());
    recipe.setServes(0);
    assertThrows(IllegalArgumentException.class, () -> {
      recipe.getScalingFactor(6);
    });
    recipe.setServes(5);
    assertEquals(2, recipe.getScalingFactor(10));
  }
}
