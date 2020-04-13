package cs3500.animator.controller;

/**
 * Represents a controller for animation. Links the view and model together so view can get access
 * to reading a model and not mutate it.
 */
public interface IController {

  /**
   * Execute animation. Will do two different things based on what view we currently have. 1. If the
   * view in controller is a swing view. The controller will command the view to display window and
   * start counting time. 2. If the view in controller is a SVG or text view. The controller will
   * command the view to write to an output file.
   */
  void playAnimation();

  /**
   * Sets the speed in tick per second that the user wants to run the animation.
   *
   * @param tickPerSecond the ticks persecond of an animation.
   */
  void setDelay(double tickPerSecond);

  /**
   * Helper to output the current message stored in the testStr variable.
   * Used in JUnit test for controller to check for action listener.
   * @return a string stored in testStr
   */
  String getTestString();

  /**
   * Get the current time of the timer.
   * @return integer time in seconds.
   */
  int getCurrentTick();

}
