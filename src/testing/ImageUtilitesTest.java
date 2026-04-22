package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.Test;

import utilities.ImageUtilities;

class ImageUtilitesTest
{

  @Test
  public void testGetFormattedImage() throws IOException
  {
    new ImageUtilities();

    ImageIcon formattedIcon = ImageUtilities.getFormattedImage("open.png", Color.BLUE, 100, 100);
    assertEquals(100, formattedIcon.getIconWidth());
    assertEquals(100, formattedIcon.getIconHeight());
    ImageIcon scaledIcon = ImageUtilities.getScaledImage("open.png", 100, 100);
    assertEquals(100, scaledIcon.getIconWidth());
    assertEquals(100, scaledIcon.getIconHeight());
  }
}
