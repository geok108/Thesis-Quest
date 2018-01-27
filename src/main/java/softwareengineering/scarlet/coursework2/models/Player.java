package softwareengineering.scarlet.coursework2.models;

/**
 * A model class to represent information about the player.
 */
public class Player extends Character {
  private int gold;

  /**
   * Constructor.
   *
   * @param name The player's name
   * @param startLocation The starting position of the player
   */
  public Player(String name, int startX, int startY) {
    super(name, startX, startY, 5, 10);
    this.gold = 0;
    this.strengthItem = new StrengthItem(10, CellType.STRENGTH1);
  }

  /**
   * The player's current gold count
   *
   * @return Player's gold count
   */
  public int getGold() {
    return gold;
  }

  /**
   * Set the player's current gold count.
   *
   * Replaces the existing count.
   *
   * @param gold The amount to set
   */
  public void setGold(int gold) {
    this.gold = gold;
  }

  /**
   * The player's current strength item
   *
   * @return The strength item
   */
  public StrengthItem getStrengthItem() {
    return strengthItem;
  }

  /**
   * Set the player's current strength item
   *
   * @param strengthItem The strength item to set
   */
  public void setStrengthItem(StrengthItem strengthItem) {
    this.strengthItem = strengthItem;
    this.setStrength(this.strengthItem.getValue());
  }
}
