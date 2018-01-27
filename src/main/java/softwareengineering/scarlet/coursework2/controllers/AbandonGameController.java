package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.App;
import softwareengineering.scarlet.coursework2.models.GameScore;
import softwareengineering.scarlet.coursework2.views.AbandonGameView;
import softwareengineering.scarlet.coursework2.views.View;

/**
 * Controller for the "abandoned game" screen, shown after the player abandons their game
 */
public class AbandonGameController implements Controller {
  private App app;
  private AbandonGameView view;
  private GameScore score;

  public AbandonGameController(App app) {
    this.app = app;
  }

  /**
   * Receive a score object from the game controller
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
   * Switch to the "winning" game end screen
   *
   * @param view The view to render the back story
   */
  @Override
  public void init(View view) {
    this.view = (AbandonGameView) view;
    setModels();
  }

  /**
   * Get the view associated with the controller
   */
  @Override
  public View getView() {
    return this.view;
  }

  @Override
  /**
   * User should press enter to continue to go back to the menu
   *
   * @param input
   */
  public void handleInput(Input input) {
    if (input == Input.CHOOSE) {
      app.switchToMenu();;
    }
  }

}
