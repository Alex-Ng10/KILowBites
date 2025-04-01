package cooking;

import java.util.ArrayList;
import java.util.List;

public class RecipeCharacterization
{
  private String name; // Name of the recipe
  private int serves; // Number of people served
  private List<Ingredients> ingredients;
  private List<Utensils> utensils;
  private List<Steps> steps;

  public RecipeCharacterization(String name, int serves)
  {
    this.name = name;
    this.serves = serves;
    this.ingredients = new ArrayList<>();
    this.utensils = new ArrayList<>();
    this.steps = new ArrayList<>();
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getServes()
  {
    return serves;
  }

  public void setServes(int serves)
  {
    this.serves = serves;
  }

  public void addIngredient(Ingredients ingredient)
  {
    ingredients.add(ingredient);
  }

  public List<Ingredients> getIngredients()
  {
    return ingredients;
  }

  public void addUtensils(Utensils utensil)
  {
    utensils.add(utensil);
  }

  public List<Utensils> getUtensils()
  {
    return utensils;
  }

  public void addStep(Steps step)
  {
    steps.add(step);
  }

  public List<Steps> getSteps()
  {
    return steps;
  }

  public double getScalingFactor(int newServings)
  {
    if (serves == 0)
    {
      throw new IllegalArgumentException("Current serving cannot be zero");
    }

    return (double) newServings / serves; // return the correct amount of serving per person
  }
  
  
}
