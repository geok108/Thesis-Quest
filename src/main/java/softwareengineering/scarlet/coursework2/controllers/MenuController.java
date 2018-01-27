package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.App;
import softwareengineering.scarlet.coursework2.models.Menu;
import softwareengineering.scarlet.coursework2.views.MenuView;
import softwareengineering.scarlet.coursework2.views.View;

/**
 * Controller for the Menu screen.
 *
 * Used by the user to choose which menu option they want, and then switches to the appropriate
 * controller.
 */
public class MenuController implements Controller {
  private App app;
  private MenuView view;
  protected Menu model;

  // List of menu options to display
  // TODO: Tie the behaviour of each option to its display text
  protected static String[] options = new String[] {
      "New Game", // 0
      "Quit" // 1
  };

  public MenuController(App app) {
    this.app = app;
  }

  /**
   * Returns the active view for the controller.
   */
  @Override
  public View getView() {
    return this.view;
  }

  /**
   * Handle the up and down motion of the pointer, and the menu choice
   *
   * @param input The type of input sent
   */
  @SuppressWarnings("incomplete-switch")
  @Override
  public void handleInput(Input input) {
    switch (input) {
      case UP:
        this.model.decrementOption();
        break;
      case DOWN:
        this.model.incrementOption();
        break;
      case CHOOSE:
        performAction();
        break;
    }
  }

  /**
   * Given the user's choice, actually decide which controller to switch to.
   */
  private void performAction() {
    switch (this.model.getOption()) {
      case 0:
        app.switchToPreGame();
        break;
      case 1:
        app.quit();
        break;
    }
  }

  /**
   * Create the relevant models.
   *
   * Separated from init for ease of testing.
   */
  protected void setUpData() {
    this.model = new Menu(options.length);
  }

  /**
   * Switch to the menu screen.
   *
   * Resets everything, so the menu pointer returns to the top of the options.
   *
   * @param view The view to render the menu
   */
  @Override
  public void init(View view) {
    this.view = (MenuView) view;
    this.view.setOptions(options);
    this.setUpData();
    this.view.setModel(model);
  }
}
