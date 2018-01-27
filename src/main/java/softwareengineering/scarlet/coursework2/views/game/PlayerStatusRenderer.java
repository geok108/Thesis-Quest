package softwareengineering.scarlet.coursework2.views.game;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Player;

/**
 * Renders the status bar at the top of the screen
 */
public class PlayerStatusRenderer {
  private Player player;

  public PlayerStatusRenderer(Player player) {
    this.player = player;
  }

  /**
   * Render the status bar to the screen
   */
  @SuppressWarnings("incomplete-switch")
  public void render(Graphics2D g2d, ImageObserver observer) {
    int x = 80;
    int y = 4;

    // Render current score
    PlayerItemsFactory.init("g").draw(x, y, g2d, observer);
    int currentGold = player.getGold();
    g2d.drawString("x", x + 28, y + 20);
    g2d.drawString(Integer.toString(currentGold), x + 45, y + 20);

    // Render current health
    PlayerItemsFactory.init("h").draw(x + 75, y, g2d, observer);
    int hp = player.getHealthPoints();
    g2d.drawString("x", x + 103, y + 20);
    g2d.drawString(Integer.toString(hp), x + 120, y + 20);
    int strength = player.getStrength();

    // Render current strength item
    CellType cellType = this.player.getStrengthItem().getType();
    switch (cellType) {
      case STRENGTH1:
        PlayerItemsFactory.init("s1").draw(x + 150, y, g2d, observer);
        break;
      case STRENGTH2:
        PlayerItemsFactory.init("s2").draw(x + 150, y, g2d, observer);
        break;
      case STRENGTH3:
        PlayerItemsFactory.init("s3").draw(x + 150, y, g2d, observer);
        break;
    }
    g2d.drawString("x", x + 178, y + 20);
    g2d.drawString(Integer.toString(strength), x + 195, y + 20);
  }
}
