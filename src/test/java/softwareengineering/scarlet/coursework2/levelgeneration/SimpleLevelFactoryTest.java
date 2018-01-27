package softwareengineering.scarlet.coursework2.levelgeneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.levelgeneration.SimpleLevelFactory;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.Level;
import softwareengineering.scarlet.coursework2.models.Room;

public class SimpleLevelFactoryTest {
  @Test
  public void testGenerateLevel() {
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());

    assertEquals(level.getRooms().size(), 1);

    Room room = level.getRooms().get(0);

    assertTrue(room.getWidth() < level.getWidth());
    assertTrue(room.getHeight() < level.getHeight());
    assertTrue(room.getX() > 0);
    assertTrue(room.getY() > 0);
  }

  @Test
  public void testLevelGrid() {
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    CellType[][] grid = level.getGrid();

    assertEquals(grid[0][0], CellType.VOID);
    assertEquals(grid[2][2], CellType.ROOM);
  }
}
