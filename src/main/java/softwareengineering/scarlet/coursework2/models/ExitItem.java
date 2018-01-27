package softwareengineering.scarlet.coursework2.models;

/**
 * An Exit, on the last level of the dungeon. The ultimate target for the player to reach.
 */
public class ExitItem extends Entity {
  public ExitItem() {
    this.type = CellType.EXIT;
  }
}
