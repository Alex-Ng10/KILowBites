package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import utilities.Units;

/**
 * Test cases for Units class.
 * 
 * @author f24team3d
 * @version 10/30/24
 */

class UnitsTest
{

  @Test
  void test()
  {
    Units units = new Units();

    assertEquals(13, units.getUnits().size());

    String[] allUnits = units.getAllUnits();
    String[] massUnits = Units.getMassUnits();
    String[] volumeUnits = Units.getVolumeUnits();

    assertEquals(14, allUnits.length);
    assertEquals("Dram", allUnits[2]);

    assertEquals(4, massUnits.length);
    assertEquals("Pound", massUnits[3]);

    assertEquals(9, volumeUnits.length);
    assertEquals("Pint", volumeUnits[6]);

    assertEquals("Volume", units.unitMeasure("Milliliter"));
    assertEquals("Mass", units.unitMeasure("Pound"));

    String[] allUnitsPlusIndividual = units.getAllUnitsPlusIndividual();
    String[] AllUnitsNoPadding = units.getAllUnitsNoPadding();

    assertEquals(15, allUnitsPlusIndividual.length);
    assertEquals("Individual", allUnitsPlusIndividual[1]);

    assertEquals(13, AllUnitsNoPadding.length);
    assertEquals("Dram", AllUnitsNoPadding[1]);

  }

}
