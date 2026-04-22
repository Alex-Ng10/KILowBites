package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import converter.MassConverter;
import converter.MassToVolume;
import converter.VolumeConverter;
import gui.UnitConverterWindow;
import utilities.Units;

/**
 * Unit Converter controller class. Handles the actions of the Unit Converter GUI elements.
 *
 * @author f24team3d
 * @version 12/4/24
 */
public class UnitConverterController implements ActionListener, DocumentListener
{
  // private static final String CALCULATE = "Calculate";
  // private static final String RESET = "Reset";
  // private static final String FROM_UNITS = "From Unit";
  // private static final String TO_UNITS = "To Unit";
  // private static final String CHOOSE_INGREDIENT = "Choose Ingredient";

  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private boolean sameMeasureType;

  private UnitConverterWindow window;

  /**
   * Constructor.
   * 
   * @param window
   *          Parent window
   */
  public UnitConverterController(final UnitConverterWindow window)
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
    if (ac.equals(STRINGS.getString("FROM_UNITS")))
    {
      updateResetButton();
      updateCalculateButton();
      disableIngredients();
    }
    if (ac.equals(STRINGS.getString("TO_UNITS")))
    {
      updateResetButton();
      updateCalculateButton();
      disableIngredients();
    }
    if (ac.equals(STRINGS.getString("CHOOSE_INGREDIENT")))
    {
      updateResetButton();
      updateCalculateButton();
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

  /*
   * Updates output field to correct conversion.
   */
  private void calculate()
  {
    double amount;
    try
    {
      // Retrieve and parse the input amount
      amount = Double.parseDouble(window.getFromAmount().getText().trim());
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      window.getOutputField().setText("Invalid input");
      return;
    }

    // TODO: implement calculate
    String[] volumeUnits = Units.getVolumeUnits();
    String[] massUnits = Units.getMassUnits();

    String fromUnits = window.getFromUnitsMenu();
    String toUnits = window.getToUnitsMenu();
    Double fromAmount = window.getFromAmountField();
    String ingredient = window.getIngredientsUnitsMenu();
    Double density = 0.0;
    if (fromAmount < 0)
    {
      window.getOutputField().setText("Cannot be a negative input.");
    }
    else
    {
      if (!sameMeasureType)
        density = KILowBites.FOODS.getDensity(ingredient);
      if (Arrays.asList(volumeUnits).contains(fromUnits)) // Volume
      {
        if (Arrays.asList(volumeUnits).contains(toUnits)) // Volume to Volume
        {
          amount = VolumeConverter.callerHelp(fromUnits, toUnits, fromAmount);
        }
        else // Volume to Mass
        {
          // Need to add density (Currently has a placeholder of 1.04)
          amount = MassToVolume.interConverting(fromUnits, toUnits, fromAmount, density);
        }
      }
      else // Mass
      {
        if (Arrays.asList(massUnits).contains(toUnits)) // Mass to Mass
        {
          amount = MassConverter.callerHelp(fromUnits, toUnits, fromAmount);
        }
        else // Mass To Volume
        {
          // Need to add density (Currently has a placeholder of 1.04
          amount = MassToVolume.interConverting(fromUnits, toUnits, fromAmount, density);
        }
      }
      window.getOutputField().setText(String.format("%.5f", amount));
    }
  }

  /**
   * Disable ingredient field if conversion does not involve mass AND volume.
   */
  private void disableIngredients()
  {
    String fromUnits = window.getFromUnitsMenu();
    String toUnits = window.getToUnitsMenu();

    if (fromUnits != null && toUnits != null)
    {
      String fromMeasureType = KILowBites.UNITS.unitMeasure(fromUnits);
      String toMeasureType = KILowBites.UNITS.unitMeasure(toUnits);

      sameMeasureType = fromMeasureType != null && fromMeasureType.equals(toMeasureType);
      window.getIngredients().setEnabled(!sameMeasureType);
    }
    else
    {
      window.getIngredients().setEnabled(true);
    }
  }

  /**
   * Reset all input menus and output field.
   */
  private void reset()
  {
    window.getIngredients().setSelectedIndex(0);
    window.getFromUnits().setSelectedIndex(0);
    window.getToUnits().setSelectedIndex(0);
    window.getFromAmount().setText("");
    window.getOutputField().setText("___________");
  }

  /**
   * Enable reset button if any there are any inputs.
   */
  private void updateResetButton()
  {
    boolean empty = !window.getIngredients().getSelectedItem().toString().isEmpty()
        || !window.getFromUnits().getSelectedItem().toString().isEmpty()
        || !window.getFromAmount().getText().trim().isEmpty()
        || !window.getToUnits().getSelectedItem().toString().isEmpty();
    window.getResetButton().setEnabled(empty);
  }

  /**
   * Enable calculate button if all inputs are entered.
   */
  private void updateCalculateButton()
  {
    boolean empty;
    if (sameMeasureType)
    {
      empty = !window.getFromUnits().getSelectedItem().toString().isEmpty()
          && !window.getFromAmount().getText().trim().isEmpty()
          && !window.getToUnits().getSelectedItem().toString().isEmpty();
    }
    else
    {
      empty = !window.getIngredients().getSelectedItem().toString().isEmpty()
          && !window.getFromUnits().getSelectedItem().toString().isEmpty()
          && !window.getFromAmount().getText().trim().isEmpty()
          && !window.getToUnits().getSelectedItem().toString().isEmpty();
    }
    window.getCalcButton().setEnabled(empty);
  }
}
