package cs3500.animator.model;

import java.awt.Color;
import java.util.Objects;

/**
 * A class implementation of a Shape in our animation. A Shape will have 2 uses. 1. One use is for
 * building the "animation" hash map. The hashmap will use a shape as the Key. 2. Second use for a
 * Shape is a {@code List<Shape>} will get passed to the view for the view to build an animation.
 * Therefore, each shape will store all parameters (color, pos, width, height, shape, name) of a
 * shape at a particular tick.
 */
public class Shape implements IShape {

  protected Color color;
  protected Position2D position;
  protected double width;
  protected double height;
  protected DifferentShapes shape;
  protected String name;

  /**
   * Constructor of abstract shape that does not conduct assignment. This will be used when the the
   * system creates a shape without a list of motions.
   *
   * @param name  name of the shape that should be initialized.
   * @param shape shape of the shape that should be initialized.
   */
  public Shape(String name, DifferentShapes shape) {
    //give default values to different fields.
    this.color = new Color(0, 0, 0);
    this.position = new Position2D(0, 0);
    this.width = 1;
    this.height = 1;
    this.shape = shape;
    this.name = name;
  }

  /**
   * Constructor of abstract shape that conducts assignment. This will be used if system creates a
   * shape with initialization values.
   *
   * @param color    value of the shape that should be initialized.
   * @param position value of the shape that should be initialized.
   * @param width    value of the shape that should be initialized.
   * @param height   value of the shape that should be initialized.
   * @param name     name of the shape that should be initialized.
   * @param shape    shape of the shape that should be initialized.
   */
  public Shape(Color color, Position2D position, double width, double height, String name,
               DifferentShapes shape) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("The width and height must be positive");
    }
    this.color = color;
    this.position = position;
    this.width = width;
    this.height = height;
    this.name = name;
    this.shape = shape;
  }

  /**
   * Copy constructor of abstract shape so the user can make a copy of another shape.
   *
   * @param shape the user wants to make a copy of.
   */
  public Shape(Shape shape) {
    this.color = shape.color;
    this.position = shape.position;
    this.width = shape.width;
    this.height = shape.height;
    this.shape = shape.shape;
    this.name = shape.name;
  }

  @Override
  public String getShapeName() {
    return this.shape.getShape();
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, position, width, height, shape, name);
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    } else {
      if (!(that instanceof Shape)) {
        return false;
      }
      return (this.color.equals(((Shape) that).color))
              && (this.position.equals(((Shape) that).position))
              && (Math.abs(this.width - ((Shape) (that)).width) < 0.1)
              && (Math.abs(this.height - ((Shape) (that)).height) < 0.1)
              && (this.shape == ((Shape) (that)).shape)
              && (this.name.equals(((Shape) (that)).name));
    }
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public Position2D getPosition() {
    return this.position;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public DifferentShapes getShape() {
    return this.shape;
  }


}
