package cooking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Recipe class. Contains a name, people served. Also includes ingredients, utensils, and steps of a
 * recipe.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class Recipe implements Serializable
{
  private static final long serialVersionUID = -8722115314424841498L;

  private String name; // Name of the recipe
  private int serves; // Number of people served
  private List<Ingredients> ingredients;
  private List<Utensils> utensils;
  private List<Steps> steps;

  /**
   * Default constructor.
   */
  public Recipe()
  {
    this.name = "";
    this.serves = 0;
    this.ingredients = new ArrayList<>();
    this.utensils = new ArrayList<>();
    this.steps = new ArrayList<>();
  }

  /**
   * Constructor with parameters.
   *
   * @param name
   * @param serves
   */
  public Recipe(final String name, final int serves)
  {
    this.name = name;
    this.serves = serves;
    this.ingredients = new ArrayList<>();
    this.utensils = new ArrayList<>();
    this.steps = new ArrayList<>();
  }

  /**
   * Get the name of the recipe.
   *
   * @return the name of the recipe
   */
  public String getName()
  {
    return name;
  }

  /**
   * Set the name of the recipe.
   *
   * @param name
   */
  public void setName(final String name)
  {
    this.name = name;
  }

  /**
   * Get the number of people served.
   *
   * @return the number of people served
   */
  public int getServes()
  {
    return serves;
  }

  /**
   * Set the number of people served.
   * 
   * @param serves
   */
  public void setServes(final int serves)
  {
    this.serves = serves;
  }

  /**
   * Add an ingredient to the recipe.
   *
   * @param ingredient
   */
  public void addIngredient(final Ingredients ingredient)
  {
    ingredients.add(ingredient);
    Collections.sort(ingredients, Comparator.comparing(i -> i.getName().toLowerCase()));
  }

  /**
   * Get the ingredients of the recipe.
   *
   * @return the ingredients of the recipe
   */
  public List<Ingredients> getIngredients()
  {
    return ingredients;
  }

  /**
   * Add a utensil to the recipe.
   *
   * @param utensil
   */
  public void addUtensils(final Utensils utensil)
  {
    utensils.add(utensil);
    Collections.sort(utensils, Comparator.comparing(u -> u.getName().toLowerCase()));
  }

  /**
   * Get the utensils of the recipe.
   *
   * @return the utensils of the recipe
   */
  public List<Utensils> getUtensils()
  {
    return utensils;
  }
  
  /**
   * removes the utensils of the recipe.
   *
   * @param utensil 
   */
  public void removeUtensils(final Utensils utensil)
  {
    utensils.remove(utensil);
  }
  
  /**
   * removes the ingredient in the recipe.
   *
   * @param ingredient 
   */
  public void removeIngredients(final Ingredients ingredient)
  {
    ingredients.remove(ingredient);
  }
  
  /**
   * removes the step in the recipe.
   *
   * @param step 
   */
  public void removeSteps(final Steps step)
  {
    steps.remove(step);
  }


  /**
   * Add a step to the recipe.
   *
   * @param step
   */
  public void addStep(final Steps step)
  {
    steps.add(step);
  }

  /**
   * Get the steps of the recipe.
   *
   * @return the steps of the recipe
   */
  public List<Steps> getSteps()
  {
    return steps;
  }

  /**
   * Get the scaling factor for the recipe.
   *
   * @param newServings
   * @return the scaling factor for the recipe
   */
  public double getScalingFactor(final int newServings)
  {
    if (serves == 0)
    {
      throw new IllegalArgumentException("Current serving cannot be zero");
    }

    return (double) newServings / serves; // return the correct amount of serving per person
  }
}
