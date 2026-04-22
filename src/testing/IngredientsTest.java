package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cooking.Ingredients;

import javax.swing.*;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class IngredientsTest
{

  private Ingredients ingredient;

  @BeforeEach
  void setUp()
  {
    ingredient = new Ingredients();
  }

  @Test
  void testToString()
  {
    ingredient.setAmount(1.5);
    ingredient.setUnit("kg");
    ingredient.setDetails("organic");
    ingredient.setName("Flour");

    ResourceBundle STRINGS = ResourceBundle.getBundle("Strings");
    String expectedString = "1.5 kg " + STRINGS.getString("OF") + " organic Flour";

//    assertEquals(expectedString, ingredient.toString());
  }

  @Test
  void testAdd()
  {
    JLabel label = ingredient.add();
    assertNotNull(label);
    assertTrue(label instanceof JLabel);
  }
}
