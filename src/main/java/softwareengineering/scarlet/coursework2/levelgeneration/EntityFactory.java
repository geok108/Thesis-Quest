package softwareengineering.scarlet.coursework2.levelgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.ExitItem;
import softwareengineering.scarlet.coursework2.models.GoldItem;
import softwareengineering.scarlet.coursework2.models.HealthItem;
import softwareengineering.scarlet.coursework2.models.StairsDownItem;
import softwareengineering.scarlet.coursework2.models.StairsUpItem;
import softwareengineering.scarlet.coursework2.models.StrengthItem;

/**
 * Used to generate entity items over appropriate distributions for the difficulty of a level.
 */
public class EntityFactory {
  protected static final int NUM_ENTITIES_PER_LEVEL = 5;

  /**
   * Generate gold for the level.
   *
   * Gold is generated on a level in proportion to its level number (+1 for zero base). E.g. level 1
   * gets half as much gold as level 2 and a third as much as level 3.
   *
   * @param levelNumber The index of the level (0-based)
   * @param totalLevels Total count of levels
   * @param totalGold Total gold to appear in entire dungeon
   * @return A list of GoldItems to populate the level with.
   */
  protected static List<GoldItem> generateGold(int levelNumber, int totalLevels, int totalGold) {
    int n = levelNumber + 1;
    double nTotal = totalLevels * (totalLevels + 1) / 2; // the nth triangle number, equal to
                                                         // summing all level numbers
    int goldInLevel = (int) Math.ceil((n / nTotal) * totalGold);

    List<GoldItem> result = new ArrayList<GoldItem>();

    for (int i = 0; i < goldInLevel; i++) {
      result.add(new GoldItem());
    }

    return result;
  }

  /**
   * Determine the percentage chance of an entity appearing, between bounds
   *
   * The "from" and "to" determine the starting and ending probabilities as the depth of the dungeon
   * increases - "from" will be at level 0, and "to" will be the final level
   *
   * @param levelNumber The current level to determine a chance for
   * @param totalLevels The maximum number of levels to consider
   * @param from The starting chance of appearing between 0.0 and 1.0
   * @param to The ending chance of appearing between 0.0 and 1.0
   * @param direction True if the chance rises as you go deeper into the dungeon
   * @return The chance of appearing, between 0.0 and 1.0
   */
  protected static float generateChance(int levelNumber, int totalLevels, float from, float to) {
    // Pin min/max values
    to = to > 1 ? 1 : to;
    from = from > 1 ? 1 : from;
    to = to < 0 ? 0 : to;
    from = from < 0 ? 0 : from;

    // Determine gradient
    float gradient = (Math.max(from, to) - Math.min(from, to)) / (totalLevels - 1);

    // Determine direction of gradient
    if (from > to) {
      gradient *= -1;
    }

    // Turn the parameters into the line equation and solve for the level number
    return from + (gradient * levelNumber);
  }

  /**
   * Ranges from a 7/8 chance to a 0% chance of getting a health item
   *
   * @param levelNumber Current level
   * @param totalLevels Total number of levels to consider
   * @return Chance of return a health item for this level, between 0.0 and 1.0
   */
  protected static float generateHealthChance(int levelNumber, int totalLevels) {
    return generateChance(levelNumber, totalLevels, 0.875f, 0);
  }

  /**
   * Ranges from a 0% chance to a 7/8 chance of getting a monster spawn
   *
   * @param levelNumber Current level
   * @param totalLevels Total number of levels to consider
   * @return Chance of return a health item for this level, between 0.0 and 1.0
   */
  protected static float generateMonsterChance(int levelNumber, int totalLevels) {
    return generateChance(levelNumber, totalLevels, 0, 0.875f);
  }

  /**
   * Ranges from a 0% chance to a 1/8 chance of getting a strength one item, but only considers the
   * first half of the levels.
   *
   * @param levelNumber Current level
   * @param totalLevels Total number of levels to consider
   * @return Chance of return a health item for this level, between 0.0 and 1.0
   */
  protected static float generateStrengthOneChance(int levelNumber, int totalLevels) {
    // Ranges between 25% and 0%, but only for the first half of the levels
    if (levelNumber >= totalLevels / 2) {
      return 0;
    }

    return generateChance(levelNumber, (totalLevels / 2) + 1, 0.125f, 0);
  }

