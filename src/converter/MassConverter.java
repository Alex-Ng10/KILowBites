package converter;

/**
 * Converts a certain mass unit to another mass unit.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class MassConverter
{

  /**
   * Calls a certain converter based on what unit it is given.
   *
   * @param toUnit
   *          The type of the to amount.
   * @param fromUnit
   *          The type of the from amount.
   * @param fromAmount
   *          The actual from amount.
   *
   * @return The converted to amount.
   */
  static String gram = "Gram";
  static String dram = "Dram";
  static String ounce = "Ounce";
  static String pound = "Pound";
  /**
   * callerHelp.
   * @param fromUnit
   * @param toUnit
   * @param fromAmount
   * @return Double
   */
  public static Double callerHelp(final String fromUnit, final String toUnit,
      final Double fromAmount)
  {
    if (gram.equals(fromUnit))
    {
      return gramConverter(toUnit, fromAmount);
    }
    if (dram.equals(fromUnit))
    {
      return dramConverter(toUnit, fromAmount);
    }
    if (ounce.equals(fromUnit))
    {
      return ounceConverter(toUnit, fromAmount);
    }
    else
    {
      return poundConverter(toUnit, fromAmount);
    }
  }

  /**
   * Calculates gram to whatever the to Unit is.
   *
   * @param toUnit
   *          The type of the to amount.
   * @param fromAmount
   *          The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double gramConverter(final String toUnit, final Double fromAmount)
  {
    if (dram.equals(toUnit))
    {
      return (fromAmount / 28.34952) * 16;
    }
    if (ounce.equals(toUnit))
    {
      return fromAmount / 28.34952;
    }
    if (pound.equals(toUnit))
    {
      return (fromAmount / 28.34952) / 16;
    }
    else
    {
      return fromAmount;
    }
  }

  /**
   * Calculates dram to whatever the to Unit is.
   *
   * @param toUnit
   *          The type of the to amount.
   * @param fromAmount
   *          The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double dramConverter(final String toUnit, final Double fromAmount)
  {
    if (gram.equals(toUnit))
    {
      return (fromAmount / 16) * 28.34952;
    }
    if (ounce.equals(toUnit))
    {
      return fromAmount / 16;
    }
    if (pound.equals(toUnit))
    {
      return (fromAmount / 16) / 16;
    }
    else
    {
      return fromAmount;
    }
  }

  /**
   * Calculates ounce to whatever the to Unit is.
   *
   * @param toUnit
   *          The type of the to amount.
   * @param fromAmount
   *          The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double ounceConverter(final String toUnit, final Double fromAmount)
  {
    if (gram.equals(toUnit))
    {
      return (fromAmount * 28.34952);
    }
    if (dram.equals(toUnit))
    {
      return fromAmount * 16;
    }
    if (pound.equals(toUnit))
    {
      return fromAmount / 16;
    }
    else
    { // Pound
      return fromAmount;
    }
  }

  /**
   * Calculates pound to whatever the to Unit is.
   *
   * @param toUnit
   *          The type of the to amount.
   * @param fromAmount
   *          The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double poundConverter(final String toUnit, final Double fromAmount)
  {
    if (gram.equals(toUnit))
    {
      return (fromAmount * 16) * 28.34952;
    }
    if (dram.equals(toUnit))
    {
      return (fromAmount * 16) * 16;
    }
    if (ounce.equals(toUnit))
    {
      return fromAmount * 16;
    }
    else
    { // Ounce
      return fromAmount;
    }
  }

  /**
   * Calculates gram to whatever the to Unit is.
   *
   * @param toUnit
   *          The type of the to amount.
   * @param amount
   *          The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double gramToFinal(final String toUnit, final Double amount)
  {
    if (gram.equals(toUnit))
    {
      return amount;
    }
    return gramConverter(toUnit, amount);
  }

}
