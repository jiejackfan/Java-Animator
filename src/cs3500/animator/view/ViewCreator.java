package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;

/**
 * Factory class to create a view based on input arguments from cs3500.animator.Excellence1. This
 * can create 3 different types of view: svg, text, and swing (visual) view.
 */
public class ViewCreator {

  /**
   * Enum to represent the 3 different types of views a user can create.
   */
  public enum viewType {
    text, svg, visual, edit
  }

  /**
   * Public constructor of the view creator class. No fields to initialize.
   */
  public ViewCreator() {
    //
  }

  /**
   * Create one of three types of view class based on a user specified "type".
   *
   * @param type   String of the view class the user wants to create.
   * @param m      the model object that will be passed on to create a view.
   * @param width  the width of the canvas.
   * @param height the height of the canvas.
   * @param x      the x coordinate of the canvas.
   * @param y      the y coordinate of the canvas.
   * @return a View object.
   */
  public IView createViewBasedOnType(String type, ReadOnlyModel m, int width, int height, int x,
                                     int y) {
    if (viewType.visual == viewType.valueOf(type.toLowerCase())) {
      return new SwingView(m, width, height, x, y);
    } else if (viewType.text == viewType.valueOf(type.toLowerCase())) {
      return new TextView(m);
    } else if (viewType.svg == viewType.valueOf(type.toLowerCase())) {
      return new SVGView(m, width, height, x, y);
    }
    throw new IllegalStateException("Can't create the type of view class "
            + "specifi ed in the input argument.");
  }

  /**
   * Function used to create a edit view specifically. This is because EditView need to be an
   *  IEditView object instead of IView object.
   * @param type the type of view to create.
   * @param m read only model.
   * @param width integer of the width for AnimatorPanel.
   * @param height integer of the height for AnimatorPanel.
   * @param x position of JFrame window.
   * @param y position of JFrame window.
   * @return IEditView for the Animation.
   */
  public IEditView createEditView(String type, ReadOnlyModel m, int width, int height, int x,
      int y) {
    if (viewType.edit == viewType.valueOf(type.toLowerCase())) {
      return new EditView(m, width, height, x, y);
    }
    throw new IllegalStateException("Can't create the type of view class "
        + "specified in the input argument.");
  }
}
