package softwareengineering.scarlet.coursework2.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import softwareengineering.scarlet.coursework2.controllers.MoveDirection;
import softwareengineering.scarlet.coursework2.controllers.Pair;

/**
 * Implementation of monster's actions depending on the player's position
 *
 * Only works with DemoMonster
 */
public class MonsterBehaviourType1 implements MonsterBehaviour {
  /**
   * Implementation of monster's actions depending on the player's position
   *
   * @param monster, dungeon, player
   */
  @Override
  public void performAction(Monster monster, Dungeon dungeon, Player player) {
    DemoMonster myMonster = (DemoMonster) monster;
    MoveDirection moveDirection;

    if (isMonsterInRoomWithPlayer(myMonster, player, dungeon.getCurrentLevel())) {
      myMonster.setHunt(true);
    } else {
      myMonster.setHunt(false);
    }

    if (myMonster.isHunt()) {
      MessageList.removeDuplicateMessages();
      MessageList.addMessage("A monster is hunting you!");
      moveDirection = huntPlayer(myMonster, player);
    } else {
      moveDirection = idleMovement();
    }

    Pair movePair = moveMap.get(moveDirection);
    int targetX = myMonster.getX() + movePair.getX();
    int targetY = myMonster.getY() + movePair.getY();

    CellType targetCellType = dungeon.getCurrentLevel().getTypeAtPos(targetX, targetY);

    switch (targetCellType) {
      case ROOM:
      case GOLD:
      case HEALTH:
      case STRENGTH1:
      case STRENGTH2:
      case STRENGTH3:
        if (targetX == player.getX() && targetY == player.getY()) {
          // if player and monster are on same tile don't move
          fightPlayer(player, myMonster);
        } else {
          myMonster.move(movePair.getX(), movePair.getY());
        }
        break;

      default:
        break;
    }
  }

  /**
   * Monster fights player if the player is one tile away
   *
   * @param player The player object
   * @param monster The monster object
   */
  public void fightPlayer(Player player, Monster monster) {
    player.decreaseHealthPoint(monster.getStrength());

    MessageList.addMessage("You got hit by the monster! Damage: " + -monster.getStrength());

    if (player.healthPoints <= 0) {
      MessageList.addMessage("You were killed by a Monster. Game Over!");
    }
  }

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

  /**
   * Implementation of the monster's idle state. The monster moves randomly in the room when it is
   * not in hunt state
   */
  private static MoveDirection idleMovement() {
    Random rand = new Random();
    int randomNumber = rand.nextInt(4);
    switch (randomNumber) {
      case 0:
        return MoveDirection.UP;
      case 1:
        return MoveDirection.DOWN;
      case 2:
        return MoveDirection.LEFT;
      default:
        return MoveDirection.RIGHT;
    }
  }

  /**
   * Are the player and the monster in the same room?
   */
  private static boolean isMonsterInRoomWithPlayer(DemoMonster monster, Player player, Level level) {
    return level.getRoomAtPos(player.getX(), player.getY()) == level.getRoomAtPos(monster.getX(), monster.getY());
  }

  /**
   * Implementation of the player hunting algorithm
   *
   * @param monster The monster object
   * @param player The player object
   */
  private static MoveDirection huntPlayer(Monster monster, Player player) {
    int dx = monster.getX() - player.getX(), dy = monster.getY() - player.getY();
    int nx = Math.abs(dx), ny = Math.abs(dy);
    int sign_x = dx > 0 ? 1 : -1, sign_y = dy > 0 ? 1 : -1;
    int ix = 0, iy = 0;

    if ((0.5 + ix) / nx < (0.5 + iy) / ny) {
      // next step is horizontal
      if (sign_x == 1) {
        return MoveDirection.LEFT;
      } else {
        return MoveDirection.RIGHT;
      }
    } else {
      // next step is vertical
      if (sign_y == 1) {
        return MoveDirection.UP;
      } else {
        return MoveDirection.DOWN;
      }
    }
  }
}
