package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import gui.DelegatingPrintable;
import gui.ShoppingListWindow;

/**
 * Shopping list controller class. Handles the actions of the shopping list GUI elements.
 *
 * @author f24team3d
 * @version 12/4/24
 */
public class ShoppingListController implements ActionListener, DocumentListener
{
  // private static String PRINT = "Print";
  // private static String CHANGE_UNIT = "Change Unit";
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private ShoppingListWindow parent;

  /**
   * Constructor.
   * 
   * @param parent
   *          Parent ShoppingListWindow
   */
  public ShoppingListController(final ShoppingListWindow parent)
  {
    this.parent = parent;
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String command = e.getActionCommand();

    if (command.equals(STRINGS.getString("PRINT")))
    {
      DelegatingPrintable dp = new DelegatingPrintable(parent.getShoppingList());
      PrinterController.print(dp, parent);
    }

    if (command.equals(STRINGS.getString("CHANGE_UNIT")))
    {
      parent.updateQuantity();
    }

  }

  @Override
  public void insertUpdate(final DocumentEvent e)
  {
    updateTable();
  }

  @Override
  public void removeUpdate(final DocumentEvent e)
  {
    updateTable();
  }

  @Override
  public void changedUpdate(final DocumentEvent e)
  {
    updateTable();
  }

  /**
   * Update table with new serving size.
   */
  private void updateTable()
  {
    int people;

    try
    {
      // Retrieve and parse the input amount
      people = Integer.parseInt(parent.getPeopleField().getText().trim());

      if (people < 0)
      {
        return;
      }

      parent.refreshTable(people);
    }
    catch (NumberFormatException ex)
    {
      // should get here if letters / non-numbers are entered
      parent.defaultTable();
    }
  }

}
