package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.RecipeEditorController;
import cooking.Recipe;
import utilities.DocumentState;
import utilities.ImageUtilities;

/**
 * RecipeEditor class. Handles the toolbar and main container of the Recipe Editor.
 *
 * @author f24team3d
 * @version 10/31/24
 */
@SuppressWarnings("serial")
public class RecipeEditor extends JFrame
{
//  private static final String KILOWBITES_RECIPE_EDITOR = "KILowBites Recipe Editor";
  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  Container outerPane;
  JPanel contentPane;
  RecipeEditorContent content;

  JButton[] buttons;

  /**
   * Constructor for RecipeEditorContent.
   *
   * @param controller
   *          the controller for the RecipeEditor
   * @param isNew Checks if it a new Recipe.
   * @param recipe The recipe.
   */
  public RecipeEditor(final Recipe recipe, final RecipeEditorController controller, 
      final boolean isNew)
  {
    super(STRINGS.getString("KILOWBITES_RECIPE_EDITOR"));

    // creates the outer border layout (contains menubar and content)
    outerPane = new Container();

    outerPane.setLayout(new BorderLayout());

    // creates the tool bar
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    this.setBackground(KILowBites.COLOR);

    // create toolbar buttons
    buttons = new JButton[5];
    String[] buttonNames = {STRINGS.getString("NEW"), STRINGS.getString("OPEN"), 
        STRINGS.getString("SAVE"), STRINGS.getString("SAVE_AS"), STRINGS.getString("CLOSE")};
    String[] buttonPaths = {"new.png", "open.png", "save.png", "save_as.png",
        "close.png"};

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

    // creates the main content panel
    content = new RecipeEditorContent(recipe, controller, isNew);
    contentPane = content;

    this.setSize(new Dimension(800, 800));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);

    outerPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north
    outerPane.add(contentPane, BorderLayout.CENTER); // adds the content pane to outerPane center

    this.add(outerPane); // adds outerPane to the frame
  }

  /**
   * Gets the Content Pane.
   *
   * @return the content pane
   */
  public RecipeEditorContent getContent()
  {
    return content;
  }

  /**
   * Resets the Recipe Editor.
   */
  public void resetRecipeEditor()
  {
    content.reset();
  }

  /**
   * Updates the toolbar according to the current document state.
   * 
   * @param state the current state of the document.
   */
  public void updateToolBar(final DocumentState state)
  {
    if (state == DocumentState.NULL)
    {
      // enabled
      buttons[0].setEnabled(true); // new
      buttons[1].setEnabled(true); // open

      // disabled
      buttons[2].setEnabled(false); // save
      buttons[3].setEnabled(false); // save as
      buttons[4].setEnabled(false); // close
    }
    else if (state == DocumentState.CHANGED)
    {
      // enabled
      buttons[2].setEnabled(true); // save
      buttons[3].setEnabled(true); // save as

      // disabled
      buttons[0].setEnabled(false); // new
      buttons[1].setEnabled(false); // open
      buttons[4].setEnabled(false); // close
    }
    else if (state == DocumentState.UNCHANGED)
    {
      // enabled
      buttons[0].setEnabled(true); // new
      buttons[1].setEnabled(true); // open
      buttons[3].setEnabled(true); // save as
      buttons[4].setEnabled(true); // close

      // disabled
      buttons[2].setEnabled(false); // save
    }
  }
}
