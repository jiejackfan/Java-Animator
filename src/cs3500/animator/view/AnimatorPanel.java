package cs3500.animator.view;

import cs3500.animator.model.DifferentShapes;
import cs3500.animator.model.IShape;
import cs3500.animator.model.ReadOnlyModel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JPanel;

/**
 * The swing panel class that will draw the animation stored in a ReadOnly model. This panel class
 * will take care of painting the animation of the current tick. It will call getAnimation() from
 * the model to get a {@code List<Shape>} where each shape has information of Color, Position,
 * width, height of itself at a particular tick.
 */
public class AnimatorPanel extends JPanel {
  private ReadOnlyModel m;

  /**
   * Public constructor used to initialize the panel. Gives the panel access to a model so it can
   * display the {@code List<Shape>} currently stored in the model.
   *
   * @param m a read only model that has the animation.
   */
  public AnimatorPanel(ReadOnlyModel m) {
    this.m = m;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;

    //List<IShape> listOfShape = m.getAnimation(m.getCurrentTick());
    List<IShape> listOfShape = m.getFrame(m.getCurrentTick());

    //Go through each shape in the List of shape and draw the corresponding shape.
    //Draws the shapes in order from first to last so the last object will be on top.
    for (IShape shape : listOfShape) {
      if (shape.getShape() == DifferentShapes.rectangle) {
        AffineTransform transform = g2.getTransform();
        transform.rotate(Math.toRadians(shape.getAngle()),
                shape.getPosition().getX() + shape.getWidth() / 2,
                shape.getPosition().getY() + shape.getHeight() / 2);
        g2.transform(transform);
        g2.setColor(shape.getColor());
        g2.fillRect((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());

      }
      else if (shape.getShape() == DifferentShapes.oval
              || shape.getShape() == DifferentShapes.ellipse) {
        AffineTransform transform = g2.getTransform();
        transform.rotate(Math.toRadians(shape.getAngle()),
                shape.getPosition().getX() + shape.getWidth() / 2,
                shape.getPosition().getY() + shape.getHeight() / 2);
        g2.transform(transform);
        g2.rotate(Math.toRadians(shape.getAngle()));
        g2.setColor(shape.getColor());
        g2.fillOval((int) shape.getPosition().getX(), (int) shape.getPosition().getY(),
                (int) shape.getWidth(), (int) shape.getHeight());
      }
      g2.setTransform(new AffineTransform());
    }
  }

}
