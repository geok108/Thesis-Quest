package softwareengineering.scarlet.coursework2.models;

/**
 * Base class for monster implementations
 */
public abstract class Monster extends Character {
  protected MonsterBehaviour behaviour;

  /**
   * Create a new monster
   *
   * @param name The "name" of the monster
   * @param startX Starting X coordinate
   * @param startY Starting Y coordinate
   * @param health Starting number of health points
   * @param strength Starting amount of strength
   */
  public Monster(String name, int startX, int startY, int health, int strength) {
    super(name, startX, startY, health, strength);
  }

  /**
   * Perform an action based on the state of the dungeon and the player.
   *
   * Defers this action to the specified MonsterBehaviour class
   *
   * @param dungeon The current dungeon
   * @param player The current player
   */
  public void performAction(Dungeon dungeon, Player player) {
    behaviour.performAction(this, dungeon, player);
  }
}
