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
import controller.CalorieCalculatorController;
import utilities.ImageUtilities;
import utilities.UnitType;

/**
 * Main window for KiLowBites calorie calculator.
 * 
 * @author f24team3d
 * @version 10/28/24
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class CalorieCalculatorWindow extends JFrame
{
  static final Locale LOCALE = Locale.getDefault();

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private static final long serialVersionUID = 1L;

  private String[] ingredients;
  private String[] units;
  private CalorieCalculatorController controller;

  private JPanel ingredientsPanel;
  private JPanel amountPanel;
  private JPanel unitsPanel;
  private JPanel outputPanel;

  private JButton calorieCalcButton;
  private JButton calorieResetButton;
  private JButton calorieOpenButton;

  private JComboBox calorieIngredientsMenu;
  private JComboBox calorieUnitsMenu;
  private JTextField calorieAmountField;
  private JLabel calorieOutputField;

  private String calculate = "CALCULATE";
  private String reset = "RESET";

  private KILowBites window;

  // private static final String KILOWBITES_CALORIES_CALCULATOR = "KILowBites Calorie Calculator";
  // private static final String CALCULATE = "Calculate";
  // private static final String RESET = "Reset";
  // private static final String OPEN = "Open";
  // private static final String OPEN_RECIPE_MEAL = "Open Recipe/Meal";
  // private static final String CHOOSE_INGREDIENT = "Choose Ingredient";
  // private static final String AMOUNT = "Amount:";
  // private static final String CHOOSE_UNIT = "Choose Unit";
  // private static final String UNITS = "Units:";
  // private static final String INGREDIENTS = "Ingredients";
  // private static final String CALORIES = "Calories:";

  /**
   * Constructor.
   * 
   * @param window
   *          Parent window
   */
  public CalorieCalculatorWindow(final KILowBites window)
  {
    super((STRINGS.getString("KILOWBITES_CALORIES_CALCULATOR")));

    this.setBackground(KILowBites.COLOR);

    this.window = window;

    controller = new CalorieCalculatorController(this);

    // adding input/output components
    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.LEFT));

    ingredients = KILowBites.FOODS.getFoodNames();

    if (UnitType.getImperialSelected())
    {
      units = UnitType.getImperialUnitsSpace();
    }
    else
    {
      units = UnitType.getMetricUnitsSpace();
    }

    setupInputs();

    inputs.add(ingredientsPanel);
    inputs.add(amountPanel);
    inputs.add(unitsPanel);
    inputs.add(outputPanel);

    add(inputs);

    // Create toolbar with buttons (and associated icons)
    // buttons should be set disabled at first
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    calorieCalcButton = new JButton(
        ImageUtilities.getFormattedImage("calculate.png", Color.GRAY, 25, 25));
    calorieCalcButton.setEnabled(false);
    calorieCalcButton.setToolTipText((STRINGS.getString(calculate)));
    calorieCalcButton.setActionCommand((STRINGS.getString(calculate)));
    calorieCalcButton.addActionListener(controller);

    calorieResetButton = new JButton(
        ImageUtilities.getFormattedImage("reset.png", Color.GRAY, 25, 25));
    calorieResetButton.setEnabled(false);
    calorieResetButton.setToolTipText((STRINGS.getString(reset)));
    calorieResetButton.setActionCommand((STRINGS.getString(reset)));
    calorieResetButton.addActionListener(controller);

    calorieOpenButton = new JButton(
        ImageUtilities.getFormattedImage("open.png", Color.GRAY, 25, 25));
    calorieOpenButton.setToolTipText((STRINGS.getString("OPEN_RECIPE_MEAL")));
    calorieOpenButton.setActionCommand((STRINGS.getString("OPEN")));
    calorieOpenButton.addActionListener(controller);

    toolbar.add(calorieOpenButton);
    toolbar.add(calorieCalcButton);
    toolbar.add(calorieResetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(700, 200);
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
      System.out.println("Closing calorie calculator...");
      window.getOpenCalc().setEnabled(true);
    }
  }

  /**
   * Set up inputs in window.
   */
  private void setupInputs()
  {
    // INGREDIENT PANEL
    ingredientsPanel = new JPanel();
    ingredientsPanel.setLayout(new BorderLayout());

    calorieIngredientsMenu = new JComboBox(ingredients);
    calorieIngredientsMenu.setActionCommand((STRINGS.getString("CHOOSE_INGREDIENT")));
    calorieIngredientsMenu.addActionListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    // west -> label, center -> ingredients
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel((STRINGS.getString("INGREDIENTS"))), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieIngredientsMenu, gbc);

    ingredientsPanel.add(boxPanel, BorderLayout.CENTER);

    // AMOUNT PANEL
    amountPanel = new JPanel();
    amountPanel.setLayout(new BorderLayout());

    calorieAmountField = new JTextField(8);
    calorieAmountField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel((STRINGS.getString("AMOUNT"))), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieAmountField, gbc);

    amountPanel.add(boxPanel, BorderLayout.CENTER);

    // UNITS PANEL
    unitsPanel = new JPanel();
    unitsPanel.setLayout(new BorderLayout());

    calorieUnitsMenu = new JComboBox(units);
    calorieUnitsMenu.setActionCommand((STRINGS.getString("CHOOSE_UNIT")));
    calorieUnitsMenu.addActionListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel((STRINGS.getString("UNITS"))), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieUnitsMenu, gbc);

    unitsPanel.add(boxPanel, BorderLayout.CENTER);

    // OUTPUT PANEL
    outputPanel = new JPanel();
    outputPanel.setLayout(new BorderLayout());

    calorieOutputField = new JLabel("___________");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel((STRINGS.getString("CALORIES"))), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(calorieOutputField, gbc);

    outputPanel.add(boxPanel, BorderLayout.CENTER);
  }

  /**
   * Return calculate button.
   * 
   * @return Calculate button
   */
  public JButton getCalcButton()
  {
    return calorieCalcButton;
  }

  /**
   * Return reset button.
   * 
   * @return Reset button
   */
  public JButton getResetButton()
  {
    return calorieResetButton;
  }

  /**
   * Return open button.
   * 
   * @return Open button
   */
  public JButton getOpenButton()
  {
    return calorieOpenButton;
  }

  /**
   * Return ingredients menu.
   * 
   * @return Ingredients menu
   */
  public JComboBox getIngredientsMenu()
  {
    return calorieIngredientsMenu;
  }

  /**
   * Return units menu.
   * 
   * @return Units menu
   */
  public JComboBox getUnitsMenu()
  {
    return calorieUnitsMenu;
  }

  /**
   * Return calorie amount field.
   * 
   * @return Calorie amount field
   */
  public JTextField getCalorieAmount()
  {
    return calorieAmountField;
  }

  /**
   * Return output field.
   * 
   * @return Output field
   */
  public JLabel getOutputField()
  {
    return calorieOutputField;
  }
}
