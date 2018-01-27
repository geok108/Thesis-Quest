package softwareengineering.scarlet.coursework2.levelgeneration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import softwareengineering.scarlet.coursework2.models.Corridor;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.Level;
import softwareengineering.scarlet.coursework2.models.Room;

public class LevelFactory {
  private static int minRoomSize = 5;

  /**
   * Utility method to return the width or height of a room as a set of integers
   *
   * @param room The room to analyse
   * @param direction The side will be parallel to the direction
   * @return The integers representing the width or height of the room
   */
  private static Set<Integer> roomSideRange(Room room, Direction direction) {
    int start = direction == Direction.HORIZONTAL ? room.getX() : room.getY();
    int stop = direction == Direction.HORIZONTAL ? room.getWidth() : room.getHeight();

    Set<Integer> side = new HashSet<Integer>();
    for (int i = start; i < stop + start; i++) {
      side.add(i);
    }

    return side;
  }

  /**
   * Given two sets of rooms and a direction, find points that are found in both sets
   *
   * Slightly more clearly: imagine two rooms next to each other, overlapping but offset. This
   * method will return the points where you could draw a straight line, perpendicular to the rooms,
   * that pass through both rooms at 90 degrees.
   *
   * @param sideA Rooms on one side
   * @param sideB Rooms on the other side
   * @param direction The direction of the line between the sides
   * @return List of matches x's or y's
   */
  public static List<Integer> findMatch(List<Room> sideA, List<Room> sideB, Direction direction) {
    Set<Integer> setA = new HashSet<Integer>();
    Set<Integer> setB = new HashSet<Integer>();

    // Find all possibilities in side A
    for (Room room : sideA) {
      setA.addAll(roomSideRange(room, direction));
    }

    // Find all possibilities in side B
    for (Room room : sideB) {
      setB.addAll(roomSideRange(room, direction));
    }

    // Find those that match
    setA.retainAll(setB);

    // Turn the set into an array before returning it
    return new ArrayList<Integer>(setA);
  }

  /**
   * Make a corridor connecting two adjacent leaves
   *
   * @param leafA The first leaf
   * @param leafB The second leaf
   * @param direction The direction of the line between the leaves
   * @return A corridor object connecting the leaves
   */
  protected static Corridor makeCorridor(Leaf leafA, Leaf leafB, Direction direction) {
    // Will replace this later, hate having randomness when testing
    Random random = new Random();

    List<Integer> matches = findMatch(leafA.getRooms(), leafB.getRooms(), direction);

    int choice = matches.get(random.nextInt(matches.size()));

    if (direction == Direction.HORIZONTAL) {
      // Order leaves correctly
      Leaf top = leafA.getY() > leafB.getY() ? leafB : leafA;
      Leaf bottom = leafA.getY() > leafB.getY() ? leafA : leafB;

      // Figure out correct x's and y's
      int x1 = choice;
      int y1 = top.findMaxYAtX(choice) + 1;
      int x2 = choice;
      int y2 = bottom.findMinYAtX(choice) - 1;

      return new Corridor(x1, y1, x2, y2);
    } else {
      // Order leaves correctly
      Leaf left = leafA.getX() > leafB.getX() ? leafB : leafA;
      Leaf right = leafA.getX() > leafB.getX() ? leafA : leafB;

      // Figure out correct x's and y's
      int x1 = left.findMaxXAtY(choice) + 1;
      int y1 = choice;
      int x2 = right.findMinXAtY(choice) - 1;
      int y2 = choice;

      return new Corridor(x1, y1, x2, y2);
    }
  }

  /**
   * Make a room object within a defined space
   *
   * @param x The x co-ordinate of the top left of the space
   * @param y The y co-ordinate of the top left of the space
   * @param width The width of the space
   * @param height The height of the space
   * @return A room object somewhere within the space
   */
  public static Room makeRoom(int x, int y, int width, int height) {
    // Will replace this later, hate having randomness when testing
    Random random = new Random();

    // We'll add a boundary of 1 space around the edge (for an imaginary wall)
    int maxWidth = width - 2;
    int maxHeight = height - 2;

    // Due to the wall, the minimum width/height will be equal to a half-plus-one - this way two
    // rooms next to each other will guarantee an overlap
    int minWidth = (width / 2) + 1;
    int minHeight = (height / 2) + 1;

    int widthDifference = maxWidth - minWidth;
    int heightDifference = maxHeight - minHeight;

    // Choose a width and height
    int roomWidth = widthDifference > 0 ? random.nextInt(widthDifference) + minWidth : minWidth;
    int roomHeight =
        heightDifference > 0 ? random.nextInt(heightDifference) + minHeight : minHeight;

    // Determine range for room placement within the leaf
    int maxX = width - roomWidth;
    int maxY = height - roomHeight;

    // Choose an x and a y! +1 for the wall
    int roomX = random.nextInt(maxX) + x + 1;
    int roomY = random.nextInt(maxY) + y + 1;

    return new Room(roomX, roomY, roomWidth, roomHeight);
  }

