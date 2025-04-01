package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
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
import gui.AddIngredientWindow;
import gui.RecipeEditor;
import gui.RecipeSearch;
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
public class RecipeSearchController implements ActionListener
{
  static final Locale LOCALE = Locale.getDefault();
  private ArrayList<Recipe> recipes; // the recipes to search
  private ArrayList<Recipe> recipesFiltered; // recipes that meet the search criteria
  private RecipeSearch recipeSearch;

  private String searchTerm;
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;


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

  public void actionPerformed(ActionEvent e)
  {
    String command;
    command = e.getActionCommand();

    // commands for toolbar
    if (command.equals(STRINGS.getString("SEARCH")))
    {
      // get user to enter search criteria
      if (recipeSearch.getSearchString().equals(""))
      {
        return;
      }
      else
      {
        searchTerm = recipeSearch.getSearchString();

        try
        {
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

          // add to filtered recipes
          for (Recipe recipe : recipesFiltered)
          {
          }
        }
        catch (Exception e1)
        {
          e1.printStackTrace();
        }

        // display the filtered recipes
        recipeSearch.updateList(recipesFiltered);

        // disable search button, enable close button
        recipeSearch.getButton(STRINGS.getString("SEARCH")).setEnabled(false);
        recipeSearch.getButton(STRINGS.getString("CLOSE")).setEnabled(true);
      }
    }
    else if (command.equals(STRINGS.getString("CLOSE")))
    {
      searchTerm = "";
      recipeSearch.reset();
      recipesFiltered.clear();
      recipes.clear();

      // enable search button, disable close button
      recipeSearch.getButton(STRINGS.getString("SEARCH")).setEnabled(true);
      recipeSearch.getButton(STRINGS.getString("CLOSE")).setEnabled(false);
    }
  }
}
