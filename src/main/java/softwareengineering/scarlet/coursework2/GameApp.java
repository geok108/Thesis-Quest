package softwareengineering.scarlet.coursework2;

import java.awt.EventQueue;
import softwareengineering.scarlet.coursework2.controllers.AbandonGameController;
import softwareengineering.scarlet.coursework2.controllers.BackstoryController;
import softwareengineering.scarlet.coursework2.controllers.GameController;
import softwareengineering.scarlet.coursework2.controllers.GameoverController;
import softwareengineering.scarlet.coursework2.controllers.MenuController;
import softwareengineering.scarlet.coursework2.controllers.SetPreGameController;
import softwareengineering.scarlet.coursework2.controllers.WinController;
import softwareengineering.scarlet.coursework2.views.AbandonGameView;
import softwareengineering.scarlet.coursework2.views.BackstoryView;
import softwareengineering.scarlet.coursework2.views.Frame;
import softwareengineering.scarlet.coursework2.views.GameoverView;
import softwareengineering.scarlet.coursework2.views.MenuView;
import softwareengineering.scarlet.coursework2.views.Panel;
import softwareengineering.scarlet.coursework2.views.SetPreGameView;
import softwareengineering.scarlet.coursework2.views.View;
import softwareengineering.scarlet.coursework2.views.WinView;
import softwareengineering.scarlet.coursework2.views.game.GameView;

/**
 * Main entry point into the application.
 *
 * Contains the main method, kicking off AWT etc. Also acts as a central point of reference for all
 * controllers, allowing them to switch without having to store references to each other.
 *
 * Each controller will have a reference to the App, and should use it to pass control to other
 * controllers/views via the switchTo* methods.
 *
 * There should be a switchTo* method for each controller, which should act as a factory method for
 * the controller. This should also include the creation of the relevant View, so that the view
 * implementation is separated from the controller. Other objects should be instantiated by the
 * controller itself.
 */
public class GameApp implements App {
  private Frame frame;
  private Panel panel;
  private MenuController menuController;
  private GameController gameController;
  private SetPreGameController setPreGameController;
  private BackstoryController backstoryController;
  private WinController winController;
  private GameoverController gameOverController;
  private AbandonGameController abandonGameController;

  public GameApp() {
    this.panel = new Panel();
    this.panel.setSize(700, 720);
    this.frame = new Frame(this.panel);
  }

  /**
   * Get the controller singleton for the menu screen
   */
  public MenuController getMenuController() {
    if (menuController == null) {
      menuController = new MenuController(this);
    }
    return menuController;
  }

  /**
   * Get the controller singleton for the game screen
   */
  public GameController getGameController() {
    if (gameController == null) {
      gameController = new GameController(this);
    }
    return gameController;
  }

  /**
   * Get the controller singleton for the pre-game (enter name) screen
   */
  public SetPreGameController getSetPreGameController() {
    if (setPreGameController == null) {
      setPreGameController = new SetPreGameController(this);
    }
    return setPreGameController;
  }

  /**
   * Get the controller singleton for the backstory screen
   */
  public BackstoryController getBackstoryController() {
    if (backstoryController == null) {
      backstoryController = new BackstoryController(this);
    }
    return backstoryController;
  }

  /**
   * Get the controller singleton for the win game screen
   */
  public WinController getWinController() {
    if (winController == null) {
      winController = new WinController(this);
    }
    return winController;
  }

  /**
   * Get the controller singleton for the abandoned game screen
   */
  public AbandonGameController getAbandonGameController() {
    if (abandonGameController == null) {
      abandonGameController = new AbandonGameController(this);
    }
    return abandonGameController;
  }

  /**
   * Get the controller singleton for the game over screen
   */
  public GameoverController getGameOverController() {
    if (gameOverController == null) {
      gameOverController = new GameoverController(this);

    }
    return gameOverController;
  }

  /**
   * Switch control to the menu screen
   */
  public void switchToMenu() {
    this.panel.setController(getMenuController());

    View view = new MenuView();
    this.menuController.init(view);
  }

  /**
   * Switch control to the pre-game (enter name) screen
   */
  public void switchToPreGame() {
    this.panel.setController(getSetPreGameController());

    SetPreGameView view = new SetPreGameView();
    this.setPreGameController.init(view);
  }

  /**
   * Switch control to the backstory screen
   */
  public void switchToBackstory() {
    this.panel.setController(getBackstoryController());

    BackstoryView view = new BackstoryView();
    this.backstoryController.init(view);
  }

  /**
   * Switch control to the game screen
   */
  public void switchToGame() {
    this.panel.setController(getGameController());

    View view = new GameView();
    this.gameController.init(view);
  }

  /**
   * Switch control to the win game screen
   */
  public void switchToWin() {
    this.panel.setController(getWinController());

    View view = new WinView();
    this.winController.init(view);
  }

  /**
   * Switch control to the abandoned game screen
   */
  public void switchToAbandonGame() {
    this.panel.setController(getAbandonGameController());

    View view = new AbandonGameView();
    this.abandonGameController.init(view);
  }

  /**
   * Switch to GameoverView when the player dies
   */
  public void switchToGameOver() {
    this.panel.setController(getGameOverController());

    View view = new GameoverView();
    this.gameOverController.init(view);
  }

  /**
   * Quit the application
   */
  public void quit() {
    System.exit(0);
  }

  /**
   * Start the application after initialization
   */
  public void start() {
    this.frame.setVisible(true);
  }

  /**
   * Main method.
   *
   * Creates the App (including basic AWT resources), and then surrenders control to the
   * MenuController.
   *
   * @param args Command line args - none respected by this application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        // Create the app
        GameApp app = new GameApp();

        // Hand over control to the Menu
        app.switchToMenu();

        // Once everything is instantiated, actually display the screen
        app.start();
      }
    });
  }
}
