package softwareengineering.scarlet.coursework2.levelgeneration;

import java.util.ArrayList;
import java.util.List;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.DemoMonster;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.Monster;

/**
 * Used to generate monsters
 */
public class MonsterFactory {
  /**
   * Generate a monster at a particular position
   *
   * @param startX X position
   * @param startY Y position
   * @return A new monster object
   */
  public static Monster generateMonster(int startX, int startY) {
    // TODO: replace with some logic to pick monsters
    return new DemoMonster(startX, startY);
  }

  /**
   * Generate monsters based on MonsterSpawners in an entities list, appropriate for the difficulty
   * of the level
   *
   * @param entities A list of entities, that may include MonsterSpawner items. Modified to remove
   *        any MonsterSpawners
   * @param levelNumber The level number being generated for
   * @param totalLevels The total number of levels in the dungeon
   * @return A list of monster objects
   */
  public static List<Monster> generateMonsters(List<Entity> entities, int levelNumber,
      int totalLevels) {
    List<Monster> monsters = new ArrayList<Monster>();
    List<Entity> entitiesToRemove = new ArrayList<Entity>();

    for (Entity entity : entities) {
      // Only care about monster spawners
      if (entity.getType() != CellType.MONSTERSPAWNER) {
        continue;
      }

      // Add a monster on the spot of the monster spawner
      monsters.add(generateMonster(entity.getX(), entity.getY()));

      // Add the spawner to the list of entities to remove
      entitiesToRemove.add(entity);
    }

    // Remove spawners from the entities list
    entities.removeAll(entitiesToRemove);

    return monsters;
  }
}
