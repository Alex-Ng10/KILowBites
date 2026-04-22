package cooking;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.swing.JLabel;

import app.KILowBites;

/**
 * Ingredients Class, a RecipeElement.
 *
 * @author f24team3d
 * @version 10/29/24
 */
public class Ingredients implements RecipeElement, StepSource, Serializable
{
  private static final long serialVersionUID = 9078168612703222174L;

  private static final ResourceBundle STRINGS = KILowBites.STRINGS;
  private double amount = 0.0;
  private String units = "";
  private String details = "";
  private String name = "";
  private RecipeElementType type = RecipeElementType.INGREDIENT;
  private final String space = " ";


  /**
   * Default constructor.
   */
  public Ingredients()
  {
    this.amount = 0.0;
    this.units = "";
    this.details = "";
    this.name = "";
  }

  /**
   * Constructor with parameters.
   *
   * @param amount
   * @param unit
   * @param details
   * @param name
   */
  public Ingredients(final double amount, final String unit, final String details,
      final String name)
  {
    this.amount = amount;
    this.units = unit;
    this.details = details;
    this.name = name;
  }

  /**
   * Get the amount of the ingredient.
   *
   * @return the amount of the ingredient
   */
  public double getAmount()
  {
    return amount;
  }
  
  /** Gets the type of the ingredient.
   *
   * @return the type of the ingredient
   */
  public RecipeElementType getType()
  {
    return type;
  }

  /**
   * Set the amount of the ingredient.
   *
   * @param amount
   */
  public void setAmount(final double amount)
  {
    this.amount = amount;
  }

  /**
   * Get the unit of the ingredient.
   *
   * @return the unit of the ingredient
   */
  public String getUnit()
  {
    return units;
  }

  /**
   * Set the unit of the ingredient.
   *
   * @param unit
   */
  public void setUnit(final String unit)
  {
    this.units = unit;
  }

  /**
   * Get the details of the ingredient.
   *
   * @return the details of the ingredient
   */
  public String getDetails()
  {
    return " " + details;
  }

  /**
   * Set the details of the ingredient.
   *
   * @param details
   */
  public void setDetails(final String details)
  {
    this.details = details;
  }

  /**
   * Get the name of the ingredient.
   *
   * @return the name of the ingredient
   */
  public String getName()
  {
    return name;
  }

  /**
   * Set the name of the ingredient.
   *
   * @param name
   */
  public void setName(final String name)
  {
    this.name = name;
  }

  @Override
  public String toString()
  {
    return amount + space + units + space + STRINGS.getString("OF") + space + details 
        + space + name;
  }

  /**
   * Creates new Jlabel.
   * 
   * @return Jlabel
   */
  public JLabel add()
  {
    return new JLabel();
  }
}
