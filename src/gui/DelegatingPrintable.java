package gui;

import java.awt.*;
import java.awt.print.*;
import javax.swing.*;
/**
 * DelegatingPrintable class.
 */
public class DelegatingPrintable implements Printable
{
  private boolean doubleBuffered;
  private boolean doubleBuffered2;
  private JComponent delegate;
  private JComponent delegate2;
  private int delegateNum;
  /**
   * DelegatingPrintable constructor.
   * @param delegate
   */
  public DelegatingPrintable(final JComponent delegate)
  {
    this.delegate = delegate;
    this.delegateNum = 1;
    doubleBuffered = delegate.isDoubleBuffered();
  }

  /**
   * DelegatingPrintable constructor.
   * @param delegate
   * @param delegate2
   */
  public DelegatingPrintable(final JComponent delegate, final JComponent delegate2)
  {
    this.delegate = delegate;
    this.delegate2 = delegate2;
    this.delegateNum = 2;
    doubleBuffered = delegate.isDoubleBuffered();
    doubleBuffered2 = delegate2.isDoubleBuffered();
  }
/**
 * Prints.
 * @param g
 * @param format
 * @param page
 * @return int if page exists
 */
  public int print(final Graphics g, final PageFormat format, final int page)
  {
    double cH, cW, h, scale, w, x, y;
    Graphics2D g2;
    int status;
    JComponent realDelegate;
    if (delegateNum == 1)
    {

      g2 = (Graphics2D) g;

      // Turn off double buffering
      delegate.setDoubleBuffered(false);

      status = Printable.NO_SUCH_PAGE;
      if (page == 0)
      {
        // Translate the origin
        x = format.getImageableX();
        y = format.getImageableY();
        g2.translate(x, y);

        // Scale the coordinate system (without changing the
        // aspect ratio)
        h = format.getImageableHeight();
        w = format.getImageableWidth();
        cW = (double) (delegate.getWidth());
        cH = (double) (delegate.getHeight());
        scale = Math.min(w / cW, h / cH);
        g2.scale(scale, scale);

        // Have the JComponent paint itself
        delegate.paint(g2);
        // Inform the caller that a page has been drawn
        status = Printable.PAGE_EXISTS;
      }

      // Restore double buffering (if it was on)
      delegate.setDoubleBuffered(doubleBuffered);
      return status;
    }
    else
    {
      g2 = (Graphics2D) g;
      if (page == 0)
      {
        realDelegate = delegate;
      }
      else if (page == 1)
      {
        realDelegate = delegate2;
      }
      else
      {
        return Printable.NO_SUCH_PAGE;
      }

      // Turn off double buffering
      realDelegate.setDoubleBuffered(false);

      // Translate the origin
      x = format.getImageableX();
      y = format.getImageableY();
      g2.translate(x, y);

      // Scale the coordinate system (without changing the aspect ratio)
      h = format.getImageableHeight();
      w = format.getImageableWidth();
      cW = (double) (realDelegate.getWidth());
      cH = (double) (realDelegate.getHeight());
      scale = Math.min(w / cW, h / cH);
      g2.scale(scale, scale);

      // Have the JComponent paint itself
      realDelegate.paint(g2);

      // Restore double buffering double buffering (if it was on)
      if (realDelegate == delegate)
      {
        realDelegate.setDoubleBuffered(doubleBuffered);
      }
      else if (realDelegate == delegate2)
      {
        realDelegate.setDoubleBuffered(doubleBuffered2);
      }

      return Printable.PAGE_EXISTS;
    }
  }
}
