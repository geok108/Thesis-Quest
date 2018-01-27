package softwareengineering.scarlet.coursework2.views;

import javax.swing.JFrame;

/**
 * Wrapper around JFrame.
 *
 * Used purely as a point to hang the Panel on.
 */
public class Frame extends JFrame {
  private static final long serialVersionUID = 1L;


  public Frame(Panel panel) {
    this.build(panel);

  }

  /**
   * Instantiates the Frame, setting various global window options
   *
   * @param panel
   */

  public void build(Panel panel) {
    this.add(panel);

    setSize(900, 720);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setFocusTraversalKeysEnabled(false); // stops treating arrow keys/tabs as changing focus
  }
}
