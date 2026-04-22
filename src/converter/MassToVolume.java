package converter;

/**
 * Converts a certain mass unit to another volume unit and vice versa.
 *
 * @author f24team3d
 * @version 10/23/24
 */
public class MassToVolume
{

  /**
  /**
   * Converts from mass to volume or vice versa.
   *
   * @param toUnit The type of the to amount.
   * @param fromUnit The type of the from amount.
   * @param density The density of the ingredient.
   * @param fromAmount The actual from amount.
   *
   * @return The converted to amount.
   */
  public static Double interConverting(final String fromUnit, final String toUnit, 
      final Double fromAmount, final Double density)
  {
    Double grams = 0.0;
    double milliliters = 0.0;
    if ("Gram".equals(fromUnit))
    {
      milliliters = fromAmount / density;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Dram".equals(fromUnit))
    {
      grams = MassConverter.dramConverter("Gram", fromAmount);
      milliliters = grams / density;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Ounce".equals(fromUnit))
    {
      grams = MassConverter.ounceConverter("Gram", fromAmount);
      milliliters = grams / density;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Pound".equals(fromUnit))
    {
      grams = MassConverter.poundConverter("Gram", fromAmount);
      milliliters = grams / density;
      return VolumeConverter.milliliterToFinal(toUnit, milliliters);
    }
    if ("Milliliter".equals(fromUnit))
    {
      grams = fromAmount * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Pinch".equals(fromUnit))
    {
      milliliters = VolumeConverter.pinchConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Teaspoon".equals(fromUnit))
    {
      milliliters = VolumeConverter.teaspoonConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Tablespoon".equals(fromUnit))
    {
      milliliters = VolumeConverter.tablespoonConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Fluid Ounce".equals(fromUnit))
    {
      milliliters = VolumeConverter.fluidOunceConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Cup".equals(fromUnit))
    {
      milliliters = VolumeConverter.cupConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Pint".equals(fromUnit))
    {
      milliliters = VolumeConverter.pintConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Quart".equals(fromUnit))
    {
      milliliters = VolumeConverter.quartConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    if ("Gallon".equals(fromUnit))
    {
      milliliters = VolumeConverter.gallonConverter("Milliliter", fromAmount);
      grams = milliliters * density;
      return MassConverter.gramToFinal(toUnit, grams);
    }
    return 0.0; // Should not get here
  }
  
}
