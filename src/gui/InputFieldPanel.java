//package gui;
//
//import java.awt.BorderLayout;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.ResourceBundle;
//
//import javax.swing.BoxLayout;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.event.DocumentEvent;
//import javax.swing.event.DocumentListener;
//
//import app.KILowBites;
//import utilities.DocumentState;
//import utilities.DocumentStateObserver;
//import utilities.DocumentStateSubject;
//import utilities.Units;
//
///**
// * InputFieldPanel class. Flexibly handles the input fields for Editor frames. Examples: Name,
// * Serves, Number of People (whats below the ribbon, but above the EditorPanel)
// *
// * @author f24team3d
// * @version 10/31/24
// */
//public class InputFieldPanel extends JPanel implements DocumentListener, DocumentStateSubject
//{
//  private Container contentPane;
//  static final Locale         LOCALE  = Locale.getDefault();
//  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
//  
////  private static final String UNITS = "Units: ";
//
//
//  JPanel inputFields; // the panel for the gui
//
//  Units temp = new Units();
//
//  // the editable fields in the IFP. The key is the label of the field.
//  HashMap<String, JTextField> fields;
//  HashMap<String, JComboBox<String>> comboBoxes;
//  HashMap<String, JButton> buttons;
//
//  private ArrayList<DocumentStateObserver> observers;
//
//  /**
//   * Constructor for InputFields.
//   */
//  public InputFieldPanel()
//  {
//    super();
//
//    observers = new ArrayList<DocumentStateObserver>();
//
//    this.setBackground(KILowBites.COLOR);
//
//    contentPane = new Container();
//    contentPane.setLayout(new BorderLayout());
//
//    // creates the text area for inputs fields (examples: Name, Serves, Number of People)
//    inputFields = new JPanel();
//    inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.X_AXIS));
//
//    // initializes the ArrayLists of editable fields
//    fields = new HashMap<String, JTextField>();
//    comboBoxes = new HashMap<String, JComboBox<String>>();
//    buttons = new HashMap<String, JButton>();
//
//    // adds the input fields into the content pane
//    contentPane.add(inputFields, BorderLayout.CENTER);
//
//    this.add(contentPane);
//  }
//
//  public void insertUpdate(DocumentEvent e)
//  {
//    // do nothing
//  }
//
//  public void removeUpdate(DocumentEvent e)
//  {
//    // do nothing
//  }
//
//  public void changedUpdate(DocumentEvent e)
//  {
//    // do nothing
//  }
//
//  /**
//   * Adds a JLabel to the input field panel.
//   *
//   * @param content
//   *          the label to add
//   */
//  public void addJLabel(String label)
//  {
//    inputFields.add(new JLabel(label));
//  }
//
//  /**
//   * Adds a JLabel and Field to the input field panel.
//   *
//   * @param content
//   *          the field to add
//   * @param size
//   *          the size of the field
//   */
//  public void addJTextField(String label, int size)
//  {
//    inputFields.add(new JLabel(label));
//
//    JTextField field = new JTextField(size);
//
//    field.getDocument().addDocumentListener(new DocumentListener()
//    {
//      // if text is added or removed from field, state is changed
//      @Override
//      public void insertUpdate(DocumentEvent e)
//      {
//        notifyObservers();
//      }
//
//      @Override
//      public void removeUpdate(DocumentEvent e)
//      {
//        notifyObservers();
//      }
//
//      @Override
//      public void changedUpdate(DocumentEvent e)
//      {
//        // do nothing
//      }
//    });
//
//    inputFields.add(field);
//    fields.put(label, field);
//  }
//
//  /**
//   * Adds a JLabel and Field to the input field panel. Has preset content
//   *
//   * @param content
//   *          the field to add
//   * @param size
//   *          the size of the field
//   */
//  public void addJTextField(String label, int size, String content)
//  {
//    inputFields.add(new JLabel(label));
//
//    JTextField field = new JTextField(size);
//    field.setText(content);
//
//    field.getDocument().addDocumentListener(new DocumentListener()
//    {
//      // if text is added or removed from field, state is changed
//      @Override
//      public void insertUpdate(DocumentEvent e)
//      {
//        notifyObservers();
//      }
//
//      @Override
//      public void removeUpdate(DocumentEvent e)
//      {
//        notifyObservers();
//      }
//
//      @Override
//      public void changedUpdate(DocumentEvent e)
//      {
//        // do nothing
//      }
//    });
//
//    inputFields.add(field);
//    fields.put(label, field);
//  }
//
//  /**
//   * Adds a JButton to the input field panel.
//   */
//  public void addJButton(String label, String actionCommand, ActionListener controller)
//  {
//    JButton button = new JButton(label);
//    button.setActionCommand(actionCommand);
//    button.addActionListener(controller);
//
//    buttons.put(label, button);
//
//    inputFields.add(button);
//  }
//
//  /**
//   * Adds a JComboBox to the input field panel.
//   */
//  public void addJComboBox(String label, String[] items)
//  {
//    inputFields.add(new JLabel(label));
//    JComboBox<String> comboBox = new JComboBox<String>();
//
//    for (String item : items)
//    {
//      comboBox.addItem(item);
//    }
//
//    comboBox.setPreferredSize(new Dimension(70, 20));
//    comboBox.setMaximumSize(new Dimension(70, 20));
//
//    comboBoxes.put(label, comboBox);
//    inputFields.add(comboBox);
//  }
//
//  /**
//   * Resets the fields in the input field panel.
//   */
//  public void resetFields()
//  {
//    for (JTextField field : fields.values())
//    {
//      field.setText("");
//    }
//
//    for (JComboBox<String> comboBox : comboBoxes.values())
//    {
//      comboBox.removeAllItems();
//
//      comboBox.addItem("");
//      comboBox.setSelectedIndex(0);
//    }
//
//    // re adds the units list
//    this.updateComboBox(STRINGS.getString("UNITS"), temp.getAllUnitsPlusIndividual());
//  }
//
//  /**
//   * Reset the fields in the step input.
//   */
//  public void resetStepInput()
//  {
//    for (JTextField field : fields.values())
//    {
//      field.setText("");
//    }
//
//    for (JComboBox<String> comboBox : comboBoxes.values())
//    {
//      comboBox.setSelectedIndex(0);
//    }
//  }
//
//  /**
//   * Checks if all fields are empty
//   *
//   * @return true if all fields are empty
//   */
//  public boolean allFieldsEmpty()
//  {
//    for (JTextField field : fields.values())
//    {
//      if (!field.getText().equals(""))
//      {
//        return false;
//      }
//    }
//
//    return true;
//  }
//
//  /**
//   * Gets the text from a JTextField.
//   *
//   * @param label
//   *          the label of the field
//   * @return the text in the field
//   */
//  public String getText(String label)
//  {
//    return fields.get(label).getText();
//  }
//
//  public String getComboBox(String label)
//  {
//    return (String) comboBoxes.get(label).getSelectedItem();
//  }
//
//  public void updateComboBox(String label, String[] newItems)
//  {
//    JComboBox<String> comboBox = comboBoxes.get(label);
//    if (comboBox != null)
//    {
//      comboBox.removeAllItems();
//
//      for (String item : newItems)
//      {
//        comboBox.addItem(item);
//      }
//
//      comboBoxes.put(comboBox.getName(), comboBox);
//
//      comboBox.setPreferredSize(new Dimension(70, 20));
//      comboBox.setMaximumSize(new Dimension(70, 20));
//      comboBox.repaint();
//      comboBox.revalidate();
//    }
//  }
//
//  public void addObserver(DocumentStateObserver observer)
//  {
//    observers.add(observer);
//  }
//
//  public void removeObserver(DocumentStateObserver observer)
//  {
//    observers.remove(observer);
//  }
//
//  public void notifyObservers()
//  {
//    for (DocumentStateObserver observer : observers)
//    {
//      observer.handleNotification(DocumentState.CHANGED);
//    }
//  }
//}


