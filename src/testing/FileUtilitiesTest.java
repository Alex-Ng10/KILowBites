package testing;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import cooking.Recipe;
import utilities.FileUtilities;

class FileUtilitiesTest
{

  @Test
  void test()
  {
    new FileUtilities();
    List<Object> open = FileUtilities.open(); // cancel
    assertNull(open);

    open = FileUtilities.open(); // unknown directory
    assertNull(open);

    open = FileUtilities.open(); // empty directory
    assertNull(open);

    open = FileUtilities.open(); // choose file & cancel
    assertNull(open);

    open = FileUtilities.open(); // choose recipe
    assertEquals(open, open);

    open = FileUtilities.open(); // choose meal
    assertEquals(open, open);

    Recipe r = FileUtilities.openRecipe();
    assertNull(r);

    // r = FileUtilities.openRecipe();
    // assertEquals(r, r);
  }

}
