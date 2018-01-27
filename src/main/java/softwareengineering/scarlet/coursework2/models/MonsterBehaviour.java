package softwareengineering.scarlet.coursework2.models;

/**
 * Defines behaviour of monsters within the level
 */
public interface MonsterBehaviour {
  /**
   * Perform an action for the monster based on the state of the dungeon and the player
   *
   * @param monster The monster to take an action
   * @param dungeon The current dungeon
   * @param player The current player
   */
  public void performAction(Monster monster, Dungeon dungeon, Player player);
}
