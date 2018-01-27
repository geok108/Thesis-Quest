package softwareengineering.scarlet.coursework2.views;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Utility class to load background images from disk and cache them
 */
public class BackgroundFactory {
  private static Image background;
  private static Image abandonBackground;
  private static Image backstoryBackground;
  private static Image gameoverBackground;
  private static Image menuBackground;
  private static Image winBackground;
  private static Image gameBackground;

  /**
   * Load an image from disk and convert it to the appropriate type
   *
   * @param filename The path to the file on disk
   * @return An Image object representing the image
   */
  private static Image getImageFromDisk(String filename) {
    InputStream stream = BackgroundFactory.class.getClassLoader().getResourceAsStream(filename);
    try {
      ImageIcon icon = new ImageIcon(ImageIO.read(stream));
      return icon.getImage();
    } catch (IOException e) {
      throw new RuntimeException(
          "Image %s not found! Assuming problem with resources and quitting");
    }
  }

  /**
   * Get the default background
   */
  public static Image getMainBackground() {
    if (background == null) {
      background = getImageFromDisk("background.png");
    }

    return background;
  }

  /**
   * Get the background used during the game
   */
  public static Image getGameBackground() {
    if (gameBackground == null) {
      gameBackground = getImageFromDisk("background2.png");
    }

    return gameBackground;
  }

  /**
   * Get the background used for the abandon game screen
   */
  public static Image getAbandonBackground() {
    if (abandonBackground == null) {
      abandonBackground = getImageFromDisk("abandonbackground.png");
    }

    return abandonBackground;
  }

  /**
   * Get the background used for the backstory screen
   */
  public static Image getBackstoryBackground() {
    if (backstoryBackground == null) {
      backstoryBackground = getImageFromDisk("background3.png");
    }

    return backstoryBackground;
  }

  /**
   * Get the background used for the game over screen
   */
  public static Image getGameoverBackground() {
    if (gameoverBackground == null) {
      gameoverBackground = getImageFromDisk("gameoverbackground.png");
    }

    return gameoverBackground;
  }

  /**
   * Get the background used for the menu screen
   */
  public static Image getMenuBackground() {
    if (menuBackground == null) {
      menuBackground = getImageFromDisk("background1.png");
    }

    return menuBackground;
  }

  /**
   * Get the background for the win game screen
   */
  public static Image getWinBackground() {
    if (winBackground == null) {
      winBackground = getImageFromDisk("winbackground.png");
    }

    return winBackground;
  }
}
