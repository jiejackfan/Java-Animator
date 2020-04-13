package cs3500.animator.model;

import java.util.Objects;

/**
 * Position class that will store where the animation is currently at. This class will have getter
 * function for the x and y positon and also comparison of two positions.
 */
public class Position2D {

  /**
   * X and Y coordinates of a 2D gird.
   */
  private double x;
  private double y;


  /**
   * Constructor 1 of a position when the user provides x and y coordinate. This is usually called
   * at initialization.
   *
   * @param x coordinate given by user.
   * @param y coordinate given by user.
   * @throws IllegalArgumentException if the given x or y is negative
   */
  public Position2D(double x, double y) {
    /*if (x < 0 || y < 0) {
      throw new IllegalArgumentException("The coordinates cannot be negative.");
    }*/
    this.x = x;
    this.y = y;
  }

  /**
   * Copy constructor of the position class. This is usually called when the user wants to make a
   * copy of the current position.
   *
   * @param position another position object that user wants a copy of.
   */
  public Position2D(Position2D position) {
    this(position.x, position.y);
  }

  /**
   * Getter function to get the current x coordinate.
   *
   * @return the x coordinate
   */
  public double getX() {
    return x;
  }

  /**
   * Getter function to get the current y coordinate.
   *
   * @return the y coordinate
   */
  public double getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Position2D)) {
      return false;
    }
    Position2D that = (Position2D) o;
    return ((Math.abs(this.x - that.x) < 0.01) && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }

  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }

}
