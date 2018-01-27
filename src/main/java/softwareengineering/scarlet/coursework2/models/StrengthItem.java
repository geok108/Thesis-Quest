package softwareengineering.scarlet.coursework2.models;

/**
 * A "strength item", that the user can collect to modify their strength
 *
 * The strength items can have different strength levels, and by represented by different types -
 * for example, different weapons.
 */
public class StrengthItem extends Entity {
  private int value;

  /**
   * Create a new strength item of a particular amount and type
   * 
   * @param value
   * @param type
   */
  public StrengthItem(int value, CellType type) {
    this.value = value;
    this.type = type;
  }

  /**
   * Get the amount of strength this item represents
   */
  public int getValue() {
    return value;
  }

  /**
   * Get the type of cell for this strength item
   */
  public CellType getType() {
    return type;
  }
}
