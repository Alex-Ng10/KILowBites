package cooking;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Meal Class.
 *
 * @author f24team3d
 * @version 10/29/24
 */
public class Meal implements Serializable
{
  private static final long serialVersionUID = -3270513840760669938L;

  private String name;
  private List<Recipe> recipes;
  
  /**
   * Constructor.
   */
  public Meal()
  {
    name = "";
    recipes = new ArrayList<>();
  }

  /**
   * Add recipe to meal.
   * 
   * @param recipe
   *          Recipe being added to meal
   * @return Returns if the recipe exists or doesnt.
   */
  public boolean addRecipe(final Recipe recipe)
  {
    boolean exists = false;

    for (Recipe r : recipes)
    {
      if (r.getName().equals(recipe.getName()))
      {
        exists = true;
        // System.out.println("this recipe exists");
      }
    }

    if (!exists)
    {
      recipes.add(recipe);
      // System.out.println("Should not get here");
    }

    return exists;
  }

  /**
   * Return name of meal.
   * 
   * @return Name of meal
   */
  public String getName()
  {
    return name;
  }

  /**
   * Retrieve recipes in meal.
   * 
   * @return Recipes in meal
   */
  public List<Recipe> getRecipes()
  {
    return recipes;
  }

  /**
   * Remove recipe from meal.
   * 
   * @param recipeName
   *          Recipe being removed from meal
   */
  public void removeRecipe(final String recipeName)
  {
    // add checking for available recipe
    for (Recipe r : recipes)
    {
      if (r.getName().equals(recipeName))
      {
        recipes.remove(r);
        return;
      }
    }
  }
  
  /**
   * Sets the name to the param.
   * @param name
   */
  public void setName(final String name)
  {
    this.name = name;
  }
  
  /**
   * Prints the recipes.
   */
  public void printRecipes()
  {
    for (Recipe r : recipes)
    {
      System.out.println(r.getName());
    }
  }
}
