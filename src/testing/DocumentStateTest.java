package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utilities.DocumentState;

public class DocumentStateTest
{

  @Test
  public void testGetState()
  {
    assertEquals("Null", DocumentState.NULL.getState());
    assertEquals("Changed", DocumentState.CHANGED.getState());
    assertEquals("Unchanged", DocumentState.UNCHANGED.getState());
  }

  @Test
  public void testSetState()
  {
    DocumentState state = DocumentState.NULL;
    assertTrue(state.setState(DocumentState.CHANGED));
    assertEquals("Changed", state.getState());

    assertTrue(state.setState(DocumentState.UNCHANGED));
    assertEquals("Unchanged", state.getState());
  }
}
