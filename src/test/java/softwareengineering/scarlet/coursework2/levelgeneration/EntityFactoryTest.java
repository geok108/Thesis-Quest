package softwareengineering.scarlet.coursework2.levelgeneration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.List;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.ExitItem;
import softwareengineering.scarlet.coursework2.models.StairsDownItem;
import softwareengineering.scarlet.coursework2.models.StairsUpItem;

public class EntityFactoryTest {

  @Test
  public void testGenerateGold_checkSum() {
    int totalLevels = 6;
    int targetGold = 100;
    int totalGold = 0;

    for (int level = 0; level < totalLevels; level++) {
      totalGold += EntityFactory.generateGold(level, totalLevels, targetGold).size();
    }

    // Test that the resulting gold is greater or equal to the amount we asked for
    assertTrue(totalGold >= targetGold);

    // Test that the resulting gold is less than the total possible rounding error - equal to the
    // number of levels, as each can be "wrong" by a max of 1
    assertTrue(totalGold <= targetGold + totalLevels);
  }

  @Test
  public void testGenerateGold_distribution() {
    int totalLevels = 6;
    int targetGold = 100;

    int amountPreviousLevel = EntityFactory.generateGold(0, totalLevels, targetGold).size();

    for (int level = 1; level < totalLevels; level++) {
      int amountThisLevel = EntityFactory.generateGold(level, totalLevels, targetGold).size();

      assertEquals(amountThisLevel / amountPreviousLevel, (level + 1) / level);

      amountPreviousLevel = amountThisLevel;
    }
  }

  @Test
  public void testGenerateChance_generation() {
    double chance = EntityFactory.generateChance(0, 5, 0, 100);

    assertTrue(chance >= 0);
    assertTrue(chance <= 1);
  }

  @Test
  public void testGenerateChance_positiveProgression() {
    int totalLevels = 5;
    double previousChance = EntityFactory.generateChance(0, totalLevels, 0, 100);
    double chance;

    for (int level = 1; level < totalLevels; level++) {
      chance = EntityFactory.generateChance(level, totalLevels, 0, 100);
      assertTrue(chance > previousChance);
    }
  }

  @Test
  public void testGenerateChance_negativeProgression() {
    int totalLevels = 5;
    double previousChance = EntityFactory.generateChance(0, totalLevels, 100, 0);
    double chance;

    for (int level = 1; level < totalLevels; level++) {
      chance = EntityFactory.generateChance(level, totalLevels, 100, 0);
      assertTrue(chance < previousChance);
    }
  }

  @Test
  public void testGenerateChange_maxChanceAtTop() {
    int totalLevels = 5;
    float maxChance = 0.75f;
    float chosenChance = EntityFactory.generateChance(totalLevels - 1, totalLevels, 0, maxChance);
    assertEquals(maxChance, chosenChance, 0);
  }

  @Test
  public void testGenerateChange_maxChanceAtBottom() {
    int totalLevels = 5;
    float maxChance = 0.75f;
    float chosenChance = EntityFactory.generateChance(0, totalLevels, maxChance, 0);
    assertEquals(maxChance, chosenChance, 0);
  }

  @Test
  public void testGenerateChange_minChanceAtTop() {
    int totalLevels = 5;
    float maxChance = 0.75f;
    float chosenChance = EntityFactory.generateChance(totalLevels - 1, totalLevels, maxChance, 0);
    assertEquals(0, chosenChance, 0);
  }

  @Test
  public void testGenerateChange_minChanceAtBottom() {
    int totalLevels = 5;
    float maxChance = 0.75f;
    float chosenChance = EntityFactory.generateChance(0, totalLevels, 0, maxChance);
    assertEquals(0, chosenChance, 0);
  }

  @Test
  public void testGenerateStrengthOneChance() {
    int totalLevels = 6;
    double chance;

    for (int level = 0; level < totalLevels; level++) {
      chance = EntityFactory.generateStrengthOneChance(level, totalLevels);
      if (level >= totalLevels / 2) {
        assertTrue(chance == 0);
      } else {
        assertTrue(chance > 0);
      }
    }
  }

  @Test
  public void testGenerateStrengthThreeChance() {
    int totalLevels = 6;
    double chance;

    for (int level = 0; level < totalLevels; level++) {
      chance = EntityFactory.generateStrengthThreeChance(level, totalLevels);
      if (level < totalLevels / 2) {
        assertTrue(chance == 0);
      } else {
        assertTrue(chance > 0);
      }
    }
  }

  @Test
  public void testGenerateHealthAndStrength() {
    List<Entity> entities = EntityFactory.generateEntities(0, 6);

    assertEquals(EntityFactory.NUM_ENTITIES_PER_LEVEL, entities.size());
  }

  @Test
  public void testGenerateStairs_firstLevel() {
    List<Entity> entities = EntityFactory.generateStairs(0, 6);

    assertEquals(1, entities.size());
    assertEquals(CellType.STAIRSDOWN, entities.get(0).getType());
  }

  @Test
  public void testGenerateStairs_middleLevel() {
    List<Entity> entities = EntityFactory.generateStairs(3, 6);
    StairsDownItem down = null;
    StairsUpItem up = null;

    assertEquals(2, entities.size());

    for (Entity entity : entities) {
      if (entity.getType() == CellType.STAIRSUP) {
        up = (StairsUpItem) entity;
      } else if (entity.getType() == CellType.STAIRSDOWN) {
        down = (StairsDownItem) entity;
      } else {
        fail();
      }
    }

    assertTrue(down != null);
    assertTrue(up != null);
  }

  @Test
  public void testGenerateStairs_finalLevel() {
    List<Entity> entities = EntityFactory.generateStairs(5, 6);
    ExitItem exit = null;
    StairsUpItem up = null;

    assertEquals(2, entities.size());

    for (Entity entity : entities) {
      if (entity.getType() == CellType.STAIRSUP) {
        up = (StairsUpItem) entity;
      } else if (entity.getType() == CellType.EXIT) {
        exit = (ExitItem) entity;
      } else {
        fail();
      }
    }

    assertTrue(exit != null);
    assertTrue(up != null);
  }
}
