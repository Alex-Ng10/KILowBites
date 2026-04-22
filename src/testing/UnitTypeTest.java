package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utilities.UnitType;

class UnitTypeTest
{
  @Test
  void test()
  {
    UnitType u1 = new UnitType();
    String[] imperialUnits = UnitType.getImperialUnits();
    String[] imperialUnitsSpace = UnitType.getImperialUnitsSpace();
    String[] imperialUnitsIndividual = UnitType.getImperialUnitsIndividual();
    String[] metricUnits = UnitType.getMetricUnits();
    String[] metricUnitsIndividual = UnitType.getMetricUnitsSpace();
    String[] metricUnitsSpace = UnitType.getMetricUnitsIndividual();
    
    UnitType.setImperialSelected();
    assertEquals(UnitType.getImperialSelected(), true);
    assertEquals(UnitType.getMetricSelected(), false);
    UnitType.setMetricSelected();
    assertEquals(UnitType.getImperialSelected(), false);
    assertEquals(UnitType.getMetricSelected(), true);
  }

}
