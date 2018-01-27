package softwareengineering.scarlet.coursework2.models;

/**
 * A superclass of mobile entities within a level
 *
 * Most notably Monsters and the Player
 */
public abstract class Character {
  private int x;
  private int y;
  protected String name;
  protected int strength;
  protected int healthPoints;
  protected StrengthItem strengthItem;

  /**
   * Create a new character entity
   *
   * @param name The character's "name"
   * @param x The starting x coordinate of the character
   * @param y The starting y coordinate of the character
   * @param health The starting amount of health points for the character
   * @param strength The starting amount of strength for the character
   */
  public Character(String name, int x, int y, int health, int strength) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.healthPoints = health;
    this.strength = strength;
  }

  /**
   * The current X coordinate of the character
   *
   * @return The character's x coordinate
   */
  public int getX() {
    return this.x;
  }

  /**
   * The current Y coordinate of the character
   *
   * @return The character's y coordinate
   */
  public int getY() {
    return this.y;
  }

  /**
   * Set the character's name
   *
   * @param name The name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Set the character's X coordinate
   *
   * @param x The x coordinate to set
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Set the character's Y coordinate
   *
   * @param y The y coordinate to set
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Moves the character's location by the input amounts to (x+dx, y+dy)
   *
   * @param dx X-axis movement
   * @param dy Y-axis movement
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Get the character's name
   *
   * @return The character's name
   */
  public String getName() {
    return name;
  }

  /**
   * Get the character's current amount of strength
   *
   * @return The amount of strength
   */
  public int getStrength() {
    return strength;
  }

  /**
   * Set the character's strength
   *
   * @param strength The amount of strength to set
   */
  public void setStrength(int strength) {
    this.strength = strength;
  }

  /**
   * Increase the character's strength by an amount
   *
   * @param strength The amount to increase the strength by
   */
  public void increaseStrength(int strength) {
    this.strength += strength;
  }

  /**
   * Decrease the character's strength by an amount
   *
   * @param strength The amount to decrease the strength by
   */
  public void decreaseStrength(int strength) {
    this.strength -= strength;
  }

  /**
   * Get the character's current amount of health points
   *
   * @return The amount of health
   */
  public int getHealthPoints() {
    return healthPoints;
  }

  /**
   * Set the character's amount of health
   *
   * @param strength The amount of health to set
   */
  public void setHealthPoints(int healthPoints) {
    this.healthPoints = healthPoints;
  }

  /**
   * Increase the character's health by an amount
   *
   * @param health The amount to increase the health by
   */
  public void increaseHealthPoint(int healthPoints) {
    this.healthPoints += healthPoints;
  }

  /**
   * Decrease the character's health by an amount
   *
   * @param health The amount to decrease the health by
   */
  public void decreaseHealthPoint(int healthPoints) {
    this.healthPoints -= healthPoints;
  }
}
