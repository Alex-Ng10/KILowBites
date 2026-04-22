package cooking;

/**
 * Interface for steps.
 */
public interface StepSource
{
  /**
   * Get the name and details of the RecipeElement.
   *
   * @return the name and details of the RecipeElement
   */
  public String toString();

  /**
   * Get the name of the source.
   *
   * @return the name of the source
   */
  public String getName();

  /**
   * Get the details of the source.
   *
   * @return the details of the source
   */
  public String getDetails();
  
  /**
   * Gets the type of the source.
   * @return The type of the source.
   */
  public RecipeElementType getType();
}
