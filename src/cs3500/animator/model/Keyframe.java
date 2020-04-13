package cs3500.animator.model;

import java.awt.Color;

/**
 * This class represents a state of a time of one particular shape. The states include:  time,
 * width, height, position, color.
 */
public class Keyframe {

  // time.
  private int time;

  // positon.
  private Position2D position;

  // width height. They are assigned double as per assignment.
  private double width;
  private double height;

  // color. Using the awt color library.
  private Color color;

  /**
   * Public constructor for keyframe. This is used when model wants to enter a new keyframe into a
   * shape's list of keyframes.
   *
   * @param time     the given time
   * @param position the given position
   * @param width    the given width
   * @param height   the given height
   * @param color    the given color
   * @throws IllegalArgumentException if the given time is less than 0
   * @throws IllegalArgumentException if any width or height are negative
   */
  public Keyframe(int time, Position2D position, double width, double height,
                  Color color) {
    // Check whether the given times are valid.
    if (time < 0) {
      throw new IllegalArgumentException("Invalid time.");
    }
    // Check whether the given sizes are valid.
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("The size must be positive.");
    }
    this.time = time;
    this.position = position;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  /**
   * Copy constructor of a keyframe. This will be used when we want to create a copy of this
   * keyframe in the model.
   *
   * @param kf a motion that we want to copy in the model.
   */
  public Keyframe(Keyframe kf) {
    this.time = kf.time;
    this.position = kf.position;
    this.width = kf.width;
    this.height = kf.height;
    this.color = kf.color;
  }

  @Override
  public String toString() {
    String output;
    output = String.join(" ", Integer.toString(time),
            Double.toString(position.getX()),
            Double.toString(position.getY()),
            Double.toString(width), Double.toString(height),
            Integer.toString(color.getRed()),
            Integer.toString(color.getGreen()),
            Integer.toString(color.getBlue()));
    return output;
  }

  /**
   * Getter function to get time.
   *
   * @return time
   */
  public int getTime() {
    return time;
  }

  /**
   * Getter function to get position.
   *
   * @return position
   */
  public Position2D getPosition() {
    return position;
  }

  /**
   * Getter function to get width.
   *
   * @return width
   */
  public double getWidth() {
    return width;
  }


  /**
   * Getter function to get height.
   *
   * @return height
   */
  public double getHeight() {
    return height;
  }


  /**
   * Getter function to get color.
   *
   * @return color
   */
  public Color getColor() {
    return color;
  }


  /**
   * Function to change time.
   *
   * @param time the user wants to change as the new time
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * Function to change position.
   *
   * @param position the user wants to change as the new position
   */
  public void setPosition(Position2D position) {
    this.position = position;
  }

  /**
   * Function to change width.
   *
   * @param width the user wants to change as the new width
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * Function to change height.
   *
   * @param height the user wants to change as the new height
   */
  public void setHeight(double height) {
    this.height = height;
  }

  /**
   * Function to change color.
   *
   * @param color the user wants to change as the new color
   */
  public void setColor(Color color) {
    this.color = color;
  }

}
