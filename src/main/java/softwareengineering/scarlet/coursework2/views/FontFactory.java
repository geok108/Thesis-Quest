package softwareengineering.scarlet.coursework2.views;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class to load fonts from disk and cache them
 */
public class FontFactory {
  private static Font sizedFont;

  /**
   * Generate and return the standard font used in the majority of the UI
   */
  public static Font getSizedFont() {
    if (sizedFont == null) {
      InputStream is = MenuView.class.getResourceAsStream("/Chalkduster.ttf");
      try {
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        sizedFont = font.deriveFont(Font.BOLD, 18f);
      } catch (FontFormatException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return sizedFont;
  }
}
