package softwareengineering.scarlet.coursework2.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import softwareengineering.scarlet.coursework2.levelgeneration.SimpleLevelFactory;
import softwareengineering.scarlet.coursework2.models.DemoMonster;
import softwareengineering.scarlet.coursework2.models.Dungeon;
import softwareengineering.scarlet.coursework2.models.ExitItem;
import softwareengineering.scarlet.coursework2.models.Level;
import softwareengineering.scarlet.coursework2.models.MessageList;
import softwareengineering.scarlet.coursework2.models.Monster;
import softwareengineering.scarlet.coursework2.models.MonsterBehaviourType1;
import softwareengineering.scarlet.coursework2.models.Player;
import softwareengineering.scarlet.coursework2.models.StairsDownItem;
import softwareengineering.scarlet.coursework2.models.StairsUpItem;

public class GameControllerTest {

  @Test
  public void testHandleInput_left() {
    GameController controller = new GameController(new DummyApp());
    Level level = SimpleLevelFactory.generateLevel(10, 10);
    controller.dungeon = new Dungeon(10, 10, Arrays.asList(level));
    controller.player = new Player("", 4, 4);

    controller.handleInput(Input.LEFT);

    assertEquals(3, controller.player.getX());
    assertEquals(4, controller.player.getY());
  }

  @Test
  public void testHandleInput_right() {
    GameController controller = new GameController(new DummyApp());
    Level level = SimpleLevelFactory.generateLevel(10, 10);
    controller.dungeon = new Dungeon(10, 10, Arrays.asList(level));
    controller.player = new Player("", 4, 4);

    controller.handleInput(Input.RIGHT);

    assertEquals(5, controller.player.getX());
    assertEquals(4, controller.player.getY());
  }

  @Test
  public void testHandleInput_up() {
    GameController controller = new GameController(new DummyApp());
    Level level = SimpleLevelFactory.generateLevel(10, 10);
    controller.dungeon = new Dungeon(10, 10, Arrays.asList(level));
    controller.player = new Player("", 4, 4);

    controller.handleInput(Input.UP);

    assertEquals(4, controller.player.getX());
    assertEquals(3, controller.player.getY());
  }

  @Test
  public void testHandleInput_down() {
    GameController controller = new GameController(new DummyApp());
    Level level = SimpleLevelFactory.generateLevel(10, 10);
    controller.dungeon = new Dungeon(10, 10, Arrays.asList(level));
    controller.player = new Player("", 4, 4);

    controller.handleInput(Input.DOWN);

    assertEquals(4, controller.player.getX());
    assertEquals(5, controller.player.getY());
  }

  @Test
  public void testPerformAction_room() {
    GameController controller = new GameController(new DummyApp());
    Level level = SimpleLevelFactory.generateLevel(10, 10);
    controller.dungeon = new Dungeon(10, 10, Arrays.asList(level));
    controller.player = new Player("", 4, 4);

    controller.performAction(new Pair(1, 0));

    assertEquals(5, controller.player.getX());
    assertEquals(4, controller.player.getY());
  }

  @Test
  public void testPerformAction_stairsDown() {
    GameController controller = new GameController(new DummyApp());
    controller.setUpModels();

    Level level1 = controller.dungeon.getLevels().get(0);
    Level level2 = controller.dungeon.getLevels().get(1);

    StairsDownItem stairsDown = new StairsDownItem();
    stairsDown.setPosition(controller.player.getX() + 1, controller.player.getY());
    level1.getEntities().add(stairsDown);

    controller.performAction(new Pair(1, 0));

    assertEquals(level2, controller.dungeon.getCurrentLevel());
    assertEquals(level2.getStairsUp().getX(), controller.player.getX());
    assertEquals(level2.getStairsUp().getY(), controller.player.getY());
  }

  @Test
  public void testPerformAction_stairsUp() {
    GameController controller = new GameController(new DummyApp());
    controller.setUpModels();

    Level level1 = controller.dungeon.getLevels().get(0);
    Level level2 = controller.dungeon.getLevels().get(1);

    // Go down a level first
    assertEquals(level1, controller.dungeon.getCurrentLevel());
    controller.dungeon.goDown();
    assertEquals(level2, controller.dungeon.getCurrentLevel());

    // Go back up again and confirm that it's the same level we came from
    StairsUpItem stairsUp = new StairsUpItem();
    stairsUp.setPosition(controller.player.getX() + 1, controller.player.getY());
    level2.getEntities().add(stairsUp);
    controller.performAction(new Pair(1, 0));

    assertEquals(level1, controller.dungeon.getCurrentLevel());
    assertEquals(level1.getStairsDown().getX(), controller.player.getX());
    assertEquals(level1.getStairsDown().getY(), controller.player.getY());
  }

