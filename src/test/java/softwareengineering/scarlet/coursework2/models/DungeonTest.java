package softwareengineering.scarlet.coursework2.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;

public class DungeonTest {
  @Test
  public void testGenerateLevels() {
    int numLevels = 6;
    List<Level> levels = Dungeon.generateLevels(50, 50, numLevels);

    assertEquals(numLevels, levels.size());
  }

  @Test
  public void testCurrentLevelFromBlank() {
    int numLevels = 6;
    Dungeon dungeon = new Dungeon(50, 50, numLevels);

    assertEquals(null, dungeon.levels);

    Level level = dungeon.getCurrentLevel();

    assertNotEquals(null, level);
    assertEquals(numLevels, dungeon.levels.size());
    assertSame(level, dungeon.levels.get(0));
  }

  @Test
  public void testGenerateLevels_addMonsters() {
    List<Level> levels = Dungeon.generateLevels(50, 50, 6);

    Level lastLevel = levels.get(5);

    assertTrue(lastLevel.getMonsters().size() > 0);
    assertTrue(lastLevel.getMonsters().get(0).getX() > 0);
    assertTrue(lastLevel.getMonsters().get(0).getY() > 0);
  }
}
