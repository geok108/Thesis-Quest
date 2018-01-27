package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.App;
import softwareengineering.scarlet.coursework2.views.BackstoryView;
import softwareengineering.scarlet.coursework2.views.View;

/**
 * Controller for the backstory screen, shown just before the game starts
 */
public class BackstoryController implements Controller {
  private App app;
  private BackstoryView view;

  public BackstoryController(App app) {
    this.app = app;
  }

  /**
   * Switch to the back story screen.
   *
   * @param view The view to render the back story
   */
  @Override
  public void init(View view) {
    this.view = (BackstoryView) view;
  }

  @Override
  public View getView() {
    return this.view;
  }

  @Override
  /**
   * User should press enter to continue to the game
   *
   * @param input
   */
  public void handleInput(Input input) {
    if (input == Input.CHOOSE) {
      app.switchToGame();
    }
  }
}
