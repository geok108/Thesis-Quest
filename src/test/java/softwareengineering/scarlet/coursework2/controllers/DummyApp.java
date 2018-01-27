package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.App;

public class DummyApp implements App {
  public boolean didIQuit = false;
  public boolean didISwitchToBackStory = false;
  public boolean didISwitchToWin = false;
  public boolean didISwitchToMenu = false;
  public boolean didISwitchtoAbandonGame = false;
  public boolean didISwitchToGameOver=false;
  private MenuController menuController;
  private GameController gameController;
  private SetPreGameController setPreGameController;
  private BackstoryController backstoryController;
  private WinController winController;
  private AbandonGameController abandonGameController;
  private GameoverController gameOverController;

  @Override
  public void quit() {
    this.didIQuit = true;
  }

  @Override
  public void switchToMenu() {
    this.didISwitchToMenu = true;
  }

  @Override
  public void switchToPreGame() {
    // TODO Auto-generated method stub
  }

  @Override
  public void switchToBackstory() {
    this.didISwitchToBackStory = true;
  }

  @Override
  public void switchToGame() {
    // TODO Auto-generated method stub
  }

  @Override
  public void switchToWin() {
    this.didISwitchToWin = true;
  }

  @Override
  public void switchToGameOver() {
    // TODO Auto-generated method stub
    this.didISwitchToGameOver=true;

  }

  public MenuController getMenuController() {
    if (menuController == null) {
      menuController = new MenuController(this);
    }
    return menuController;
  }

  public GameController getGameController() {
    if (gameController == null) {
      gameController = new GameController(this);
    }
    return gameController;
  }

  public SetPreGameController getSetPreGameController() {
    if (setPreGameController == null) {
      setPreGameController = new SetPreGameController(this);
    }
    return setPreGameController;
  }

  public BackstoryController getBackstoryController() {
    if (backstoryController == null) {
      backstoryController = new BackstoryController(this);
    }
    return backstoryController;
  }

  public WinController getWinController() {
    if (winController == null) {
      winController = new WinController(this);
    }
    return winController;
  }

  public AbandonGameController getAbandonGameController() {
    // TODO Auto-generated method stub
    if (abandonGameController == null) {
      abandonGameController = new AbandonGameController(this);
    }
    return abandonGameController;
  }

  @Override
  public void switchToAbandonGame() {
    // TODO Auto-generated method stub
    this.didISwitchtoAbandonGame = true;
  }

  @Override
  public GameoverController getGameOverController() {
    // TODO Auto-generated method stub
    if (gameOverController == null) {
      gameOverController = new GameoverController(this);
    }
    return gameOverController;
  }
}
