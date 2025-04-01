package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import app.KILowBites;
import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;
import cooking.Meal;
import gui.AddIngredientWindow;
import gui.RecipeEditor;
import gui.RecipeSearch;
import gui.MealSearch;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.FileUtilities;
import utilities.InputUtilities;

/**
 * RecipeSearch controller class. Handles the actions of the RecipeSearch GUI elements
 *
 * @author f24team3d
 * @version 12/2/24
 */
public class MealSearchController implements ActionListener
{
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  // private ArrayList<Recipe> recipes; // the recipes to search
  private ArrayList<Recipe> recipesFiltered; // recipes that meet the search criteria

  private HashMap<Meal, List<Recipe>> meals; // the meals to search
  private HashMap<Meal, List<Recipe>> mealsFiltered; // meals that meet the search criteria

  private MealSearch mealSearch;

  private String searchTerm;

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

  public void actionPerformed(ActionEvent e)
  {
    String command;
    command = e.getActionCommand();

    // commands for toolbar
    if (command.equals("SEARCH"))
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
        System.out.println("search " + searchTerm);

        try
        {
          meals = FileUtilities.openMealDirectory2();
          mealsFiltered = new HashMap<>();

          if (meals == null)
          {
            System.out.println("User cancelled file selection.");
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
                    System.out.println("Ingredient Found: " + ingredient.getName() + " in " + recipe.getName() + " in " + meal.getName());
                    recipesFiltered.add(recipe);
                    System.out.println();
                  }
              }
            }

            System.out.println("debug1");

            // if meal contains the filtered recipes, add it
            for (Recipe recipeTemp : recipesFiltered)
            {
              if (meal.getRecipes().contains(recipeTemp))
              {
                System.out.println("put " + meal.getName() + " " + recipeTemp.getName() + " in mealsFiltered");
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
        }
        catch (Exception e1)
        {
          System.out.println("Canceled file selection, or no files found.");
          e1.printStackTrace();
        }

        System.out.println("debug");

        // display the filtered meals + recipes
        ArrayList<String> mealsToDisplay = new ArrayList<>();
        for (Meal tempMeal2 : mealsFiltered.keySet())
        {
          for (Recipe recipe : mealsFiltered.get(tempMeal2))
          {
            if (!mealsToDisplay.contains(tempMeal2.getName() + ".mel (" + recipe.getName() + ")"))
            {
              mealsToDisplay.add(tempMeal2.getName() + ".mel (" + recipe.getName() + ")");
            }
          }
        }

        // display the filtered recipes
        mealSearch.updateDisplayList(mealsToDisplay);

        // disable search button, enable close button
        mealSearch.getButton("SEARCH").setEnabled(false);
        mealSearch.getButton("CLOSE").setEnabled(true);
      }
    }
    else if (command.equals("CLOSE"))
    {
      searchTerm = "";
      mealSearch.reset();
      recipesFiltered.clear();
      // recipes.clear();
      System.out.println("close");

      // enable search button, disable close button
      mealSearch.getButton("SEARCH").setEnabled(true);
      mealSearch.getButton("CLOSE").setEnabled(false);
    }
  }
}
