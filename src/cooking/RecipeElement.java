package cooking;

/**
 * An interface for RecipeElements.
 * Allows grouping of Ingredients, Utensils, and Steps.
 *
 *  @author f24team3d
 *  @version 10/31/24
 */
public interface RecipeElement
{
  /**
   * Get the name and details of the RecipeElement.
   *
   * @return the name and details of the RecipeElement
   */
  public String toString();
  
  /**
   * Get the name of the RecipeElement.
   *
   * @return the name of the RecipeElement
   */
  public String getName();
}
