package softwareengineering.scarlet.coursework2.models;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import softwareengineering.scarlet.coursework2.views.game.ItemImage;

/**
 * Utility implementation of ItemImage to draw nothing - ie an invisible entity
 */
public class NullImage extends ItemImage {
  @Override
  public void draw(int x, int y, Graphics2D g2d, ImageObserver observer) {
    // No-op
  }
}
