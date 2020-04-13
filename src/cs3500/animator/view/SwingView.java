package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;

import javax.swing.JFrame;

/**
 * The swing view class that will create a running GUI of the animation. This view class will take
 * care of initializing the GUI window, adding a panel to the GUI, telling the GUI to refresh and to
 * show itself in the beginning.
 */
public class SwingView extends JFrame implements IView {

  /**
   * Initializing constructor for the swing view class. Takes in the readonly model for shape
   * information retrieval. Also takes in location and size information given by the input text to
   * set up the canvas and window.
   *
   * @param m      a read only model of our animation.
   * @param width  the width of our canvas.
   * @param height the height of our canvas.
   * @param x      the leftmost x position of our pop up window.
   * @param y      the top most y position of our pop up window.
   */
  public SwingView(ReadOnlyModel m, int width, int height, int x, int y) {
    super("Swing View of Animation");
    setSize(width, height);
    setLocation(x, y);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    AnimatorPanel p = new AnimatorPanel(m);
    this.add(p);
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  /**
   * Helper function for render(), makes the GUI window visible.
   */
  private void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void render() {
    makeVisible();
  }

  @Override
  public void setOutputFileName(String outputFileName) {
    setTitle(outputFileName);
    //throw new UnsupportedOperationException("Swing view should not need an output file name");
  }

  @Override
  public void setDelay(int delay) {
    throw new UnsupportedOperationException("This is not supported");
  }
}
