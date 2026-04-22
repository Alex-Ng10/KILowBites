package controller;

import java.awt.print.*;
import javax.swing.*;

/**
 * PrinterController Class.
 */
public class PrinterController
{
  /**
   * Print.
   * 
   * @param printable
   * @param parent
   */
  public static void print(final Printable printable, final JFrame parent)
  {
    PrinterJob job = PrinterJob.getPrinterJob();
    job.setPrintable(printable);
    boolean shouldPrint = job.printDialog();
    if (shouldPrint)
    {
      try
      {
        job.print();
      }
      catch (PrinterException e)
      {
        e.printStackTrace();
      }
    }
  }
}
