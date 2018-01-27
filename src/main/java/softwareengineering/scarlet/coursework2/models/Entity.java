package softwareengineering.scarlet.coursework2.models;

/**
 * Superclass for all static objects in the dungeon
 *
 * eg Gold, Health, Stairs etc
 */
public abstract class Entity {
  private int x;
  private int y;
  protected CellType type;

  /**
   * Returns X position of entity
   *
   * @return X coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * Returns Y position of entity
   *
   * @return Y coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * Set the position of the entity within a level
   *
   * @param x The x coordinate
   * @param y The y coordinate
   */
  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the type of entity
   *
   * @return The enum representing the type of the entity
   */
  public CellType getType() {
    return type;
  }
}
