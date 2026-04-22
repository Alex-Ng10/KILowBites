package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cooking.RecipeElementType;

public class RecipeElementTypeTest {

    @Test
    public void testGetLabelSingular() {
        assertEquals("Ingredient", RecipeElementType.INGREDIENT.getLabel(false));
        assertEquals("Utensil", RecipeElementType.UTENSIL.getLabel(false));
        assertEquals("Step", RecipeElementType.STEP.getLabel(false));
    }

    @Test
    public void testGetLabelPlural() {
        assertEquals("Ingredients", RecipeElementType.INGREDIENT.getLabel(true));
        assertEquals("Utensils", RecipeElementType.UTENSIL.getLabel(true));
        assertEquals("Steps", RecipeElementType.STEP.getLabel(true));
    }

    @Test
    public void testGetDeleteCommand() {
        assertEquals("Ingredient Delete", RecipeElementType.INGREDIENT.getDeleteCommand());
        assertEquals("Utensil Delete", RecipeElementType.UTENSIL.getDeleteCommand());
        assertEquals("Step Delete", RecipeElementType.STEP.getDeleteCommand());
    }

//    @Test
//    public void testGetType() {
//        assertEquals(RecipeElementType.INGREDIENT, RecipeElementType.INGREDIENT.getType());
//        assertEquals(RecipeElementType.UTENSIL, RecipeElementType.UTENSIL.getType());
//        assertEquals(RecipeElementType.STEP, RecipeElementType.STEP.getType());
//    }
}
