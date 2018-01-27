package softwareengineering.scarlet.coursework2.models;

/**
 * A single rectangular room within the level
 */
public class Room {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Create a room of a given position and size
   *
   * @param x The x coordinate of the room
   * @param y The y coordinate of the room
   * @param width The width of the room
   * @param height The height of the room
   */
  public Room(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * The leftmost X coordinate of the room
   *
   * @return The x-coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * The uppermost Y coordinate of the room
   *
   * @return The y-coordinate
   */
  public int getY() {
    return y;
  }

  /**
   * The width of the room
   *
   * @return The width
   */
  public int getWidth() {
    return width;
  }

  /**
   * The height of the room
   *
   * @return The height
   */
  public int getHeight() {
    return height;
  }

  /**
   * The rightmost x-coordinate of the room
   *
   * @return The x-coordinate
   */
  public int getX2() {
    return x + width - 1;
  }

  /**
   * The lowermost y-coordinate of the room
   *
   * @return The y-coordinate
   */
  public int getY2() {
    return y + height - 1;
  }
}
