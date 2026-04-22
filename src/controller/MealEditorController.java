package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.KILowBites;
import cooking.Meal;
import cooking.Recipe;
import gui.MealEditor;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.FileUtilities;

/**
 * MealEditor controller class. Handles the actions of the MealEditor GUI elements
 *
 * @version 10/29/24
 */
public class MealEditorController implements ActionListener, DocumentStateObserver
{
  public static String mealSavePath = "";
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private MealEditor editor;
  private Meal meal;
  private DocumentState state;

  private boolean savedAs = false;
  private String fileName;

  /**
   * Constructor for controller.
   */
  public MealEditorController()
  {
    createMealEditor();
  }

  private void createMealEditor()
  {
    state = DocumentState.NULL;
    meal = new Meal();
    editor = new MealEditor(meal, this, true);

    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getRecipeIFP().addObserver(this);
    setFieldsEditable(false);
  }

  /**
   * Called when the document state changes.
   *
   * @param state
   */
  public void handleNotification(final DocumentState state)
  {
    this.state = state;
    editor.updateToolBar(state);
    setFieldsEditable(state != DocumentState.NULL);
  }

  /**
   * Method for when action is performed.
   * 
   * @param e
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command;

    command = e.getActionCommand();

    // commands for Toolbar
    if (command.equals(STRINGS.getString("NEW")))
    {
      FileNameExtensionFilter mealFilter = new FileNameExtensionFilter("Meal Files", "mel");

      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(mealFilter);

      int result = chooser.showOpenDialog(null);

      if (result == JFileChooser.APPROVE_OPTION)
      {

        File selected = chooser.getSelectedFile();
        mealSavePath = selected.getAbsolutePath();

        fileName = selected.getName();

        mealSavePath = selected.getAbsolutePath().substring(0,
            selected.getAbsolutePath().length() - fileName.length());

        meal = new Meal(); // Create a new meal
        editor.resetMealEditor(); // Reset editor
        state = DocumentState.UNCHANGED; // Set document state
        savedAs = false; // Indicate not yet saved
        editor.updateToolBar(state); // Update toolbar state
        setFieldsEditable(true);
      }
    }
    else if (command.equals(STRINGS.getString("OPEN")))
    {
      meal = FileUtilities.openMeal();
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
      setFieldsEditable(true);

      editor.resetMealEditor();
      editor.dispose();
      editor = new MealEditor(meal, this, false);
    }
    else if (command.equals(STRINGS.getString("SAVE")))
    {
      String name = editor.getContent().getNameField();

      if (name != null)
      {
        meal.setName(name);
      }

      if (!mealSavePath.equals(""))
      {
        FileUtilities.saveMeal(mealSavePath, meal, fileName); // save
      }

      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(STRINGS.getString("SAVE_AS")))
    {
      String name = editor.getContent().getNameField();

      if (name != null)
      {
        meal.setName(name);
      }

      mealSavePath = FileUtilities.saveAsMeal(meal, fileName); // save as

      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(STRINGS.getString("CLOSE")))
    {
      meal = null;
      mealSavePath = "";
      editor.resetMealEditor();
      state = DocumentState.NULL;
      editor.updateToolBar(state);
      setFieldsEditable(false);
    }
    else if (command.equals(STRINGS.getString("RECIPE_ADD")))
    {
      Recipe r = FileUtilities.openRecipe();

      boolean added = meal.addRecipe(r);
      if (!added)
      {
        editor.getContent().getEditorPanel().addMealElement(r.getName());
      }
    }
    else if (command.equals(STRINGS.getString("RECIPE_DELETE")))
    {
      meal.removeRecipe(editor.getContent().getEditorPanel().getMealList().getSelectedValue());
      editor.getContent().getEditorPanel().deleteMealElement();
      meal.printRecipes();
    }
  }

  private void setFieldsEditable(final boolean editable)
  {
    editor.getContent().getMainIFP().setFieldsEditable(editable);
    editor.getContent().getRecipeIFP().setFieldsEditable(editable);
  }
}
