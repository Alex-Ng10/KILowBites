
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import converter.MassConverter;
import converter.VolumeConverter;
import cooking.Ingredients;
import cooking.Meal;
import cooking.Recipe;
import gui.CalorieCalculatorWindow;
import gui.CalorieOutputWindow;
import utilities.FileUtilities;
import utilities.InputUtilities;

/**
 * Controller for actions in CalorieCalculatorWindow.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

public class CalorieCalculatorController implements ActionListener, DocumentListener
{
  // private static final String CALCULATE = "Calculate";
  // private static final String RESET = "Reset";
  // private static final String CHOOSE_INGREDIENT = "Choose Ingredient";
  // private static final String CHOOSE_UNIT = "Choose Unit";
  // private static final String OPEN = "Open";
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private String omitted;
  private String invalid = "Invalid input";
  private String gram = "Gram";
  private String milliliter = "Milliliter";
  private String mass = "Mass";
  private String comma = ", ";

  private CalorieCalculatorWindow window;

  /**
   * Constructor.
   * 
   * @param window
   *          Parent window
   */
  public CalorieCalculatorController(final CalorieCalculatorWindow window)
  {
    this.window = window;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(STRINGS.getString("CALCULATE")))
    {
      calculate();
    }
    if (ac.equals(STRINGS.getString("RESET")))
    {
      reset();
      updateResetButton();
      updateCalculateButton();
    }
    if (ac.equals(STRINGS.getString("CHOOSE_INGREDIENT")))
    {
      updateResetButton();
      updateCalculateButton();
    }
    if (ac.equals(STRINGS.getString("CHOOSE_UNIT")))
    {
      updateResetButton();
      updateCalculateButton();
    }
    if (ac.equals(STRINGS.getString("OPEN")))
    {
      // open recipe/meal, and store in object
      // using stored recipe/meal, call calculateRecipe/calculateMeal
      // make output window and pass in recipe/meal & calories

      // System.out.println("Opening recipe/meal...");

      omitted = "";

      CalorieOutputWindow output;
      double calories;
      List<Object> data = FileUtilities.open();

      if (data == null)
      {
        output = new CalorieOutputWindow("Could not open file", null, 0.0);
      }
      else
      {
        String openedFile = (String) data.get(0);

        if (data.get(1) instanceof Recipe)
        {
          Recipe recipe = (Recipe) data.get(1);

          calories = calculateRecipe(recipe);
          output = new CalorieOutputWindow(InputUtilities.separateByCapital(openedFile), omitted,
              calories);
        }
        else
        {
          Meal meal = (Meal) data.get(1);

          calories = calculateMeal(meal);
          output = new CalorieOutputWindow(InputUtilities.separateByCapital(openedFile), omitted,
              calories);
        }
      }

      output.setVisible(true);
    }
  }

  @Override
  public void insertUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateCalculateButton();
  }

  @Override
  public void removeUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateCalculateButton();
  }

  @Override
  public void changedUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateCalculateButton();
  }

  /**
   * Updates output field to calculated number of calories.
   */
  private void calculate()
  {
    double amount;
    try
    {
      // Retrieve and parse the input amount
      amount = Double.parseDouble(window.getCalorieAmount().getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      window.getOutputField().setText(invalid);
      return;
    }

    if (amount < 0)
    {
      window.getOutputField().setText(invalid);
      return;
    }

    // retrieve ingredient name from input and get values associated with ingredient
    String ingredient = window.getIngredientsMenu().getSelectedItem().toString();
    String unit = window.getUnitsMenu().getSelectedItem().toString();
    String unitMeasure = KILowBites.UNITS.unitMeasure(unit);
    double[] values = KILowBites.FOODS.getFoods().get(ingredient);

    // convert to grams/milliliters if not already, depending on mass/volume unit selected
    if (!(unit.equals(gram)) || !(unit.equals(milliliter)))
    {
      if (unitMeasure.equals(mass))
      {
        amount = MassConverter.callerHelp(unit, gram, amount);
      }
      else
      {
        amount = VolumeConverter.callerHelp(unit, milliliter, amount);
      }
    }

    double gramsPerUnit;
    if (unitMeasure.equals(mass))
    {
      // Use the value in grams directly if mass unit
      gramsPerUnit = 1.0; // 1 gram = 1 gram
    }
    else
    {
      // Use g/ml conversion
      gramsPerUnit = values[1];
    }

    // Calculate total calories
    double calories = ((amount * gramsPerUnit) / 100) * values[0];

    // Display result to 2 decimal places
    window.getOutputField().setText(String.format(LOCALE, "%.2f", calories));
  }

  private double calculate(final String ingredient, final String unit, final double amount)
  {
    System.out.println(ingredient);

    String unitMeasure = KILowBites.UNITS.unitMeasure(unit);
    double[] values = KILowBites.FOODS.getFoods().get(ingredient);
    double newAmount = 0.0;

    if (values == null)
    {
      if (omitted.equals(""))
      {
        omitted += ingredient;
      }
      else
      {
        omitted += comma + ingredient;
      }
      return 0.0;
    }

    // convert to grams/milliliters if not already, depending on mass/volume unit selected
    if (!(unit.equals(gram)) || !(unit.equals(milliliter)))
    {
      if (unitMeasure.equals(mass))
      {
        newAmount = MassConverter.callerHelp(unit, gram, amount);
      }
      else
      {
        newAmount = VolumeConverter.callerHelp(unit, milliliter, amount);
      }
    }

    double gramsPerUnit;
    if (unitMeasure.equals(mass))
    {
      // Use the value in grams directly if mass unit
      gramsPerUnit = 1.0; // 1 gram = 1 gram
    }
    else
    {
      // Use g/ml conversion
      gramsPerUnit = values[1];
    }

    // Calculate total calories
    double calories = ((newAmount * gramsPerUnit) / 100) * values[0];

    return calories;
  }

  /**
   * 
   * @param meal
   * @return returns the total calories.
   */
  private double calculateMeal(final Meal meal)
  {
    double calories = 0.0;

    List<Recipe> recipes = meal.getRecipes();

    for (Recipe r : recipes)
    {
      calories += calculateRecipe(r);
    }

    return calories;
  }

  /**
   * 
   * @param recipe
   * @return the total calories
   */
  private double calculateRecipe(final Recipe recipe)
  {
    double calories = 0.0;

    List<Ingredients> ingredients = recipe.getIngredients();

    for (Ingredients i : ingredients)
    {
      String ingreName = i.getName().toLowerCase();
      String unit = i.getUnit();
      Double amount = i.getAmount();

      if (unit.equals("Individual")) // will not calculate individual ingredients
      {
        if (omitted.equals(""))
        {
          omitted += ingreName;
        }
        else
        {
          omitted += comma + ingreName;
        }
      }
      else
      {
        calories += calculate(ingreName, unit, amount);
      }
    }

    return calories;
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    window.getIngredientsMenu().setSelectedIndex(0);
    window.getUnitsMenu().setSelectedIndex(0);
    window.getCalorieAmount().setText("");
    window.getOutputField().setText("___________");
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !window.getIngredientsMenu().getSelectedItem().toString().isEmpty()
        || !window.getUnitsMenu().getSelectedItem().toString().isEmpty()
        || !window.getCalorieAmount().getText().trim().isEmpty();
    window.getResetButton().setEnabled(empty);
  }

  /**
   * Enable calculate button if all inputs are entered.
   */
  private void updateCalculateButton()
  {
    boolean empty = !window.getIngredientsMenu().getSelectedItem().toString().isEmpty()
        && !window.getUnitsMenu().getSelectedItem().toString().isEmpty()
        && !window.getCalorieAmount().getText().trim().isEmpty();
    window.getCalcButton().setEnabled(empty);
  }
}
