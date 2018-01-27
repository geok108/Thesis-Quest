package softwareengineering.scarlet.coursework2.controllers;

import java.util.HashMap;
import java.util.Map;
import softwareengineering.scarlet.coursework2.App;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Dungeon;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.GameScore;
import softwareengineering.scarlet.coursework2.models.MessageList;
import softwareengineering.scarlet.coursework2.models.Monster;
import softwareengineering.scarlet.coursework2.models.Player;
import softwareengineering.scarlet.coursework2.models.Room;
import softwareengineering.scarlet.coursework2.models.StrengthItem;
import softwareengineering.scarlet.coursework2.views.View;
import softwareengineering.scarlet.coursework2.views.game.GameView;

/**
 * Controller for the game screen, where the actual game is played
 *
 * This controller holds logic for handling player actions, references to state in the Dungeon and
 * Player models, and hooks for monster implementations
 */
public class GameController implements Controller {
  private GameView view;
  protected Dungeon dungeon;
  protected Player player;
  private String playerName;
  private App app;

  // TODO: split these constants into some kind of gameConfig class?

  private static final int LEVEL_HEIGHT = 16;
  private static final int LEVEL_WIDTH = 25;
  private static final int NUM_LEVELS = 6;

  private boolean yn = false;

  // Map of movement directions to their x, y movements
  private static final Map<MoveDirection, Pair> moveMap;
  static {
    moveMap = new HashMap<MoveDirection, Pair>();
    moveMap.put(MoveDirection.RIGHT, new Pair(1, 0));
    moveMap.put(MoveDirection.LEFT, new Pair(-1, 0));
    moveMap.put(MoveDirection.UP, new Pair(0, -1));
    moveMap.put(MoveDirection.DOWN, new Pair(0, 1));
    moveMap.put(MoveDirection.UPLEFT, new Pair(-1, -1));
    moveMap.put(MoveDirection.UPRIGHT, new Pair(1, -1));
    moveMap.put(MoveDirection.DOWNLEFT, new Pair(-1, 1));
    moveMap.put(MoveDirection.DOWNRIGHT, new Pair(1, 1));
  }

  public GameController(App app) {
    this.app = app;
  }

  /**
   * Set the player's name for this play through.
   *
   * Used by the SetPreGameController to pass the name to the GameController so it can be recorded
   * in the Player object
   *
   * @param playerName The player's chosen name
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Return the current view
   */
  @Override
  public View getView() {
    return view;
  }

  /**
   * Assumed to be instantiated once per game, therefore it also creates a dungeon and player for
   * the game.
   *
   * @param playerName The player's name for this playthrough
   */
  public void setUpModels() {
    // Create new dungeon
    this.dungeon = new Dungeon(GameController.LEVEL_WIDTH, GameController.LEVEL_HEIGHT,
        GameController.NUM_LEVELS);

    // Get the location of the first room (arbitrarily...) in the dungeon
    // TODO: make the start location selection more intelligent
    Room startRoom = dungeon.getCurrentLevel().getRooms().get(0);
    int startX = startRoom.getX();
    int startY = startRoom.getY();

    // Create player
    this.player = new Player(playerName, startX, startY);
  }

