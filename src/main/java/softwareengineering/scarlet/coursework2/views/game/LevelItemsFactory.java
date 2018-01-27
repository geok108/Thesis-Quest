package softwareengineering.scarlet.coursework2.views.game;

import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.NullImage;

/**
 * Utility class to cache image objects for items in the dungeon
 */
public class LevelItemsFactory {
  private static Room room;
  private static Corridor corridor;
  private static Gold gold;
  private static Health health;
  private static Exit exit;
  private static StrengthType1 strength1;
  private static StrengthType2 strength2;
  private static StrengthType3 strength3;
  private static StairsDown stairsdown;
  private static StairsUp stairsup;
  private static NullImage nullimage;

  /**
   * Get the image object for the given type
   */
  public static ItemImage init(CellType type) {
    switch (type) {
      case ROOM:
        if (room == null) {
          room = new Room();
        }
        return room;
      case CORRIDOR:
        if (corridor == null) {
          corridor = new Corridor();
        }
        return corridor;
      case GOLD:
        if (gold == null) {
          gold = new Gold();
        }
        return gold;
      case HEALTH:
        if (health == null) {
          health = new Health();
        }
        return health;
      case EXIT:
        if (exit == null) {
          exit = new Exit();
        }
        return exit;
      case STRENGTH1:
        if (strength1 == null) {
          strength1 = new StrengthType1();
        }
        return strength1;
      case STRENGTH2:
        if (strength2 == null) {
          strength2 = new StrengthType2();
        }
        return strength2;
      case STRENGTH3:
        if (strength3 == null) {
          strength3 = new StrengthType3();
        }
        return strength3;
      case STAIRSDOWN:
        if (stairsdown == null) {
          stairsdown = new StairsDown();
        }
        return stairsdown;
      case STAIRSUP:
        if (stairsup == null) {
          stairsup = new StairsUp();
        }
        return stairsup;
      default:
        if (nullimage == null) {
          nullimage = new NullImage();
        }
        return nullimage;
    }
  }
}
