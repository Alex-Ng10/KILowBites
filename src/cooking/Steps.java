package cooking;

import java.io.Serializable;
import java.util.ResourceBundle;

import app.KILowBites;

/**
 * Ingredients Class, a RecipeElement.
 *
 * @author f24team3d
 * @version 10/29/24
 */
public class Steps implements RecipeElement, Serializable
{
  private static final long serialVersionUID = 2378201077635363735L;

  private String action;
  private StepSource source;
  private Utensils destination;
  private String details;
  private RecipeElementType type = RecipeElementType.STEP;

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;

  // private static final String THE_CONTENTS_OF_THE = " the contents of the ";
  // private static final String IN_THE = " in the ";
  // private static final String THE = " the ";
  /**
   * Default constructor.
   */
  public Steps()
  {
    this.action = "";
    this.source = null;
    this.destination = null;
    this.details = "";
  }

  /**
   * Constructor with parameters.
   *
   * @param action
   * @param source
   * @param destination
   * @param details
   */
  public Steps(final String action, final StepSource source, 
      final Utensils destination, final String details)
  {
    this.action = action;
    this.source = source;
    this.destination = destination;
    this.details = details;
  }

  /**
   * Get the action of the step.
   *
   * @return the action of the step
   */
  public String getAction()
  {
    return action;
  }

  /**
   * Set the action of the step.
   *
   * @param action
   */
  public void setAction(final String action)
  {
    this.action = action;
  }

  /**
   * Get the source of the step.
   *
   * @return the source of the step
   */
  public StepSource getSource()
  {
    return source;
  }

  /**
   * Set the source of the step.
   *
   * @param source
   */
  public void setSource(final StepSource source)
  {
    this.source = source;
  }

  /**
   * Get the destination of the step.
   *
   * @return the destination of the step
   */
  public Utensils getDestination()
  {
    return destination;
  }

  /**
   * Set the destination of the step.
   *
   * @param destination
   */
  public void setDestination(final Utensils destination)
  {
    this.destination = destination;
  }

  /**
   * Get the details of the step.
   *
   * @return the details of the step
   */
  public String getDetails()
  {
    return details;
  }

  /**
   * Set the details of the step.
   *
   * @param details
   */
  public void setDetails(final String details)
  {
    this.details = details;
  }

  @Override
  public String toString()
  {
    if (source.getType() == RecipeElementType.UTENSIL
        && destination.getType() == RecipeElementType.UTENSIL
        && source.getName().equals(destination.getName()))
    {
      return action + " " + STRINGS.getString("THE_CONTENTS_OF_THE") + " " + source.getName() + " " + details;
    }
    else if (source.getType() == RecipeElementType.UTENSIL)
    {
      return action + " " + STRINGS.getString("THE_CONTENTS_OF_THE") + " " + source.getName() + " "
          + STRINGS.getString("IN_THE") + " " + destination.getName() + " " + details;
    }
    else if (StepSource.class.isInstance(Utensils.class))
    {
      return action + " " + STRINGS.getString("THE_CONTENTS_OF_THE") + " " + source.getName()
          + STRINGS.getString("IN_THE") + " " + destination.getName() + " " + details;
    }
    return action + " " + STRINGS.getString("THE") + " " + source.getName() + " "
        + STRINGS.getString("IN_THE") + " " + destination.getName() + " " + details;
  }
  
  /**
   * Gets Type.
   * @return The type of Element.
   */
  public RecipeElementType getType()
  {
    return type;
  }

  @Override
  public String getName()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