  /**
   * Make a new level in the binary tree out of a given space
   *
   * @param x The x co-ordinate of the top left of the space
   * @param y The y co-ordinate of the top left of the space
   * @param width The width of the space
   * @param height The height of the space
   * @param direction The direction in which to split the space
   * @return A leaf object representing the space
   */
  public static Leaf makeNode(int x, int y, int width, int height, Direction direction) {
    if ((direction == Direction.VERTICAL && width <= minRoomSize * 2)
        || (direction == Direction.HORIZONTAL && height <= minRoomSize * 2)) {
      Leaf leaf = new Leaf(x, y, width, height);
      Room room = makeRoom(x, y, width, height);
      leaf.getRooms().add(room);
      return leaf;
    }

    // Will replace this later, hate having randomness when testing
    Random random = new Random();

    // Slightly different depending on direction!
    int toSplit = (direction == Direction.HORIZONTAL) ? height : width;

    // Only choose somewhere between the two quartiles, setting a hard lower/upper limit on min
    // height
    int min = toSplit / 4;
    min = min >= minRoomSize ? min : minRoomSize;
    int max = toSplit - min;

    int split = random.nextInt(max - min) + min;

    Direction newDirection =
        direction == Direction.HORIZONTAL ? Direction.VERTICAL : Direction.HORIZONTAL;

    // Make the two leaves - this will recurse until it reaches the end
    Leaf leafA = makeNode(x, y, direction == Direction.HORIZONTAL ? width : split,
        direction == Direction.HORIZONTAL ? split : height, newDirection);

    Leaf leafB = makeNode(direction == Direction.HORIZONTAL ? x : x + split,
        direction == Direction.HORIZONTAL ? y + split : y,
        direction == Direction.HORIZONTAL ? width : width - split,
        direction == Direction.HORIZONTAL ? height - split : height, newDirection);

    // Add a corridor between the two leaves
    Corridor corridor = LevelFactory.makeCorridor(leafA, leafB, direction);
    leafA.getCorridors().add(corridor);

    // Merge the two leaves together
    leafA.getRooms().addAll(leafB.getRooms());
    leafA.getCorridors().addAll(leafB.getCorridors());

    return leafA;
  }

  /**
   * Place a single entity in a room in a level.
   *
   * @param level The level in which to place the entity
   * @param room The room (ie the space) in which to place the entity
   * @param entity The entity to place
   */
  public static void placeObjectInLevel(Level level, Room room, Entity entity) {
    // Needs replacing with shared Random
    Random random = new Random();

    boolean placed = false;
    int x = 0;
    int y = 0;

    while (!placed) {
      // Generate a new position
      x = random.nextInt(room.getWidth()) + room.getX();
      y = random.nextInt(room.getHeight()) + room.getY();
      placed = true;

      // Check other entities in the level for clashes
      for (Entity otherEntity : level.getEntities()) {
        if (otherEntity.getX() == x && otherEntity.getY() == y) {
          placed = false;
          break;
        }
      }
    }

    // Actually set the position of the entity
    entity.setPosition(x, y);
    level.getEntities().add(entity);
  }

  /**
   * Place entities on a level
   *
   * @param level The level on which to place the entities
   * @param entities The list of entities to place
   * @return The original level
   */
  public static Level placeObjects(Level level, List<Entity> entities) {
    List<Room> rooms = new ArrayList<Room>(level.getRooms());
    List<Entity> toPlace = new ArrayList<Entity>(entities);
    Room room;
    Collections.shuffle(toPlace);
    Iterator<Room> roomIterator = rooms.iterator();

    // Iterate through the rooms, place an entity in each
    // When there are no rooms left, shuffle the list of rooms and start again
    // Continue until no new entities to place
    while (toPlace.size() > 0) {
      try {
        room = roomIterator.next();
        placeObjectInLevel(level, room, toPlace.remove(0));
      } catch (NoSuchElementException ex) {
        rooms = new ArrayList<Room>(level.getRooms());
        Collections.shuffle(rooms);
        roomIterator = rooms.iterator();
      }
    }

    return level;
  }

  /**
   * Make a level of a given size
   *
   * @param width Width in cells
   * @param height Height in cells
   * @return A new Level containing rooms and corridors
   */
  public static Level generateLevel(int width, int height) {
    return generateLevel(width, height, new ArrayList<Entity>());
  }

  /**
   * Make a level of a given size
   *
   * @param width Width in cells
   * @param height Height in cells
   * @param entities List of objects to place in the dungeon (gold etc)
   * @param entities List of monsters to place in the dungeon
   * @return A new Level containing rooms and corridors
   */
  public static Level generateLevel(int width, int height, List<Entity> entities) {
    Level level = new Level(width, height);
    Leaf root = makeNode(0, 0, width, height, Direction.HORIZONTAL);
    level.getRooms().addAll(root.getRooms());
    level.getCorridors().addAll(root.getCorridors());
    level = placeObjects(level, entities);
    return level;
  }
}
