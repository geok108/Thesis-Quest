package softwareengineering.scarlet.coursework2.levelgeneration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import softwareengineering.scarlet.coursework2.models.Corridor;
import softwareengineering.scarlet.coursework2.models.Room;

/**
 * A node in the BSP tree used in level generation. Represents an area of the grid.
 *
 * The nodes won't all be leaves, but the logic only really applies when the BSP is rolled back - so
 * when the logic takes affect it is actually a leaf
 */
public class Leaf {
  private ArrayList<Room> rooms;
  private ArrayList<Corridor> corridors;
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Create a new node in the BSP
   *
   * @param x X position of the area covered by the node
   * @param y Y position of the area covered by the node
   * @param width Width of the area covered by the node
   * @param height Height of the area covered by the node
   */
  public Leaf(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.rooms = new ArrayList<Room>();
    this.corridors = new ArrayList<Corridor>();
  }

  /**
   * Rooms covered by this node
   *
   * @return A list of rooms
   */
  public List<Room> getRooms() {
    return rooms;
  }

  /**
   * Corridors covered by this node
   *
   * @return A list of corridors
   */
  public List<Corridor> getCorridors() {
    return corridors;
  }

  /**
   * @return Far left X coordinate of the area covered by the node
   */
  public int getX() {
    return x;
  }

  /**
   * @return Uppermost Y coordinate of the area covered by the node
   */
  public int getY() {
    return y;
  }

  /**
   * @return Width of the area covered by the node
   */
  public int getWidth() {
    return width;
  }

  /**
   * @return Height of the area covered by the node
   */
  public int getHeight() {
    return height;
  }

  /**
   * @return Far right X coordinate of the area covered by the node
   */
  public int getX2() {
    return x + width;
  }

  /**
   * @return Lowermost Y coordinate of the area covered by the node
   */
  public int getY2() {
    return y + height;
  }

  /**
   * Find the maximum X coordinate covered by a room along a particular Y line
   *
   * Used to figure out corridor placement.
   *
   * @param targetY The Y line along which to search
   * @return The maximum X coordinate
   */
  public int findMaxXAtY(int targetY) {
    // Sort rooms by their far right edge
    getRooms().sort(new Comparator<Room>() {
      public int compare(Room r1, Room r2) {
        return r2.getX2() - r1.getX2();
      }
    });

    // Find the furthest room that sits on the y line
    int result = -1;
    for (Room room : getRooms()) {
      if (room.getY() <= targetY && room.getY2() >= targetY) {
        result = room.getX2();
        break;
      }
    }
    return result;
  }

  /**
   * Find the minimum X coordinate covered by a room along a particular Y line.
   *
   * Used to figure out corridor placement.
   *
   * @param targetY The Y line along which to search
   * @return The minimum X coordinate
   */
  public int findMinXAtY(int targetY) {
    // Sort rooms by their far left edge
    getRooms().sort(new Comparator<Room>() {
      public int compare(Room r1, Room r2) {
        return r1.getX2() - r2.getX2();
      }
    });

    // Find the furthest room that sits on the y line
    int result = -1;
    for (Room room : getRooms()) {
      if (room.getY() <= targetY && room.getY2() >= targetY) {
        result = room.getX();
        break;
      }
    }
    return result;
  }

  /**
   * Find the maximum Y coordinate covered by a room along a particular X line
   *
   * Used to figure out corridor placement.
   *
   * @param targetX The X line along which to search
   * @return The maximum Y coordinate
   */
  public int findMaxYAtX(int targetX) {
    // Sort rooms by bottom edge
    getRooms().sort(new Comparator<Room>() {
      public int compare(Room r1, Room r2) {
        return r2.getY2() - r1.getY2();
      }
    });

    // Find the furthest room that sits on the x line
    int result = -1;
    for (Room room : getRooms()) {
      if (room.getX() <= targetX && room.getX2() >= targetX) {
        result = room.getY2();
        break;
      }
    }
    return result;
  }


  /**
   * Find the minimum Y coordinate covered by a room along a particular X line
   *
   * Used to figure out corridor placement.
   *
   * @param targetX The X line along which to search
   * @return The maximum Y coordinate
   */
  public int findMinYAtX(int targetX) {
    // Sort rooms by top edge
    getRooms().sort(new Comparator<Room>() {
      public int compare(Room r1, Room r2) {
        return r1.getY() - r2.getY();
      }
    });

    // Find the furthest room that sits on the x line
    int result = -1;
    for (Room room : getRooms()) {
      if (room.getX() <= targetX && room.getX2() >= targetX) {
        result = room.getY();
        break;
      }
    }
    return result;
  }
}