  @Test
  public void testPerformAction_ExitWithGold() {
    DummyApp app = new DummyApp();
    GameController controller = new GameController(app);
    controller.setUpModels();
    Level level1 = controller.dungeon.getLevels().get(0);

    ExitItem exitItem = new ExitItem();
    exitItem.setPosition(controller.player.getX() + 1, controller.player.getY());
    level1.getEntities().add(exitItem);
    controller.player.setGold(Dungeon.REQUIRED_SCORE);
    controller.performAction(new Pair(1, 0));

    assertTrue(app.didISwitchToWin);
  }

  @Test
  public void testPerformAction_ExitWithoutGold() {
    DummyApp app = new DummyApp();
    GameController controller = new GameController(app);
    controller.setUpModels();
    Level level1 = controller.dungeon.getLevels().get(0);

    ExitItem exitItem = new ExitItem();
    exitItem.setPosition(controller.player.getX() + 1, controller.player.getY());
    level1.getEntities().add(exitItem);
    controller.performAction(new Pair(1, 0));

    assertFalse(app.didISwitchToWin);
    assertEquals(exitItem.getX(), controller.player.getX());
    assertEquals(exitItem.getY(), controller.player.getY());
    assertTrue(MessageList.hasMessages());
    MessageList.clear();
  }

  @Test
  public void testHandleMonsters() {
    DummyApp app = new DummyApp();
    GameController controller = new GameController(app);
    controller.setUpModels();

    controller.handleMonsters();

    for (Monster monster : controller.dungeon.getCurrentLevel().getMonsters()) {
      assertTrue(((DemoMonster) monster).hasHadTurn);
    }
  }

  @Test
  public void testMoveMonsters() {
    GameController controller = new GameController(new DummyApp());
    Level level = SimpleLevelFactory.generateLevel(10, 10);
    controller.dungeon = new Dungeon(10, 10, Arrays.asList(level));
    controller.player = new Player("", 4, 4);
    int prevX, prevY;
    DemoMonster monster1 = new DemoMonster(6, 6);
    DemoMonster monster2 = new DemoMonster(6, 6);
    DemoMonster monster3 = new DemoMonster(6, 6);

    List<DemoMonster> monsterList = new ArrayList<DemoMonster>();
    monsterList.add(monster1);
    monsterList.add(monster2);
    monsterList.add(monster3);
    MonsterBehaviourType1 behaviour = new MonsterBehaviourType1();
    for (Monster monster : monsterList) {
      prevX = monster.getX();
      prevY = monster.getY();
      behaviour.performAction(monster, controller.dungeon, controller.player);
      assertTrue(prevX != monster.getX() || prevY != monster.getY());
    }
  }

  @Test
  public void testPlayerAttackMonster() {
    DummyApp app = new DummyApp();
    GameController controller = new GameController(app);
    controller.setUpModels();
    DemoMonster monster = new DemoMonster(5, 5);
    int prevHealth = monster.getHealthPoints();
    controller.player.move(3, 5);
    // controller.performAction(new Pair(1, 0));
    monster.decreaseHealthPoint(controller.player.getStrength());
    assertTrue(monster.getHealthPoints() == prevHealth - controller.player.getStrength());
  }

  @Test
  public void testPlayerDies() {
    DummyApp app = new DummyApp();
    GameController controller = new GameController(app);
    controller.setUpModels();
    DemoMonster monster = new DemoMonster(controller.player.getX() + 2, controller.player.getY());
    MonsterBehaviourType1 behaviour = new MonsterBehaviourType1();
    int prevHealth = controller.player.getHealthPoints();
    controller.performAction(new Pair(1, 0));
    behaviour.fightPlayer(controller.player, monster);
    assertTrue(controller.player.getHealthPoints() == prevHealth - monster.getStrength());
  }

  public void testMonsterFightPlayer() {
    DummyApp app = new DummyApp();
    GameController controller = new GameController(app);
    controller.setUpModels();
    DemoMonster monster = new DemoMonster(controller.player.getX() + 2, controller.player.getY());
    MonsterBehaviourType1 behaviour = new MonsterBehaviourType1();
    int prevHealth = controller.player.getHealthPoints();
    controller.performAction(new Pair(1, 0));
    behaviour.fightPlayer(controller.player, monster);
    assertTrue(controller.player.getHealthPoints() < prevHealth);
  }
}
