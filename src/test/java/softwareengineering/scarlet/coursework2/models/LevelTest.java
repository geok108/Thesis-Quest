package softwareengineering.scarlet.coursework2.models;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.levelgeneration.SimpleLevelFactory;

public class LevelTest {
  @Test
  public void testVoidTypeAtPos() {
    int entityPositionX = 0;
    int entityPositionY = 0;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());

    assertEquals(CellType.VOID, level.getTypeAtPos(entityPositionX, entityPositionY));
  }

  @Test
  public void testRoomTypeAtPos() {
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());

    assertEquals(CellType.ROOM, level.getTypeAtPos(entityPositionX, entityPositionY));
  }

  @Test
  public void testGetTypeAtPos_maxX() {
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    assertEquals(CellType.VOID, level.getTypeAtPos(50, 5));
  }

  @Test
  public void testGetTypeAtPos_minX() {
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    assertEquals(CellType.VOID, level.getTypeAtPos(-1, 5));
  }

  @Test
  public void testGetTypeAtPos_maxY() {
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    assertEquals(CellType.VOID, level.getTypeAtPos(5, 50));
  }

  @Test
  public void testGetTypeAtPos_minY() {
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    assertEquals(CellType.VOID, level.getTypeAtPos(5, -1));
  }

  @Test
  public void testEntityTypeAtPos() {
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    Entity entity = new HealthItem();
    entity.setPosition(entityPositionX, entityPositionY);
    level.getEntities().add(entity);

    assertEquals(entity.type, level.getTypeAtPos(entityPositionX, entityPositionY));
  }

  @Test
  public void testGetEntityAtPos() {
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    Entity entity = new HealthItem();
    entity.setPosition(entityPositionX, entityPositionY);
    level.getEntities().add(entity);

    assertEquals(entity, level.getEntityAtPos(entityPositionX, entityPositionY));
  }

  @Test
  public void testGetNoEntityAtPos() {
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());

    assertEquals(null, level.getEntityAtPos(entityPositionX, entityPositionY));
  }
}
