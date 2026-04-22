package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import app.KILowBites;
import cooking.Ingredients;
import cooking.Recipe;
import cooking.Meal;
import gui.RecipeSearch;
import gui.MealSearch;
import utilities.FileUtilities;

/**
 * RecipeSearch controller class. Handles the actions of the RecipeSearch GUI elements
 *
 * @author f24team3d
 * @version 12/2/24
 */
public class MealSearchController implements ActionListener
{
  static final Locale LOCALE = Locale.getDefault();
  @SuppressWarnings("unused")
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  // private ArrayList<Recipe> recipes; // the recipes to search
  private ArrayList<Recipe> recipesFiltered; // recipes that meet the search criteria

  private HashMap<Meal, List<Recipe>> meals; // the meals to search
  private HashMap<Meal, List<Recipe>> mealsFiltered; // meals that meet the search criteria

  private MealSearch mealSearch;

  private String searchTerm;
  private String search = "SEARCH";
  private String close = "CLOSE";
  private String me = ".mel (";
  private String para = ")";
  
  /**
   * Constructor.
   */
  public MealSearchController()
  {
    searchTerm = "";
    recipesFiltered = new ArrayList<>();
    createRecipeSearch();
  }

  /**
   * Creates the Recipe Search window.
   */
  private void createRecipeSearch()
  {
    mealSearch = new MealSearch(this);
    mealSearch.setSize(800, 600);
    mealSearch.setDefaultCloseOperation(RecipeSearch.DISPOSE_ON_CLOSE);
    mealSearch.setVisible(true);
    mealSearch.setResizable(false);
  }
  

  /**
   * Searches for the meal.
   * @param e
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command;
    command = e.getActionCommand();

    // commands for toolbar
    if (command.equals(search))
    {
      // get user to enter search criteria
      if (mealSearch.getSearchString().equals(""))
      {
        System.out.println("Please enter a search term.");
        return;
      }
      else
      {
        searchTerm = mealSearch.getSearchString();
        meals = FileUtilities.openMealDirectory2();
        mealsFiltered = new HashMap<>();

        if (meals == null)
        {
          return;
        }
        for (Meal meal : meals.keySet()) // search each recipe for ingredient
        {
          for (Recipe recipe : meal.getRecipes())
          {
            for (Ingredients ingredient : recipe.getIngredients())
            {
              // if search criteria matches...
              if (ingredient.getName().toLowerCase().contains(searchTerm.toLowerCase()))
              {
                recipesFiltered.add(recipe);
              }
            }
          }

            // if meal contains the filtered recipes, add it
          for (Recipe recipeTemp : recipesFiltered)
          {
            if (meal.getRecipes().contains(recipeTemp))
            {
              List<Recipe> temps = new ArrayList<>();
              for (Recipe thisRecipe : recipesFiltered)
              {
                temps.add(thisRecipe);
              }
              mealsFiltered.put(meal, temps);
            }
          }
          recipesFiltered.clear();
        }


        // display the filtered meals + recipes
        ArrayList<String> mealsToDisplay = new ArrayList<>();
        for (Meal tempMeal2 : mealsFiltered.keySet())
        {
          for (Recipe recipe : mealsFiltered.get(tempMeal2))
          {
            if (!mealsToDisplay.contains(tempMeal2.getName() + me + recipe.getName() + para))
            {
              mealsToDisplay.add(tempMeal2.getName() + me + recipe.getName() + para);
            }
          }
        }

        // display the filtered recipes
        mealSearch.updateDisplayList(mealsToDisplay);

        // disable search button, enable close button
        mealSearch.getButton(search).setEnabled(false);
        mealSearch.getButton(close).setEnabled(true);
      }
    }
    else if (command.equals(close))
    {
      searchTerm = "";
      mealSearch.reset();
      recipesFiltered.clear();

      // enable search button, disable close button
      mealSearch.getButton(search).setEnabled(true);
      mealSearch.getButton(close).setEnabled(false);
    }
  }
}
