package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.KILowBites;
import controller.UnitConverterController;
import controller.UnitTypeController;
import utilities.UnitType;

/**
 * Main window for KiLowBites unit Type.
 * 
 * @author f24team3d
 * @version 10/29/24
 */
public class UnitTypeWindow extends JFrame
{
  public static JButton metricButton;
  public static JButton imperialButton;
  public static JLabel titleLabel;
  static final Locale         LOCALE  = Locale.getDefault();
  private static final long serialVersionUID = 1L;
  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private UnitTypeController controller;
  
//  private static final String UNIT_TYPE_SELECTOR = "Unit Type Selector";
//  private static final String METRIC = "Metric";
//  private static final String IMPERIAL = "Imperial";
//  private static final String CHANGE_UNIT_TYPE_TO_METRIC = "Change Unit types to Metric";
//  private static final String CHANGE_UNIT_TYPE_TO_IMPERIAL = "Change Unit types to Imperial";
//  private static final String CURRENT_UNIT_TYPE_IMPERIAL = "Current Unit Type: Imperial";
//  private static final String CURRENT_UNIT_TYPE_METRIC = "Current Unit Type: Metric";
  
  
  /**
   * Creates the Unit Type Window.
   */
  public UnitTypeWindow() 
  {
    setTitle(STRINGS.getString("UNIT_TYPE_SELECTOR"));
    controller = new UnitTypeController();

    this.setBackground(KILowBites.COLOR);
    
    metricButton = new JButton(STRINGS.getString("METRIC"));
    metricButton.setToolTipText(STRINGS.getString("CHANGE_UNIT_TYPE_TO_METRIC"));
    metricButton.setActionCommand(STRINGS.getString("METRIC" ));
    metricButton.addActionListener(controller);
    
    imperialButton = new JButton(STRINGS.getString("IMPERIAL"));
    imperialButton.setToolTipText(STRINGS.getString("CHANGE_UNIT_TYPE_TO_IMPERIAL"));
    imperialButton.setActionCommand(STRINGS.getString("IMPERIAL"));
    imperialButton.addActionListener(controller);
    if (UnitType.getImperialSelected()) 
    {
      imperialButton.setEnabled(false);
      metricButton.setEnabled(true);
    } else 
    {
      imperialButton.setEnabled(true);
      metricButton.setEnabled(false);
    }
    
    JPanel inputs = new JPanel(new GridLayout(1, 2, 0, 0)); 
    inputs.add(metricButton);
    inputs.add(imperialButton);
    if (UnitType.getImperialSelected()) 
    {
      titleLabel = new JLabel(STRINGS.getString("CURRENT_UNIT_TYPE_IMPERIAL"), JLabel.CENTER);
    } else 
    {
      titleLabel = new JLabel(STRINGS.getString("CURRENT_UNIT_TYPE_METRIC"), JLabel.CENTER);
    }
    
    setLayout(new BorderLayout());
    add(titleLabel, BorderLayout.NORTH);
    add(inputs, BorderLayout.CENTER);
    
    setSize(300, 100);
    setLocationRelativeTo(null); 
  }
}
