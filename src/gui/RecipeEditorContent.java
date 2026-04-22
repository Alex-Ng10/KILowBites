package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import app.KILowBites;
import controller.RecipeEditorController;
import cooking.Recipe;
import cooking.RecipeElementType;
import utilities.UnitType;
import utilities.Units;

/**
 * RecipeEditorContent class. Handles RecipeEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class RecipeEditorContent extends JPanel
{
  static final Locale         LOCALE  = Locale.getDefault();
  private static final long serialVersionUID = -5218221163153774982L;

  private static final String NAME = "NAME";
  private static final String SERVES = "SERVES";
  private static final String DETAILS = "DETAILS";
  private static final String AMOUNT = "AMOUNT";
  private static final String UNITS = "UNITS";
  private static final String ADD = "ADD";
//  private static final String UTENSIL_ADD = "Utensil Add";
//  private static final String INGREDIENT_ADD = "Ingredient Add";
//  private static final String ACTION = "Action: ";
  private static final String ON = "ON";
  private static final String UTENSIL = "UTENSIL";
//  private static final String STEP_ADD = "Step Add";

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  
  Container contentPane;

  Recipe currentRecipe; // recipe being used

  JPanel editorPanel;

  InputFieldPanel mainIFP;
  InputFieldPanel utensilIFP;
  InputFieldPanel ingredientIFP;
  InputFieldPanel stepIFP;

  EditorPanel utensilEditorPanel;
  EditorPanel ingredientEditorPanel;
  EditorPanel stepEditorPanel;

  String[] stepOn;
  String[] stepUtensils;
  String[] stepIngredients;
  String[] stepSources;

  Units units;

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   * @param isNew Checks if it a new Recipe.
   * @param recipe The recipe.
   */
  public RecipeEditorContent(final Recipe recipe, final RecipeEditorController controller, 
      final boolean isNew)
  {
    super();

    currentRecipe = recipe;

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    units = new Units();

    this.setBackground(KILowBites.COLOR);

    // **** INPUT FIELDS ****

    // adds all the elements to the input fields
    mainIFP = new InputFieldPanel();
    if (!isNew)
    {
      mainIFP.addJTextField(STRINGS.getString(NAME), 50, recipe.getName());
      mainIFP.addJTextField(STRINGS.getString(SERVES), 10, String.valueOf(recipe.getServes()));
    }
    else
    {
      mainIFP.addJTextField(STRINGS.getString(NAME), 50);
      mainIFP.addJTextField(STRINGS.getString(SERVES), 10);
    }

    // **** EDITOR PANELS ****

    // Utensils
    utensilIFP = new InputFieldPanel();
    utensilIFP.addJTextField(STRINGS.getString(NAME), 25);
    utensilIFP.addJTextField(STRINGS.getString(DETAILS), 25);
    utensilIFP.addJButton(STRINGS.getString(ADD), STRINGS.getString("UTENSIL_ADD"), controller);

    // Ingredients
    ingredientIFP = new InputFieldPanel();
    ingredientIFP.addJTextField(STRINGS.getString(NAME), 15);
    ingredientIFP.addJTextField(STRINGS.getString(DETAILS), 7);
    ingredientIFP.addJTextField(STRINGS.getString(AMOUNT), 7);
    if (UnitType.getImperialSelected()) //Checks what the user selected for unit type
    {    
      ingredientIFP.addJComboBox(STRINGS.getString(UNITS), UnitType.getImperialUnitsIndividual());
    } 
    else 
    {
      ingredientIFP.addJComboBox(STRINGS.getString(UNITS), UnitType.getMetricUnitsIndividual());
    }
    
    
    ingredientIFP.addJButton(STRINGS.getString(ADD), 
        STRINGS.getString("INGREDIENT_ADD"), controller);

    // Steps
    stepIFP = new InputFieldPanel();
    stepIFP.addJTextField(STRINGS.getString("ACTION"), 7);

    stepOn = new String[] {""};
    stepUtensils = new String[] {""};
    stepIngredients = new String[] {""};

    if (!isNew)
    {
      stepOn = new String[recipe.getUtensils().size() + recipe.getIngredients().size()];
      for (int i = 0; i < recipe.getUtensils().size(); i++)
      {
        stepOn[i] = recipe.getUtensils().get(i).getName();
      }

      int ingredientIndex = 0; // Track position in ingredients list
      for (int i = recipe.getUtensils().size(); i < stepOn.length; i++)
      {
        stepOn[i] = recipe.getIngredients().get(ingredientIndex).getName();
        ingredientIndex++;
      }

      stepUtensils = new String[recipe.getUtensils().size()];
      for (int i = 0; i < stepUtensils.length; i++)
      {
        stepUtensils[i] = recipe.getUtensils().get(i).getName();
      }

      stepIngredients = new String[recipe.getIngredients().size()];
      for (int i = 0; i < stepIngredients.length; i++)
      {
        stepIngredients[i] = recipe.getIngredients().get(i).getName();
      }
    }

    stepIFP.addJComboBox(STRINGS.getString(ON), stepOn);
    stepIFP.addJComboBox(STRINGS.getString(UTENSIL), stepUtensils);

    stepIFP.addJTextField(STRINGS.getString(DETAILS), 15);
    stepIFP.addJButton(STRINGS.getString(ADD), STRINGS.getString("STEP_ADD"), controller);

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.Y_AXIS));

    utensilEditorPanel = new EditorPanel(RecipeElementType.UTENSIL, recipe, utensilIFP, controller,
        isNew);
    ingredientEditorPanel = new EditorPanel(RecipeElementType.INGREDIENT, recipe, ingredientIFP,
        controller, isNew);
    stepEditorPanel = new EditorPanel(RecipeElementType.STEP, recipe, stepIFP, controller, isNew);

    editorPanel.add(utensilEditorPanel);
    editorPanel.add(ingredientEditorPanel);
    editorPanel.add(stepEditorPanel);

    // adds the input fields into the content pane
    contentPane.add(mainIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(editorPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  /**
   * Gets the Name Field.
   * 
   * @return The string representation of the name field.
   */
  public String getNameField()
  {
    return mainIFP.getText(STRINGS.getString(NAME));
  }

  /**
   * Gets the serves Field.
   * 
   * @return The string representation of the serves field.
   */
  public String getServesField()
  {
    return mainIFP.getText(STRINGS.getString(SERVES));
  }

  /**
   * Gets the Utensil panel.
   * 
   * @return A panel.
   */
  public EditorPanel getUtensilPanel()
  {
    return utensilEditorPanel;
  }

  /**
   * Gets the Ingredient panel.
   * 
   * @return A panel.
   */
  public EditorPanel getIngredientPanel()
  {
    return ingredientEditorPanel;
  }
  
  /**
   * Updates the Step source panel.
   */
  public void updateStepSourcePanel()
  {
    stepUtensils = new String[utensilEditorPanel.getRecipeList().getModel().getSize()];

    for (int i = 0; i < stepUtensils.length; i++)
    {
      stepUtensils[i] = utensilEditorPanel.getRecipeList().getModel().getElementAt(i).getName();
    }

    stepIngredients = new String[ingredientEditorPanel.getRecipeList().getModel().getSize()];

    for (int i = 0; i < stepIngredients.length; i++)
    {
      stepIngredients[i] = ingredientEditorPanel.getRecipeList().getModel().getElementAt(i)
          .getName();
    }

    stepIFP.updateComboBox(STRINGS.getString(UTENSIL), stepUtensils);

    stepSources = new String[stepUtensils.length + stepIngredients.length];
    System.arraycopy(stepUtensils, 0, stepSources, 0, stepUtensils.length);
    System.arraycopy(stepIngredients, 0, stepSources, stepUtensils.length, stepIngredients.length);

    stepIFP.updateComboBox(STRINGS.getString(ON), stepSources);
  }

  /**
   * Gets the step panel.
   * 
   * @return A panel.
   */
  public EditorPanel getStepPanel()
  {
    return stepEditorPanel;
  }

  /**
   * Resets all the fields.
   */
  public void reset()
  {
    mainIFP.resetFields();
    utensilIFP.resetFields();
    ingredientIFP.resetFields();
    stepIFP.resetFields();

    utensilEditorPanel.reset();
    ingredientEditorPanel.reset();
    stepEditorPanel.reset();
  }

  /**
   * Gets the main input field panel.
   * 
   * @return The input field panel.
   */
  public InputFieldPanel getMainIFP()
  {
    return mainIFP;
  }

  /**
   * Gets the Utensil input field panel.
   * 
   * @return The input field panel.
   */
  public InputFieldPanel getUtensilIFP()
  {
    return utensilIFP;
  }
  
  /**
   * Gets the Ingredient input field panel.
   * 
   * @return The input field panel.
   */
  public InputFieldPanel getIngredientIFP()
  {
    return ingredientIFP;
  }

  /**
   * Gets the Step input field panel.
   * 
   * @return The input field panel.
   */
  public InputFieldPanel getStepIFP()
  {
    return stepIFP;
  }
}
