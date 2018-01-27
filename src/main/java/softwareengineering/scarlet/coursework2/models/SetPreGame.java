package softwareengineering.scarlet.coursework2.models;

/**
 * A model used to track state for the SetPreGameController, most notably the player's name as it is
 * entered. This is then passed to the GameController to be used in the creation of a Player object.
 */
public class SetPreGame {
  private String name;
  private boolean warning;

  /**
   * Should the view display the "not enough characters" warning?
   *
   * @return True if yes, false otherwise
   */
  public boolean isWarning() {
    return warning;
  }

  /**
   * Set whether the "not enough characters" warning is displayed
   *
   * @param warning Whether the warning should be shown - true if yes, false if no
   */
  public void setWarning(boolean warning) {
    this.warning = warning;
  }

  /**
   * Create the SetPreGame object
   */
  public SetPreGame() {
    this.name = "----";
    warning = false;
  }

  /**
   * Set the current version of the player's name
   *
   * @param name The player's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the current version of the player's name
   *
   * @return The player's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Add a letter to the player's name
   *
   * @param letter The letter to add
   */
  public void appendLetter(String letter) {
    if (name.contains("-"))
      name = name.replaceFirst("-", letter);
  }

  /**
   * Remove the right-most letter from the player's name
   */
  public void deleteLetter() {
    if (name.contains("-")) {
      int index = name.indexOf("-");
      if (index > 0) {
        char[] chars = name.toCharArray();
        chars[index - 1] = '-';
        name = String.valueOf(chars);
      }
    } else {
      name = name.substring(0, name.length() - 1);
      name = name.concat("-");
    }
  }
}
