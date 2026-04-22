package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cooking.*;

public class StepsTest
{

  private Steps step;
  private Utensils destination;
  private StepSource source;

  private class MockStepSource implements StepSource
  {
    private String name;
    private String details;
    private RecipeElementType type;

    public MockStepSource(String name, String details, RecipeElementType type)
    {
      this.name = name;
      this.details = details;
      this.type = type;
    }

    @Override
    public String getName()
    {
      return name;
    }

    @Override
    public String getDetails()
    {
      return details;
    }

    @Override
    public RecipeElementType getType()
    {
      return type;
    }

    @Override
    public String toString()
    {
      return name + " - " + details;
    }
  }

  @BeforeEach
  public void setUp()
  {
    // Setting up initial objects for testing
    source = new MockStepSource("Bowl", "Large", RecipeElementType.UTENSIL);
    destination = new Utensils("Pan", "Non-stick");
    step = new Steps("Mix", source, destination, "gently");
  }

  // @Test
  // public void testDefaultConstructor()
  // {
  // Steps defaultStep = new Steps();
  // assertNull(defaultStep.getSource());
  // assertNull(defaultStep.getDestination());
  // assertEquals("", defaultStep.getAction());
  // assertEquals("", defaultStep.getDetails());
  // }

  @Test
  public void testConstructorWithParameters()
  {
    assertEquals("Mix", step.getAction());
    assertEquals(source, step.getSource());
    assertEquals(destination, step.getDestination());
    assertEquals("gently", step.getDetails());
  }

  @Test
  public void testSetAction()
  {
    step.setAction("Stir");
    assertEquals("Stir", step.getAction());
  }

  @Test
  public void testSetSource()
  {
    StepSource newSource = new MockStepSource("Spoon", "Wooden", RecipeElementType.UTENSIL);
    step.setSource(newSource);
    assertEquals("Spoon", step.getSource().getName());
  }

  @Test
  public void testSetDestination()
  {
    Utensils newDestination = new Utensils("Bowl", "Large");
    step.setDestination(newDestination);
    assertEquals("Bowl", step.getDestination().getName());
  }

  @Test
  public void testSetDetails()
  {
    step.setDetails("quickly");
    assertEquals("quickly", step.getDetails());
  }

  @Test
  public void testGetType()
  {
    assertEquals(RecipeElementType.STEP, step.getType());
  }

  @Test
  public void testToStringWithSameSourceAndDestination()
  {
    // Test where the source and destination are the same
    Utensils sameUtensil = new Utensils("Pan", "Non-stick");
    step.setDestination(sameUtensil);
    step.setSource(sameUtensil);
    assertEquals("Mix the contents of the Pan gently", step.toString());
  }

  @Test
  public void testToStringWithDifferentSourceAndDestination()
  {
    // Test where source and destination are different
    assertEquals("Mix the contents of the Bowl in the Pan gently", step.toString());
  }

  @Test
  public void testToStringWithDifferentRecipeElementType()
  {
    // Test where the source is not an utensil
    StepSource ingredientSource = new MockStepSource("Flour", "500g", RecipeElementType.INGREDIENT);
    step.setSource(ingredientSource);
    assertEquals("Mix the Flour in the Pan gently", step.toString());
  }

  @Test
  public void testGetName()
  {
    // This is a temp fix, so it should return "oops"
    assertEquals("oops", step.getName());
  }

  @Test
  public void testDefaultConstructor()
  {
    Steps steps = new Steps();
    assertEquals("", steps.getAction());
    assertNull(steps.getSource());
    assertNull(steps.getDestination());
    assertEquals("", steps.getDetails());
  }

  @Test
  public void testParameterizedConstructor()
  {
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    Utensils uten = new Utensils("Skillet", "large");
    Steps steps = new Steps("simmer", ing, uten, "for 2 minutes");
    assertEquals("simmer", steps.getAction());
    assertEquals(ing, steps.getSource());
    assertEquals(uten, steps.getDestination());
    assertEquals("for 2 minutes", steps.getDetails());
  }

  @Test
  public void testSettersAndGetters()
  {
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    Utensils uten = new Utensils("Skillet", "large");
    Steps steps = new Steps();
    steps.setAction("simmer");
    steps.setSource(ing);
    steps.setDestination(uten);
    steps.setDetails("for 2 minutes");
    assertEquals("simmer", steps.getAction());
    assertEquals(ing, steps.getSource());
    assertEquals(uten, steps.getDestination());
    assertEquals("for 2 minutes", steps.getDetails());
  }

  @Test
  public void testToString()
  {
    Ingredients ing = new Ingredients(3, "individual", "(sliced ripe)", "bananas");
    Utensils uten = new Utensils("Skillet", "large");
    Steps steps = new Steps("simmer", ing, uten, "for 2 minutes");
    assertEquals("simmer the bananas in the Skillet for 2 minutes", steps.toString());
  }
}
