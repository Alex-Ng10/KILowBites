package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.KILowBitesController;
import utilities.Foods;
import utilities.ImageUtilities;
import utilities.Units;

/**
 * KILowBites main class. Runs the program and the main window.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class KILowBites extends JFrame implements Runnable
{
  public static final Foods FOODS = new Foods();
  public static final Units UNITS = new Units();

  // CHANGE THIS LINE TO CHANGE TO COLOR SCHEME
  public static final Color COLOR = null;

  public static ResourceBundle STRINGS;

  // BEFORE RUNNING - CHECK FOR COMMENTS IN :
  // Foods.java - loadFoods()
  // FileUtilities.java - saveFoods()

  private String[] args;
  private JMenuItem openCalc;
  private JMenuItem openConvert;

  /**
   * Explicit Value Constructor.
   *
   * @param args
   *          The command-line arguments
   */
  public KILowBites(final String[] args)
  {
    // Store the command-line arguments if needed
    this.args = args;

    Locale.setDefault(getLocale());
    STRINGS = ResourceBundle.getBundle("Strings", Locale.getDefault());

    // STRINGS = ResourceBundle.getBundle("Strings", new Locale("es", "ES"));

    // Here is the command to run it in english:
    // java "-Duser.language=en" "-Duser.country=US" -jar KILowBites.jar

    // Cmd for French
    // java "-Duser.language=fr" "-Duser.country=FR" -jar KILowBites.jar

    // Cmd for Spanish
    // java "-Duser.language=es" "-Duser.country=ES" -jar KILowBites.jar
  }

  /**
   * The entry point of the application.
   *
   * @param args
   *          The command-line arguments (ignored)
   * @throws InterruptedException
   *           If the system is interrupted
   * @throws InvocationTargetException
   *           If there is a problem starting the system
   */
  public static void main(final String[] args)
      throws InterruptedException, InvocationTargetException
  {

    // Perform all of the setup activities in the event dispatch thread
    SwingUtilities.invokeAndWait(new KILowBites(args));
  }

  /**
   * The main thread of the application.
   */
  public void run()
  {
    // options: "Nimbus", "CDE/Motif"
    setLookAndFeel("Nimbus");

    // initial setup
    JFrame frame = new JFrame(STRINGS.getString("KILOWBITES_MAIN_WINDOW"));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel contentPane = (JPanel) frame.getContentPane();
    contentPane.setLayout(new BorderLayout());

    // Set the background color of the main content pane to white
    frame.setBackground(COLOR);
    contentPane.setBackground(COLOR);

    // Construct the controller
    KILowBitesController controller = new KILowBitesController(this);

    // creates the ribbon
    JMenuBar menuBar = new JMenuBar();
    frame.setJMenuBar(menuBar);

    // adds ribbon items
    JMenu menu;
    JMenuItem menuItem;

    menu = new JMenu(STRINGS.getString("FILE"));
    menuBar.add(menu);
    menuItem = new JMenuItem(STRINGS.getString("EXIT"));
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu(STRINGS.getString("EDIT"));
    menuBar.add(menu);
    menuItem = new JMenuItem(STRINGS.getString("RECIPE"));
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem(STRINGS.getString("MEAL"));
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    // TODO: ADD LANGUAGE SUPPORT FOR THESE STRINGS
    // ****************************************************
    menu = new JMenu("Search");
    menuBar.add(menu);
    menuItem = new JMenuItem("Recipes");
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem("Meals");
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu(STRINGS.getString("VIEW"));
    menuBar.add(menu);
    menuItem = new JMenuItem(STRINGS.getString("SHOPPING_LIST"));
    menu.add(menuItem);
    menuItem.addActionListener(controller);
    menuItem = new JMenuItem(STRINGS.getString("PROCESS"));
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    menu = new JMenu(STRINGS.getString("TOOLS"));
    menuBar.add(menu);
    openCalc = new JMenuItem(STRINGS.getString("CALORIE_CALCULATOR"));
    menu.add(openCalc);
    openCalc.addActionListener(controller);
    openConvert = new JMenuItem(STRINGS.getString("UNITS_CONVERTER"));
    openConvert.addActionListener(controller);
    menu.add(openConvert);

    menu = new JMenu(STRINGS.getString("CONFIGURE"));
    menuBar.add(menu);
    menuItem = new JMenuItem(STRINGS.getString("PREFERENCES"));
    menu.add(menuItem);
    menuItem.addActionListener(controller);

    // menuItem = new JMenuItem("Shortcuts");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);

    // menu = new JMenu("Help");
    // menuBar.add(menu);
    // menuItem = new JMenuItem("About");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);
    // menuItem = new JMenuItem("User Guide");
    // menu.add(menuItem);
    // menuItem.addActionListener(controller);

    // Creates the logo in the main panel
    JPanel logoPanel = new JPanel();
    JLabel logo = new JLabel(ImageUtilities.getScaledImage("KILowBites_Logo.png", 200, 80));
    logoPanel.add(logo);

    // Set the background color of the logo panel to white to match the content pane
    logoPanel.setBackground(COLOR);

    // Layout the GUI
    contentPane.add(logoPanel, BorderLayout.CENTER);

    frame.setSize(600, 200);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public JMenuItem getOpenCalc()
  {
    return openCalc;
  }

  public JMenuItem getOpenConvert()
  {
    return openConvert;
  }

  private void setLookAndFeel(final String selection)
  {
    // Setup the look and feel
    boolean done = false;
    try
    {
      // Use selection if it is available
      UIManager.LookAndFeelInfo[] lfs = UIManager.getInstalledLookAndFeels();
      for (int i = 0; i < lfs.length && !done; i++)
      {
        if (selection.equals(lfs[i].getName()))
        {
          UIManager.setLookAndFeel(lfs[i].getClassName());
          done = true;
        }
      }

      // If selection isn't available, use the system look and feel
      if (!done)
      {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        UIManager.setLookAndFeel(lookAndFeel);
      }
    }
    catch (ClassNotFoundException cnfe)
    {
      // Use the default look and feel
    }
    catch (IllegalAccessException iae)
    {
      // Use the default look and feel
    }
    catch (InstantiationException ie)
    {
      // Use the default look and feel
    }
    catch (UnsupportedLookAndFeelException ulale)
    {
      // Use the default look and feel
    }
  }
}
