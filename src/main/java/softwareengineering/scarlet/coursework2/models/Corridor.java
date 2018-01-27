package softwareengineering.scarlet.coursework2.models;

/**
 * A corridor within a level
 *
 * All corridors should have a width or height of 1, and be a straight line
 */
public class Corridor {
  private int x1;
  private int y1;
  private int x2;
  private int y2;

  /**
   * Create a new corridor of a given size and position
   *
   * @param x1 The leftmost x coordinate of the corridor
   * @param y1 The uppermost y coordinate of the corridor
   * @param x2 The rightmost x coordinate of the corridor
   * @param y2 The lowermost y coordinate of the corridor
   */
  public Corridor(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }

  /**
   * The leftmost X coordinate of the corridor
   *
   * @return The x coordinate
   */
  public int getX1() {
    return x1;
  }

  /**
   * The uppermost Y coordinate of the corridor
   *
   * @return The y coordinate
   */
  public int getY1() {
    return y1;
  }

  /**
   * The rightmost X coordinate of the corridor
   *
   * @return The x coordinate
   */
  public int getX2() {
    return x2;
  }

  /**
   * The lowermost Y coordinate of the corridor
   *
   * @return The y coordinate
   */
  public int getY2() {
    return y2;
  }
}
