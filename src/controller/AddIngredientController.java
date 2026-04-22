
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import gui.AddIngredientWindow;
import utilities.InputUtilities;

/**
 * Controller for AddIngredientWindow.
 * 
 * @author f24team3d
 * @version 12/2/24
 */
public class AddIngredientController implements ActionListener, DocumentListener
{
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  // private static final String ADD_INGREDIENT = "Add Ingredient";
  // private static final String RESET = "Reset";

  private AddIngredientWindow window;
  private String invalid = "INVALID_INPUT";

  /**
   * Constructor.
   * 
   * @param window
   *          Add Ingredient Window
   */
  public AddIngredientController(final AddIngredientWindow window)
  {
    this.window = window;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(STRINGS.getString("ADD_INGREDIENT")))
    {
      addIngredient();
    }
    if (ac.equals(STRINGS.getString("RESET")))
    {
      reset();
      updateResetButton();
      updateAddButton();
    }

  }

  @Override
  public void insertUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();
  }

  @Override
  public void removeUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();
  }

  @Override
  public void changedUpdate(final DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();
  }

  /**
   * Adds ingredient to food list.
   */
  private void addIngredient()
  {
    String ingreName;

    if (InputUtilities.isAlphabetical(window.getNameField().getText().trim()))
    {
      ingreName = window.getNameField().getText().trim().toLowerCase();
    }
    else
    {
      window.getSuccessfulAdd().setText(STRINGS.getString(invalid));
      return;
    }

    double cals100G, gML;
    try
    {
      // Retrieve and parse the input amount
      cals100G = Double.parseDouble(window.getCalorieField().getText().trim());
      gML = Double.parseDouble(window.getDensityField().getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      window.getSuccessfulAdd().setText(STRINGS.getString(invalid));
      return;
    }

    if (cals100G < 0 || gML < 0)
    {
      window.getSuccessfulAdd().setText(STRINGS.getString(invalid));
      return;
    }

    double[] values = {cals100G, gML};
    boolean success = KILowBites.FOODS.addFoods(ingreName, values);

    if (success)
    {
      window.getSuccessfulAdd().setText(
          String.format(LOCALE, STRINGS.getString("SUCCESSFULLY_ADDED") + " %s!", ingreName));

      window.setAdded(true);

      new javax.swing.Timer(2000, e ->

      {
        window.dispose(); // Adjust this to match your window closing logic
      }).start();
    }
    else
    {
      window.getSuccessfulAdd()
          .setText(String.format(LOCALE, STRINGS.getString("ALREADY_IN_SYSTEM"), ingreName));
    }
    System.out.println(Arrays.toString(KILowBites.FOODS.getFoodNames()));
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    window.getCalorieField().setText("");
    window.getDensityField().setText("");
    window.getSuccessfulAdd().setText("");
  }

  private void updateAddButton()
  {
    boolean empty = !window.getCalorieField().getText().trim().isEmpty()
        && !window.getDensityField().getText().trim().isEmpty();
    window.getAddButton().setEnabled(empty);
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !window.getCalorieField().getText().trim().isEmpty()
        || !window.getDensityField().getText().trim().isEmpty();
    window.getResetButton().setEnabled(empty);
  }
}
