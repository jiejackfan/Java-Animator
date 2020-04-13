import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.DifferentShapes;
import cs3500.animator.model.IModel;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Position2D;
import cs3500.animator.model.Shape;
import java.awt.Color;
import org.junit.Test;

/**
 * Test class for our animation model. Verify that our model can create shapes and add each shape's
 * list of motions. Also verify the model can remove shape and list of motions. Lastly, the test
 * should prove the model can correctly print out a string version of the entire animation and can
 * create a list of shapes that contains shapes with its characteristics at a certain time.
 */
public class AnimationModelTest {

  IModel animationOne = new AnimationModel();

  /**
   * createShape(shape, name) Invalid: shape = null/"", name = null/"" valid: create one given
   * shape, and create more than one shapes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeNull() {
    animationOne.createShape(null, "R");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeEmptyString() {
    animationOne.createShape("", "R");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeNameNull() {
    animationOne.createShape("rectangle", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeNameEmptyString() {
    animationOne.createShape("rectangle", "");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateShapeNotExist() {
    animationOne.createShape("triangle", "T");
  }

  @Test
  public void testShapesNoMotionToString() {
    animationOne.createShape("rectangle", "R");
    animationOne.createShape("oval", "O");
    assertEquals("Shape R rectangle\nShape O oval\n", animationOne.toString());
  }

  /**
   * removeShape(name) invalid: name does not exist in the animation valid: name exist in the
   * animation.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveEmpty() {
    animationOne.removeShape("C");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemove() {
    animationOne.createShape("rectangle", "R");
    animationOne.createShape("oval", "O");
    animationOne.removeShape("C");
  }

  @Test
  public void testValidRemove() {
    animationOne.createShape("rectangle", "R");
    animationOne.createShape("oval", "O");
    animationOne.removeShape("R");
    animationOne.removeShape("O");
    assertEquals("", animationOne.toString());
  }

  /**
   * AddMotion(...)
   */

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionShapeNotCreated() {
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidName() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("C", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartTime() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", -1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndTime() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, -1,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionStartTimeGreaterThanEndTime() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 100, 200, 200, 50,
            100, 255, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartWidth() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, -10,
            100, 255, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndWidth() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100, 255, 0, 0, 20,
            200, 200, -50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartHeight() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            -100.75, 255, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndHeight() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 255, 0, 0, 20,
            200, 200, 50, -10, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartColorRed1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 700, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartColorRed2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, -700, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartColorGreen1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, -255, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartColorGreen2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 256, 0, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartColorBlue1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 0, -1, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidStartColorBlue2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 0, 800, 20,
            200, 200, 50, 100, 255, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndColorRed1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 255, 0, 0, 20,
            200, 200, 50, 100, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndColorRed2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 255, 0, 0, 20,
            200, 200, 50, 100, 700, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndColorGreen1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 255, 0, 20,
            200, 200, 50, 100, 255, -2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndColorGreen2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 255, 0, 20,
            200, 200, 50, 100, 255, 800, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndColorBlue1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 0, 0, 20,
            200, 200, 50, 100, 255, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddMotionInvalidEndColorBlue2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 200, 200, 200,
            100.75, 0, 0, 0, 20,
            200, 200, 50, 100, 255, 0, 900);
  }

