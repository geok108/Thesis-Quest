package softwareengineering.scarlet.coursework2.models;

import java.util.ArrayList;
import java.util.List;
import softwareengineering.scarlet.coursework2.levelgeneration.EntityFactory;
import softwareengineering.scarlet.coursework2.levelgeneration.LevelFactory;
import softwareengineering.scarlet.coursework2.levelgeneration.MonsterFactory;

/**
 * A container class for the current game, most notably holding all of the levels within the
 * dungeon.
 *
 * Should be one instance per play through - when the player starts again, a new dungeon is created.
 */
public class Dungeon {
  public static final int REQUIRED_SCORE = 20;
  protected List<Level> levels;
  private Level currentLevel;
  private int numLevels;
  private int width;
  private int height;

  /**
   * Create a new, empty dungeon of a given size.
   *
   * New levels will be created as needed.
   *
   * @param width The width, in cells, of all levels within the dungeon
   * @param height The height, in cells, of all levels within the dungeon
   * @param numLevels How many levels there should be within the dungeon
   */
  public Dungeon(int width, int height, int numLevels) {
    this.numLevels = numLevels;
    this.width = width;
    this.height = height;
  }

  /**
   * Create a new dungeon with a given list of levels
   *
   * @param width The width, in cells, of all levels within the dungeon
   * @param height The height, in cells, of all levels within the dungeon
   * @param levels A List of levels to hold in the dungeon
   */
  public Dungeon(int width, int height, List<Level> levels) {
    this.levels = levels;
    this.numLevels = levels.size();
    this.width = width;
    this.height = height;
    this.currentLevel = this.levels.get(0);
  }

  /**
   * Generate all the levels for the dungeon.
   *
   * @param width Size of the dungeon in cells
   * @param height Size of the dungeon in cells
   * @param numLevels How many levels are in the dungeon
   * @return The full list of levels, in order of difficulty
   */
  protected static List<Level> generateLevels(int width, int height, int numLevels) {
    List<Level> levels = new ArrayList<Level>();

    for (int i = 0; i < numLevels; i++) {
      // Pass in level information as difficulty is proportional to level
      List<Entity> entities = Dungeon.generateEntities(i, numLevels);

      // Make a level given the generated entities
      Level level = LevelFactory.generateLevel(width, height, entities);

      // Now generate monsters based on the placed entities
      List<Monster> monsters = Dungeon.generateMonsters(level.getEntities(), i, numLevels);
      level.getMonsters().addAll(monsters);

      // Finally add the level to the list of levels
      levels.add(level);
    }

    return levels;
  }

  /**
   * Generates a list of entities to populate a level
   *
   * @param level The depth of the level to be populated - the higher the number, the harder the
   *        level
   * @return A list of entities
   */
  protected static List<Entity> generateEntities(int level, int numLevels) {
    return EntityFactory.generateItems(level, numLevels, REQUIRED_SCORE);
  }

  /**
   * Generates a list of monsters to populate a level
   *
   * @param level The depth of the level to be populated - the higher the number, the harder the
   *        level
   * @return A list of entities
   */
  protected static List<Monster> generateMonsters(List<Entity> entities, int level, int numLevels) {
    return MonsterFactory.generateMonsters(entities, level, numLevels);
  }

  /**
   * @return The current level being played
   */
  public Level getCurrentLevel() {
    if (this.levels == null || this.levels.size() == 0) {
      this.levels = Dungeon.generateLevels(this.width, this.height, this.numLevels);
      this.currentLevel = this.levels.get(0);
    }
    return this.currentLevel;
  }

  /**
   * Drop the player to the next lowest level
   *
   * @return The new level
   */
  public Level goDown() {
    int currentIndex = this.levels.indexOf(this.getCurrentLevel());
    this.currentLevel = levels.get(currentIndex + 1);
    return this.getCurrentLevel();
  }

  /**
   * Raise the player to the next highest level
   *
   * @return The new level
   */
  public Level goUp() {
    int currentIndex = this.levels.indexOf(this.getCurrentLevel());
    this.currentLevel = levels.get(currentIndex - 1);
    return this.getCurrentLevel();
  }

  /**
   * Get the list of levels within the dungeon
   *
   * @return A list of levels
   */
  public List<Level> getLevels() {
    return levels;
  }
}
