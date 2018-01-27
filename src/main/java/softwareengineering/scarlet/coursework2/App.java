package softwareengineering.scarlet.coursework2;

import softwareengineering.scarlet.coursework2.controllers.AbandonGameController;
import softwareengineering.scarlet.coursework2.controllers.BackstoryController;
import softwareengineering.scarlet.coursework2.controllers.GameController;
import softwareengineering.scarlet.coursework2.controllers.GameoverController;
import softwareengineering.scarlet.coursework2.controllers.MenuController;
import softwareengineering.scarlet.coursework2.controllers.SetPreGameController;
import softwareengineering.scarlet.coursework2.controllers.WinController;

/**
 * Represents the behaviour of the application.
 *
 * This interface used so we can easily mock out the live GameApp, which is inseparable from AWT.
 */
public interface App {
  /**
   * Quit the application.
   */
  public void quit();

  /**
   * Get the controller singleton for the menu screen
   */
  public MenuController getMenuController();

  /**
   * Get the controller singleton for the pre-game (enter name) screen
   */
  public SetPreGameController getSetPreGameController();

  /**
   * Get the controller singleton for the backstory screen
   */
  public BackstoryController getBackstoryController();

  /**
   * Get the controller singleton for the game screen
   */
  public GameController getGameController();

  /**
   * Get the controller singleton for the win game screen
   */
  public WinController getWinController();

  /**
   * Get the controller singleton for the abandon game screen
   */
  public AbandonGameController getAbandonGameController();

  /**
   * Get the controller singleton for the game over screen
   */
  public GameoverController getGameOverController();

  /**
   * Switch to the menu screen
   */
  public void switchToMenu();

  /**
   * Switch to the pre-game ("enter name") screen
   */
  public void switchToPreGame();

  /**
   * Switch to the backstory screen
   */
  public void switchToBackstory();

  /**
   * Switch to the game screen
   */
  public void switchToGame();

  /**
   * Switch to win game screen
   */
  public void switchToWin();

  /**
   * Switch to the abandoned game screen
   */
  public void switchToAbandonGame();

  /**
   * Switch to the game over screen
   */
  public void switchToGameOver();

}
