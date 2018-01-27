package softwareengineering.scarlet.coursework2.levelgeneration;

import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Entity;

/**
 * A dummy entity used to place monsters on the map during entity placement
 *
 * Placed on the map, and then replaced by actual monsters at the end of level generation, so there
 * shouldn't be any in the final map
 */
public class MonsterSpawner extends Entity {
  public MonsterSpawner() {
    this.type = CellType.MONSTERSPAWNER;
  }
}
