package softwareengineering.scarlet.coursework2.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.levelgeneration.SimpleLevelFactory;

public class PlayerTest {

  @Test
  public void testGetters() {
    Player player = new Player("test", 2, 3);

    assertEquals("test", player.getName());
    assertEquals(2, player.getX());
    assertEquals(3, player.getY());
  }

  @Test
  public void testMovePlayer() {
    Player player = new Player("test", 5, 3);

    int dx = 3;
    int dy = -2;

    player.move(dx, dy);

    int newX = player.getX();
    int newY = player.getY();

    // Note: the Point class getters return double for some stupid reason, therefore we
    // access its attributes directly.
    assertEquals(8, newX);
    assertEquals(1, newY);

  }

  @Test
  public void testPlayerhasStrengthItem() {
    Player player = new Player("test", 5, 3);
    player.setStrengthItem(new StrengthItem(10, CellType.STRENGTH1));
    assertEquals(10, player.getStrength());
  }

  @Test
  public void testPlayerhasHealthItem() {
    Player player = new Player("test", 5, 3);
    player.setHealthPoints(15);
    player.increaseHealthPoint(5);
    assertEquals(20, player.getHealthPoints());
  }

  @Test
  public void testCollectStrengthItem() {
    Player player = new Player("test", 5, 3);
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    Entity entity = new StrengthItem(20, CellType.STRENGTH2);
    entity.setPosition(entityPositionX, entityPositionY);
    level.getEntities().add(entity);
    player.move(entityPositionX, entityPositionY);
    player.setStrengthItem((StrengthItem) entity);
    assertEquals(entity, level.getEntityAtPos(entityPositionX, entityPositionY));
    assertEquals(20, player.getStrength());
    assertTrue(level.getEntities().remove(entity));
  }

  @Test
  public void testCollectHealthItem() {
    Player player = new Player("test", 5, 3);
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    Entity entity = new HealthItem();
    entity.setPosition(entityPositionX, entityPositionY);
    level.getEntities().add(entity);
    player.move(entityPositionX, entityPositionY);
    player.increaseHealthPoint(1);
    assertEquals(entity, level.getEntityAtPos(entityPositionX, entityPositionY));
    assertEquals(6, player.getHealthPoints());
    assertTrue(level.getEntities().remove(entity));
  }

  @Test
  public void testCollectGoldItem() {
    Player player = new Player("test", 5, 3);
    int entityPositionX = 10;
    int entityPositionY = 10;
    Level level = SimpleLevelFactory.generateLevel(50, 50, new ArrayList<Entity>());
    Entity entity = new GoldItem();
    entity.setPosition(entityPositionX, entityPositionY);
    level.getEntities().add(entity);
    player.move(entityPositionX, entityPositionY);
    player.setGold(player.getGold() + 1);
    assertEquals(entity, level.getEntityAtPos(entityPositionX, entityPositionY));
    assertEquals(5, player.getHealthPoints());
    assertTrue(level.getEntities().remove(entity));
  }

}