package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import app.KILowBites;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.DocumentStateSubject;
import utilities.Units;

/**
 * InputFieldPanel class. Flexibly handles the input fields for Editor frames. Examples: Name,
 * Serves, Number of People (whats below the ribbon, but above the EditorPanel)
 *
 * @version 10/31/24
 */
public class InputFieldPanel extends JPanel implements DocumentListener, DocumentStateSubject
{
  static final Locale         LOCALE  = Locale.getDefault();
  private static final long serialVersionUID = 1L;
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  JPanel inputFields; // the panel for the gui

  Units temp = new Units();

  // the editable fields in the IFP. The key is the label of the field.
  HashMap<String, JTextField> fields;
  HashMap<String, JComboBox<String>> comboBoxes;
  HashMap<String, JButton> buttons;
  private Container contentPane;

  private ArrayList<DocumentStateObserver> observers;

  /**
   * Constructor for InputFields.
   */
  public InputFieldPanel()
  {
    super();

    observers = new ArrayList<DocumentStateObserver>();

    this.setBackground(KILowBites.COLOR);

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the text area for inputs fields (examples: Name, Serves, Number of People)
    inputFields = new JPanel();
    inputFields.setLayout(new BoxLayout(inputFields, BoxLayout.X_AXIS));

    // initializes the ArrayLists of editable fields
    fields = new HashMap<String, JTextField>();
    comboBoxes = new HashMap<String, JComboBox<String>>();
    buttons = new HashMap<String, JButton>();

    // adds the input fields into the content pane
    contentPane.add(inputFields, BorderLayout.CENTER);

    this.add(contentPane);
  }