  /**
   * Ranges from a 0% chance to a 1/8 chance of getting a strength two item, rising and then
   * falling.
   *
   * @param levelNumber Current level
   * @param totalLevels Total number of levels to consider
   * @return Chance of return a health item for this level, between 0.0 and 1.0
   */
  protected static float generateStrengthTwoChance(int levelNumber, int totalLevels) {
    if (levelNumber <= totalLevels / 2) {
      return generateChance(levelNumber, totalLevels / 2, 0, 0.125f);
    } else {
      return generateChance(levelNumber - (totalLevels / 2), totalLevels / 2, 0.125f, 0);
    }
  }

  /**
   * Ranges from a 0% chance to a 1/8 chance of getting a strength three item, but only considers
   * the second half of the levels.
   *
   * @param levelNumber Current level
   * @param totalLevels Total number of levels to consider
   * @return Chance of return a health item for this level, between 0.0 and 1.0
   */
  protected static float generateStrengthThreeChance(int levelNumber, int totalLevels) {
    if (levelNumber < totalLevels / 2) {
      return 0;
    }

    int minLevel = levelNumber - ((totalLevels / 2) - 1); // Off by one so the zero point is beyond
                                                          // the range of consideration

    return generateChance(minLevel, totalLevels / 2, 0, 0.125f);
  }

  /**
   * Generate a list of entities, e.g. health and strength
   *
   * @param levelNumber Current level
   * @param totalLevels Total number of levels to consider
   * @return A list of entities appropriate for the level
   */
  protected static List<Entity> generateEntities(int levelNumber, int totalLevels) {
    List<Entity> entities = new ArrayList<Entity>();

    // Make cumulative chance boundaries using the functions for each entity type
    // The total chance of all the entities should equal 1
    float healthLevel = generateHealthChance(levelNumber, totalLevels);
    float strengthOneLevel = healthLevel + generateStrengthOneChance(levelNumber, totalLevels);
    float strengthTwoLevel = strengthOneLevel + generateStrengthTwoChance(levelNumber, totalLevels);
    float strengthThreeLevel =
        strengthTwoLevel + generateStrengthThreeChance(levelNumber, totalLevels);

    float choice;
    Random random = new Random();

    // For each "slot" in a level, randomly pick an entity
    for (int i = 0; i < NUM_ENTITIES_PER_LEVEL; i++) {
      choice = random.nextFloat();
      if (choice < healthLevel) {
        entities.add(new HealthItem());
      } else if (choice < strengthOneLevel) {
        entities.add(new StrengthItem(1, CellType.STRENGTH1));
      } else if (choice < strengthTwoLevel) {
        entities.add(new StrengthItem(2, CellType.STRENGTH2));
      } else if (choice < strengthThreeLevel) {
        entities.add(new StrengthItem(3, CellType.STRENGTH3));
      } else {
        entities.add(new MonsterSpawner());
      }
    }

    return entities;
  }

  /**
   * Make the stairs up, stairs down and exit entities
   *
   * @param levelNumber Current level
   * @param totalLevels Total levels in the dungeon
   * @return A combination of StairsUpItem, StairsDownItem and ExitItem
   */
  protected static List<Entity> generateStairs(int levelNumber, int totalLevels) {
    List<Entity> entities = new ArrayList<Entity>();

    if (levelNumber != 0) {
      entities.add(new StairsUpItem());
    }
    if (levelNumber == totalLevels - 1) {
      entities.add(new ExitItem());
    } else {
      entities.add(new StairsDownItem());
    }

    return entities;
  }

  /**
   * Generate all non-character entities for the dungeon
   *
   * @param levelNumber The depth of the level
   * @param totalLevels The total number of levels in the dungeon
   * @return A list of items with which to populate the map
   */
  public static List<Entity> generateItems(int levelNumber, int totalLevels, int totalGold) {
    List<Entity> entities = new ArrayList<Entity>();

    // First, add the gold items, which vary based on the total amount of gold in the dungeon
    entities.addAll(generateGold(levelNumber, totalLevels, totalGold));

    // Next, randomly choose health and strength items
    entities.addAll(generateEntities(levelNumber, totalLevels));

    // Finally add "stairs up" and "stairs down"
    entities.addAll(generateStairs(levelNumber, totalLevels));

    return entities;
  }
}
