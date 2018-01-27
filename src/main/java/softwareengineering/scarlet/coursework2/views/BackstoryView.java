package softwareengineering.scarlet.coursework2.views;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 * Controller for the backstory view, shown just before the game starts.
 *
 * Simply displays text for the user to read
 */
public class BackstoryView implements View {
  /**
   * Render to the screen
   */
  @Override
  public void render(Graphics2D g2d, ImageObserver observer) {
    this.drawBackstory(g2d, observer);
  }

  /**
   * Render the background and title
   */
  private void drawBackstory(Graphics2D g2d, ImageObserver observer) {
    String bsTitle = "Back Story";
    g2d.drawString(bsTitle, 200, 170);

    g2d.drawImage(BackgroundFactory.getBackstoryBackground(), 0, 0, observer);
  }
}

