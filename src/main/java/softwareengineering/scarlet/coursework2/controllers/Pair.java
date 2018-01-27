package softwareengineering.scarlet.coursework2.controllers;

/**
 * A simple data structure class for holding position information.
 */
public class Pair {
  private int x;
  private int y;

  /**
   * Create a new position
   *
   * @param x X-coordinate
   * @param y Y-coordinate
   */
  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * The X coordinate of the position
   */
  public int getX() {
    return x;
  }

  /**
   * Set the X coordinate of the position
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Get the Y coordinate of the position
   */
  public int getY() {
    return y;
  }

  /**
   * Set the Y coordinate of the position
   */
  public void setY(int y) {
    this.y = y;
  }
}
