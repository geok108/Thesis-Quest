package softwareengineering.scarlet.coursework2.views;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import softwareengineering.scarlet.coursework2.models.GameScore;

/**
 * View for the win game screen
 *
 * Shows the player's score and a congratulatory message
 */
public class WinView implements View {
  private GameScore score;
  private Image paperpageIcon;

  /**
   * Render the view
   */
  @Override
  public void render(Graphics2D g2d, ImageObserver observer) {
    g2d.setFont(FontFactory.getSizedFont());
    this.drawBackground(g2d, observer);
    this.drawScore(g2d, observer);
  }

  /**
   * Draw the background image to the screen
   */
  private void drawBackground(Graphics2D g2d, ImageObserver observer) {
    g2d.drawImage(BackgroundFactory.getWinBackground(), 0, 0, observer);
  }

  /**
   * Draw the player's score to the screen
   */
  private void drawScore(Graphics2D g2d, ImageObserver observer) {
    if (this.paperpageIcon == null) {
      this.paperpageIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/paperpage.gif"));
    }

    g2d.drawImage(this.paperpageIcon, 327, 397, observer);
    g2d.drawString("x", 360, 417);
    g2d.drawString(Integer.toString(score.getScore()), 377, 417);
  }

  /**
   * Receive the player's score from the controller
   */
  public void setScore(GameScore score) {
    this.score = score;
  }
}