  /**
   * Implementation of player actions, based on movement.
   *
   * The actual action performed depends on the type of the cell the player is moving on to.
   *
   * @param movePair The movement action of the player
   */
  @SuppressWarnings("incomplete-switch")
  protected void performAction(Pair movePair) {
    int targetX = player.getX() + movePair.getX();
    int targetY = player.getY() + movePair.getY();

    CellType targetCellType = this.dungeon.getCurrentLevel().getTypeAtPos(targetX, targetY);

    switch (targetCellType) {
      case ROOM:
      case CORRIDOR:
        player.move(movePair.getX(), movePair.getY());
        break;

      case GOLD:
        player.setGold(player.getGold() + 1);
        player.move(movePair.getX(), movePair.getY());
        Entity gold = this.dungeon.getCurrentLevel().getEntityAtPos(targetX, targetY);
        // Remove gold
        this.dungeon.getCurrentLevel().getEntities().remove(gold);
        break;

      case HEALTH:
        player.increaseHealthPoint(1);
        player.move(movePair.getX(), movePair.getY());
        Entity health = this.dungeon.getCurrentLevel().getEntityAtPos(targetX, targetY);
        // Remove health item
        this.dungeon.getCurrentLevel().getEntities().remove(health);
        break;

      case STRENGTH1:
        player.setStrengthItem(new StrengthItem(10, CellType.STRENGTH1));
        player.move(movePair.getX(), movePair.getY());
        Entity strength1 = this.dungeon.getCurrentLevel().getEntityAtPos(targetX, targetY);
        // Remove strength1
        this.dungeon.getCurrentLevel().getEntities().remove(strength1);
        break;

      case STRENGTH2:
        player.setStrengthItem(new StrengthItem(20, CellType.STRENGTH2));
        player.move(movePair.getX(), movePair.getY());
        Entity strength2 = this.dungeon.getCurrentLevel().getEntityAtPos(targetX, targetY);
        // Remove strength2
        this.dungeon.getCurrentLevel().getEntities().remove(strength2);
        break;

      case STRENGTH3:
        player.setStrengthItem(new StrengthItem(30, CellType.STRENGTH3));
        player.move(movePair.getX(), movePair.getY());
        Entity strength3 = this.dungeon.getCurrentLevel().getEntityAtPos(targetX, targetY);
        // Remove strength3
        this.dungeon.getCurrentLevel().getEntities().remove(strength3);
        break;

      case STAIRSUP:
        dungeon.goUp();
        player.setX(dungeon.getCurrentLevel().getStairsDown().getX());
        player.setY(dungeon.getCurrentLevel().getStairsDown().getY());
        break;

      case STAIRSDOWN:
        dungeon.goDown();
        player.setX(dungeon.getCurrentLevel().getStairsUp().getX());
        player.setY(dungeon.getCurrentLevel().getStairsUp().getY());
        break;

      case EXIT:
        if (player.getGold() >= Dungeon.REQUIRED_SCORE) {
          GameScore score = new GameScore(player.getName(), player.getGold());
          app.getWinController().setScore(score);
          app.switchToWin();
        } else {
          MessageList.addMessage(String.format("You still need %d more pages!",
              Dungeon.REQUIRED_SCORE - player.getGold()));
          player.move(movePair.getX(), movePair.getY());
        }
        break;

      case MONSTER:
        Monster monster = dungeon.getCurrentLevel().getMonsterAtPos(targetX, targetY);
        monster.decreaseHealthPoint(player.getStrength());
        MessageList.addMessage("You hit the monster! Damage: " + -player.getStrength()
            + " Monster's current health:" + monster.getHealthPoints());

        if (monster.getHealthPoints() <= 0) {
          dungeon.getCurrentLevel().getMonsters().remove(monster);
        }
        break;
    }
  }

  /**
   * Moves the player to adjacent tile in the specified direction.
   *
   * @param direction A valid direction as defined in the MoveDirection enum
   */
  public void movePlayer(MoveDirection direction) {
    Pair movePair = moveMap.get(direction);
    performAction(movePair);
  }

  /**
   * Give the monsters a turn each.
   *
   * Iterates through each monster on the level, asking each to perform an action based on the state
   * of the dungeon and the player.
   */
  protected void handleMonsters() {
    for (Monster monster : dungeon.getCurrentLevel().getMonsters()) {
      monster.performAction(dungeon, player);
    }
  }

  /**
   * Perform an action based on the player's input when the game is in the "game" mode (i.e. normal
   * game actions)
   *
   * Once complete, control is handed to the monsters for them to have a turn
   *
   * @param input The input from the view
   */
  @SuppressWarnings("incomplete-switch")
  private void handleGameInput(Input input) {
    switch (input) {
      case UP:
        MessageList.clear();
        movePlayer(MoveDirection.UP);
        break;
      case DOWN:
        MessageList.clear();
        movePlayer(MoveDirection.DOWN);
        break;
      case LEFT:
        MessageList.clear();
        movePlayer(MoveDirection.LEFT);
        break;
      case RIGHT:
        MessageList.clear();
        movePlayer(MoveDirection.RIGHT);
        break;
      case QUIT:
      case Q:
        MessageList.addMessage("Are you sure you want to quit? (Y/N)");
        yn = true;
        break;
    }

    checkPlayerDeath();
    handleMonsters();
  }

  /**
   * Check to see if the player has died - if so, move to the game over screen
   */
  public void checkPlayerDeath() {
    if (player.getHealthPoints() <= 0) {
      GameScore score = new GameScore(player.getName(), player.getGold());
      app.getGameOverController().setScore(score);
      app.switchToGameOver();
    }
  }

  /**
   * Perform an action based on the player's input when the game is in the "abandon" mode (i.e. the
   * user has an expressed a desire to quit and the game is asking for confirmation)
   *
   * @param input The input from the view
   */
  @SuppressWarnings("incomplete-switch")
  private void handleAbandonInput(Input input) {
    switch (input) {
      case Y:
        GameScore score = new GameScore(player.getName(), player.getGold());
        MessageList.clear();
        yn = false;
        app.getAbandonGameController().setScore(score);
        app.switchToAbandonGame();
        break;
      case N:
        MessageList.clear();
        yn = false;
        break;
    }
  }

  /**
   * Perform an action based on the player's input
   *
   * The action depends on what "mode" the game is in - normal play, or the abandon question
   *
   * @param input The input from the view
   */
  @Override
  public void handleInput(Input input) {
    if (yn) {
      handleAbandonInput(input);
    } else {
      handleGameInput(input);
    }
  }

  /**
   * Start the game
   *
   * Resets various bits of game state so that it's a new game
   */
  @Override
  public void init(View view) {
    MessageList.clear();
    this.view = (GameView) view;
    setUpModels();
    this.view.setModels(this.dungeon, this.player);
  }

}
