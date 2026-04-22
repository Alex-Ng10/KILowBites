
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.KILowBites;
import cooking.Ingredients;
import cooking.Recipe;
import cooking.Steps;
import cooking.Utensils;
import gui.AddIngredientWindow;
import gui.RecipeEditor;
import utilities.DocumentState;
import utilities.DocumentStateObserver;
import utilities.FileUtilities;
import utilities.InputUtilities;

/**
 * RecipeEditor controller class. Handles the actions of the RecipeEditor GUI elements
 *
 * @version 10/31/24
 */
public class RecipeEditorController implements ActionListener, DocumentStateObserver
{
  public static String recipeSavePath = "";
  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  @SuppressWarnings("unused")
  private boolean savedAs = false;

  private RecipeEditor editor;
  private Recipe recipe;
  private DocumentState state;

  private String fileName;

  /**
   * Constructor for controller.
   */
  public RecipeEditorController()
  {
    createRecipeEditor();
  }

  /**
   * Creates a new RecipeEditor window.
   */
  private void createRecipeEditor()
  {
    recipe = new Recipe();
    state = DocumentState.NULL;
    editor = new RecipeEditor(recipe, this, true);
    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getUtensilIFP().addObserver(this);
    editor.getContent().getIngredientIFP().addObserver(this);
    editor.getContent().getStepIFP().addObserver(this);
    setFieldsEditable(false);
  }

  /**
   * Gets the name of the recipe from the editor Includes input checking.
   * 
   * @return Returns the name of the recipe in the name field.
   */
  private String getName()
  {
    String name = editor.getContent().getNameField();
    if (name == null || name.equals(""))
    {
      name = "Untitled Recipe";
    }

    return name;
  }

  /**
   * Gets the number of serves from the editor Includes input checking.
   * 
   * @return Returns the amount of people a recipe serves
   */
  private int getServes()
  {
    String serves = editor.getContent().getServesField();
    if (serves == null || serves.equals(""))
    {
      serves = "0";
    }

    if (serves.matches("[0-9]+")) // valid numerical input
    {
      return Integer.parseInt(serves);
    }
    else
    {
      return 0; // default
    }
  }

  @SuppressWarnings("unused")
  private void updateAllElements(final Recipe recipe)
  {
    // creates everything with preset values
    editor.dispose();

    state = DocumentState.UNCHANGED;
    editor = new RecipeEditor(recipe, this, false);
    editor.updateToolBar(state);

    editor.getContent().getMainIFP().addObserver(this);
    editor.getContent().getUtensilIFP().addObserver(this);
    editor.getContent().getIngredientIFP().addObserver(this);
    editor.getContent().getStepIFP().addObserver(this);
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
      // ALLOW USERS TO CHOOSE DIRECTORY TO SAVE RECIPE
      FileNameExtensionFilter recipeFilter = new FileNameExtensionFilter("Recipe Files", "rcp");

      JFileChooser chooser = new JFileChooser();
      chooser.setFileFilter(recipeFilter);

      int result = chooser.showOpenDialog(null);

      if (result == JFileChooser.APPROVE_OPTION)
      {
        File selected = chooser.getSelectedFile();

        fileName = selected.getName();

        recipeSavePath = selected.getAbsolutePath().substring(0,
            selected.getAbsolutePath().length() - fileName.length());

        recipe = new Recipe(); // Create a new recipe
        editor.resetRecipeEditor(); // Reset editor
        state = DocumentState.UNCHANGED; // Set document state
        savedAs = false; // Indicate not yet saved
        editor.updateToolBar(state); // Update toolbar state
        setFieldsEditable(true);
      }
      else
      {
        return;
      }
    }
    else if (command.equals(STRINGS.getString("OPEN")))
    {
      try
      {
        recipe = FileUtilities.openRecipe();
        state = DocumentState.UNCHANGED;

        editor.resetRecipeEditor();
        editor.updateToolBar(state);
        editor.dispose();

        editor = new RecipeEditor(recipe, this, false);
        setFieldsEditable(true);
      }
      catch (Exception anotherException)
      {
      }
    }
    else if (command.equals(STRINGS.getString("SAVE")))
    {
      String name = editor.getContent().getNameField();
      String serves = editor.getContent().getServesField();

      // Validating inputs
      if (name != null)
      {
        recipe.setName(name);
      }

      if (InputUtilities.isPositiveInt(serves))
      {
        recipe.setServes(Integer.parseInt(serves));
      }

      if (!recipeSavePath.equals(""))
      {
        FileUtilities.saveRecipe(recipeSavePath, recipe, fileName); // save
      }

      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(STRINGS.getString("SAVE_AS")))
    {
      String name = editor.getContent().getNameField();
      String serves = editor.getContent().getServesField();

      if (name != null)
      {
        recipe.setName(name);
      }
      if (InputUtilities.isPositiveInt(serves))
      {
        recipe.setServes(Integer.parseInt(serves));
      }

      recipeSavePath = FileUtilities.saveAsRecipe(recipe, fileName);
      state = DocumentState.UNCHANGED;
      editor.updateToolBar(state);
    }
    else if (command.equals(STRINGS.getString("CLOSE")))
    {
      recipe = null;
      recipeSavePath = "";
      savedAs = false;
      editor.resetRecipeEditor();
      state = DocumentState.NULL;
      editor.updateToolBar(state);
      setFieldsEditable(false);
    }

