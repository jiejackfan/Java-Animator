package cs3500.animator.view;

/**
 * View for animation: display the animation panel and provide visual to the user. This class will
 * take care of render (starting) the view, refreshing the view everytime controller updates its
 * tick and also set output file name for each view.
 */
public interface IView {

  /**
   * Refresh view to reflect change in the game state. Used in swing state to repain the canvas with
   *  animation of a new tick.
   *
   * @throws UnsupportedOperationException in text and SVG view because no animation will be played
   *                                       in those two classes live.
   */
  void refresh();

  /**
   * Called at the beginning of each view to start their animation building process.
   *  For swing view, render will display the GUI window.
   *  For text and svg, render will use FileWriter to write to an output file.
   */
  void render();

  /**
   * Set the output file name for the text. Function should be called after contructing the view.
   *
   * @param outputFileName String that you want to name the output file with. Given as an input arg.
   *                       In swing view, output file name will be the title of the GUI window.
   */
  void setOutputFileName(String outputFileName);

  /**
   * Set the delay in view.
   *
   * @param delay the given delay.
   */
  void setDelay(int delay);




}
