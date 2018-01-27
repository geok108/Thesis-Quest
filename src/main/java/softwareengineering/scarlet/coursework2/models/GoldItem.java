package softwareengineering.scarlet.coursework2.models;

/**
 * A "Gold" entity, represented in the game as a page, is collected by the player to exit the
 * dungeon.
 */
public class GoldItem extends Entity {
  public GoldItem() {
    this.type = CellType.GOLD;
  }
}