    // commands for Editors
    else if (command.equals(STRINGS.getString("UTENSIL_ADD")))
    {
      Utensils utensil = new Utensils();

      String name = editor.getContent().getUtensilIFP().getText(STRINGS.getString("NAME"));
      String details = editor.getContent().getUtensilIFP().getText(STRINGS.getString("DETAILS"));

      if (InputUtilities.isAlphaNumeric(name))
      {
        utensil.setName(name);
        if (InputUtilities.isAlphaNumeric(details) && !details.equals(""))
        {
          utensil.setDetails(details);
        }

        editor.getContent().getUtensilPanel().addRecipeElement(utensil);

        try
        {
          recipe.addUtensils(utensil);
          editor.getContent().getUtensilIFP().resetFields();
        }
        catch (NullPointerException ex)
        {

        }
        editor.getContent().getUtensilIFP().resetFields();
      }
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(STRINGS.getString("UTENSIL_DELETE")))
    {
      try
      {
        for (Utensils utensil : recipe.getUtensils())
        {
          try
          {
            if (utensil.getName().equals(editor.getContent().getUtensilPanel()
                .getSelectedUtensil(utensil.getName()).getName()))
            {
              recipe.removeUtensils(utensil);
              break;
            }
          }
          catch (NullPointerException ex)
          {
          }
        }
      }
      catch (NullPointerException ex)
      {
      }

      editor.getContent().getUtensilPanel().deleteRecipeElement();
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(STRINGS.getString("INGREDIENT_ADD")))
    {
      Ingredients ingredient = new Ingredients();

      String name = editor.getContent().getIngredientIFP().getText(STRINGS.getString("NAME"));
      String details = editor.getContent().getIngredientIFP().getText(STRINGS.getString("DETAILS"));
      String amount = editor.getContent().getIngredientIFP().getText(STRINGS.getString("AMOUNT"));
      String unit = editor.getContent().getIngredientIFP().getComboBox(STRINGS.getString("UNITS"));

      if (InputUtilities.isAlphaNumeric(name) && InputUtilities.isPositiveDouble(amount))
      {
        boolean addedIngredient = true;
        if (!KILowBites.FOODS.containsFood(name.trim().toLowerCase()))
        {
          AddIngredientWindow window = new AddIngredientWindow(editor, name);
          window.setVisible(true);
          addedIngredient = window.getAdded();
        }

        if (addedIngredient)
        {
          ingredient.setName(name);
          ingredient.setAmount(Double.parseDouble(amount));
          if (InputUtilities.isAlphaNumeric(details))
          {
            ingredient.setDetails(details);
          }

          ingredient.setUnit(unit);

          editor.getContent().getIngredientPanel().addRecipeElement(ingredient);

          recipe.addIngredient(ingredient);
          editor.getContent().getIngredientIFP().resetFields();
        }
      }
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(STRINGS.getString("INGREDIENT_DELETE")))
    {
      try
      {
        for (Ingredients ingredient : recipe.getIngredients())
        {
          try
          {
            if (ingredient.getName().equals(editor.getContent().getIngredientPanel()
                .getSelectedIngredient(ingredient.getName()).getName()))
            {
              recipe.removeIngredients(ingredient);
              break;
            }
          }
          catch (NullPointerException ex)
          {
          }
        }
      }
      catch (NullPointerException ex)
      {
      }

      editor.getContent().getIngredientPanel().deleteRecipeElement();
      editor.getContent().updateStepSourcePanel();
    }
    else if (command.equals(STRINGS.getString("STEP_ADD")))
    {
      Steps step = new Steps();

      String action = editor.getContent().getStepIFP().getText(STRINGS.getString("ACTION"));
      String on = editor.getContent().getStepIFP().getComboBox(STRINGS.getString("ON"));
      String utensil = editor.getContent().getStepIFP().getComboBox(STRINGS.getString("UTENSIL"));
      String details = editor.getContent().getStepIFP().getText(STRINGS.getString("DETAILS"));

      if (InputUtilities.isAlphaNumeric(action) && !on.equals("") && !utensil.equals(""))
      {
        step.setAction(action);

        // if the Utensil and ingredient have the same name, the Utensil will be selected
        step.setSource(editor.getContent().getUtensilPanel().getSelectedUtensil(on));
        if (step.getSource() == null)
        {
          step.setSource(editor.getContent().getIngredientPanel().getSelectedIngredient(on));
        }

        step.setDestination(editor.getContent().getUtensilPanel().getSelectedUtensil(utensil));
        step.setDetails(details);

        editor.getContent().getStepPanel().addRecipeElement(step);

        recipe.addStep(step);
        editor.getContent().getStepIFP().resetStepInput();
      }
    }
    else if (command.equals(STRINGS.getString("STEP_DELETE")))
    {
      try
      {
        for (Steps step : recipe.getSteps())
        {
          try
          {
            if (step.getDetails().equals(editor.getContent().getStepPanel()
                .getSelectedStep(step.getAction(), step.getDetails()).getDetails()))
            {
              recipe.removeSteps(step);
              break;
            }
          }
          catch (NullPointerException ex)
          {
          }
        }
      }
      catch (NullPointerException ex)
      {
      }

      editor.getContent().getStepPanel().deleteRecipeElement();
    }
  }

  private void setFieldsEditable(final boolean editable)
  {
    editor.getContent().getMainIFP().setFieldsEditable(editable);
    editor.getContent().getUtensilIFP().setFieldsEditable(editable);
    editor.getContent().getIngredientIFP().setFieldsEditable(editable);
    editor.getContent().getStepIFP().setFieldsEditable(editable);
  }
}
