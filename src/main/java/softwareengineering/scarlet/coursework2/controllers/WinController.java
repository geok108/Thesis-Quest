package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.App;
import softwareengineering.scarlet.coursework2.models.GameScore;
import softwareengineering.scarlet.coursework2.views.View;
import softwareengineering.scarlet.coursework2.views.WinView;

/**
 * The controller for the win game screen, shown after the player successfully exits the dungeon
 */
public class WinController implements Controller {
  private App app;
  private WinView view;
  private GameScore score;

  public WinController(App app) {
    this.app = app;
  }

  /**
   * Receive the score object from the game controller
   */
  public void setScore(GameScore score) {
    this.score = score;
  }

  /**
   * Set the score object in the view
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
    this.view = (WinView) view;
    setModels();
  }

  /**
   * Get the current view
   */
  @Override
  public View getView() {
    return this.view;
  }

  @Override
  /**
   * User should press enter to continue to go back to the menu
   *
   * @param input The user's input
   */
  public void handleInput(Input input) {
    if (input == Input.CHOOSE) {
      app.switchToMenu();
    }
  }

}
