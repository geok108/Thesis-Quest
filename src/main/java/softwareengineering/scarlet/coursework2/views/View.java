package softwareengineering.scarlet.coursework2.views;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;

/**
 * Classes that implement this can be used by the GameApp to render to the screen.
 */
public interface View {
  /**
   * Called as part of the paint cycle.
   *
   * Should render the current state of the controller's models, regardless of what the controller
   * is doing.
   *
   * @param g2d The Graphics2D to render to.
   * @param observer An appropriate ImageObserver
   */
  public void render(Graphics2D g2d, ImageObserver observer);
}
