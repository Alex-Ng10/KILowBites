package utilities;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Units class.
 * 
 * Contains all units, both mass and volume, mentioned in product domain glossary.
 *
 * @author f24team3d
 * @version 10/30/24
 */
public class Units
{
  private static Map<String, String> units;

  private static final String MASS = "Mass";
  private static final String VOLUME = "Volume";

  private static final String GRAM = "Gram";
  private static final String DRAM = "Dram";
  private static final String OUNCE = "Ounce";
  private static final String POUND = "Pound";

  private static final String MILLILITER = "Milliliter";
  private static final String PINCH = "Pinch";
  private static final String TEASPOON = "Teaspoon";
  private static final String TABLESPOON = "Tablespoon";
  private static final String FLUID_OUNCE = "Fluid Ounce";
  private static final String CUP = "Cup";
  private static final String PINT = "Pint";
  private static final String QUART = "Quart";
  private static final String GALLON = "Gallon";

  /**
   * Default constructor.
   */
  public Units()
  {
    units = new LinkedHashMap<>();

    units.put(GRAM, MASS);
    units.put(DRAM, MASS);
    units.put(OUNCE, MASS);
    units.put(POUND, MASS);

    units.put(MILLILITER, VOLUME);
    units.put(PINCH, VOLUME);
    units.put(TEASPOON, VOLUME);
    units.put(TABLESPOON, VOLUME);
    units.put(FLUID_OUNCE, VOLUME);
    units.put(CUP, VOLUME);
    units.put(PINT, VOLUME);
    units.put(QUART, VOLUME);
    units.put(GALLON, VOLUME);
  }

  /**
   * Return array of all units.
   * 
   * @return all units
   */
  public String[] getAllUnits()
  {
    Set<String> keys = units.keySet();
    String[] allUnits = new String[keys.size() + 1];

    allUnits[0] = "";

    int index = 1;
    for (String key : keys)
    {
      allUnits[index++] = key;
    }

    return allUnits;
  }

  /**
   * Return array of all units + individual.
   * 
   * @return all units + individual
   */
  public String[] getAllUnitsPlusIndividual()
  {
    Set<String> keys = units.keySet();
    String[] allUnits = new String[keys.size() + 2];

    allUnits[0] = "";
    allUnits[1] = "Individual";

    int index = 2;
    for (String key : keys)
    {
      allUnits[index++] = key;
    }

    return allUnits;
  }

  /**
   * Return array of all units without first being empty.
   * 
   * @return all units
   */
  public String[] getAllUnitsNoPadding()
  {
    Set<String> keys = units.keySet();
    String[] allUnits = new String[keys.size()];

    int index = 0;
    for (String key : keys)
    {
      allUnits[index++] = key;
    }

    return allUnits;
  }

  /**
   * Return array of mass units.
   * 
   * @return array of mass units
   */
  public static String[] getMassUnits()
  {
    List<String> massUnits = new ArrayList<>();

    for (Map.Entry<String, String> entry : units.entrySet())
    {
      if (MASS.equals(entry.getValue()))
      {
        massUnits.add(entry.getKey());
      }
    }

    return massUnits.toArray(new String[0]);
  }

  /**
   * Return map of units.
   * 
   * @return map of units
   */
  public Map<String, String> getUnits()
  {
    return units;
  }

  /**
   * Return array of volume units.
   * 
   * @return array of volume units
   */
  public static String[] getVolumeUnits()
  {
    List<String> volumeUnits = new ArrayList<>();

    for (Map.Entry<String, String> entry : units.entrySet())
    {
      if (VOLUME.equals(entry.getValue()))
      {
        volumeUnits.add(entry.getKey());
      }
    }
    return volumeUnits.toArray(new String[0]);
  }

  /**
   * Return whether a unit is for mass or volume.
   * 
   * @param unit
   *          Unit of measurement
   * @return mass or volume
   */
  public String unitMeasure(final String unit)
  {
    return units.get(unit);
  }

}
