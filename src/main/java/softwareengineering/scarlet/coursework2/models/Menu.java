package softwareengineering.scarlet.coursework2.models;

/**
 * Simple model to store the state of the menu.
 */
public class Menu {
  private int selectedOption;
  private int numOptions;

  public Menu(int numOptions) {
    this.selectedOption = 0;
    this.numOptions = numOptions;
  }

  /**
   * Move to the next menu option
   */
  public void incrementOption() {
    this.selectedOption++;
    this.selectedOption = this.selectedOption % numOptions;
  }

  /**
   * Move to the previous menu option
   */
  public void decrementOption() {
    this.selectedOption--;
    if (this.selectedOption == -1) {
      // Loop back to bottom option
      this.selectedOption = numOptions - 1;
    }
  }

  /**
   * Get the currently selected menu option
   *
   * @return The selected option
   */
  public int getOption() {
    return this.selectedOption;
  }
}
