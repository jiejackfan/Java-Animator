package cs3500.animator.model;

import java.util.List;

/**
 * This is the read only interface of the cs3500.animator.excellence1 logic of this animation model.
 * It contains getAnimation() which is what the view will use to build an animation. It also
 * contains function like toString for text view. There are lots of a getter functions for the
 * fields in the model.
 */
public interface ReadOnlyModel {

  /**
   * This will be a function that builds a list of shapes that can be passed to view. Each shape
   * stores its Position2D, Color, width, height at a particular time. View will use this list of
   * shapes to draw each shape at a particular time. A Shape that did not start showing it self in
   * the panel will not be added to the list. A Shape that was added to the list but ended animation
   * earlier will still be put into the list, with its ending characteristics filled in. This list
   * of shapes is constructed using lists of motions.
   *
   * @param time at which each shape should be built with
   * @return a list of shapes with updated information on whats happening in that shape
   * @throws IllegalArgumentException if the given time is less than 1
   */
  List<IShape> getAnimation(int time);

  /**
   * This will be a function that builds a list of shapes that can be passed to view. Each shape
   * stores its Position2D, Color, width, height at a particular time. View will use this list of
   * shapes to draw each shape at a particular time. A Shape that did not start showing it self in
   * the panel will not be added to the list. A Shape that was added to the list but ended animation
   * earlier will still be put into the list, with its ending characteristics filled in. This list
   * of shapes is constructed using lists of keyframes.
   *
   * @param time at which each shape should be built with
   * @return a list of shapes with updated information on whats happening in that shape
   * @throws IllegalArgumentException if the given time is less than 1
   */
  List<IShape> getFrame(int time);

  /**
   * Return the complete animation as a string that discribes a shape and its list of motions.
   * declares a rectangle shape named R shape R rectangle describes the motions of shape R, between
   * two moments of animation: t == tick (x,y) == position (w,h) == dimensions (r,g,b) == color
   * (with values between 0 and 255) .
   *                        start                           end
   *          t  x   y   w  h   r   g  b    t   x   y   w  h   r   g  b
   * shape R rectangle[n]
   * motion R 1  200 200 50 100 255 0  0    10  200 200 50 100 255 0  0[n]
   * motion R 10 200 200 50 100 255 0  0    50  300 300 50 100 255 0  0[n]
   * motion R 50 300 300 50 100 255 0  0    51 300 300 50 100 255 0  0[n]
   * motion R 51 300 300 50 100 255 0  0    70  300 300 25 100 255 0 0[n]
   * motion R 70 300 300 25 100 255 0  0    100 200 200 25 100 255 0  0[n]
   * where [n] is the new line character.
   * @return the formated string as above
   * @throws IllegalStateException if any shape in the Animation has overlapping attributes or
   *                               teleportation
   */
  String toString();

  /**
   * Get the current tick of the model.
   *
   * @return an integer that represents current tick
   */
  int getCurrentTick();

  /**
   * Get the max tick that the model will run to.
   *
   * @return integer that represents max tick of the animation
   */
  int getMaxTick();

  ////////////////////////// getMaxTick() & returnMaxTick()?

  /**
   * Return the max tick that the model will run to.
   *
   * @return integer that represents max tick of the animation
   */
  int returnMaxTick();

  /**
   * Getter function to get the x coordinate of the canvas.
   *
   * @return an integer x coordinate
   */
  int getCanvasX();

  /**
   * Getter function to get the y coordinate of the canvas.
   *
   * @return an integer y coordinate.
   */
  int getCanvasY();

  /**
   * Getter function to get the width of the canvas.
   *
   * @return an integer that represent width
   */
  int getCanvasWidth();

  /**
   * Getter function to get the height of the canvas.
   *
   * @return an integer that represent height
   */
  int getCanvasHeight();

  /**
   * Getter function to get a list of Shapes that the model current stored.
   *
   * @return a list of IShape
   */
  List<IShape> getAllShapes();

  /**
   * Getter function to get a list of Motions of a particular shape that the model current stored.
   *
   * @return a list of motions
   */
  List<Motion> getAllMotionsOfShape(IShape shape);

  /**
   * Getter function to get a list of the shapes as they appear first time in the animation.
   *
   * @return a list of shapes
   */
  List<IShape> getShapesBeginning();

}