  @Test
  public void testShapesOrderedMotionsToString() {
    animationOne.createShape("rectangle", "R");
    animationOne.createShape("oval", "O");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("O", 6, 440, 70, 120,
            60, 0, 0, 255, 20,
            440, 70, 120, 60, 0, 0, 255);
    animationOne.addMotion("O", 20, 440, 70, 120,
            60, 0, 0, 255, 50,
            440, 250, 120, 60, 0, 0, 255);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 200.0 200.0 50.0 100.0 255 0 0 "
                    + "10 200.0 200.0 50.0 100.0 255 0 0\n"
                    + "motion R 10 200.0 200.0 50.0 100.0 255 0 0 "
                    + "50 300.0 300.0 50.0 100.0 255 0 0\n"
                    + "Shape O oval\n"
                    + "motion O 6 440.0 70.0 120.0 60.0 0 0 255 "
                    + "20 440.0 70.0 120.0 60.0 0 0 255\n"
                    + "motion O 20 440.0 70.0 120.0 60.0 0 0 255 "
                    + "50 440.0 250.0 120.0 60.0 0 0 255\n",
            animationOne.toString());
  }

  @Test
  public void testSameShapeSameMotionsDifferentNames() {
    animationOne.createShape("rectangle", "R1");
    animationOne.createShape("oval", "O2");
    animationOne.createShape("oval", "O1");
    animationOne.createShape("rectangle", "R2");

    animationOne.addMotion("O2", 6, 440, 70, 120,
            60, 0, 0, 255, 20,
            440, 70, 120, 60, 0, 0, 255);
    animationOne.addMotion("O2", 20, 440, 70, 120,
            60, 0, 0, 255, 50,
            440, 250, 120, 60, 0, 0, 255);
    animationOne.addMotion("R1", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R1", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R2", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R2", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("O1", 6, 440, 70, 120,
            60, 0, 0, 255, 20,
            440, 70, 120, 60, 0, 0, 255);
    animationOne.addMotion("O1", 20, 440, 70, 120,
            60, 0, 0, 255, 50,
            440, 250, 120, 60, 0, 0, 255);

    assertEquals("Shape R1 rectangle\n"
                    + "motion R1 1 200.0 200.0 50.0 100.0 255 0 0 "
                    + "10 200.0 200.0 50.0 100.0 255 0 0\n"
                    + "motion R1 10 200.0 200.0 50.0 100.0 255 0 0 "
                    + "50 300.0 300.0 50.0 100.0 255 0 0\n"
                    + "Shape O2 oval\n"
                    + "motion O2 6 440.0 70.0 120.0 60.0 0 0 255 "
                    + "20 440.0 70.0 120.0 60.0 0 0 255\n"
                    + "motion O2 20 440.0 70.0 120.0 60.0 0 0 255 "
                    + "50 440.0 250.0 120.0 60.0 0 0 255\n"
                    + "Shape O1 oval\n"
                    + "motion O1 6 440.0 70.0 120.0 60.0 0 0 255 "
                    + "20 440.0 70.0 120.0 60.0 0 0 255\n"
                    + "motion O1 20 440.0 70.0 120.0 60.0 0 0 255 "
                    + "50 440.0 250.0 120.0 60.0 0 0 255\n"
                    + "Shape R2 rectangle\n"
                    + "motion R2 1 200.0 200.0 50.0 100.0 255 0 0 "
                    + "10 200.0 200.0 50.0 100.0 255 0 0\n"
                    + "motion R2 10 200.0 200.0 50.0 100.0 255 0 0 "
                    + "50 300.0 300.0 50.0 100.0 255 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testShapesUnorderedMotionsToString() {
    animationOne.createShape("rectangle", "R");
    animationOne.createShape("oval", "O");

    animationOne.addMotion("O", 20, 440, 70, 120,
            60, 0, 0, 255, 50,
            440, 250, 120, 60, 0, 0, 255);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("O", 6, 440, 70, 120,
            60, 0, 0, 255, 20,
            440, 70, 120, 60, 0, 0, 255);

    assertEquals("Shape R rectangle\n"
                    + "motion R 1 200.0 200.0 50.0 100.0 255 0 0 "
                    + "10 200.0 200.0 50.0 100.0 255 0 0\n"
                    + "motion R 10 200.0 200.0 50.0 100.0 255 0 0 "
                    + "50 300.0 300.0 50.0 100.0 255 0 0\n"
                    + "Shape O oval\n"
                    + "motion O 6 440.0 70.0 120.0 60.0 0 0 255 "
                    + "20 440.0 70.0 120.0 60.0 0 0 255\n"
                    + "motion O 20 440.0 70.0 120.0 60.0 0 0 255 "
                    + "50 440.0 250.0 120.0 60.0 0 0 255\n",
            animationOne.toString());
  }

  /**
   * CheckValidAnimation(name) - this function is called in the public functions.
   */
  @Test
  public void testNoShape() {
    assertEquals("", animationOne.toString());
  }

  @Test
  public void testShapeNoMotion() {
    // Create shapes
    animationOne.createShape("rectangle", "R");
    animationOne.createShape("oval", "O");
    assertEquals("Shape R rectangle\nShape O oval\n", animationOne.toString());
  }

  @Test
  public void testShapeGetName() {
    IShape r = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R",
            DifferentShapes.rectangle);
    assertEquals("rectangle", r.getShapeName());
  }

  @Test
  public void testShapesHashCode() {
    IShape r1 = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R1",
        DifferentShapes.rectangle);
    IShape r2 = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R2",
        DifferentShapes.rectangle);
    assertFalse(r1.hashCode() == r2.hashCode());
  }

  @Test
  public void testShapesEqual() {
    IShape r1 = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R",
        DifferentShapes.rectangle);
    IShape r2 = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R",
        DifferentShapes.rectangle);
    assertTrue(r1.equals(r2));
  }

  @Test
  public void testShapesNotEqual() {
    IShape r1 = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R1",
            DifferentShapes.rectangle);
    IShape r2 = new Shape(new Color(0, 0, 0), new Position2D(0, 0), 10, 10, "R2",
            DifferentShapes.rectangle);
    assertFalse(r1.equals(r2));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveMotionNameDoesNotExist() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("O", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveMotionNameNull() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion(null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveMotionNameEmpty() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("", 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveMotionIndexTooSmall() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("R", -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveMotionIndexTooLarge() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("R", 800);
  }

  @Test
  public void testRemoveFirstMotion() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("R", 0);
    assertEquals("Shape R rectangle\n"
            + "motion R 10 200.0 200.0 50.0 100.0 255 0 0 "
            + "50 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R 50 300.0 300.0 50.0 100.0 255 0 0 "
            + "51 300.0 300.0 50.0 100.0 255 0 0\n"
            + "motion R 51 300.0 300.0 50.0 100.0 255 0 0 "
            + "70 300.0 300.0 25.0 100.0 255 0 0\n", animationOne.toString());
  }

  @Test
  public void testRemoveLastMotion() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("R", 3);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 200.0 200.0 50.0 100.0 255 0 0 "
                    + "10 200.0 200.0 50.0 100.0 255 0 0\n"
                    + "motion R 10 200.0 200.0 50.0 100.0 255 0 0 "
                    + "50 300.0 300.0 50.0 100.0 255 0 0\n"
                    + "motion R 50 300.0 300.0 50.0 100.0 255 0 0 "
                    + "51 300.0 300.0 50.0 100.0 255 0 0\n",
            animationOne.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRemoveMotionFromMiddle() {
    animationOne.createShape("rectangle", "R");

    animationOne.addMotion("R", 1, 200, 200, 50,
            100, 255, 0, 0, 10,
            200, 200, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 10, 200, 200, 50,
            100, 255, 0, 0, 50,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 50, 300, 300, 50,
            100, 255, 0, 0, 51,
            300, 300, 50, 100, 255, 0, 0);
    animationOne.addMotion("R", 51, 300, 300, 50,
            100, 255, 0, 0, 70,
            300, 300, 25, 100, 255, 0, 0);
    animationOne.removeMotion("R", 1);
  }


  /**
   * deleteKeyframe(...)
   *
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the index given by the user is invalid
   */

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeInvalidName1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.deleteKeyframe("O", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeInvalidName2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.deleteKeyframe("", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeInvalidName3() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.deleteKeyframe(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeInvalidIndex1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.deleteKeyframe("R", -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeInvalidIndex2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.deleteKeyframe("R", 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDeleteKeyframeFromEmpty() {
    animationOne.createShape("rectangle", "R");
    animationOne.deleteKeyframe("R", 0);
  }

  @Test
  public void testDeleteFirstKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
    animationOne.deleteKeyframe("R", 0);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testDeleteMiddleKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 11 0.0 0.0 1.0 1.0 0 0 0 41 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
    animationOne.deleteKeyframe("R", 1);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 41 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testDeleteLastKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
    animationOne.deleteKeyframe("R", 1);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  /**
   * insertKeyframe(...)
   *
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the time given by the user is invalid
   * @throws IllegalArgumentException if the time given by the user already has a corresponding
   *                                  keyframe
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInsertKeyframeInvalidName() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("O", 11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInsertKeyframeInvalidTime() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", -11);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInsertExistingKeyframe1() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInsertExistingKeyframe2() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 41);
  }

  @Test
  public void testInsertKeyframeToEmpty1() {
    animationOne.createShape("rectangle", "R");
    animationOne.insertKeyframe("R", 1);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 0.0 0.0 0 0 0 1 0.0 0.0 0.0 0.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testInsertKeyframeToEmpty11() {
    animationOne.createShape("rectangle", "R");
    animationOne.insertKeyframe("R", 11);
    assertEquals("Shape R rectangle\n"
                    + "motion R 11 0.0 0.0 0.0 0.0 0 0 0 11 0.0 0.0 0.0 0.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testInsertKeyframeAtFront() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 11, 0, 0, 1, 1,
            0, 0, 0, 11, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 11, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 1);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 11 0.0 0.0 1.0 1.0 0 0 0 41 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testInsertKeyframeInMiddle() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 11 0.0 0.0 1.0 1.0 0 0 0 41 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testInsertKeyframeGreaterThanMaxTick() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 101);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 41 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 41 0.0 0.0 1.0 1.0 0 0 0 101 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
    assertEquals(101, animationOne.getMaxTick());
  }

  /**
   * modifyKeyframe(...)
   *
   * @throws IllegalArgumentException if the str name given by the user does not exist or empty or
   *                                  null in current animation
   * @throws IllegalArgumentException if the given position, color, or size is invalid
   * @throws IllegalArgumentException if the time given by the user does not have a corresponding
   *                                  keyframe or no keyframes at all
   */

  @Test(expected = IllegalArgumentException.class)
  public void testModifyKeyframeNotExistingName() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("O", 1, 2, 2, 2, 2, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifyKeyframeNullName() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe(null, 1, 2, 2, 2, 2, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifyKeyframeInvalidWidth() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("R", 1, 2, 2, -2, 2, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifyKeyframeInvalidHeight() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("R", 1, 2, 2, 2, -2, 255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifyKeyframeInvalidColor() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("R", 1, 2, 2, 2, 2, -255, 255, 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModifyEmptyKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.modifyKeyframe("R", 1, 2, 2, 2, 2, 255, 255, 255);
  }

  @Test
  public void testModifyFirstKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("R", 1, 2, 2, 2, 2, 255, 255, 255);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 2.0 2.0 2.0 2.0 255 255 255 1 2.0 2.0 2.0 2.0 255 255 255\n"
                    + "motion R 1 2.0 2.0 2.0 2.0 255 255 255 11 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 11 0.0 0.0 1.0 1.0 0 0 0 41 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testModifyMiddleKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("R", 11, 2, 2, 2, 2, 255, 255, 255);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 2.0 2.0 2.0 2.0 255 255 255\n"
                    + "motion R 11 2.0 2.0 2.0 2.0 255 255 255 41 0.0 0.0 1.0 1.0 0 0 0\n",
            animationOne.toString());
  }

  @Test
  public void testModifyLastKeyframe() {
    animationOne.createShape("rectangle", "R");
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 1, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.addMotion("R", 1, 0, 0, 1, 1,
            0, 0, 0, 41, 0, 0, 1, 1,
            0, 0, 0);
    animationOne.insertKeyframe("R", 11);
    animationOne.modifyKeyframe("R", 41, 2, 2, 2, 2, 255, 255, 255);
    assertEquals("Shape R rectangle\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 1 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 1 0.0 0.0 1.0 1.0 0 0 0 11 0.0 0.0 1.0 1.0 0 0 0\n"
                    + "motion R 11 0.0 0.0 1.0 1.0 0 0 0 41 2.0 2.0 2.0 2.0 255 255 255\n",
            animationOne.toString());
  }

}
