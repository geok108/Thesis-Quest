package softwareengineering.scarlet.coursework2.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores information about the position of objects within the dungeon.
 *
 * Only stores direct references to rooms and corridors - objects are then stored within these.
 *
 * Use {@code getGrid} to get a 2-dimensional array giving every cell in the entire map.
 *
 * You can use {@code printGrid} to dump the map to the console.
 */
public class Level {
  private ArrayList<Room> rooms;
  private ArrayList<Corridor> corridors;
  private List<Entity> entities;
  private List<Monster> monsters;
  private int width;
  private int height;

  public Level(int width, int height) {
    this.rooms = new ArrayList<Room>();
    this.corridors = new ArrayList<Corridor>();
    this.entities = new ArrayList<Entity>();
    this.monsters = new ArrayList<Monster>();
    this.width = width;
    this.height = height;
  }

  /**
   * Get the list of rooms within the level.
   *
   * The ordering of this list is not consistent and may change.
   *
   * @return The list of rooms
   */
  public ArrayList<Room> getRooms() {
    return rooms;
  }

  /**
   * Get the list of corridors within the level.
   *
   * The ordering of this list is not consistent and may change.
   *
   * @return The list of corridors
   */
  public ArrayList<Corridor> getCorridors() {
    return corridors;
  }

  /**
   * Get the list of entities within the level.
   *
   * The ordering of this list is not consistent and may change.
   *
   * @return The list of entities
   */
  public List<Entity> getEntities() {
    return entities;
  }

  /**
   * Get the list of monsters within the level.
   *
   * The ordering of this list is not consistent and may change.
   *
   * @return The list of monsters
   */
  public List<Monster> getMonsters() {
    return monsters;
  }

  /**
   * Return a two dimensional representation of the level
   *
   * @return A two dimensional array, width first then height
   */
  public CellType[][] getGrid() {
    /**
     * Return a 2-dimensional grid representing the dungeon.
     */
    CellType[][] grid = new CellType[width][height];

    // Fill the map in with void
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid[x][y] = CellType.VOID;
      }
    }

    // Draw rooms
    for (Room room : getRooms()) {
      // The actual room
      for (int x = room.getX(); x <= room.getX2(); x++) {
        for (int y = room.getY(); y <= room.getY2(); y++) {
          grid[x][y] = CellType.ROOM;
        }
      }
    }

    // Draw corridors
    for (Corridor corridor : getCorridors()) {
      for (int x = corridor.getX1(); x <= corridor.getX2(); x++) {
        for (int y = corridor.getY1(); y <= corridor.getY2(); y++) {
          grid[x][y] = CellType.CORRIDOR;
        }
      }
    }

    // Draw entities
    for (Entity entity : getEntities()) {
      grid[entity.getX()][entity.getY()] = entity.type;
    }

    // Draw monsters
    for (Monster monster : getMonsters()) {
      grid[monster.getX()][monster.getY()] = CellType.MONSTER;
    }

    return grid;
  }

  /**
   * The width of the level
   *
   * @return The width of the level in cells, zero based
   */
  public int getWidth() {
    return width;
  }

  /**
   * The height of the level
   *
   * @return The height of the level in cells, zero based
   */
  public int getHeight() {
    /**
     * The height of the dungeon in cells, zero based
     */
    return height;
  }

  /**
   * What kind of thing is at grid position x, y?
   *
   * @param x Position of where we're looking
   * @param y Position of where we're looking
   * @return The type of cell at that point
   */
  public CellType getTypeAtPos(int x, int y) {
    if (x >= width || y >= height || x <= -1 || y <= -1) {
      return CellType.VOID;
    }

    return getGrid()[x][y];
  }

  /**
   * Find the entity at grid position x, y
   *
   * @param x Position of where we're looking
   * @param y Position of where we're looking
   * @return The entity at that position, or null if not found
   */
  public Entity getEntityAtPos(int x, int y) {
    // Iterate through the list of entities, breaking if the entity's position matches the given x
    // and y
    for (Entity entity : getEntities()) {
      if (entity.getX() == x && entity.getY() == y) {
        return entity;
      }
    }

    return null;
  }

  /**
   * Find the monster at grid position x, y
   *
   * @param x Position of where we're looking
   * @param y Position of where we're looking
   * @return The monster at that position, or null if not found
   */
  public Monster getMonsterAtPos(int x, int y) {
    for (Monster monster : getMonsters()) {
      if (monster.getX() == x && monster.getY() == y) {
        return monster;
      }
    }

    return null;
  }


  /**
   * Find the room at grid position x, y
   *
   * @param x Position of where we're looking
   * @param y Position of where we're looking
   * @return The room at that position, or null if not found
   */
  public Room getRoomAtPos(int x, int y) {
    for (Room room : getRooms()) {
      if ((x <= room.getX2() && x >= room.getX())
          && (y <= room.getY2() && y >= room.getY())) {
        return room;
      }
    }

    return null;
  }

  /**
   * Dump a textual representation of the map to the console, for debugging.
   *
   * Be careful with large maps!
   *
   * Note that the default font is not square, and one character is used per cell, so it will appear
   * stretched.
   */
  public void printGrid() {
    CellType[][] grid = this.getGrid();

    for (int x = 0; x < this.getWidth(); x++) {
      for (int y = 0; y < this.getHeight(); y++) {
        switch (grid[x][y]) {
          case VOID:
            System.out.print(" ");
            break;
          case ROOM:
            System.out.print(".");
            break;
          case CORRIDOR:
            System.out.print("*");
            break;
          case EXIT:
            System.out.print("X");
            break;
          case GOLD:
            System.out.print("G");
            break;
          case HEALTH:
            System.out.print("H");
            break;
          case STAIRSDOWN:
            System.out.print("<");
            break;
          case STAIRSUP:
            System.out.print(">");
            break;
          case STRENGTH1:
            System.out.print("1");
            break;
          case STRENGTH2:
            System.out.print("2");
            break;
          case STRENGTH3:
            System.out.print("3");
            break;
          case MONSTERSPAWNER:
            System.out.print("#");
            break;
          case MONSTER:
            System.out.print("M");
            break;
        }
      }
      System.out.print("\n");
    }
  }

  /**
   * Find an entity of a given type in the level
   *
   * The entity may not be the same for each call
   *
   * @param type The type to search for
   * @return The first occurrence of the type, or null if not found
   */
  private Entity getFirstEntityByType(CellType type) {
    // Iterate through all entities in the level, breaking when one of the specified type is found
    for (Entity entity : this.getEntities()) {
      if (entity.type == type) {
        return entity;
      }
    }

    return null;
  }

  /**
   * Find the "stairs up" entity on the level.
   *
   * If there are multiple "stairs up" entities, one will be returned
   *
   * @return The StairsUpItem on the level, or null if there isn't one
   */
  public Entity getStairsUp() {
    return getFirstEntityByType(CellType.STAIRSUP);
  }

  /**
   * Find the "stairs down" entity on the level.
   *
   * If there are multiple "stairs down" entities, one will be returned
   *
   * @return The StairsDownItem on the level, or null if there isn't one
   */
  public Entity getStairsDown() {
    return getFirstEntityByType(CellType.STAIRSDOWN);
  }
}
