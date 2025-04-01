package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.KILowBites;
import gui.CalorieCalculatorWindow;
import gui.UnitConverterWindow;

/**
 * KILowBites controller class. Handles the actions of the main menu items.
 *
 * @author f24team3d
 * @version 10/24/24
 */
public class KILowBitesController implements ActionListener
{
  // File
  private static final String EXIT = "Exit";

  // Edit
  private static final String RECIPE = "Recipe";
  private static final String MEAL = "Meal";

  // Search
  private static final String RECIPES = "Recipes";
  private static final String MEALS = "Meals";

  // View
  private static final String SHOPPING_LIST = "Shopping List";
  private static final String PROCESS = "Process";

  // Tools
  private static final String CALORIE_CALCULATOR = "Calorie Calculator";
  private static final String UNITS_CONVERTER = "Units Converter";

  // Configure
<<<<<<< HEAD
  private static final String PREFERENCES = "Preferences";
  private static final String SHORTCUTS = "Shortcuts";
=======
  private static String PREFERENCES = "Preferences";
  private static String SHORTCUTS = "Shortcuts";
  private static String ADD_INGREDIENT = "Add Ingredient";
>>>>>>> e25f913ed71b05a19a44e5f92cbd6a133eabe6ec

  // Help
  private static final String ABOUT = "About";
  private static final String USER_GUIDE = "User Guide";

  private RecipeEditorController recipeController;
  private ProcessViewerController recipeViewerController;

  private MealEditorController mealController;

  /**
   * Constructor for controller.
   */
  public KILowBitesController()
  {
    // empty constructor
  }

  /**
   * Method for when action is performed
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command;

    command = e.getActionCommand();

    // commands for File
    if (command.equals(EXIT))
    {
      System.exit(0);
    }

    // commands for Edit
    if (command.equals(RECIPE))
    {
      recipeController = new RecipeEditorController();
    }
    if (command.equals(MEAL))
    {
      mealController = new MealEditorController();
    }

    // commands for Search
    if (command.equals(RECIPES))
    {
      System.out.println("recipe search");
    }
    if (command.equals(MEALS))
    {
      System.out.println("meal search");
    }

    // commands for View
    if (command.equals(SHOPPING_LIST))
    {
      System.out.println("view shopping list");
    }
    if (command.equals(PROCESS))
    {
      recipeViewerController = new ProcessViewerController();
      System.out.println("view process");
    }

    // commands for Tools
    if (command.equals(CALORIE_CALCULATOR))
    {
      System.out.println("Opening calorie calculator...");
      CalorieCalculatorWindow calorieCalc = new CalorieCalculatorWindow();
      calorieCalc.setVisible(true);
      KILowBites.openCalc.setEnabled(false);
    }
    if (command.equals(UNITS_CONVERTER))
    {
      System.out.println("Opening unit converter...");
      UnitConverterWindow unitConverter = new UnitConverterWindow();
      unitConverter.setVisible(true);
      KILowBites.openConvert.setEnabled(false);
    }

    // commands for Configure
    if (command.equals(PREFERENCES))
    {
      System.out.println("open preferences");
    }
    if (command.equals(SHORTCUTS))
    {
      System.out.println("open shortcuts");
    }
    if (command.equals(ADD_INGREDIENT))
    {
      System.out.println("Get this shiiii");
    }

    // commands for Help
    if (command.equals(ABOUT))
    {
      System.out.println("open about");
    }
    if (command.equals(USER_GUIDE))
    {
      System.out.println("open user guide");
    }
  }
}
