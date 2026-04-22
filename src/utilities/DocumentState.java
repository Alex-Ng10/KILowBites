package utilities;

/**
 * An interface for Document States. Contains the states of a document.
 *
 * @author f24team3d
 * @version 10/31/24
 */
public enum DocumentState
{
  NULL("Null"), CHANGED("Changed"), UNCHANGED("Unchanged");

  String state;

  /**
   * Constructor for DocumentState.
   *
   * @param state
   *          the state of the document
   */
  private DocumentState(final String state)
  {
    this.state = state;
  }

  /**
   * Get the state of the document.
   *
   * @return the state of the document
   */
  public String getState()
  {
    return state;
  }

  /**
   * Change the state of the document.
   *
   * @param s
   *          the new state of the document
   * @return State of document
   */
  public boolean setState(final DocumentState s)
  {
    this.state = s.getState();
    return true;
  }
}
