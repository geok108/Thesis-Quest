package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.App;
import softwareengineering.scarlet.coursework2.models.GameScore;
import softwareengineering.scarlet.coursework2.views.GameoverView;
import softwareengineering.scarlet.coursework2.views.View;

/**
 * Controller for the game over screen
 */
public class GameoverController implements Controller {
  private App app;
  private GameoverView view;
  private GameScore score;

  public GameoverController(App app) {
    this.app = app;
  }

  /**
   * Receive the score object for the game controller
   */
  public void setScore(GameScore score) {
    this.score = score;
  }

  /**
   * Set the score object into the model
   */
  protected void setModels() {
    this.view.setScore(score);
  }

  /**
   * Switch to the "winning" game end screen.
   *
   * @param view The view to render the back story
   */
  @Override
  public void init(View view) {
    this.view = (GameoverView) view;
    setModels();
  }

  /**
   * Get the current view
   */
  @Override
  public View getView() {
    return this.view;
  }

  /**
   * User should press enter to continue to go back to the menu
   *
   * @param input
   */
  @Override
  public void handleInput(Input input) {
    if (input == Input.CHOOSE) {
      app.switchToMenu();;
    }
  }

}
