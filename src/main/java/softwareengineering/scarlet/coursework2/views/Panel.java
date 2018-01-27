package softwareengineering.scarlet.coursework2.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import softwareengineering.scarlet.coursework2.controllers.Controller;
import softwareengineering.scarlet.coursework2.controllers.Input;

/**
 * Wrapper around JPanel.
 *
 * Handles rendering and key presses from the user, using the appropriate view and controller.
 */
public class Panel extends JPanel implements KeyListener {
  private static final long serialVersionUID = 1L;
  private Controller controller;

  /**
   * Creates the JPanel, and applies the necessary application-level settings.
   *
   * @param textArea
   */
  public Panel() {
    addKeyListener(this);
    setFocusable(true);
    // does not get arrow keys as input
    setFocusTraversalKeysEnabled(false);
  }

  /**
   * Change the currently 'live' controller.
   *
   * Changing this will affect which view is used to render to the screen, and which controller
   * receives input.
   *
   * @param controller The controller to switch to.
   */
  public void setController(Controller controller) {
    this.controller = controller;
  }

  /**
   * The paint method, called by JPanel.
   *
   * Hands over to the controller's view for rendering.
   *
   * @param g The graphics object to draw on.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // Call the view's render method
    this.controller.getView().render(g2d, this);
  }

  /**
   * Receive a key entry from the user.
   *
   * Translates into a non-AWT representation, and hands over to the controller, before calling
   * repaint.
   */
  @Override
  public void keyReleased(KeyEvent event) {
    Input key = Input.NONE;
    switch (event.getKeyCode()) {
      case KeyEvent.VK_UP:
        key = Input.UP;
        break;
      case KeyEvent.VK_LEFT:
        key = Input.LEFT;
        break;
      case KeyEvent.VK_DOWN:
        key = Input.DOWN;
        break;
      case KeyEvent.VK_RIGHT:
        key = Input.RIGHT;
        break;
      case KeyEvent.VK_ENTER:
        key = Input.CHOOSE;
        break;
      case KeyEvent.VK_ESCAPE:
        key = Input.QUIT;
        break;
      case KeyEvent.VK_BACK_SPACE:
        key = Input.DELETE;
        break;
      case KeyEvent.VK_A:
        key = Input.A;
        break;
      case KeyEvent.VK_B:
        key = Input.B;
        break;
      case KeyEvent.VK_C:
        key = Input.C;
        break;
      case KeyEvent.VK_D:
        key = Input.D;
        break;
      case KeyEvent.VK_E:
        key = Input.E;
        break;
      case KeyEvent.VK_F:
        key = Input.F;
        break;
      case KeyEvent.VK_G:
        key = Input.G;
        break;
      case KeyEvent.VK_H:
        key = Input.H;
        break;
      case KeyEvent.VK_I:
        key = Input.I;
        break;
      case KeyEvent.VK_J:
        key = Input.J;
        break;
      case KeyEvent.VK_K:
        key = Input.K;
        break;
      case KeyEvent.VK_L:
        key = Input.L;
        break;
      case KeyEvent.VK_M:
        key = Input.M;
        break;
      case KeyEvent.VK_N:
        key = Input.N;
        break;
      case KeyEvent.VK_O:
        key = Input.O;
        break;
      case KeyEvent.VK_P:
        key = Input.P;
        break;
      case KeyEvent.VK_Q:
        key = Input.Q;
        break;
      case KeyEvent.VK_R:
        key = Input.R;
        break;
      case KeyEvent.VK_S:
        key = Input.S;
        break;
      case KeyEvent.VK_T:
        key = Input.T;
        break;
      case KeyEvent.VK_U:
        key = Input.U;
        break;
      case KeyEvent.VK_V:
        key = Input.V;
        break;
      case KeyEvent.VK_W:
        key = Input.W;
        break;
      case KeyEvent.VK_X:
        key = Input.X;
        break;
      case KeyEvent.VK_Y:
        key = Input.Y;
        break;
      case KeyEvent.VK_Z:
        key = Input.Z;
        break;
    }

    this.controller.handleInput(key);

    // TODO: Have the controller return a "dirty" flag and only repaint if set
    repaint();
  }

  @Override
  public void keyTyped(KeyEvent event) {
    // Override not required
  }

  @Override
  public void keyPressed(KeyEvent event) {
    // Override not required
  }
}
