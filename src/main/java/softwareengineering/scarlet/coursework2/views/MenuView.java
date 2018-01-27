package softwareengineering.scarlet.coursework2.views;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import softwareengineering.scarlet.coursework2.models.Menu;

/**
 * Renders the title and menu to the screen.
 */
public class MenuView implements View {
  private static final int ySpacing = 40; // height between menu option
  private Menu model;
  private Font sizedFont;
  private String[] options;

  /**
   * Render to the screen.
   *
   * @param g2d The graphics object to render to.
   */
  @Override
  public void render(Graphics2D g2d, ImageObserver observer) {
    this.drawBackground(g2d, observer);
    this.drawOptions(g2d);
    this.drawMenuArrow(g2d);
  }

  /**
   * Draw the background
   */
  private void drawBackground(Graphics2D g2d, ImageObserver observer) {
    g2d.drawImage(BackgroundFactory.getMenuBackground(), 0, 0, observer);
  }

  /**
   * Renders the menu options.
   *
   * @param g2d The graphics object the options are rendered to.
   */
  private void drawOptions(Graphics2D g2d) {
    g2d.setFont(FontFactory.getSizedFont());
    for (int i = 0; i < options.length; i++) {
      g2d.drawString("  " + options[i], 370, 350 + ySpacing * i);
    }
  }

  /**
   * Shows the arrow on the appropriate option.
   *
   * @param g2d The graphics object the arrow is rendered to.
   */
  private void drawMenuArrow(Graphics2D g2d) {
    g2d.setFont(sizedFont);
    g2d.drawString("=>", 360, 350 + model.getOption() * ySpacing);
  }

  /**
   * Set the model object that will be used as the source of state for rendering.
   *
   * @param menu The Menu object to be used
   */
  public void setModel(Menu menu) {
    this.model = menu;
  }

  /**
   * Receive the options to render from the controller
   */
  public void setOptions(String[] options) {
    this.options = options;
  }
}
