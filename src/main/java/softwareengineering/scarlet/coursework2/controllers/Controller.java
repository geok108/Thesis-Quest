package softwareengineering.scarlet.coursework2.controllers;

import softwareengineering.scarlet.coursework2.views.View;

/**
 * Classes that implement this can be used as controllers within the application, and be switched
 * between using App.
 *
 * All of these methods must be implemented correctly for the switching to work.
 */
public interface Controller {
  /**
   * The view object used to render the controller to the screen.
   *
   * Used by Panel to determine which object has current control of rendering.
   *
   * @return An implementation of View
   */
  public View getView();

  /**
   * Handle an input from the application.
   *
   * This method may be a no-op if no input is handled by the view.
   *
   * Do not call repaint! The Panel will do this after this method has returned.
   *
   * @param input The input type
   */
  public void handleInput(Input input);

  /**
   * Take control of the application.
   *
   * Any object creation etc should happen here, not in the constructor.
   *
   * This may be called several times as the user switches between views, but the controller object
   * is a singleton. Be conscious of this in the controller's design.
   *
   * @param view The view object used to render to the screen.
   */
  public void init(View view);
}
