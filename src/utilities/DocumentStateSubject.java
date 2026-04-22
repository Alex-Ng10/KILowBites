package utilities;

/**
 * DocumentStateSubject interface.
 *
 * @author f24team3d
 * @version 11/12/24
 */

public interface DocumentStateSubject
{
  /**
   * Add observer.
   * 
   * @param o
   *          DocumentStateObserver to be added
   */
  public void addObserver(DocumentStateObserver o);

  /**
   * Remove observer.
   * 
   * @param o
   *          DocumentStateObserver to be removed
   */
  public void removeObserver(DocumentStateObserver o);

  /**
   * Notify observers.
   */
  public void notifyObservers();
}