  /**
   * Adds a JLabel to the input field panel.
   *
   * @param label
   *          the label to add
   */
  public void addJLabel(final String label)
  {
    inputFields.add(new JLabel(label));
  }

  /**
   * Adds a JLabel and Field to the input field panel.
   *
   * @param label
   *          the field to add
   * @param size
   *          the size of the field
   */
  public void addJTextField(final String label, final int size)
  {
    inputFields.add(new JLabel(label));

    JTextField field = new JTextField(size);

    field.getDocument().addDocumentListener(new DocumentListener()
    {
      // if text is added or removed from field, state is changed
      @Override
      public void insertUpdate(final DocumentEvent e)
      {
        notifyObservers();
      }

      @Override
      public void removeUpdate(final DocumentEvent e)
      {
        notifyObservers();
      }

      @Override
      public void changedUpdate(final DocumentEvent e)
      {
        // do nothing
      }
    });

    inputFields.add(field);
    fields.put(label, field);
  }

  /**
   * Adds a JLabel and Field to the input field panel. Has preset content
   *
   * @param content
   *          the field to add
   * @param size
   *          the size of the field
   * @param label
   */
  public void addJTextField(final String label, final int size, final String content)
  {
    inputFields.add(new JLabel(label));

    JTextField field = new JTextField(size);
    field.setText(content);

    field.getDocument().addDocumentListener(new DocumentListener()
    {
      // if text is added or removed from field, state is changed
      @Override
      public void insertUpdate(final DocumentEvent e)
      {
        notifyObservers();
      }

      @Override
      public void removeUpdate(final DocumentEvent e)
      {
        notifyObservers();
      }

      @Override
      public void changedUpdate(final DocumentEvent e)
      {
        // do nothing
      }
    });

    inputFields.add(field);
    fields.put(label, field);
  }

  /**
   * Adds a JButton to the input field panel.
   * @param label
   * @param controller adds the controller to the button
   * @param actionCommand The command
   * 
   */
  public void addJButton(final String label, final String actionCommand, 
      final ActionListener controller)
  {
    JButton button = new JButton(label);
    button.setActionCommand(actionCommand);
    button.addActionListener(controller);

    buttons.put(label, button);

    inputFields.add(button);
  }

