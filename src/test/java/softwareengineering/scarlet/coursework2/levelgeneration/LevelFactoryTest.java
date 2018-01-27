package softwareengineering.scarlet.coursework2.levelgeneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Corridor;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.ExitItem;
import softwareengineering.scarlet.coursework2.models.GoldItem;
import softwareengineering.scarlet.coursework2.models.HealthItem;
import softwareengineering.scarlet.coursework2.models.Level;
import softwareengineering.scarlet.coursework2.models.Room;
import softwareengineering.scarlet.coursework2.models.StairsDownItem;
import softwareengineering.scarlet.coursework2.models.StairsUpItem;
import softwareengineering.scarlet.coursework2.models.StrengthItem;

public class LevelFactoryTest {
  @Test
  public void testMakeRoom() {
    int x = 0;
    int y = 0;
    int width = 50;
    int height = 50;

    Room room = LevelFactory.makeRoom(x, y, width, height);

    // Test the room is within the specified bounds
    assertTrue("Width too big: " + room.getWidth(), room.getWidth() < width);
    assertTrue("Height too big: " + room.getHeight(), room.getHeight() < height);
    assertTrue("X too small: " + room.getX(), room.getX() > x);
    assertTrue("Y too small: " + room.getY(), room.getY() > y);
    assertTrue("X too big: " + room.getX(), room.getX() < x + width);
    assertTrue("Y too big: " + room.getY(), room.getY() < y + height);

    // Test that the room crosses the midpoint (ie to guarantee corridor placement)
    assertTrue(room.getX() < width / 2);
    assertTrue(room.getX2() > width / 2);
    assertTrue(room.getY() < height / 2);
    assertTrue(room.getY2() > height / 2);
  }

  @Test
  public void testFinalMakeNode() {
    int x = 0;
    int y = 0;
    int width = 8;
    int height = 8;

    Direction direction = Direction.HORIZONTAL;
    Leaf leaf = LevelFactory.makeNode(x, y, width, height, direction);

    assertEquals(leaf.getRooms().size(), 1);

    Room room = leaf.getRooms().get(0);

    // Is the generated room within the leaf?
    assertTrue("Room X " + room.getX() + " <= 0", room.getX() > leaf.getX());
    assertTrue(room.getY() > leaf.getY());
    assertTrue(room.getX() < leaf.getX() + leaf.getWidth());
    assertTrue(room.getY() < leaf.getY() + leaf.getHeight());
  }

  @Test
  public void testMakeCorridor() {
    int width = 100;
    int height = 100;

    Level level = TwoRoomLevelFactory.generateLevel(width, height, new ArrayList<Entity>());
    Corridor corridor = level.getCorridors().get(0);

    assertEquals(2, level.getRooms().size());
    // These values are slightly odd because of the off-by-1 error of using "width" as a coordinate
    // in a zero-based system
    assertEquals((width / 2) - 2, corridor.getX1());
    assertEquals((width / 2) + 1, corridor.getX2());
  }

  @Test
  public void testTreeMakeNode() {
    int x = 0;
    int y = 0;
    int width = 1000;
    int height = 1000;

    Direction direction = Direction.HORIZONTAL;
    Leaf leaf = LevelFactory.makeNode(x, y, width, height, direction);

    assertTrue(leaf.getRooms().size() > 1);
    assertEquals("Not the right number of corridors", leaf.getRooms().size() - 1,
        leaf.getCorridors().size());
  }

  @Test
  public void testFindMatchTwoRooms() {
    Room roomA = new Room(0, 0, 10, 1);
    Room roomB = new Room(5, 2, 10, 1);

    List<Room> sideA = Arrays.asList(roomA);
    List<Room> sideB = Arrays.asList(roomB);

    List<Integer> matches = LevelFactory.findMatch(sideA, sideB, Direction.HORIZONTAL);

    assertEquals(5, matches.size());
  }

  @Test
  public void testFindMatchThreeRooms() {
    Room roomA = new Room(4, 0, 10, 1);
    Room roomB = new Room(0, 2, 8, 1);
    Room roomC = new Room(11, 2, 8, 1);

    List<Room> sideA = Arrays.asList(roomA);
    List<Room> sideB = Arrays.asList(roomB, roomC);

    List<Integer> matches = LevelFactory.findMatch(sideA, sideB, Direction.HORIZONTAL);

    assertEquals(7, matches.size());
  }

  @Test
  public void testGenerateLevel() {
    int width = 100;
    int height = 100;
    Level level = LevelFactory.generateLevel(width, height);
    assertTrue(level.getRooms().size() > 0);
    assertTrue(level.getCorridors().size() > 0);
  }

  @Test
  public void testNoOverlap() {
    int width = 100;
    int height = 100;

    Level level = LevelFactory.generateLevel(width, height);

    CellType[][] grid = new CellType[width][height];

    for (Room room : level.getRooms()) {
      for (int x = room.getX(); x <= room.getX2(); x++) {
        for (int y = room.getY(); y <= room.getY2(); y++) {
          grid[x][y] = CellType.ROOM;
        }
      }
    }

    for (Corridor corridor : level.getCorridors()) {
      for (int x = corridor.getX1(); x <= corridor.getX2(); x++) {
        for (int y = corridor.getY1(); y <= corridor.getY2(); y++) {
          assertFalse(grid[x][y] == CellType.ROOM);
        }
      }
    }
  }

  @Test
  public void testEntityPlacement() {
    List<Entity> entities = new ArrayList<Entity>();

    entities.add(new GoldItem());
    entities.add(new HealthItem());
    entities.add(new StrengthItem(1, CellType.STRENGTH1));
    entities.add(new StairsUpItem());
    entities.add(new StairsDownItem());
    entities.add(new ExitItem());

    Level level = LevelFactory.generateLevel(50, 50, entities);

    assertEquals(entities.size(), level.getEntities().size());
  }

  @Test
  public void testNotEnoughRoomsForEntities() {
    List<Entity> entities = Arrays.asList(new GoldItem(), new GoldItem(), new GoldItem());

    Level level = TwoRoomLevelFactory.generateLevel(20, 20, entities);

    assertEquals(entities.size(), level.getEntities().size());
  }

  // Monsters are generated
  // No MonsterSpawners remain
}
