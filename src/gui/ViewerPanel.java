package gui;

import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;

import app.KILowBites;
import controller.MealEditorController;
import controller.RecipeEditorController;
import controller.ProcessViewerController;
import cooking.*;
import utilities.Units;

/**
 * ViewerPanel class. Flexibly handles the Editor panels for Editor frames.
 * Examples: Utensils, Ingredients, Steps, Recipes
 * (where the file changes go)
 *
 * @author f24team3d
 * @version 10/26/24
 */
@SuppressWarnings("serial")
public class ViewerPanel extends JPanel
{
  // strings needed to determine the type of the input, needed since no interface
  final String UTENSILS = "Utensils";
  final String INGREDIENTS = "Ingredients";
  final String STEPS = "Steps";
//  private static final String DELETE = "Delete";
//  private static final String RECIPE_DELETE = "Recipe Delete";
  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  
  Container contentPane;

  // shared variables
  JPanel fileViewerPanel;
  JButton deleteButton;
  JScrollPane scrollPane;

  // RecipeEditor variables
  DefaultListModel<RecipeElement> recipeFileArea;
  JList<RecipeElement> recipeList;

  // MealEditor variables
  DefaultListModel<Recipe> mealFileArea;
  JList<Recipe> mealList;

  /**
   * Constructor for ViewerPanel (for RecipeEditor).
   *
   * @param inputFieldPanel the input field panel
   * @param recipeViewerController the controller for the RecipeEditor
   */
  public ViewerPanel(RecipeElementType type, Recipe recipe, InputFieldPanel inputFieldPanel, ProcessViewerController recipeViewerController, boolean isNew)
  {
    super();

    contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // set preferred size for ViewerPanels
    this.setPreferredSize(new Dimension(650, 215));

    // creates the editor label border
    this.setBorder(BorderFactory.createTitledBorder(type.getLabel(true)));

    // creates the file editor
    fileViewerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // creates the lists shown in the file editor
    if (!isNew)
    {
      recipeFileArea = new DefaultListModel<>();
      recipeList = new JList<>(recipeFileArea);

      if (type == RecipeElementType.UTENSIL)
      {
        recipeFileArea.clear();
        for (Utensils utensil : recipe.getUtensils())
        {
          recipeFileArea.addElement(utensil);
        }
      }
      else if (type == RecipeElementType.INGREDIENT)
      {
        recipeFileArea.clear();
        for (Ingredients ingredient : recipe.getIngredients())
        {
          recipeFileArea.addElement(ingredient);
        }
      }
      else if (type == RecipeElementType.STEP)
      {
        recipeFileArea.clear();
        for (Steps step : recipe.getSteps())
        {
          recipeFileArea.addElement(step);
        }
      }
    }
    else
    {
      recipeFileArea = new DefaultListModel<>();
      recipeList = new JList<>(recipeFileArea);
    }

    // creates the file area for the editor panel
    scrollPane = new JScrollPane(recipeList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 125));

    // creates the delete button
    deleteButton = new JButton(STRINGS.getString("DELETE"));
    deleteButton.addActionListener(recipeViewerController);
    deleteButton.setActionCommand(type.getDeleteCommand());

    // adds the details field
    fileViewerPanel.add(scrollPane);

    // adds the delete button
    fileViewerPanel.add(deleteButton);

    // adds the input field panel
    contentPane.add(inputFieldPanel);

    // adds the file editor container
    contentPane.add(fileViewerPanel);

    this.add(contentPane);
  }

  /**
   * Constructor for ViewerPanel (for MealEditor).
   *
   * @param name the name of the ViewerPanel
   * @param inputFieldPanel the input field panel
   * @param controller the controller for the MealEditor
   * @param type the type of the input
   */
  public ViewerPanel(String name, InputFieldPanel inputFieldPanel, MealEditorController controller)
  {
    super();

    this.setLayout(new BorderLayout());

    Container contentPane = new Container();
    contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

    // set preferred size for ViewerPanels
    this.setPreferredSize(new Dimension(800, 215));

    // creates the editor label border
    // this.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(2.0f)));
    this.setBorder(BorderFactory.createTitledBorder(""));

    // creates the file editor
    JPanel fileViewerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // creates the lists shown in the file editor
    mealFileArea = new DefaultListModel<Recipe>();
    mealList = new JList<Recipe>(mealFileArea);

    // creates the file area for the editor panel
    JScrollPane scrollPane = new JScrollPane(mealList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 150));

    // creates the delete button
    deleteButton = new JButton(STRINGS.getString("DELETE"));
    deleteButton.setActionCommand(STRINGS.getString("RECIPE_DELETE"));
    deleteButton.addActionListener(controller);

    fileViewerPanel.add(scrollPane);
    fileViewerPanel.add(deleteButton);

    this.add(inputFieldPanel, BorderLayout.NORTH);
    this.add(fileViewerPanel, BorderLayout.CENTER);
    this.add(contentPane, BorderLayout.SOUTH);
  }

  public void reset()
  {
    try
    {
      recipeFileArea.clear();
    }
    catch (NullPointerException e)
    {
      System.out.println("nothing to clear");
    }
  }


    public void addRecipeElement(RecipeElement element)
  {
    recipeFileArea.addElement(element);
  }

  public void addMealElement(Recipe e)
  {
    mealFileArea.addElement(e);
  }

  public JList<RecipeElement> getRecipeList()
  {
    return recipeList;
  }

  public JList<Recipe> getMealList()
  {
    return mealList;
  }

  public Ingredients getSelectedIngredient(String name)
  {
    Ingredients[] ingredient = new Ingredients[recipeList.getModel().getSize()];

    for (int i = 0; i < ingredient.length; i++)
    {
      ingredient[i] = (Ingredients) recipeList.getModel().getElementAt(i);
    }

    for (int i = 0; i < ingredient.length; i++)
    {
      if (ingredient[i].getName().equals(name))
      {
        return ingredient[i];
      }
    }
    return null;
  }

  public Utensils getSelectedUtensil(String name)
  {
    Utensils[] utensil = new Utensils[recipeList.getModel().getSize()];

    for (int i = 0; i < utensil.length; i++)
    {
      utensil[i] = (Utensils) recipeList.getModel().getElementAt(i);
    }

    for (int i = 0; i < utensil.length; i++)
    {
      if (utensil[i].getName().equals(name))
      {
        return utensil[i];
      }
    }
    return null;
  }

  public Steps getSelectedStep(String action, String details)
  {
    Steps[] step = new Steps[recipeList.getModel().getSize()];

    for (int i = 0; i < step.length; i++)
    {
      step[i] = (Steps) recipeList.getModel().getElementAt(i);
    }

    for (int i = 0; i < step.length; i++)
    {
      if (step[i].getDetails().equals(details) && step[i].getAction().equals(action))
      {
        return step[i];
      }
    }
    return null;
  }

  public void deleteRecipeElement()
  {
    try
    {
      int index = recipeList.getSelectedIndex();

      if (index >= 0 && index < recipeList.getModel().getSize());
      {
        recipeFileArea.remove(index);
      }
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      System.out.println("No element selected");
    }
  }

  public void deleteMealElement()
  {
    try
    {
      int index = mealList.getSelectedIndex();

      if (index >= 0 && index < mealList.getModel().getSize());
      {
        mealFileArea.remove(index);
      }
    }
    catch (ArrayIndexOutOfBoundsException e)
    {
      System.out.println("No element selected");
    }
  }
}
