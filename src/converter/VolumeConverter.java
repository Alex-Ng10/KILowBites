package converter;

/**
 * Converts a certain volume unit to another volume unit.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class VolumeConverter
{
  /**
   * Calls a certain converter based on what unit it is given.
   *
   * @param toUnit The type of the to amount.
   * @param fromUnit The type of the from amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double callerHelp(final String fromUnit, final String toUnit, 
      final Double fromAmount) 
  {
    if ("Milliliter".equals(fromUnit))
    {
      return milliliterConverter(toUnit, fromAmount);
    }
    if ("Teaspoon".equals(fromUnit))
    {
      return teaspoonConverter(toUnit, fromAmount);
    }
    if ("Tablespoon".equals(fromUnit))
    {
      return tablespoonConverter(toUnit, fromAmount);
    }
    if ("Fluid Ounce".equals(fromUnit))
    {
      return fluidOunceConverter(toUnit, fromAmount);
    }
    if ("Cup".equals(fromUnit))
    {
      return cupConverter(toUnit, fromAmount);
    }
    if ("Pint".equals(fromUnit))
    {
      return pintConverter(toUnit, fromAmount);
    }
    if ("Quart".equals(fromUnit))
    {
      return quartConverter(toUnit, fromAmount);
    }
    if ("Gallon".equals(fromUnit))
    {
      return gallonConverter(toUnit, fromAmount);
    }
    return pinchConverter(toUnit, fromAmount);
  }
  
  
  /**
   * Calculates any amount of pinches to whatever the to unit is.
   *
   * @param toUnit The type of the to amount.
   * @param pinches The amount of pinches.
   *
   * @return The converted to amount.
   */
  private static Double convertHelp(Double pinches, String toUnit)
  {
    if ("Milliliter".equals(toUnit))
    {
      return (pinches / 48) * 14.7867648;
    }
    if ("Teaspoon".equals(toUnit))
    {
      return pinches / 16;
    }
    if ("Tablespoon".equals(toUnit))
    {
      return pinches / 48;
    }
    if ("Fluid Ounce".equals(toUnit))
    {
      return pinches / 96;
    }
    if ("Cup".equals(toUnit))
    {
      return pinches / 768;
    }
    if ("Pint".equals(toUnit))
    {
      return pinches / 1536;
    }
    if ("Quart".equals(toUnit))
    {
      return pinches / 3072;
    }
    if ("Gallon".equals(toUnit))
    {
      return pinches / 12288;
    }
    return pinches;
  }
  
  /**
   * Calculates teaspoon to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double teaspoonConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 16, toUnit);
  }
  
  /**
   * Calculates tablespoon to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double tablespoonConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 48, toUnit);
  }

  /**
   * Calculates fluid ounce to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double fluidOunceConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 96, toUnit);
  }

  /**
   * Calculates cup to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double cupConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 768, toUnit);
  }

  /**
   * Calculates pint to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double pintConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 1536, toUnit);
  }

  /**
   * Calculates quart to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double quartConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 3072, toUnit);
  }

  /**
   * Calculates gallon to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double gallonConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount * 12288, toUnit);
  }

  /**
   * Calculates milliliter to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double milliliterConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp((fromAmount * 48) / 14.7867648, toUnit);
  }

  /**
   * Calculates pinch to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double pinchConverter(final String toUnit, final Double fromAmount)
  {
    return convertHelp(fromAmount, toUnit);
  }
  
  /**
   * Calculates milliliter to whatever the to Unit is.
   *
   * @param toUnit The type of the to amount.
   * @param amount The amount of milliliters.
   *
   * @return The converted to amount.
   */
  public static Double milliliterToFinal(final String toUnit, final Double amount)
  {
    if ("Milliliter".equals(toUnit))
    {
      return amount;
    }
    return milliliterConverter(toUnit, amount);
  }
}
