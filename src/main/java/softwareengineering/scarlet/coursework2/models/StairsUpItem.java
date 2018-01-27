package softwareengineering.scarlet.coursework2.models;

/**
 * A "Stairs Up" entity, used by the player to move to the next uppermost level
 */
public class StairsUpItem extends Entity {
  public StairsUpItem() {
    this.type = CellType.STAIRSUP;
  }
}
