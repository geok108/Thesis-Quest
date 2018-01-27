package softwareengineering.scarlet.coursework2.models;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MonsterTest {

  public void testGetters() {
    DemoMonster monster = new DemoMonster(2, 3);
    assertEquals(2, monster.getX());
    assertEquals(3, monster.getY());
  }

  @Test
  public void testMoveMonster() {
    DemoMonster monster = new DemoMonster(5, 3);

    int dx = 3;
    int dy = -2;

    monster.move(dx, dy);

    int newX = monster.getX();
    int newY = monster.getY();

    // Note: the Point class getters return double for some stupid reason, therefore we
    // access its attributes directly.
    assertEquals(8, newX);
    assertEquals(1, newY);

  }

  @Test
  public void testMonsterhasStrengthItem() {
    DemoMonster monster = new DemoMonster(5, 3);
    assertEquals(5, monster.getStrength());
  }

  @Test
  public void testMonsterhasHealthItem() {
    DemoMonster monster = new DemoMonster(5, 3);
    assertEquals(20, monster.getHealthPoints());
  }
}
