package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import app.KILowBites;
import controller.ShoppingListController;
import converter.MassConverter;
import converter.VolumeConverter;
import cooking.Ingredients;
import cooking.Meal;
import cooking.Recipe;
import utilities.FileUtilities;
import utilities.ImageUtilities;
import utilities.InputUtilities;

/**
 * Main window for shopping list.
 * 
 * @author f24team3d
 * @version 12/4/24
 */
public class ShoppingListWindow extends JFrame
{
  static final Locale LOCALE = Locale.getDefault();
  private static final long serialVersionUID = 1L;
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private static final String PRINT = "PRINT";
  private static final String SHOPPING_LIST = "SHOPPING_LIST";
  private static final String INDIVIDUAL = "INDIVIDUAL";
  private static final String CHANGE_UNIT = "CHANGE_UNIT";
  private static final String MASS = "MASS";
  private static final String VOLUME = "VOLUME";

  private static Meal meal;

  private ShoppingListController controller;
  private JButton printButton;
  private String individual = "Individual";

  private String[] columnNames = {STRINGS.getString("INGREDIENT"), STRINGS.getString("QUANTITY"),
      STRINGS.getString("UNITS"), STRINGS.getString("RECIPES"), STRINGS.getString("SERVES"),
      STRINGS.getString("AISLE"), STRINGS.getString("PRICE"), STRINGS.getString("INDEX")};

  private List<Object[]> data;
  private Object[][] ingredients;
  private Object[][] originalIngredients;
  private Map<String, Integer> aisles;
  private Map<String, Double> prices;

  private JPanel peoplePanel;
  private JPanel shoppingListPanel;
  private JPanel inputs;
  private JComboBox<String> unitComboBox;

  private JTextField peopleField;
  private JTable shoppingList;

  /**
   * Constructor.
   * 
   * @param meal
   *          Meal being viewed.
   */

