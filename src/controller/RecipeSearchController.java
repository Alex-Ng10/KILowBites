package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import app.KILowBites;
import cooking.Ingredients;
import cooking.Recipe;
import gui.RecipeSearch;
import utilities.FileUtilities;

/**
 * RecipeSearch controller class. Handles the actions of the RecipeSearch GUI elements
 *
 * @author f24team3d
 * @version 12/2/24
 */
public class RecipeSearchController implements ActionListener
{
  static final Locale LOCALE = Locale.getDefault();
  @SuppressWarnings("unused")
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private ArrayList<Recipe> recipes; // the recipes to search
  private ArrayList<Recipe> recipesFiltered; // recipes that meet the search criteria
  private RecipeSearch recipeSearch;
  private String searchTerm;
  private String search = "SEARCH";
  private String close = "CLOSE";


  /**
   * Constructor.
   */
  public RecipeSearchController()
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
    recipeSearch = new RecipeSearch(this);
    recipeSearch.setSize(800, 600);
    recipeSearch.setDefaultCloseOperation(RecipeSearch.DISPOSE_ON_CLOSE);
    recipeSearch.setVisible(true);
    recipeSearch.setResizable(false);
  }
  
  /**
   * Searches for the recipe.
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
      if (recipeSearch.getSearchString().equals(""))
      {
        return;
      }
      else
      {
        searchTerm = recipeSearch.getSearchString();
        recipes = FileUtilities.openRecipeDirectory();

        if (recipes == null)
        {
          return;
        }
        for (Recipe recipe : recipes) // search each recipe for ingredient
        {
          for (Ingredients ingredient : recipe.getIngredients())
          {
            // if search criteria matches...
            if (ingredient.getName().toLowerCase().contains(searchTerm.toLowerCase()))
            {
              if (!recipesFiltered.contains(recipe)) // don't repeat recipes
              {
                recipesFiltered.add(recipe);
              }
            }
          }
        }

        // display the filtered recipes
        recipeSearch.updateList(recipesFiltered);

        // disable search button, enable close button
        recipeSearch.getButton(search).setEnabled(false);
        recipeSearch.getButton(close).setEnabled(true);
      }
    }
    else if (command.equals(close))
    {
      searchTerm = "";
      recipeSearch.reset();
      recipesFiltered.clear();
      recipes.clear();

      // enable search button, disable close button
      recipeSearch.getButton(search).setEnabled(true);
      recipeSearch.getButton(close).setEnabled(false);
    }
  }
}
