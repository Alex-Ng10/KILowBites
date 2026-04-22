package gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import app.KILowBites;
import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import cooking.RecipeElement;
import cooking.RecipeElementType;
import cooking.Steps;
import cooking.Utensils;
import utilities.InputUtilities;

/**
 * ViewerPanel class. Flexibly handles the Editor panels for Editor frames. Examples: Utensils,
 * Ingredients, Steps, Recipes (where the file changes go)
 *
 * @author f24team3d
 * @version 10/26/24
 */
public class ProcessViewerPanel extends JPanel
{
  static final Locale LOCALE = Locale.getDefault();
  private static final long serialVersionUID = 1L;
  // strings needed to determine the type of the input, needed since no interface

  Container contentPane;

  // shared variables
  JPanel fileViewerPanel;
  JScrollPane scrollPane;

  // RecipeEditor variables
  DefaultListModel<Object> processArea;
  JList<Object> recipeList;
  private String seperator = "-----------------------------";

  /**
   * Constructor for ViewerPanel (for RecipeEditor).
   *
   * @param inputFieldPanel
   *          the input field panel
   * @param recipeViewerController
   *          the controller for the RecipeEditor
   * @param type
   * @param recipe
   */
  public ProcessViewerPanel(final RecipeElementType type, 
      final Recipe recipe, final InputFieldPanel inputFieldPanel,
      final ProcessViewerController recipeViewerController)
  {
    super();

    this.setBackground(KILowBites.COLOR);

    contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    contentPane.setBackground(KILowBites.COLOR);

    // set preferred size for ViewerPanels
    this.setPreferredSize(new Dimension(650, 215));

    // creates the editor label border
    this.setBorder(BorderFactory.createTitledBorder(type.getLabel(true)));

    // creates the file editor
    fileViewerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    processArea = new DefaultListModel<>();
    recipeList = new JList<>(processArea);

    if (type == RecipeElementType.UTENSIL)
    {
      processArea.clear();
      for (Utensils utensil : recipe.getUtensils())
      {
        processArea.addElement(utensil);
      }
    }
    else if (type == RecipeElementType.STEP)
    {
      processArea.clear();
      for (Steps step : recipe.getSteps())
      {
        processArea.addElement(step);
      }
    }

    // creates the file area for the editor panel
    scrollPane = new JScrollPane(recipeList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 125));

    fileViewerPanel.add(scrollPane);
    contentPane.add(fileViewerPanel);

    this.add(contentPane);
  }
  
  /**
   * Constructor for the process viewer pane.
   * @param type
   * @param meal
   * @param inputFieldPanel
   * @param recipeViewerController
   */
  public ProcessViewerPanel(final RecipeElementType type, final Meal meal, 
      final InputFieldPanel inputFieldPanel,
      final ProcessViewerController recipeViewerController)
  {
    super();

    List<Recipe> recipes = meal.getRecipes();

    contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // Set preferred size for ViewerPanels
    this.setPreferredSize(new Dimension(650, 215));

    // Create the editor label border
    this.setBorder(BorderFactory.createTitledBorder(type.getLabel(true)));

    // Create the file editor
    fileViewerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    processArea = new DefaultListModel<>();
    recipeList = new JList<>(processArea);

    processArea.clear();
    processArea.addElement(seperator);
    for (Recipe recipe : recipes)
    {
      // Add recipe name as a separator
      if (type == RecipeElementType.UTENSIL)
      {
        processArea.addElement(InputUtilities.separateByCapital(recipe.getName()));
        processArea.addElement(seperator);
        for (Utensils utensil : recipe.getUtensils())
        {
          processArea.addElement(utensil);
        }
        // processArea.addElement("\n");
        processArea.addElement(seperator);

      }
      else if (type == RecipeElementType.STEP)
      {
        processArea.addElement(InputUtilities.separateByCapital(recipe.getName()));
        processArea.addElement(seperator);
        for (Steps step : recipe.getSteps())
        {
          processArea.addElement(step);
        }
        processArea.addElement(seperator);

      }
    }

    // Create the file area for the editor panel
    scrollPane = new JScrollPane(recipeList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 125));

    fileViewerPanel.add(scrollPane);
    contentPane.add(fileViewerPanel);

    this.add(contentPane);
  }
  /**
   * Adds the given element to the process area.
   * @param element
   */
  public void addRecipeElement(final RecipeElement element)
  {
    processArea.addElement(element);
  }
  
  /**
   * Gets the process Area.
   * @return The process Area.
   */
  public DefaultListModel<Object> getProcessArea() 
  {
    return processArea;
  }
  
  /**
   * Gets the Recipe List.
   * @return the Recipe List.
   */
  public JList<Object> getRecipeList()
  {
    return recipeList;
  }
}
