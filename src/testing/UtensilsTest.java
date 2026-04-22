package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cooking.Utensils;

public class UtensilsTest {

    @Test
    public void testToStringWithDetails() {
        Utensils utensil = new Utensils("Spatula", "Stainless Steel");
        assertEquals("Stainless Steel Spatula", utensil.toString());
    }

    @Test
    public void testToStringWithoutDetails() {
        Utensils utensil = new Utensils("Spatula", "");
        assertEquals("Spatula", utensil.toString());
    }
}
