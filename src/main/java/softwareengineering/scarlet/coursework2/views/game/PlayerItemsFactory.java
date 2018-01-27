package softwareengineering.scarlet.coursework2.views.game;

/**
 * Utility class to load and cache images for the player status bar
 */
public class PlayerItemsFactory {
  private static Gold gold;
  private static Health health;
  private static StrengthType1 strength1;
  private static StrengthType2 strength2;
  private static StrengthType3 strength3;

  /**
   * Load and return images for the status bar.
   *
   * The image returned depends on the passed in string, which is a short code for a
   * particular image. These codes are as follows:
   *
   * * g: gold
   * * h: health
   * * s1: lowest strength item
   * * s2: medium strength item
   * * s3: highest strength item
   *
   * @param item The "code" for the required image
   */
  public static ItemImage init(String item) {
    switch (item) {
      case "g":
        if (gold == null) {
          gold = new Gold();
        }
        return gold;
      case "h":
        if (health == null) {
          health = new Health();
        }
        return health;
      case "s1":
        if (strength1 == null) {
          strength1 = new StrengthType1();
        }
        return strength1;
      case "s2":
        if (strength2 == null) {
          strength2 = new StrengthType2();
        }
        return strength2;
      case "s3":
        if (strength3 == null) {
          strength3 = new StrengthType3();
        }
        return strength3;
      default:
        return null;
    }
  }
}
