package utilities;

/**
 * InputUtilites class.
 *
 * @author f24team3d
 * @version 11/12/24
 */
public class InputUtilities
{
  /**
   * Checks if string is an integer.
   * 
   * @param str
   *          String being checked
   * @return whether string is an integer
   */
  public static boolean isInt(final String str)
  {
    return str.matches("[0-9]+");
  }

  /**
   * Checks if string is a double.
   * 
   * @param str
   *          String being checked
   * @return whether string is a double
   */
  public static boolean isDouble(final String str)
  {
    return str.matches("[0-9]+(\\.[0-9]+)?");
  }

  /**
   * Checks if string is a positive integer.
   * 
   * @param str
   *          String being checked
   * @return whether string is positive integer
   */
  public static boolean isPositiveInt(final String str)
  {
    if (isInt(str))
    {
      return Integer.parseInt(str) > 0;
    }
    else
    {
      return false;
    }
  }

  /**
   * Checks if string is a positive double.
   * 
   * @param str
   *          String being checked
   * @return whether string is positive double
   */
  public static boolean isPositiveDouble(final String str)
  {
    if (isDouble(str))
    {
      return Double.parseDouble(str) > 0;
    }
    else
    {
      return false;
    }
  }

  /**
   * Checks if string is alphanumeric.
   * 
   * @param str
   *          String being checked
   * @return whether string is alphanumeric
   */
  public static boolean isAlphaNumeric(final String str)
  {
    return str.matches("[a-zA-Z0-9 ]+");
  }

  /**
   * Checks if string is alphabetical.
   * 
   * @param str
   *          String being checked
   * @return whether string is alphabetical
   */
  public static boolean isAlphabetical(final String str)
  {
    return str.matches("[a-zA-Z ]+");
  }

  /**
   * Separate string by capital letters.
   * 
   * @param input
   *          String
   * @return string separated by capital letters
   */
  public static String separateByCapital(final String input)
  {
    String s = "";

    // Remove the .rcp/.mel extension if present
    if (input.endsWith(".rcp") || input.endsWith(".mel"))
    {
      s = input.substring(0, input.length() - 4);
    }

    // Use a regular expression to add a space before each capital letter, except the first one
    String separated = s.replaceAll("(?<!^)([A-Z])", " $1");

    return separated;
  }
}
