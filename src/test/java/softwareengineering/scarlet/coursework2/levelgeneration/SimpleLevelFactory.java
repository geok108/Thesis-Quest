package softwareengineering.scarlet.coursework2.levelgeneration;

import java.util.ArrayList;
import java.util.List;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.Level;
import softwareengineering.scarlet.coursework2.models.Room;

/**
 * Used to generate a simple map containing a single room.
 *
 * The room almost fills the entire map.
 *
 * Used for testing.
 */
public class SimpleLevelFactory extends LevelFactory {
  /**
   * Make a Level of a defined size containing a single room.
   *
   * @param width Width of the map in cells
   * @param height Height of the map in cells
   * @return A Level with a single room
   */
  public static Level generateLevel(int width, int height) {
    return generateLevel(width, height, new ArrayList<Entity>());
  }

  /**
   * Make a Level of a defined size containing a single room.
   *
   * @param width Width of the map in cells
   * @param height Height of the map in cells
   * @param entities A list of entities to place in the level
   * @return A Level with a single room
   */
  public static Level generateLevel(int width, int height, List<Entity> entities) {
    Room room = new Room(2, 2, width - 4, height - 4);
    Level level = new Level(width, height);
    level.getRooms().add(room);
    placeObjects(level, entities);
    return level;
  }
}
