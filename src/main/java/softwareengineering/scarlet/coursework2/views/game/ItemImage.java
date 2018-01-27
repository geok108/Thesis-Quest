package softwareengineering.scarlet.coursework2.views.game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Base class for images rendered to the screen.
 *
 * Subclasses should set "filename" to an appropriate value.
 *
 * This class can handle pngs and gifs.
 */
public abstract class ItemImage {
  protected Image image;
  protected String filename;

  /**
   * Loads a gif from disk
   */
  private Image getGif(String filename) throws IOException {
    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/" + this.filename));

    if (image == null) {
      return getPng("placeholder.png");
    }

    return image;
  }

  /**
   * Loads a png from disk
   */
  private Image getPng(String filename) throws IOException {
    InputStream stream = getClass().getResourceAsStream("/" + this.filename);

    if (stream == null) {
      stream = getClass().getResourceAsStream("/placeholder.png");
    }

    ImageIcon icon = new ImageIcon(ImageIO.read(stream));
    return icon.getImage();
  }

  /**
   * Load an image from disk and cache it
   */
  private Image getImage() {
    if (this.image == null) {
      try {
        if (this.filename.endsWith(".gif")) {
          this.image = getGif(filename);
        } else {
          this.image = getPng(filename);
        }
      } catch (IOException io) {
        throw new RuntimeException(String.format(
            "Image %s not found! Assuming problem with resources and quitting", this.filename));
      }
    }

    return this.image;
  }

  /**
   * Draw the image at the specified screen position
   */
  public void draw(int x, int y, Graphics2D g2d, ImageObserver observer) {
    g2d.drawImage(this.getImage(), x, y, observer);
  }
}