  /**
   * Adds a JComboBox to the input field panel.
   * @param label
   * @param items The items to add in the combo box
   */
  public void addJComboBox(final String label, final String[] items)
  {
    inputFields.add(new JLabel(label));
    JComboBox<String> comboBox = new JComboBox<String>();

    for (String item : items)
    {
      comboBox.addItem(item);
    }

    comboBox.setPreferredSize(new Dimension(70, 20));
    comboBox.setMaximumSize(new Dimension(70, 20));

    comboBoxes.put(label, comboBox);
    inputFields.add(comboBox);
  }

  /**
   * Resets the fields in the input field panel.
   */
  public void resetFields()
  {
    for (JTextField field : fields.values())
    {
      field.setText("");
    }

    for (JComboBox<String> comboBox : comboBoxes.values())
    {
      comboBox.removeAllItems();

      comboBox.addItem("");
      comboBox.setSelectedIndex(0);
    }

    // re adds the units list
    this.updateComboBox(STRINGS.getString("UNITS"), temp.getAllUnitsPlusIndividual());
  }

  /**
   * Reset the fields in the step input.
   */
  public void resetStepInput()
  {
    for (JTextField field : fields.values())
    {
      field.setText("");
    }

    for (JComboBox<String> comboBox : comboBoxes.values())
    {
      comboBox.setSelectedIndex(0);
    }
  }

  /**
   * Checks if all fields are empty.
   *
   * @return true if all fields are empty
   */
  public boolean allFieldsEmpty()
  {
    for (JTextField field : fields.values())
    {
      if (!field.getText().equals(""))
      {
        return false;
      }
    }

    return true;
  }

  /**
   * Gets the text from a JTextField.
   *
   * @param label
   *          the label of the field
   * @return the text in the field
   */
  public String getText(final String label)
  {
    return fields.get(label).getText();
  }
  /**
   * Gets the combo box.
   * @param label the name of the combo box.
   * @return the combo box
   */
  public String getComboBox(final String label)
  {
    return (String) comboBoxes.get(label).getSelectedItem();
  }
  
  /**
   * Updates the combo box with new items.
   * @param label the name of the combo box
   * @param newItems the new items that are going to be added.
   */
  public void updateComboBox(final String label, final String[] newItems)
  {
    JComboBox<String> comboBox = comboBoxes.get(label);
    if (comboBox != null)
    {
      comboBox.removeAllItems();

      for (String item : newItems)
      {
        comboBox.addItem(item);
      }

      comboBoxes.put(comboBox.getName(), comboBox);

      comboBox.setPreferredSize(new Dimension(70, 20));
      comboBox.setMaximumSize(new Dimension(70, 20));
      comboBox.repaint();
      comboBox.revalidate();
    }
  }
  
  /**
   * Adds an observer to the pane.
   * @param observer
   */
  public void addObserver(final DocumentStateObserver observer)
  {
    observers.add(observer);
  }
  
  /**
   * Removes an observer to the pane.
   * @param observer
   */
  public void removeObserver(final DocumentStateObserver observer)
  {
    observers.remove(observer);
  }
  
  /**
   * Creates a notification for observers.
   */
  public void notifyObservers()
  {
    for (DocumentStateObserver observer : observers)
    {
      observer.handleNotification(DocumentState.CHANGED);
    }
  }

  /**
   * Sets the Fields and the combo box to editable.
   * @param editable
   */
  public void setFieldsEditable(final boolean editable)
  {
    for (JTextField field : fields.values())
    {
      field.setEditable(editable);
    }

    for (JComboBox<String> comboBox : comboBoxes.values())
    {
      comboBox.setEnabled(editable);
    }
  }

  @Override
  public void insertUpdate(final DocumentEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeUpdate(final DocumentEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void changedUpdate(final DocumentEvent e)
  {
    // TODO Auto-generated method stub
    
  }
}
