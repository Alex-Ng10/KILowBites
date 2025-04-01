package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import gui.AddIngredientWindow;
import utilities.InputUtilities;

public class AddIngredientController implements ActionListener, DocumentListener
{
  private static final String ADD_INGREDIENT = "Add Ingredient";
  private static final String RESET = "Reset";

  @Override
  public void actionPerformed(ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(ADD_INGREDIENT))
    {
      addIngredient();
    }
    if (ac.equals(RESET))
    {
      reset();
      updateResetButton();
      updateAddButton();
    }

  }

  @Override
  public void insertUpdate(DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();

  }

  @Override
  public void removeUpdate(DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();

  }

  @Override
  public void changedUpdate(DocumentEvent e)
  {
    updateResetButton();
    updateAddButton();

  }

  /**
   * 
   */
  private void addIngredient()
  {
    String ingreName;

    if (InputUtilities.isAlphabetical(AddIngredientWindow.ingredientNameField.getText().trim()))
    {
      ingreName = AddIngredientWindow.ingredientNameField.getText().trim().toLowerCase();
    }
    else
    {
      AddIngredientWindow.successfulAdd.setText("Invalid input");
      return;
    }

    double cals100G, gML;
    try
    {
      // Retrieve and parse the input amount
      cals100G = Double.parseDouble(AddIngredientWindow.ingredientCalorieField.getText().trim());
      gML = Double.parseDouble(AddIngredientWindow.ingredientDensityField.getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      AddIngredientWindow.successfulAdd.setText("Invalid input");
      return;
    }

    if (cals100G < 0 || gML < 0)
    {
      AddIngredientWindow.successfulAdd.setText("Invalid input");
      return;
    }

    double[] values = {cals100G, gML};
    boolean success = KILowBites.FOODS.addFoods(ingreName, values);

    if (success)
    {
      AddIngredientWindow.successfulAdd.setText(String.format("Successfully added %s!", ingreName));
    }
    else
    {
      AddIngredientWindow.successfulAdd.setText(String.format("Already in system!", ingreName));
    }
    System.out.println(Arrays.toString(KILowBites.FOODS.getFoodNames()));
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    AddIngredientWindow.ingredientNameField.setText("");
    AddIngredientWindow.ingredientCalorieField.setText("");
    AddIngredientWindow.ingredientDensityField.setText("");
    AddIngredientWindow.successfulAdd.setText("");
  }

  private void updateAddButton()
  {
    boolean empty = !AddIngredientWindow.ingredientNameField.getText().trim().isEmpty()
        && !AddIngredientWindow.ingredientCalorieField.getText().trim().isEmpty()
        && !AddIngredientWindow.ingredientDensityField.getText().trim().isEmpty();
    AddIngredientWindow.ingredientAddButton.setEnabled(empty);
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !AddIngredientWindow.ingredientNameField.getText().trim().isEmpty()
        || !AddIngredientWindow.ingredientCalorieField.getText().trim().isEmpty()
        || !AddIngredientWindow.ingredientDensityField.getText().trim().isEmpty();
    AddIngredientWindow.ingredientResetButton.setEnabled(empty);
  }
}
