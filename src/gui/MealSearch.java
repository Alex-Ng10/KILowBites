package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

import app.KILowBites;
import controller.MealSearchController;
import cooking.Recipe;
import utilities.ImageUtilities;

/**
 * RecipeSearch class. Handles the toolbar and main container of the Recipe Search.
 *
 * @author f24team3d
 * @version 12/2/24
 */
@SuppressWarnings("serial")
public class MealSearch extends JFrame
{

  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  // toolbar
  JButton[] buttons;
  Container outerPane;

  // results panel
  InputFieldPanel searchBar;
  JPanel searchResultsPane;
  JScrollPane scrollPane;
  DefaultListModel<String> recipeResults;
  JList<String> recipeResultsList;

  DefaultListModel<String> mealResults;
  JList<String> mealResultsList;
  private String close = "CLOSE";
  private String searchForIngredient = "SEARCH_FOR_INGREDIENT";
  private String noFound = "NO_MEALS_FOUND";

  
  
  /**
   * The constructor for a Recipe Search Window.
   * @param controller
   */
  public MealSearch(final MealSearchController controller)
  {
    // super(STRINGS.getString(""));
    super("Meal Search");

    outerPane = new Container();
    outerPane.setLayout(new BorderLayout());

    searchResultsPane = new JPanel();
    searchResultsPane.setLayout(new BorderLayout());

    // toolbar
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    this.setBackground(KILowBites.COLOR);

    buttons = new JButton[2];
    String[] buttonNames = {"SEARCH", close};
    String[] buttonPaths = {"search.png", "close.png"};

    for (int i = 0; i < buttons.length; i++)
    {
      buttons[i] = new JButton(
          ImageUtilities.getFormattedImage(buttonPaths[i], Color.GRAY, 25, 25));
      buttons[i].setActionCommand(buttonNames[i]);
      buttons[i].setToolTipText(buttonNames[i]);
      buttons[i].setEnabled(true);
      buttons[i].addActionListener(controller);
    }

    // adds the buttons to the toolbar
    for (JButton button : buttons)
    {
      toolbar.add(button);
    }

    // adds the search bar
    searchBar = new InputFieldPanel();
    searchBar.addJTextField(STRINGS.getString(searchForIngredient), 50);

    // adds the results area
    // recipeResults = new DefaultListModel<>();
    // recipeResultsList = new JList<>(recipeResults);
    // scrollPane = new JScrollPane(recipeResultsList);

    mealResults = new DefaultListModel<>();
    mealResultsList = new JList<>(mealResults);
    scrollPane = new JScrollPane(mealResultsList);

    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setPreferredSize(new Dimension(500, 125));


    // adds everything
    searchResultsPane.add(searchBar, BorderLayout.NORTH);
    searchResultsPane.add(scrollPane, BorderLayout.CENTER);
    outerPane.add(toolbar, BorderLayout.NORTH);
    outerPane.add(searchResultsPane, BorderLayout.CENTER);

    this.add(outerPane);

    // disables close button on start
    this.getButton(close).setEnabled(false);
  }
  
  /**
   * Updates with new recipes.
   * @param recipes
   */
  public void updateList(final ArrayList<Recipe> recipes)
  {
    recipeResults.clear();
    for (Recipe recipe : recipes)
    {
      recipeResults.addElement(recipe.getName() + ".mel");
    }
    if (recipeResults.isEmpty())
    {
      recipeResults.addElement(STRINGS.getString(noFound));
    }
  }
  
  /**
   * Updates the meal Results.
   * @param toDisplay
   */
  public void updateDisplayList(final ArrayList<String> toDisplay)
  {
    mealResults.clear();
    for (String meal : toDisplay)
    {
      mealResults.addElement(meal);
    }
    if (mealResults.isEmpty())
    {
      mealResults.addElement(STRINGS.getString(noFound));
    }
  }

  /**
   * Gets the Content Pane.
   *
   * @return the content pane
   */
  public JPanel getSearchResultsPane()
  {
    return searchResultsPane;
  }

  /**
   * Gets the search string.
   *
   * @return the search string
   */
  public String getSearchString()
  {
    return searchBar.getText(STRINGS.getString(searchForIngredient));
  }
  
  /**
   * Gets a button according to what the parameter/the name is.
   * @param name the name of a button.
   * @return the button.
   */
  public JButton getButton(final String name)
  {
    for (JButton button : buttons)
    {
      if (button.getActionCommand().equals(name))
      {
        return button;
      }
    }
    return null;
  }

  /**
   * resets all fields.
   */
  public void reset()
  {
    searchBar.resetFields();
    // recipeResults.clear();
    mealResults.clear();
  }
}
