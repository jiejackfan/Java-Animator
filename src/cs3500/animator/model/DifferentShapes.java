package cs3500.animator.model;

/**
 * Enumeration of the different shapes we want to create. This is a feature to remove concrete shape
 * classes in the model.
 */
public enum DifferentShapes {
  rectangle("rectangle"), oval("oval"), ellipse("ellipse");

  private final String shape;

  /**
   * Constructor of the enumeration.
   *
   * @param shape a string representation of each element in the enum.
   */
  DifferentShapes(String shape) {
    this.shape = shape;
  }

  /**
   * Getter function to get the string representation of each enum.
   *
   * @return String representation of enum.
   */
  public String getShape() {
    return shape;
  }

}
