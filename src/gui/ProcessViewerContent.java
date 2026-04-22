package gui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import app.KILowBites;
import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import cooking.RecipeElementType;

/**
 * RecipeEditorContent class. Handles RecipeEditor main content.
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class ProcessViewerContent extends JPanel
{
  private static final long serialVersionUID = 1L;

  Container contentPane;

  Recipe currentRecipe; // recipe being used
  Meal currentMeal;

  JPanel processViewerPanel;

  InputFieldPanel mainIFP;
  InputFieldPanel utensilIFP;
  InputFieldPanel stepIFP;

  ProcessViewerPanel utensilProcessViewerPanel;
  ProcessViewerPanel stepProcessViewerPanel;
  ProcessViewerPanel printProcessViewerPanel;

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param recipe
   * @param controller
   *          the controller for the RecipeEditor
   */
  public ProcessViewerContent(final Recipe recipe, final ProcessViewerController controller)
  {
    super();

    currentRecipe = recipe;

    this.setBackground(KILowBites.COLOR);

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel processViewerPanel = new JPanel();
    processViewerPanel.setLayout(new BoxLayout(processViewerPanel, BoxLayout.Y_AXIS));

    utensilProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, recipe,
        utensilIFP, controller);
    stepProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.STEP, recipe, stepIFP,
        controller);

    processViewerPanel.add(utensilProcessViewerPanel);
    processViewerPanel.add(stepProcessViewerPanel);

    contentPane.add(processViewerPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   * @param meal
   *          the controller for the RecipeEditor
   */
  public ProcessViewerContent(final Meal meal, final ProcessViewerController controller)
  {
    super();

    currentMeal = meal;

    contentPane = new Container();
    contentPane.setLayout(new BorderLayout());

    // creates the panel for the Utensils, Ingredients, and Steps
    JPanel processViewerPanel = new JPanel();
    processViewerPanel.setLayout(new BoxLayout(processViewerPanel, BoxLayout.Y_AXIS));

    utensilProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, meal, utensilIFP,
        controller);
    stepProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.STEP, meal, stepIFP,
        controller);
//    printProcessViewerPanel = new ProcessViewerPanel(RecipeElementType.UTENSIL, meal, utensilIFP,
//            controller);

    processViewerPanel.add(utensilProcessViewerPanel);
    processViewerPanel.add(stepProcessViewerPanel);

    // adds the input fields into the content pane
    // contentPane.add(mainIFP, BorderLayout.NORTH);

    // adds the editorPane into the content pane
    contentPane.add(processViewerPanel, BorderLayout.CENTER);

    this.add(contentPane);
  }

/**
 * Gets Utensil Panel.
 * 
 * @return ProcessViewerPanel
 */
  public ProcessViewerPanel getUtensilPanel()
  {
    return utensilProcessViewerPanel;
  }
  /**
   * Gets Step Panel.
   * 
   * @return ProcessViewerPanel
   */
  public ProcessViewerPanel getStepPanel()
  {
    return stepProcessViewerPanel;
  }
  
  /**
   * Gets Main IFP.
   * 
   * @return ProcessViewerPanel
   */
  public InputFieldPanel getMainIFP()
  {
    return mainIFP;
  }
  /**
   * Gets Utensil IFP.
   * 
   * @return ProcessViewerPanel
   */
  public InputFieldPanel getUtensilIFP()
  {
    return utensilIFP;
  }
  /**
   * Gets Step IFP.
   * 
   * @return ProcessViewerPanel
   */
  public InputFieldPanel getStepIFP()
  {
    return stepIFP;
  }
  /**
   * Gets ProcessViewerPanel.
   * 
   * @return ProcessViewerPanel
   */
  public JPanel getProcessViewerPanel()
  {
    return processViewerPanel;
  }
}
