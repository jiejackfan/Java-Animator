package cs3500.animator.model;

/**
 * The model interface that contains functions that can mutate the model. This Animation model
 * interface can create a shape, remove a shape, add motions to a shape, remove motions, and
 * contains setter functions for model's tick and canvas size.
 */
public interface IModel extends ReadOnlyModel {

  /**
   * This function will create a shape (out of rectangle, oval right now). User will store this
   * shape into two map data structures (for both motions and keyframes) that will be used to
   * represent the entire animation.
   *
   * @param shape This is a string that represents the shape user wants to create. This can be one
   *              of "rectangle", "oval"
   * @param name  This is a custom name the user can assign to this shape
   * @throws IllegalArgumentException under 3 situations: 1.shape is null or "" 2.name is null or
   *                                  "". 3.shape user wants to create does not exist
   */
  void createShape(String shape, String name);

  /**
   * Removes a shape and its corresponding list of motions and keyframes from the animation.
   *
   * @param name User can delete a shape by passing in the custom name that was assigned in the
   *             beginning
   * @throws IllegalArgumentException if the name given does not match existing shapes in the
   *                                  animation
   */
  void removeShape(String name);

  /**
   * This function will add one  motion (transition of attributes of a shape from a time to another)
   * into a list of motions to a corresponding shape.
   *
   * @param name        This is the custom name of the shape that user wants to add the motion to
   * @param startTime   Start time of the shape
   * @param startX      Start position X of the shape
   * @param startY      Start position Y of the shape
   * @param startWidth  Start width of the shape
   * @param startHeight Start height of the shape
   * @param startColorR Start red color of the shape
   * @param startColorB Start blue color of the shape
   * @param startColorG Start green color of the shape
   * @param endTime     End time of the shape
   * @param endX        End position X of the shape
   * @param endY        End position Y of the shape
   * @param endWidth    End width of the shape
   * @param endHeight   End height of the shape
   * @param endColorR   End red color of the shape
   * @param endColorB   End blue color of the shape
   * @param endColorG   End green color of the shape
   * @throws IllegalArgumentException if the name given by the user does not exist in current
   *                                  animation
   * @throws IllegalArgumentException if the given (starting and ending) position X or position Y is
   *                                  negative
   * @throws IllegalArgumentException if the given colors (starting and ending) R, G, B are not
   *                                  within the range between 0 to 255 (including 0 to 255)
   * @throws IllegalArgumentException if the given width and height (starting and ending) are
   *                                  negative
   */
  void addMotion(String name, int startTime, int startX, int startY, double startWidth,
                 double startHeight, int startColorR, int startColorG, int startColorB,
                 int endTime, int endX, double endY, double endWidth,
                 double endHeight, int endColorR,
                 int endColorG, int endColorB);

  /**
   * Remove a motion from a list of motion for a given shape.
   *
   * @param name  arbitrary str name that user assigned to their shape upon initialization
   * @param index the index of the motion the user wants to delete. This index needs to be either
   *              the first or the last motion in the list of motions
   * @throws IllegalArgumentException if the str name given by the user does not exist or null in
   *                                  current animation
   * @throws IllegalArgumentException if the index given by the user is not the first or the last
   *                                  motion in a list of motions
   */
  void removeMotion(String name, int index);

  /**
   * This function will add one keyframe (a state of a shape at a time) into a list of keyframes to
   * a corresponding shape.
   *
   * @param name   the custom name of the shape that user wants to add the motion to
   * @param time   time of the shape
   * @param x      position X of the shape
   * @param y      position Y of the shape
   * @param width  width of the shape
   * @param height height of the shape
   * @param colorR red color of the shape
   * @param colorG blue color of the shape
   * @param colorB green color of the shape
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the time given by the user is less than 0
   * @throws IllegalArgumentException if the given colors (starting and ending) R, G, B are not
   *                                  within the range between 0 to 255 (including 0 to 255)
   * @throws IllegalArgumentException if the given width and height (starting and ending) are
   *                                  negative
   */
  void addKeyframe(String name, int time, int x, int y, double width,
                   double height, int colorR, int colorG, int colorB);

  /**
   * Remove a keyframe from a list of keyframes for a given shape by the given index.
   *
   * @param name  arbitrary str name that user assigned to their shape upon initialization
   * @param index the index of the keyframe the user wants to delete
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the index given by the user is invalid
   */
  void deleteKeyframe(String name, int index);

  /**
   * Insert a keyframe at the given time. This action will not affect the appearance of the original
   * animation. The only thing it does is adding a keyframe at the given time, if there is no
   * keyframe for the time yet, without changing the animation. If there is no frame yet for a
   * shape, then a default keyframe will be added at the given time.
   *
   * @param name the name of the shape
   * @param time the insertion time
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the time given by the user is invalid
   * @throws IllegalArgumentException if the time given by the user already has a corresponding
   *                                  keyframe
   */
  void insertKeyframe(String name, int time);

  /**
   * Modify a keyframe of the given shape at the given time.
   *
   * @param name   the name of the shape
   * @param time   the time that you want to modify
   * @param x      the position x of the shape
   * @param y      the position y of the shape
   * @param width  the width of the shape
   * @param height the height of the shape
   * @param colorR the red component of the shape
   * @param colorG the green component of the shape
   * @param colorB the blue component of the shape
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the given position, color, or size is invalid
   * @throws IllegalArgumentException if the time given by the user does not have a corresponding
   *                                  keyframe or no keyframes at all
   */
  void modifyKeyframe(String name, int time, int x, int y, double width,
                      double height, int colorR, int colorG, int colorB);

  /**
   * Allow the controller to set the current tick of the system.
   *
   * @param newTick give the new tick that we want to assign to the model
   */
  void setTick(int newTick);

  /**
   * Check the model by first sorting list of motion of each shape and check if each list has
   * teleportation.
   */
  void sortAndCheckListsOfMotions();

  /**
   * Function to store parameters that will be needed to set canvas location and size later in the
   * view.
   *
   * @param x coordinate of the canvas location
   * @param y coordinate of the canvas location
   * @param w width of the canvas
   * @param h height of the canvas
   */
  void setCanvas(int x, int y, int w, int h);

}
