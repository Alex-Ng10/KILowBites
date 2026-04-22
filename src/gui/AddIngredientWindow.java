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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.AddIngredientController;
import utilities.ImageUtilities;

/**
 * Main window for AddIngredientWindow.
 * 
 * @author f24team3d
 * @version 12/2/24
 */
public class AddIngredientWindow extends JDialog
{
  static final Locale LOCALE = Locale.getDefault();

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private static final long serialVersionUID = 1L;

  private String add = "ADD_INGREDIENT";
  private String reset = "RESET";

  private AddIngredientController controller;

  private JPanel namePanel;
  private JPanel caloriePanel;
  private JPanel densityPanel;
  private JPanel successPanel;

  private String name;

  private boolean added;

  private JButton ingredientAddButton;
  private JButton ingredientResetButton;

  private JTextField ingredientNameField;
  private JTextField ingredientCalorieField;
  private JTextField ingredientDensityField;
  private JLabel successfulAdd;

  /**
   * Constructor.
   * 
   * @param parent
   *          Parent JFrame
   * @param name
   *          Name of window
   **/
  public AddIngredientWindow(final JFrame parent, final String name)
  {
    super(parent, "Add Ingredient to System", true);

    this.name = name;
    added = false;

    controller = new AddIngredientController(this);

    this.setBackground(KILowBites.COLOR);

    JPanel inputs = new JPanel(new FlowLayout(FlowLayout.CENTER));

    setupInputs();

    inputs.add(namePanel);
    inputs.add(caloriePanel);
    inputs.add(densityPanel);
    inputs.add(successPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    ingredientAddButton = new JButton(STRINGS.getString(add));
    ingredientAddButton.setEnabled(false);
    ingredientAddButton.setToolTipText(STRINGS.getString(add));
    ingredientAddButton.setActionCommand(STRINGS.getString(add));
    ingredientAddButton.addActionListener(controller);

    ingredientResetButton = new JButton(
        ImageUtilities.getFormattedImage("reset.png", Color.GRAY, 25, 25));
    ingredientResetButton.setEnabled(false);
    ingredientResetButton.setToolTipText(STRINGS.getString(reset));
    ingredientResetButton.setActionCommand(STRINGS.getString(reset));
    ingredientResetButton.addActionListener(controller);

    toolbar.add(ingredientAddButton);
    toolbar.add(ingredientResetButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(700, 200);
    setResizable(false);
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    // setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

    this.setBackground(KILowBites.COLOR);
  }

  /**
   * Set up inputs in window.
   */
  private void setupInputs()
  {
    // NAME PANEL
    namePanel = new JPanel();
    namePanel.setLayout(new BorderLayout());

    namePanel.setBackground(KILowBites.COLOR);

    ingredientNameField = new JTextField(name, 15);
    ingredientNameField.setEditable(false);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    boxPanel.setBackground(KILowBites.COLOR);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel(STRINGS.getString("INGREDIENT_NAME")), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(ingredientNameField, gbc);

    namePanel.add(boxPanel, BorderLayout.CENTER);

    // CALORIE PANEL
    caloriePanel = new JPanel();
    caloriePanel.setLayout(new BorderLayout());

    caloriePanel.setBackground(KILowBites.COLOR);

    ingredientCalorieField = new JTextField(5);
    ingredientCalorieField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    boxPanel.setBackground(KILowBites.COLOR);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("cal/100g:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(ingredientCalorieField, gbc);

    caloriePanel.add(boxPanel, BorderLayout.CENTER);

    // DENSITY PANEL
    densityPanel = new JPanel();
    densityPanel.setLayout(new BorderLayout());

    densityPanel.setBackground(KILowBites.COLOR);

    ingredientDensityField = new JTextField(5);
    ingredientDensityField.getDocument().addDocumentListener(controller);

    boxPanel = new JPanel(new GridBagLayout());

    boxPanel.setBackground(KILowBites.COLOR);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(10, 20, 10, 10);
    boxPanel.add(new JLabel("g/mL:"), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(ingredientDensityField, gbc);

    densityPanel.add(boxPanel, BorderLayout.CENTER);

    // SUCCESS PANEL
    successPanel = new JPanel();
    successPanel.setLayout(new BorderLayout());

    successPanel.setBackground(KILowBites.COLOR);

    successfulAdd = new JLabel("");

    boxPanel = new JPanel(new GridBagLayout());

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(successfulAdd, gbc);

    successPanel.add(boxPanel, BorderLayout.CENTER);
  }

  /**
   * Return added.
   * 
   * @return boolean
   */
  public boolean getAdded()
  {
    return added;
  }

  /**
   * Sets added.
   * 
   * @param success
   *          successful add
   */
  public void setAdded(final boolean success)
  {
    added = success;
  }

  /**
   * Return ingredient add button.
   * 
   * @return button
   */
  public JButton getAddButton()
  {
    return ingredientAddButton;
  }

  /**
   * Return ingredient add button.
   * 
   * @return button
   */
  public JButton getResetButton()
  {
    return ingredientResetButton;
  }

  /**
   * Return name field.
   * 
   * @return name field
   */
  public JTextField getNameField()
  {
    return ingredientNameField;
  }

  /**
   * Return calorie field.
   * 
   * @return calorie field
   */
  public JTextField getCalorieField()
  {
    return ingredientCalorieField;
  }

  /**
   * Return density field.
   * 
   * @return density field
   */
  public JTextField getDensityField()
  {
    return ingredientDensityField;
  }

  /**
   * Return successful add field.
   * 
   * @return return successful add field
   */
  public JLabel getSuccessfulAdd()
  {
    return successfulAdd;
  }
}
