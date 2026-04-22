package utilities;

/**
 * DocumentStateObserver interface.
 *
 * @author f24team3d
 * @version 11/12/24
 */

public interface DocumentStateObserver
{
  /**
   * Handle notification.
   * 
   * @param state
   *          Document state
   */
  public void handleNotification(DocumentState state);
}
