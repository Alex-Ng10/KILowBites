package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import app.KILowBites;
import controller.MealEditorController;
import cooking.Meal;

/**
 * MealEditorContent class. Handles MealEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
@SuppressWarnings("serial")
public class MealEditorContent extends JPanel
{
  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  Container contentPane;

  Meal currentMeal; // meal being used

  InputFieldPanel mainMealIFP;
  InputFieldPanel recipeMealIFP;

  EditorPanel mealEditorPanel;
  private String name = "NAME";

//  private static final String NAME = "Name: ";
//  private static final String ADD_RECIPE = "Add Recipe";
//  private static final String RECIPE_ADD = "Recipe Add";
//  private static final String RECIPES = "Recipes";
  
  
  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   * @param isNew
   * @param meal
   */
  public MealEditorContent(final Meal meal, final MealEditorController controller, 
      final boolean isNew)
  {
    super();

    currentMeal = meal;

    this.setBackground(KILowBites.COLOR);

    Container contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // **** INPUT FIELDS ****
    mainMealIFP = new InputFieldPanel();
    if (!isNew)
    {
      mainMealIFP.addJTextField(STRINGS.getString(name), 50, meal.getName());
    }
    else
    {
      mainMealIFP.addJTextField(STRINGS.getString(name), 50);
    }

    // **** EDITOR PANELS ****

    // Recipes
    recipeMealIFP = new InputFieldPanel();
    recipeMealIFP.addJButton(STRINGS.getString("ADD_RECIPE"), STRINGS.getString(
        "RECIPE_ADD"), controller);

    // creates the panel for recipes
    JPanel editorPanel = new JPanel();
    editorPanel.setLayout(new BorderLayout());

    mealEditorPanel = new EditorPanel(STRINGS.getString("RECIPES"), meal, recipeMealIFP,
        controller, isNew);

    editorPanel.add(mealEditorPanel, BorderLayout.CENTER);

    // adds the input fields into the content pane
    contentPane.add(mainMealIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(editorPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }
  /**
   * Gets the Name Field.
   * @return the Name Field
   */
  public String getNameField()
  {
    return mainMealIFP.getText(STRINGS.getString(name));
  }
  
  /**
   * Resets the added information.
   */
  public void reset()
  {
    mainMealIFP.resetFields();
    recipeMealIFP.resetFields();
    mealEditorPanel.resetMeal();
  }
  
  /**
   * Gets the main Input Field Panel.
   * @return The main Input Field Panel.
   */
  public InputFieldPanel getMainIFP()
  {
    return mainMealIFP;
  }
  
  /**
   * Gets the Recipe Input Field Panel.
   * @return The Recipe Input Field Panel.
   */
  public InputFieldPanel getRecipeIFP()
  {
    return recipeMealIFP;
  }
  
  /**
   * Gets the Editor Input Field Panel.
   * @return The Editor Input Field Panel.
   */
  public EditorPanel getEditorPanel()
  {
    return mealEditorPanel;
  }
}
