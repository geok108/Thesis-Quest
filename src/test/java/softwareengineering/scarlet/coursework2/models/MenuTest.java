package softwareengineering.scarlet.coursework2.models;

import static org.junit.Assert.*;
import org.junit.Test;

public class MenuTest {

  @Test
  public void testCreation() {
    Menu menu = new Menu(3);

    // menu should start at top option
    assertEquals(0, menu.getOption());
  }

  @Test
  public void testIncrement() {
    Menu menu = new Menu(3);
    menu.incrementOption();

    assertEquals(1, menu.getOption());
  }

  @Test
  public void testIncrementOverBound() {
    int numOptions = 3;
    Menu menu = new Menu(numOptions);

    for (int i = 0; i < numOptions; i++) {
      menu.incrementOption();
    }

    // Menu wraps back to first option
    assertEquals(0, menu.getOption());
  }

  @Test
  public void testDecrement() {
    int numOptions = 3;
    Menu menu = new Menu(numOptions);

    for (int i = 0; i < numOptions - 1; i++) {
      menu.incrementOption();
    }

    menu.decrementOption();

    assertEquals(1, menu.getOption());
  }

  @Test
  public void testDecrementOverBound() {
    int numOptions = 3;
    Menu menu = new Menu(numOptions);
    menu.decrementOption();

    // Menu wraps to bottom option
    assertEquals(numOptions - 1, menu.getOption());
  }

}
