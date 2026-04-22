package utilities;

/**
 * UnitType class.
 *
 * @author f24team3d
 * @version 11/12/24
 */
public class UnitType
{
  private static boolean imperialSelected = true; // Imperial is automatically used unless user
  // specifies to use metric.
  private static boolean metricSelected = false;

  private static final String[] imperialUnits = {"Pinch", "Teaspoon", "Tablespoon", "Fluid Ounce", 
      "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static final String[] imperialUnitsSpace = {"", "Pinch", "Teaspoon", "Tablespoon",
      "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  private static final String[] metricUnits = {"Gram", "Milliliter"};
  private static final String[] metricUnitsSpace = {"", "Gram", "Milliliter"};
  private static final String[] metricUnitsIndividual = {"", "Individual", "Gram", "Milliliter"};
  private static final String[] imperialUnitsIndividual = {"", "Individual", "Pinch", "Teaspoon",
      "Tablespoon", "Fluid Ounce", "Cup", "Pint", "Quart", "Gallon", "Dram", "Ounce", "Pound"};
  
  /**
   * Gets the Imperial variable.
   *
   * @return Returns true or false based on the variable.
   */
  public static boolean getImperialSelected() 
  {
    return imperialSelected;
  }
  
  /**
   * Gets the Metric variable.
   *
   * @return Returns true or false based on the variable.
   */
  public static boolean getMetricSelected()
  {
    return metricSelected;
  }
  
  /**
   * Sets the Imperial variable to true so the GUI only shows Metric units.
   *
   */
  public static void setImperialSelected() 
  {
    imperialSelected = true;
    metricSelected = false;
  }
  
  /**
   * Sets the Metric variable to true so the GUI only shows Metric units.
   *
   */
  public static void setMetricSelected()
  {
    imperialSelected = false;
    metricSelected = true;
  }
  
  /**
   * Gets all Metric Units.
   * 
   * @return Returns an array of Metric Units.
   */
  public static String[] getMetricUnits()
  {
    return metricUnits;
  }
  
  /**
   * Gets all Imperial Units.
   * 
   * @return Returns an array of Imperial Units.
   */
  public static String[] getImperialUnits()
  {
    return imperialUnits;
  }
  
  /**
   * Gets all Imperial Units but with an additional unit called individual. (Used in recipe editor) 
   * Also includes a null space for JComboBoxes.
   * 
   * @return Returns an array of Imperial Units with the null space and individual unit.
   */
  public static String[] getImperialUnitsIndividual()
  {
    return imperialUnitsIndividual;
  }
  
  /**
   * Gets all Metric Units but with an additional unit called individual. (Used in recipe editor) 
   * Also includes a null space for JComboBoxes.
   * 
   * @return Returns an array of Metric Units with the null space and individual unit.
   */
  public static String[] getMetricUnitsIndividual()
  {
    return metricUnitsIndividual;
  }
  
  /**
   * Gets all Metric Units but with a null space for JComboBoxes.
   * 
   * @return Returns an Metric of imperial Units with the null space.
   */
  public static String[] getMetricUnitsSpace()
  {
    return metricUnitsSpace;
  }
  
  /**
   * Gets all Imperial Units but with a null space for JComboBoxes.
   * 
   * @return Returns an array of imperial Units with the null space.
   */
  public static String[] getImperialUnitsSpace()
  {
    return imperialUnitsSpace;
  }
}
