package gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import app.KILowBites;
import controller.ProcessViewerController;
import cooking.Meal;
import cooking.Recipe;
import utilities.ImageUtilities;
import utilities.InputUtilities;

/**
 * RecipeViewer class. Handles the toolbar and main container of the Recipe Viewer.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public class ProcessViewer extends JFrame
{
  static final String KPV = "KILowBites Process Viewer: ";
  static final Locale LOCALE = Locale.getDefault();
  private static final long serialVersionUID = 1L;
  final String print = "Print";
  
  Container processOuterPane;
  JPanel processContentPane;
  ProcessViewerContent processContent;
  ProcessViewerController processViewerController;
  private JButton printButton;
  
  
  
  /**
   * Constructor for RecipeViewer.
   * 
   * @param recipe
   * @param processViewerController
   */
  public ProcessViewer(final Recipe recipe, final ProcessViewerController processViewerController)
  {
    super(KPV + InputUtilities.separateByCapital(recipe.getName()));
    this.processViewerController = processViewerController;

    System.out.println("a");

    setup();

    // creates the main content panel
    processContent = new ProcessViewerContent(recipe, processViewerController);
    processContentPane = processContent;

    processOuterPane.add(processContentPane, BorderLayout.CENTER); // adds the content pane to
                                                                   // outerPane
    this.add(processOuterPane); // adds outerPane to the frame
  }

  /**
   * Constructor for MealViewer.
   * 
   * @param meal
   * @param processViewerController
   */
  public ProcessViewer(final Meal meal, final ProcessViewerController processViewerController)
  {
    super(KPV + InputUtilities.separateByCapital(meal.getName()));
    this.processViewerController = processViewerController;

    setup();

    // creates the main content panel
    processContent = new ProcessViewerContent(meal, processViewerController);
    processContentPane = processContent;

    processOuterPane.add(processContentPane, BorderLayout.CENTER);

    this.add(processOuterPane); // adds outerPane to the frame
  }

  /**
   * Gets the Content Pane.
   *
   * @return the content pane
   */
  public ProcessViewerContent getContent()
  {
    return processContent;
  }

  /**
   * Set up visual components of window.
   */
  private void setup()
  {
    // creates the outer border layout (contains menubar and content)
    processOuterPane = new Container();

    processOuterPane.setLayout(new BorderLayout());

    processOuterPane.setBackground(KILowBites.COLOR);

    // creates the tool bar
    JToolBar toolbar = new JToolBar();
    toolbar.setFloatable(false);
    toolbar.setRollover(true);

    // create toolbar buttons
    printButton = new JButton(ImageUtilities.getFormattedImage("print.png", Color.GRAY, 25, 25));
    printButton.setActionCommand(print);
    printButton.setToolTipText(print);
    printButton.setEnabled(true);
    printButton.addActionListener(processViewerController);

    toolbar.add(printButton);
    processOuterPane.add(toolbar, BorderLayout.NORTH); // adds the toolbar to outerPane north

    this.setSize(new Dimension(800, 550));
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    this.setLocationRelativeTo(null);
  }

}
