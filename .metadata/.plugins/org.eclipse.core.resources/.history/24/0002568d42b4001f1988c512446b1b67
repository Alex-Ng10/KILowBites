package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import app.KILowBites;
import cooking.Meal;
import gui.CalorieCalculatorWindow;
import gui.ShoppingListWindow;
import gui.UnitConverterWindow;
import gui.UnitTypeWindow;
import utilities.FileUtilities;

/**
 * KILowBites controller class. Handles the actions of the main menu items.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class KILowBitesController implements ActionListener
{
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private RecipeEditorController recipeController;
  private ProcessViewerController recipeViewerController;

  private RecipeSearchController recipeSearchController;
  private MealSearchController mealSearchController;

  private MealEditorController mealController;
  private KILowBites window;

  /**
   * Constructor.
   * 
   * @param window
   *          Parent window
   */
  public KILowBitesController(final KILowBites window)
  {
    this.window = window;
  }

  /**
   * Method for when action is performed.
   * 
   * @param e
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command = e.getActionCommand();

    // commands for Edit
    if (command.equals(STRINGS.getString("RECIPE")))
    {
      recipeController = new RecipeEditorController();
    }
    if (command.equals(STRINGS.getString("MEAL")))
    {
      mealController = new MealEditorController();
    }

    // commands for Search
    if (command.equals(STRINGS.getString("RECIPES")))
    {
      recipeSearchController = new RecipeSearchController();
    }
    if (command.equals(STRINGS.getString("MEALS")))
    {
      mealSearchController = new MealSearchController();
    }

    // commands for View
    if (command.equals(STRINGS.getString("SHOPPING_LIST")))
    {
      Meal meal = FileUtilities.openMeal();

      if (meal != null)
      {
        ShoppingListWindow shoppingList = new ShoppingListWindow(meal);
        shoppingList.setVisible(true);
      }
    }
    if (command.equals(STRINGS.getString("PROCESS")))
    {
      recipeViewerController = new ProcessViewerController();
    }

    // commands for Tools
    if (command.equals(STRINGS.getString("CALORIE_CALCULATOR")))
    {
      // System.out.println("Opening calorie calculator...");
      CalorieCalculatorWindow calorieCalc = new CalorieCalculatorWindow(window);
      calorieCalc.setVisible(true);
      window.getOpenCalc().setEnabled(false);
    }
    if (command.equals(STRINGS.getString("UNITS_CONVERTER")))
    {
      // System.out.println("Opening unit converter...");
      UnitConverterWindow unitConverter = new UnitConverterWindow(window);
      unitConverter.setVisible(true);
      window.getOpenConvert().setEnabled(false);
    }

    // commands for Configure
    if (command.equals(STRINGS.getString("PREFERENCES")))
    {
      // System.out.println("open preferences");
      UnitTypeWindow unitTypeWindow = new UnitTypeWindow();
      unitTypeWindow.setVisible(true);
    }
    if (command.equals(STRINGS.getString("SHORTCUTS")))
    {
      // System.out.println("open shortcuts");
    }

    // commands for Help
    if (command.equals(STRINGS.getString("ABOUT")))
    {
      // System.out.println("open about");
    }
    if (command.equals(STRINGS.getString("USER_GUIDE")))
    {
      // System.out.println("open user guide");
    }
  }
}
