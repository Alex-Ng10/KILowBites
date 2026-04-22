package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.UnitConverterController;
import utilities.ImageUtilities;
import utilities.UnitType;

/**
 * Main window for KiLowBites unit converter.
 * 
 * @author f24team3d
 * @version 10/29/24
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class UnitConverterWindow extends JFrame
{
  static final Locale LOCALE = Locale.getDefault();

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private static final long serialVersionUID = 1L;

  private String[] units;
  private String[] ingredients;
  private UnitConverterController controller;

  private JPanel ingredientsPanel;
  private JPanel fromUnitsPanel;
  private JPanel toUnitsPanel;
  private JPanel fromAmountPanel;
  private JPanel outputPanel;

  private JButton unitCalcButton;
  private JButton unitResetButton;

  private JComboBox fromUnitsMenu;
  private JComboBox toUnitsMenu;
  private JComboBox unitIngredientsMenu;
  private JTextField fromAmountField;
  private JLabel unitOutputField;

  private String calculate = "CALCULATE";
  private String reset = "RESET";
  private String toUnits = "TO_UNITS";
  private String fromUnits = "FROM_UNITS";

  private KILowBites window;

  // private static final String CALCULATE = "Calculate";
  // private static final String RESET = "Reset";
  // private static final String FROM_UNITS = "From Units:";
  // private static final String TO_UNITS = "To Units:";
  // private static final String INGREDIENT = "Ingredient:";
  // private static final String AMOUNT = "Amount:";
  // private static final String TO_AMOUNT = "To Amount:";
  // private static final String CHOOSE_INGREDIENT = "Choose Ingredient";

  /**
   * Constructor.
   * 
   * @param window
   *          Parent window
   */
  public UnitConverterWindow(final KILowBites window)
  {
    super(STRINGS.getString("KILOWBITES_UNIT_CONVERTER"));

    controller = new UnitConverterController(this);
    this.window = window;

    this.setBackground(KILowBites.COLOR);

    if (UnitType.getImperialSelected())
    {
      units = UnitType.getImperialUnitsSpace();
    }
    else
    {
      units = UnitType.getMetricUnitsSpace();
    }

    ingredients = KILowBites.FOODS.getFoodNames();

    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    setupInputs();

    inputs.add(fromUnitsPanel);
    inputs.add(toUnitsPanel);
    inputs.add(ingredientsPanel);
    inputs.add(fromAmountPanel);
    inputs.add(outputPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    unitCalcButton = new JButton(
        ImageUtilities.getFormattedImage("calculate.png", Color.GRAY, 25, 25));
    unitCalcButton.setEnabled(false);
    unitCalcButton.setToolTipText(STRINGS.getString(calculate));
    unitCalcButton.setActionCommand(STRINGS.getString(calculate));
    unitCalcButton.addActionListener(controller);

    unitResetButton = new JButton(
        ImageUtilities.getFormattedImage("reset.png", Color.GRAY, 25, 25));
    unitResetButton.setEnabled(false);
    unitResetButton.setToolTipText(STRINGS.getString(reset));
    unitResetButton.setActionCommand(STRINGS.getString(reset));
    unitResetButton.addActionListener(controller);

    toolbar.add(unitCalcButton);
    toolbar.add(unitResetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    getContentPane().setBackground(KILowBites.COLOR);

    setSize(750, 200);
    setResizable(false);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  @Override
  protected void processWindowEvent(final java.awt.event.WindowEvent e)
  {
    super.processWindowEvent(e);
    if (e.getID() == java.awt.event.WindowEvent.WINDOW_CLOSING)
    {
      System.out.println("Closing unit converter...");
      window.getOpenConvert().setEnabled(true);
    }
  }

  /**
   * Set up inputs in window.
   */
  private void setupInputs()
  {
    // FROM UNITS PANEL
    fromUnitsPanel = new JPanel();
    fromUnitsPanel.setLayout(new BorderLayout());

    fromUnitsMenu = new JComboBox(units);
    fromUnitsMenu.setActionCommand(STRINGS.getString(fromUnits));
    fromUnitsMenu.addActionListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // west -> label, center -> ingredients
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString(fromUnits)), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(fromUnitsMenu, gbc);

    fromUnitsPanel.add(boxPanel, BorderLayout.CENTER);

    // TO UNITS PANEL
    toUnitsPanel = new JPanel();
    toUnitsPanel.setLayout(new BorderLayout());

    toUnitsMenu = new JComboBox(units);
    toUnitsMenu.setActionCommand(STRINGS.getString(toUnits));
    toUnitsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString(toUnits)), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(toUnitsMenu, gbc);

    toUnitsPanel.add(boxPanel, BorderLayout.CENTER);

    // INGREDIENTS PANEL
    ingredientsPanel = new JPanel();
    ingredientsPanel.setLayout(new BorderLayout());

    unitIngredientsMenu = new JComboBox(ingredients);
    unitIngredientsMenu.setActionCommand(STRINGS.getString("CHOOSE_INGREDIENT"));
    unitIngredientsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString("INGREDIENT")), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(unitIngredientsMenu, gbc);

    ingredientsPanel.add(boxPanel, BorderLayout.CENTER);

    // FROM AMOUNT PANEL
    fromAmountPanel = new JPanel();
    fromAmountPanel.setLayout(new BorderLayout());

    fromAmountField = new JTextField(8);
    fromAmountField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString("AMOUNT")), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(fromAmountField, gbc);

    fromAmountPanel.add(boxPanel, BorderLayout.CENTER);

    // OUTPUT PANEL
    outputPanel = new JPanel();
    outputPanel.setLayout(new BorderLayout());

    unitOutputField = new JLabel("___________");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString("TO_AMOUNT")), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(unitOutputField, gbc);

    outputPanel.add(boxPanel, BorderLayout.CENTER);
  }

  /**
   * Gets the selected from Item.
   * 
   * @return The string representation of the selected item.
   */
  public String getFromUnitsMenu()
  {
    return (String) fromUnitsMenu.getSelectedItem();
  }

  /**
   * Gets the selected to Item.
   * 
   * @return The string representation of the selected item.
   */
  public String getToUnitsMenu()
  {
    return (String) toUnitsMenu.getSelectedItem();
  }

  /**
   * Gets the selected Ingredient.
   * 
   * @return The string representation of the selected item.
   */
  public String getIngredientsUnitsMenu()
  {
    return (String) unitIngredientsMenu.getSelectedItem();
  }

  /**
   * Gets the number from the from Amount field.
   * 
   * @return The string representation of the selected item.
   */
  public Double getFromAmountField()
  {
    return Double.parseDouble(fromAmountField.getText());
  }

  /**
   * Return calculate button.
   * 
   * @return Calculate button
   */
  public JButton getCalcButton()
  {
    return unitCalcButton;
  }

  /**
   * Return reset button.
   * 
   * @return Reset button
   */
  public JButton getResetButton()
  {
    return unitResetButton;
  }

  /**
   * Return from units menu.
   * 
   * @return From units menu
   */
  public JComboBox getFromUnits()
  {
    return fromUnitsMenu;
  }

  /**
   * Return to units menu.
   * 
   * @return To units menu
   */
  public JComboBox getToUnits()
  {
    return toUnitsMenu;
  }

  /**
   * Return ingredients menu.
   * 
   * @return Ingredient menu
   */
  public JComboBox getIngredients()
  {
    return unitIngredientsMenu;
  }

  /**
   * Return from amount field.
   * 
   * @return From amount field
   */
  public JTextField getFromAmount()
  {
    return fromAmountField;
  }

  /**
   * Return output field.
   * 
   * @return Output field
   */
  public JLabel getOutputField()
  {
    return unitOutputField;
  }

}
