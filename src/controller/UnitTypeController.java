package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import app.KILowBites;
import gui.UnitTypeWindow;
import utilities.UnitType;

/**
 * Unit Type controller class. Handles the actions of the Unit Type GUI elements.
 *
 * @author f24team3d
 * @version 12/4/24
 */
public class UnitTypeController implements ActionListener
{
//  private static final String IMPERIAL = "Imperial";
  static final Locale         LOCALE  = Locale.getDefault();
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  
  /**
   * Changes the button that was selected to disabled and the other enabled.
   * @param e
   */
  public void actionPerformed(final ActionEvent e)
  {
    String ac = e.getActionCommand();

    if (ac.equals(STRINGS.getString("IMPERIAL")))
    {
      UnitTypeWindow.imperialButton.setEnabled(false);
      UnitTypeWindow.metricButton.setEnabled(true);
      UnitTypeWindow.titleLabel.setText("Current Unit Type: Imperial");
      UnitType.setImperialSelected();
    } else 
    {
      UnitTypeWindow.imperialButton.setEnabled(true);
      UnitTypeWindow.metricButton.setEnabled(false);
      UnitTypeWindow.titleLabel.setText("Current Unit Type: Metric");
      UnitType.setMetricSelected();
    }
  }
}
