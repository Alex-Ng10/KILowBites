package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utilities.InputUtilities;

class InputUtilitiesTest
{

  @Test
  void test() {
    InputUtilities u1 = new InputUtilities();
    assertEquals(true, InputUtilities.isInt("123"));
    assertEquals(false, InputUtilities.isInt("123.45"));
    assertEquals(true, InputUtilities.isDouble("123.45"));
    assertEquals(false, InputUtilities.isDouble("abc"));
    assertEquals(true, InputUtilities.isPositiveInt("123"));
    assertEquals(false, InputUtilities.isPositiveInt("-123"));
    assertEquals(true, InputUtilities.isPositiveDouble("123.45"));
    assertEquals(false, InputUtilities.isPositiveDouble("-123.45"));
    assertEquals(true, InputUtilities.isAlphaNumeric("abc 123"));
    assertEquals(false, InputUtilities.isAlphaNumeric("abc!123"));
    assertEquals(true, InputUtilities.isAlphabetical("abc ABC"));
    assertEquals(false, InputUtilities.isAlphabetical("abc123"));
    assertEquals("Test Case", InputUtilities.separateByCapital("TestCase"));
    assertEquals("Meal Food", InputUtilities.separateByCapital("MealFood.mel"));
    assertEquals("Pizza Toast", InputUtilities.separateByCapital("PizzaToast.rcp"));
    assertEquals(InputUtilities.isPositiveDouble("0"), false);
    assertEquals(InputUtilities.isPositiveInt("0"), false);
  }
}
