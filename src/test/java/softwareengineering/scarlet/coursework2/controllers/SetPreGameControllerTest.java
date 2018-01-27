package softwareengineering.scarlet.coursework2.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SetPreGameControllerTest {
  private DummyApp app = new DummyApp();

  @Test
  public void testInitial() {
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    assertEquals("----", controller.model.getName());
  }

  @Test
  public void testAppend() {
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    controller.handleInput(Input.A);
    controller.handleInput(Input.B);
    assertEquals("AB--", controller.model.getName());
  }

  @Test
  public void testDelete() {
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    controller.handleInput(Input.A);
    controller.handleInput(Input.B);
    controller.handleInput(Input.DELETE);

    assertEquals("A---", controller.model.getName());
    controller.handleInput(Input.B);
    controller.handleInput(Input.C);
    controller.handleInput(Input.D);
    controller.handleInput(Input.DELETE);

    assertEquals("ABC-", controller.model.getName());
  }

  @Test
  public void testDeleteWithEmptyString() {
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    controller.handleInput(Input.DELETE);
  }

  @Test
  public void testValidName() {
    app = new DummyApp();
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    controller.handleInput(Input.A);
    controller.handleInput(Input.B);
    controller.handleInput(Input.C);
    controller.handleInput(Input.D);
    assertEquals("ABCD", controller.model.getName());
  }

  @Test
  public void testSwitchtoBackStory() {
    app = new DummyApp();
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    controller.handleInput(Input.A);
    controller.handleInput(Input.B);
    controller.handleInput(Input.C);
    controller.handleInput(Input.D);
    controller.handleInput(Input.CHOOSE);
    assertTrue(app.didISwitchToBackStory);
  }

  @Test
  public void testReturnToMenu() {
    app = new DummyApp();
    SetPreGameController controller = new SetPreGameController(app);
    controller.setUpData();
    controller.handleInput(Input.QUIT);
    assertTrue(app.didISwitchToMenu);
  }
}
