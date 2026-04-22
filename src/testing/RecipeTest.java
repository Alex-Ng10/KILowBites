package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;

public class RecipeTest
{

  private Recipe recipe;

  @BeforeEach
  public void setup()
  {
    recipe = new Recipe("Test Recipe", 4);
  }

  @Test
  public void testDefaultConstructor()
  {
    Recipe defaultRecipe = new Recipe();
    assertEquals("", defaultRecipe.getName());
    assertEquals(0, defaultRecipe.getServes());
    assertTrue(defaultRecipe.getIngredients().isEmpty());
    assertTrue(defaultRecipe.getUtensils().isEmpty());
    assertTrue(defaultRecipe.getSteps().isEmpty());
  }

  @Test
  public void testParameterizedConstructor()
  {
    assertEquals("Test Recipe", recipe.getName());
    assertEquals(4, recipe.getServes());
    assertTrue(recipe.getIngredients().isEmpty());
    assertTrue(recipe.getUtensils().isEmpty());
    assertTrue(recipe.getSteps().isEmpty());
  }

  @Test
  public void testSetName()
  {
    recipe.setName("New Recipe");
    assertEquals("New Recipe", recipe.getName());
  }

  @Test
  public void testSetServes()
  {
    recipe.setServes(6);
    assertEquals(6, recipe.getServes());
  }

  @Test
  public void testAddAndRemoveIngredient()
  {
    Ingredients ingredient = new Ingredients(100, "grams", "fine", "Sugar");
    recipe.addIngredient(ingredient);
    List<Ingredients> ingredients = recipe.getIngredients();
    assertEquals(1, ingredients.size());
    assertEquals("Sugar", ingredients.get(0).getName());

    recipe.removeIngredients(ingredient);
    assertTrue(recipe.getIngredients().isEmpty());
  }

  @Test
  public void testAddAndRemoveUtensils()
  {
    Utensils utensil = new Utensils("Spatula", "wooden");
    recipe.addUtensils(utensil);
    List<Utensils> utensils = recipe.getUtensils();
    assertEquals(1, utensils.size());
    assertEquals("Spatula", utensils.get(0).getName());

    recipe.removeUtensils(utensil);
    assertTrue(recipe.getUtensils().isEmpty());
  }

  @Test
  public void testAddAndRemoveSteps()
  {
    Ingredients ingredient = new Ingredients(100, "grams", "fine", "Sugar");
    Utensils utensil = new Utensils("Spatula", "wooden");
    Steps step = new Steps("Stir", ingredient, utensil, "until well mixed");
    recipe.addStep(step);
    List<Steps> steps = recipe.getSteps();
    assertEquals(1, steps.size());
    assertEquals("Stir", steps.get(0).getAction());

    recipe.removeSteps(step);
    assertTrue(recipe.getSteps().isEmpty());
  }

  @Test
  public void testGetScalingFactor()
  {
    double scalingFactor = recipe.getScalingFactor(8);
    assertEquals(2.0, scalingFactor);

    assertThrows(IllegalArgumentException.class, () -> {
      new Recipe("Zero Serving", 0).getScalingFactor(8);
    });
  }

  @Test
  public void testSortingIngredients()
  {
    Ingredients sugar = new Ingredients(100, "grams", "fine", "Sugar");
    Ingredients flour = new Ingredients(200, "grams", "all-purpose", "Flour");

    recipe.addIngredient(sugar);
    recipe.addIngredient(flour);

    List<Ingredients> ingredients = recipe.getIngredients();
    assertEquals("Flour", ingredients.get(0).getName());
    assertEquals("Sugar", ingredients.get(1).getName());
  }

  @Test
  public void testSortingUtensils()
  {
    Utensils spoon = new Utensils("Spoon", "metal");
    Utensils spatula = new Utensils("Spatula", "wooden");

    recipe.addUtensils(spatula);
    recipe.addUtensils(spoon);

    List<Utensils> utensils = recipe.getUtensils();
    assertEquals("Spatula", utensils.get(0).getName());
    assertEquals("Spoon", utensils.get(1).getName());
  }

  @Test
  public void testToStringSteps()
  {
    Ingredients ingredient = new Ingredients(100, "grams", "fine", "Sugar");
    Utensils utensil = new Utensils("Spatula", "wooden");
    Steps step = new Steps("Stir", ingredient, utensil, "until well mixed");

    String stepString = step.toString();
    assertEquals("Stir the Sugar in the Spatula until well mixed", stepString);
  }
}
