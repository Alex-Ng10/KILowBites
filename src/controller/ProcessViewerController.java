package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import app.KILowBites;
import cooking.Meal;
import cooking.Recipe;
import gui.DelegatingPrintable;
import gui.ProcessViewer;
import utilities.FileUtilities;

/**
 * RecipeViewer controller class. Handles the actions of the RecipeViewer GUI elements
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class ProcessViewerController implements ActionListener
{
  static final Locale LOCALE = Locale.getDefault();
  private static String PRINT = "Print";

  @SuppressWarnings("unused")
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  private ProcessViewer viewer;
  @SuppressWarnings("unused")
  private Recipe recipe;
  @SuppressWarnings("unused")
  private Meal meal;

  /**
   * Constructor for controller.
   */
  public ProcessViewerController()
  {
    createRecipeViewer();
  }

  /**
   * Creates a new RecipeViewer window.
   */
  private void createRecipeViewer()
  {
    List<Object> data = FileUtilities.open();

    if (data == null)
    {
      return;
    }
    else
    {
      String openedFile = (String) data.get(0);
      @SuppressWarnings("unused")
      String extension = openedFile.substring(openedFile.length() - 4, openedFile.length());

      if (data.get(1) instanceof Recipe)
      {

        viewer = new ProcessViewer((Recipe) data.get(1), this);
      }
      else
      {
        viewer = new ProcessViewer((Meal) data.get(1), this);
      }
      viewer.setVisible(true);
    }
  }


  /**
   * Method for when action is performed.
   * @param e
   */
  public void actionPerformed(final ActionEvent e)
  {
    String command = e.getActionCommand();

    // commands for Toolbar
    if (command.equals(PRINT))
    {
      DelegatingPrintable dp = new DelegatingPrintable(viewer.getContent()
          .getUtensilPanel().getRecipeList(),
    		  viewer.getContent().getStepPanel().getRecipeList());
      PrinterController.print(dp, viewer);
    }
  }
}
