package utilities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * ImageUtilities class.
 *
 * @author f24team3d
 * @version 11/12/24
 */
public class ImageUtilities
{
  /**
   * Scale and color correction for icons.
   * 
   * @param name
   *          Name of icon
   * @param color
   *          Color of icon
   * @param width
   *          Width of icon
   * @param height
   *          Height of icon
   * @return colored and scaled icon
   */
  public static ImageIcon getFormattedImage(final String name, final Color color, final int width,
      final int height)
  {
    try
    {
      // Load the original image
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      URL url = classLoader.getResource(name);
      BufferedImage original = ImageIO.read(url);

      // Create a colored version of the original image
      BufferedImage colored = new BufferedImage(original.getWidth(), original.getHeight(),
          BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = colored.createGraphics();
      g.drawImage(original, 0, 0, null);
      g.setComposite(AlphaComposite.SrcIn);
      g.setColor(color);
      g.fillRect(0, 0, original.getWidth(), original.getHeight());
      g.dispose();

      // Scale the colored image
      Image scaledImage = colored.getScaledInstance(width, height, Image.SCALE_SMOOTH);

      // Return the scaled image wrapped in an ImageIcon
      return new ImageIcon(scaledImage);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Get Scaled image.
   *
   * @param name
   *          Name of image
   * @param width
   *          Width of image
   * @param height
   *          Height of image
   * @return scaled image
   */
  public static ImageIcon getScaledImage(final String name, final int width, final int height)
  {
    try
    {
      // Load the original image
      ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      URL url = classLoader.getResource(name);
      BufferedImage original = ImageIO.read(url);

      // Scale the colored image
      Image scaledImage = original.getScaledInstance(width, height, Image.SCALE_SMOOTH);

      // Return the scaled image wrapped in an ImageIcon
      return new ImageIcon(scaledImage);
    }
    catch (IOException e)
    {
      return null;
    }
  }
}
