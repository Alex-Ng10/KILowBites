package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import app.KILowBites;

/**
 * 
 */

public class CalorieOutputWindow extends JFrame
{
  // private static final String OMITTED_INGREDIENTS = "Omitted ingredients: ";

  static final Locale LOCALE = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  /**
   * Explicit value constructor.
   * 
   * @param name
   *          Name of recipe/meal
   * @param calories
   *          Number of calories in recipe/meal
   * @param omitted
   */
  public CalorieOutputWindow(final String name, final String omitted, final double calories)
  {
    super(name);

    this.setBackground(KILowBites.COLOR);

    JLabel rmName = new JLabel(name);
    JLabel rmCalories = new JLabel(
        String.format(LOCALE, "%.2f " + STRINGS.getString("CALORIES_LOWER_CASE"), calories));
    JLabel omittedLabel = null;

    if (omitted != null && !omitted.isEmpty())
    {
      omittedLabel = new JLabel(STRINGS.getString("OMITTED_INGREDIENTS") + " " + omitted);
    }

    // Set layout to GridBagLayout
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0; // Column 0
    gbc.gridy = 0; // Row 0
    gbc.anchor = GridBagConstraints.CENTER; // Center alignment
    gbc.insets = new Insets(10, 0, 10, 0); // Add padding around the labels

    // Add name label
    add(rmName, gbc);

    if (omittedLabel != null)
    {
      gbc.gridy = 1; // Move to row 1
      add(omittedLabel, gbc);
    }

    // Move to the next row for the calories label
    gbc.gridy = 2;
    add(rmCalories, gbc);

    setLocationRelativeTo(null);
    setSize(500, 200);
    setResizable(false);
  }
}
