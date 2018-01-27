package softwareengineering.scarlet.coursework2.views.game;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import softwareengineering.scarlet.coursework2.models.Dungeon;
import softwareengineering.scarlet.coursework2.models.Monster;

/**
 * Render monsters to the screen
 */
public class MonsterRenderer {
  private MonsterAvatar avatar;
  private Dungeon dungeon;

  public MonsterRenderer(Dungeon dungeon) {
    avatar = new MonsterAvatar();
    this.dungeon = dungeon;
  }

  /**
   * Render all monsters in the level to the screen
   */
  public void render(Graphics2D g2d, ImageObserver observer) {
    for (Monster monster : dungeon.getCurrentLevel().getMonsters()) {
      int screenX = GameView.getXForGridX(monster.getX());
      int screenY = GameView.getYForGridY(monster.getY());
      this.avatar.draw(screenX, screenY, g2d, observer);
    }
  }
}
