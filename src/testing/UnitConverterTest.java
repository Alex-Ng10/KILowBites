package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import converter.MassConverter;
import converter.MassToVolume;
import converter.VolumeConverter;

class UnitConverterTest
{

  @Test
  void test()
  {
    Double num = 9.0;
    Double ten = 10.0;
    String milliliter = "Milliliter";
    String pinch = "Pinch";
    String teaspoon = "Teaspoon";
    String tablespoon = "Tablespoon";
    String fluidOunce = "Fluid Ounce";
    String cup = "Cup";
    String pint = "Pint";
    String quart = "Quart";
    String gallon = "Gallon";
    String gram = "Gram";
    String dram = "Dram";
    String ounce = "Ounce";
    String pound = "Pound";
    assertEquals(3.0, VolumeConverter.teaspoonConverter(tablespoon, 9.0));
    assertEquals(0.01171875, VolumeConverter.teaspoonConverter(gallon, num));
    assertEquals(0.03515625, VolumeConverter.tablespoonConverter(gallon, num));
    assertEquals(0.28125, VolumeConverter.fluidOunceConverter(quart, num));
    
    assertEquals(0.625 , MassConverter.dramConverter(ounce, ten));
    assertEquals(160, MassConverter.ounceConverter(dram, ten));
    assertEquals(0.039 , MassConverter.dramConverter(pound, ten), 0.001);
    assertEquals(0.625 , MassConverter.dramConverter(ounce, ten));
    assertEquals(2560 , MassConverter.poundConverter(dram, ten), 1);
    assertEquals(0.625, MassConverter.ounceConverter(pound, ten));
    assertEquals(160, MassConverter.poundConverter(ounce, ten));
    assertEquals(5.64, MassConverter.gramConverter(dram, ten), 0.01);
    
    
    assertEquals(9.61538461538462, MassToVolume.interConverting(gram, milliliter, 10.0, 1.04), 0.001);
    assertEquals(8.679219291, MassToVolume.interConverting(cup, ounce, 1.0, 1.04), 0.001);
    assertEquals(18.434, MassToVolume.interConverting(ounce, tablespoon, 10.0, 1.04), 0.01);
    assertEquals(884.8719, MassToVolume.interConverting(pound, teaspoon, 10.0, 1.04), 0.01);
    assertEquals(55.3, MassToVolume.interConverting(dram, pinch, 10.0, 1.04), 0.1);
    assertEquals(0.325, MassToVolume.interConverting(gram, fluidOunce, 10.0, 1.04), 0.1);
    assertEquals(0.04, MassToVolume.interConverting(gram, cup, 10.0, 1.04), 0.001);
    assertEquals(0.02, MassToVolume.interConverting(gram, pint, 10.0, 1.04), 0.001);
    assertEquals(0.01, MassToVolume.interConverting(gram, quart, 10.0, 1.04), 0.001);
    assertEquals(0.0025, MassToVolume.interConverting(gram, gallon, 10.0, 1.04), 0.0001);
    assertEquals(10.4 , MassToVolume.interConverting(milliliter, gram, 10.0, 1.04));
    assertEquals(3.20 , MassToVolume.interConverting(pinch, gram, 10.0, 1.04), 0.01);
    assertEquals(51.26 , MassToVolume.interConverting(teaspoon, gram, 10.0, 1.04), 0.01);
    assertEquals(153.78 , MassToVolume.interConverting(tablespoon, gram, 10.0, 1.04), 0.01);
    assertEquals(307.56 , MassToVolume.interConverting(fluidOunce, gram, 10.0, 1.04), 0.01);
    assertEquals(2460.48 , MassToVolume.interConverting(cup, gram, 10.0, 1.04), 0.1);
    assertEquals(4920.96 , MassToVolume.interConverting(pint, gram, 10.0, 1.04), 0.1);
    assertEquals(21 , MassToVolume.interConverting(quart, pound, 10.0, 1.04), 1);
    assertEquals(1388 , MassToVolume.interConverting(gallon, ounce, 10.0, 1.04), 1);
    assertEquals(128.0, VolumeConverter.callerHelp(gallon, quart, 32.0));
    assertEquals(30283.294, VolumeConverter.callerHelp(quart, milliliter, 32.0), 0.001);
    assertEquals(64.0, VolumeConverter.callerHelp(quart, tablespoon, 1.0));
    assertEquals(16.0, VolumeConverter.callerHelp(pint, fluidOunce, 1.0));
    assertEquals(8.0, VolumeConverter.callerHelp(cup, cup, 8.0));
    assertEquals(1.0, VolumeConverter.callerHelp(fluidOunce, cup, 8.0));
    assertEquals(1.0, VolumeConverter.callerHelp(teaspoon, cup, 48.0));
    assertEquals(1.0, VolumeConverter.callerHelp(pinch, teaspoon, 16.0));
    assertEquals(0.25, MassConverter.callerHelp(ounce, pound, 4.0));
    assertEquals(0.25, MassConverter.callerHelp(dram, pound, 64.0));
    assertEquals(1.0, MassConverter.callerHelp(gram, ounce, 28.34952), 0.001);
    assertEquals(16.0, MassConverter.callerHelp(pound, ounce, 1.0));
    assertEquals(1.0, VolumeConverter.callerHelp(milliliter, tablespoon, 14.7), 0.1);
    assertEquals(1.0, VolumeConverter.callerHelp(tablespoon, fluidOunce, 2.0), 0.1);
    assertEquals(14.7, VolumeConverter.callerHelp(tablespoon, milliliter, 1.0), 0.1);
    assertEquals(1.0, MassConverter.callerHelp(ounce, ounce, 1.0));
    assertEquals(1.0, MassConverter.callerHelp(dram, dram, 1.0));
    assertEquals(1.0, MassConverter.callerHelp(gram, gram, 1.0));
    assertEquals(1.0, MassConverter.callerHelp(pound, pound, 1.0));
    assertEquals(1.0, MassConverter.callerHelp(pound, pound, 1.0));
    MassConverter.callerHelp("yes", ounce, 1.0);
    MassToVolume.interConverting("Yes", pound, ten, 1.04);
    
  }
  
  /**
   * Ensures 100% coverage.
   */
  @Test
  void test2() 
  {
	  new MassConverter();
	  new VolumeConverter();
	  new MassToVolume();
  }
  

}