  public ShoppingListWindow(final Meal meal)
  {
    super(STRINGS.getString("KILOWBITES_SHOPPING_LIST_VIEWER") + " "
        + InputUtilities.separateByCapital(meal.getName()));

    ShoppingListWindow.meal = meal;

    // Initialize aisles and prices maps
    aisles = FileUtilities.loadAisles();
    if (aisles == null)
    {
      aisles = new HashMap<>();
    }

    prices = FileUtilities.loadPrices();
    if (prices == null)
    {
      prices = new HashMap<>();
    }

    data = getIngredients();
    ingredients = new Object[data.size()][7]; // [5] for testing
    originalIngredients = new Object[data.size()][7];

    for (int i = 0; i < data.size(); i++)
    {
      ingredients[i] = data.get(i).clone();
      originalIngredients[i] = data.get(i).clone();
    }

    controller = new ShoppingListController(this);

    inputs = new JPanel();
    inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));

    setupInputs();

    inputs.add(peoplePanel);
    inputs.add(shoppingListPanel);

    add(inputs);

    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);

    printButton = new JButton(ImageUtilities.getFormattedImage("print.png", Color.GRAY, 25, 25));
    printButton.setToolTipText(STRINGS.getString(PRINT));
    printButton.setActionCommand(STRINGS.getString(PRINT));
    printButton.addActionListener(controller);

    toolbar.add(printButton);

    getContentPane().add(toolbar, BorderLayout.NORTH);

    setSize(800, 600);
    setResizable(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.setBackground(KILowBites.COLOR);
  }

  /**
   * Set up inputs in window.
   */
  @SuppressWarnings("serial")
  private void setupInputs()
  {
    // PEOPLE PANEL
    peoplePanel = new JPanel();
    peoplePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    peoplePanel.setBackground(KILowBites.COLOR);

    peopleField = new JTextField(5);
    peopleField.getDocument().addDocumentListener(controller);

    JPanel boxPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    boxPanel.setBackground(KILowBites.COLOR);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(0, 5, 0, 0);
    boxPanel.add(new JLabel(STRINGS.getString("NUMBER_OF_PEOPLE")), gbc);

    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER;
    boxPanel.add(peopleField, gbc);

    peoplePanel.add(boxPanel, BorderLayout.CENTER);

    // SHOPPING LIST PANEL
    shoppingListPanel = new JPanel();
    shoppingListPanel.setSize(650, 215);
    shoppingListPanel.setLayout(new BorderLayout());
    shoppingListPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString(SHOPPING_LIST)));

    shoppingListPanel.setBackground(KILowBites.COLOR);

    shoppingList = new JTable(new DefaultTableModel(originalIngredients, columnNames))
    {
      @Override
      public boolean isCellEditable(final int row, final int column)
      {
        // Make the unit column editable only if the unit is not "Individual"
        if (column == 2)
        {
          String unit = (String) getValueAt(row, column);
          return !unit.equals(STRINGS.getString(INDIVIDUAL));
        }
        return super.isCellEditable(row, column); // Default behavior for other columns
      }
    };

    shoppingList.getColumnModel().getColumn(1).setCellRenderer(new DecimalTableCellRenderer());
    shoppingList.getColumnModel().getColumn(6).setCellRenderer(new DecimalTableCellRenderer());

    unitComboBox = new JComboBox<>(KILowBites.UNITS.getAllUnitsNoPadding());
    unitComboBox.setActionCommand(STRINGS.getString(CHANGE_UNIT));
    unitComboBox.addActionListener(controller);
    shoppingList.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(unitComboBox));

    TableRowSorter<TableModel> sorter = new TableRowSorter<>(shoppingList.getModel());
    shoppingList.setRowSorter(sorter);

    JScrollPane scrollPane = new JScrollPane(shoppingList); // Add scrolling if needed
    shoppingListPanel.add(scrollPane, BorderLayout.CENTER);

  }

  @SuppressWarnings("serial")
  private void setupUpdatedInputs(final Object[][] update)
  {
    inputs.remove(shoppingListPanel);
    inputs.revalidate();
    inputs.repaint();

    shoppingListPanel = new JPanel();
    shoppingListPanel.setSize(650, 215);
    shoppingListPanel.setLayout(new BorderLayout());
    shoppingListPanel.setBorder(BorderFactory.createTitledBorder(STRINGS.getString(SHOPPING_LIST)));

    shoppingListPanel.setBackground(KILowBites.COLOR);

    shoppingList = new JTable(new DefaultTableModel(update, columnNames))
    {
      @Override
      public boolean isCellEditable(final int row, final int column)
      {
        // Make the unit column editable only if the unit is not "Individual"
        if (column == 2)
        {
          String unit = (String) getValueAt(row, column);
          return !unit.equals(STRINGS.getString(INDIVIDUAL));
        }
        return super.isCellEditable(row, column); // Default behavior for other columns
      }
    };

    shoppingList.getColumnModel().getColumn(1).setCellRenderer(new DecimalTableCellRenderer());
    shoppingList.getColumnModel().getColumn(6).setCellRenderer(new DecimalTableCellRenderer());

    unitComboBox = new JComboBox<>(KILowBites.UNITS.getAllUnitsNoPadding());
    unitComboBox.setActionCommand(STRINGS.getString(CHANGE_UNIT));
    unitComboBox.addActionListener(controller);
    shoppingList.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(unitComboBox));

    TableRowSorter<TableModel> sorter = new TableRowSorter<>(shoppingList.getModel());
    shoppingList.setRowSorter(sorter);

    JScrollPane scrollPane = new JScrollPane(shoppingList); // Add scrolling if needed
    shoppingListPanel.add(scrollPane, BorderLayout.CENTER);

    inputs.add(shoppingListPanel);
    inputs.revalidate();
    inputs.repaint();
  }

  /**
   * Return list of ingredients.
   * 
   * @return List of ingredients
   */
  private List<Object[]> getIngredients()
  {
    List<Object[]> ingredientArray = new ArrayList<>();
    int index = 0;

    for (Recipe r : meal.getRecipes())
    {
      for (Ingredients i : r.getIngredients())
      {
        String na = "N/A";

        Object aisle = na;
        Object price = na;

        Object[] info;

        String name = i.getName().toLowerCase();
        String unit = i.getUnit();

        double amount = i.getAmount();

        if (aisles.containsKey(name))
        {
          aisle = aisles.get(name);
        }

        if (prices.containsKey(name))
        {
          price = prices.get(name);
          if (name.equals("egg"))
          {
            System.out.println(name + ": " + price);
          }
        }
        
        if (name.equals("egg"))
        {
          System.out.println(name + ": " + price);
        }

        if (unit.equals(individual) || price.equals(na))
        {
          info = new Object[] {i.getName(), amount, unit, r.getName(), r.getServes(), aisle, na,
              index};
        }
        else
        {
          double adjustedPrice = priceConvert(price, unit, amount);

          info = new Object[] {i.getName(), amount, unit, r.getName(), r.getServes(), aisle,
              adjustedPrice, index};
        }
        ingredientArray.add(info);
        index++;
      }
    }

    // sort ingredients alphabetically in shopping list
    // easier for user to see duplicate ingredients
    // ingredients.sort((o1, o2) -> ((String o1[0]).compareTo((String) o2[0]));

    return ingredientArray;
  }

  /**
   * Return shopping list table.
   * 
   * @return shopping list
   */
  public JTable getShoppingList()
  {
    return shoppingList;
  }

  /**
   * Adjusts price.
   * 
   * @param price
   *          Original price of ingredient (per g or per mL)
   * @param unit
   *          Unit to be converted from
   * @param amount
   *          Amount of ingredient
   * @return Adjusted price
   */
  private double priceConvert(final Object price, final String unit, final double amount)
  {
    double convertedAmount;
    String unitMeasure = KILowBites.UNITS.unitMeasure(unit);
    if ("Mass".equals(unitMeasure))
    {
      convertedAmount = MassConverter.callerHelp(unit, "Gram", amount);
    }
    else if ("Volume".equals(unitMeasure))
    {
      convertedAmount = VolumeConverter.callerHelp(unit, "Milliliter", amount);
    }
    else
    {
      // Handle other unit types or throw an exception
      throw new IllegalArgumentException("Unsupported unit type: " + unitMeasure);
    }
    return convertedAmount * (double) price;
  }

  /**
   * Refreshes the table with the original shopping list.
   */
  public void defaultTable()
  {
    setupUpdatedInputs(originalIngredients);
  }

  /**
   * Refresh table with new values accounting for the new number of people.
   * 
   * @param people
   *          New number of people
   */
  public void refreshTable(final int people)
  {
    int i = 0;
    for (Object[] ingredient : ingredients)
    {
      int basePeople = (int) originalIngredients[i][4];
      double price;
      String name = (String) ingredient[0];
      String unit = (String) ingredient[2];

      double baseQuantity = (double) originalIngredients[i][1];

      double newQuantity = (baseQuantity / basePeople) * people;
      ingredient[1] = newQuantity;

      if (prices.containsKey(name) && !unit.equals(individual))
      {
        price = prices.get(name);
        double newPrice = priceConvert(price, (String) unit, newQuantity);

        ingredient[6] = newPrice;
      }

      ingredient[4] = people;

      i++;
    }

    setupUpdatedInputs(ingredients);
  }

  /**
   * Return people field of shopping list.
   * 
   * @return people field
   */
  public JTextField getPeopleField()
  {
    return peopleField;
  }

  /**
   * Updates quantity of ingredient in shopping list.
   */
  public void updateQuantity()
  {
    int row = shoppingList.getSelectedRow();
    if (row < 0)
    {
      return;
    }

    double newQuantity;
    double quantity = (double) shoppingList.getValueAt(row, 1);

    if (row != -1)
    {
      int index = (int) shoppingList.getValueAt(row, 7);

      String oldUnit = (String) ingredients[index][2];
      String oldUnitMeasure = KILowBites.UNITS.unitMeasure(oldUnit);

      String newUnit = (String) unitComboBox.getSelectedItem();
      String newUnitMeasure = KILowBites.UNITS.unitMeasure(newUnit);

      if (newUnit.equals(oldUnit))
      {
        return;
      }
      else
      {
        if (newUnitMeasure.equals(STRINGS.getString(MASS))
            && oldUnitMeasure.equals(STRINGS.getString(MASS)))
        {
          // System.out.println(
          // STRINGS.getString("CONVERTING_FROM") + oldUnit + STRINGS.getString("TO") + newUnit);
          newQuantity = MassConverter.callerHelp(oldUnit, newUnit, quantity);

          shoppingList.setValueAt(newQuantity, row, 1);
        }
        else if (newUnitMeasure.equals(STRINGS.getString(VOLUME))
            && oldUnitMeasure.equals(STRINGS.getString(VOLUME)))
        {
          // System.out.println(
          // STRINGS.getString("CONVERTING_FROM") + oldUnit + STRINGS.getString("TO") + newUnit);
          newQuantity = VolumeConverter.callerHelp(oldUnit, newUnit, quantity);

          shoppingList.setValueAt(newQuantity, row, 1);
        }
        else
        {
          // System.out.println(STRINGS.getString("DO NOT CHANGE MEASURES"));
          return;
        }
        ingredients[index][2] = newUnit;
      }
    }
  }

  // Custom renderer to format numeric values
  @SuppressWarnings("serial")
  class DecimalTableCellRenderer extends DefaultTableCellRenderer
  {
    private static final DecimalFormat FORMAT = new DecimalFormat("#.##");

    @Override
    public void setValue(final Object value)
    {
      String newValue = "N/A";
      if (value instanceof Number)
      {
        newValue = FORMAT.format(value); // Format the number to 2 decimals
      }

      super.setValue(newValue);
    }
  }
}
