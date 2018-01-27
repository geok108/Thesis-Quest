package softwareengineering.scarlet.coursework2.models;

public class DummyMonster extends Monster {
  public boolean hasHadTurn = false;

  public DummyMonster(int startX, int startY) {
    super("Dummy", startX, startY, 1, 1);
    behaviour = new DummyMonsterBehaviour();
  }

  @Override
  public void performAction(Dungeon dungeon, Player player) {
    super.performAction(dungeon, player);
    hasHadTurn = true;
  }
}
