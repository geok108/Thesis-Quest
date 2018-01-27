package softwareengineering.scarlet.coursework2.models;

/**
 * A health item on the level, collected by the player to regain health
 */
public class HealthItem extends Entity {
  public HealthItem() {
    this.type = CellType.HEALTH;
  }
}
