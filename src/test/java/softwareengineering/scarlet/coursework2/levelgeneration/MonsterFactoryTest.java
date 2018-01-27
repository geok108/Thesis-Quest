package softwareengineering.scarlet.coursework2.levelgeneration;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Entity;
import softwareengineering.scarlet.coursework2.models.GoldItem;
import softwareengineering.scarlet.coursework2.models.HealthItem;
import softwareengineering.scarlet.coursework2.models.Monster;
import softwareengineering.scarlet.coursework2.models.StrengthItem;

public class MonsterFactoryTest {
  @Test
  public void testGenerateMonsters_fromEntities() {
    int numSpawners = 3;
    List<Entity> entities = new ArrayList<Entity>();

    for (int i = 0; i < numSpawners; i++) {
      entities.add(new MonsterSpawner());
    }

    List<Monster> monsters = MonsterFactory.generateMonsters(entities, 0, 1);

    assertEquals(numSpawners, monsters.size());
    assertEquals(0, entities.size());
  }

  @Test
  public void testGenerateMonsters_noSpawners() {
    List<Entity> entities =
        Arrays.asList(new GoldItem(), new HealthItem(), new StrengthItem(1, CellType.STRENGTH1));

    List<Monster> monsters = MonsterFactory.generateMonsters(entities, 0, 1);

    assertEquals(0, monsters.size());
  }
}
