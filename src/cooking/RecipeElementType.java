package cooking;

import java.util.ResourceBundle;

import app.KILowBites;

/**
 * Enum for RecipeElementType.
 * Contains the types of RecipeElements.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public enum RecipeElementType 
{
  INGREDIENT("INGREDIENTS", "INGREDIENT_DELETE"),
  STEP("STEP", "STEP_DELETE"),
  UTENSIL("UTENSIL", "UTENSIL_DELETE");

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private final String labelKey;
  private final String deleteCommandKey;

  RecipeElementType(final String labelKey, final String deleteCommandKey)
  {
    this.labelKey = labelKey;
    this.deleteCommandKey = deleteCommandKey;
  }
  
  /**
   * Gets the Recipe label.
   * @param capitalize 
   * @return A capitalized label if the param was true.
   */
  public String getLabel(final boolean capitalize)
  {
    String label = STRINGS.getString(labelKey);
    return capitalize ? capitalizeFirstLetter(label) : label;
  }
  
  /**
   *  Gets the the delete command.
   * @return the delete command
   */
  public String getDeleteCommand() 
  {
    return STRINGS.getString(deleteCommandKey);
  }

  private String capitalizeFirstLetter(final String str) 
  {
    if (str == null || str.isEmpty()) 
    {
      return str;
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1);
  }
}
