package cs3500.animator.model;

import java.awt.Color;

/**
 * This interface represents a geometric shape. It contains a lists of operations such as get the
 * name of the shape, compare different shapes. Because each shape will contain information of each
 * parameter at a particular tick, there are getter functions for getting all parameter.
 */
public interface IShape {

  /**
   * This function will find and return the custom name of each shape user created.
   *
   * @return custom str of a shape.
   */
  String getShapeName();

  /**
   * This function will find and return the custom name of each shape user created.
   *
   * @return custom str of a shape that user assigned at shape creation.
   */
  String getName();

  /**
   * This will aid the equals function that we will rewrite below.
   *
   * @return aa
   */
  int hashCode();

  /**
   * This will compare two different shapes based on its attributes.
   *
   * @param that is another shape that we would like to compare our current shape to.
   * @return boolean value of whther the two shapes are equal.
   */
  boolean equals(Object that);

  /**
   * Getter function to get this shape's color at specific tick.
   *
   * @return a Color that the shape has at tick time.
   */
  Color getColor();

  /**
   * Getter function to get this shape's Position at specific tick.
   *
   * @return a Position2D that the shape has at tick time.
   */
  Position2D getPosition();

  /**
   * Getter function to get this shape's width at specific tick.
   *
   * @return a double that the shape has at tick time.
   */
  double getWidth();

  /**
   * Getter function to get this shape's height at specific tick.
   *
   * @return a double that the shape has at tick time.
   */
  double getHeight();

  /**
   * Getter function to get this shape's shape type (DifferentShapes enum) at specific tick.
   *
   * @return a DifferentShapes object that the shape has at tick time.
   */
  DifferentShapes getShape();


}
