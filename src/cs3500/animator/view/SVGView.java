package cs3500.animator.view;

import cs3500.animator.model.DifferentShapes;
import cs3500.animator.model.IShape;
import cs3500.animator.model.Motion;
import cs3500.animator.model.ReadOnlyModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * The SVG view class that will generate a .svg file with the animation expressed in the SVG format.
 * This view class will take care of creating an new output file if it does not exist, writing the
 * animation description according to the SVG documentation to the output file, and close the
 * writing when done.
 */
public class SVGView implements IView {

  private String outputFileName;
  private final ReadOnlyModel readOnlyModel;
  private double delay;
  private File saveToLocation = null;

  /**
   * Initializing constructor for the SVG view class. Takes in the readonly model for shape
   * information retrieval. Also takes in location and size information given by the input text to
   * set up the window.
   *
   * @param m      the given readonly model
   * @param width  the given width of the window
   * @param height the given height of the window
   * @param x      the given x of the location
   * @param y      the given y of the location
   */
  public SVGView(ReadOnlyModel m, int width, int height, int x, int y) {
    this.readOnlyModel = m;
  }

  @Override
  public void refresh() {
    // no need to refresh
    throw new UnsupportedOperationException("This function is not supported in SVG view.");
  }

  @Override
  public void render() {
    File file = new File(outputFileName);
    // Create the output file first if it does not exist
    String svgContent = "";
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException ioe) {
        throw new IllegalStateException("Output file creation failed.");
      }
    }
    // Write to the text file
    try {
      FileWriter fileWriter = new FileWriter(outputFileName);
      List<IShape> initialShapes = readOnlyModel.getShapesBeginning();
      List<IShape> allShapes = readOnlyModel.getAllShapes();
      int counter = 0;
      svgContent += String.format("<svg viewBox=\"%d %d %d %d\" version=\"1.1\" "
                      + "xmlns=\"http://www.w3.org/2000/svg\">\n",
              readOnlyModel.getCanvasX(),
              readOnlyModel.getCanvasY(),
              readOnlyModel.getCanvasWidth(),
              readOnlyModel.getCanvasHeight());
      // Add the animation shape by insertion order
      for (IShape s : allShapes) {
        if (!readOnlyModel.getAllMotionsOfShape(s).isEmpty()) {
          switch (s.getShape()) {
            case rectangle:
              svgContent += String.format("\t<rect id=\"%s\" x=\"%.3f\" y=\"%.3f\" width=\"%.3f\" "
                              + "height=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\">\n",
                      initialShapes.get(counter).getName(),
                      initialShapes.get(counter).getPosition().getX(),
                      initialShapes.get(counter).getPosition().getY(),
                      initialShapes.get(counter).getWidth(),
                      initialShapes.get(counter).getHeight(),
                      initialShapes.get(counter).getColor().getRed(),
                      initialShapes.get(counter).getColor().getGreen(),
                      initialShapes.get(counter).getColor().getBlue());
              svgContent = getShapeAnimationDetail(s, svgContent) + "\t</rect>\n";
              break;
            case ellipse:
            case oval:
              svgContent += String.format("\t<ellipse id=\"%s\" cx=\"%.3f\" "
                              + "cy=\"%.3f\" rx=\"%.3f\" "
                              + "ry=\"%.3f\" fill=\"rgb(%d,%d,%d)\" visibility=\"hidden\">\n",
                      initialShapes.get(counter).getName(),
                      initialShapes.get(counter).getPosition().getX(),
                      initialShapes.get(counter).getPosition().getY(),
                      initialShapes.get(counter).getWidth(),
                      initialShapes.get(counter).getHeight(),
                      initialShapes.get(counter).getColor().getRed(),
                      initialShapes.get(counter).getColor().getGreen(),
                      initialShapes.get(counter).getColor().getBlue());
              svgContent = getShapeAnimationDetail(s, svgContent) + "\t</ellipse>\n";
              break;
            default:
              throw new IllegalArgumentException("Please provide a valid shape");
          }
        }
        counter++;
      }
      svgContent += "</svg>";
      fileWriter.write(svgContent);
      fileWriter.close();
    } catch (IOException ioe) {
      throw new IllegalStateException("Failed to write to the output file.");
    }
  }


  /**
   * This is a helper method that creates animation details for the given shape according to the SVG
   * format and adds to the current SVG file content.
   *
   * @param s          the given shape
   * @param svgContent the current SVG file contents
   * @return a String that represents the new SVG file contents after the addition
   */
  private String getShapeAnimationDetail(IShape s, String svgContent) {
    // Generate SVG descriptions for all the changes in all motions of the given shape
    List<Motion> allMotions = readOnlyModel.getAllMotionsOfShape(s);
    for (Motion m : allMotions) {
      double startTime = m.getStartTime() * delay;
      double duration = (m.getEndTime() - m.getStartTime()) * delay;

      //if the animation is about to start
      if (m.getStartTime() == m.getEndTime()) {
        svgContent += String.format("\t\t<set attributeName=\"visibility\" attributeType=\"CS\" "
            + "to=\"visible\" begin=\"%.3fms\" dur=\"0.000ms\" fill=\"freeze\" />", startTime);
      }

      if (m.getStartPosition().getX() != m.getEndPosition().getX()) {
        String x = (s.getShape() != DifferentShapes.rectangle) ? "cx" : "x";
        svgContent += String.format("\t\t<animate attributeType=\"xml\" "
                        + "begin=\"%.3fms\" dur=\"%.3fms\" "
                        + "attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\"/>\n",
                startTime, duration, x, m.getStartPosition().getX(),
                m.getEndPosition().getX());
      }
      if (m.getStartPosition().getY() != m.getEndPosition().getY()) {
        String y = (s.getShape() != DifferentShapes.rectangle) ? "cy" : "y";
        svgContent += String.format("\t\t<animate attributeType=\"xml\" "
                        + "begin=\"%.3fms\" dur=\"%.3fms\" "
                        + "attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\"/>\n",
                startTime, duration, y, m.getStartPosition().getY(), m.getEndPosition().getY());
      }
      if (m.getStartWidth() != m.getEndWidth()) {
        String w = (s.getShape() != DifferentShapes.rectangle) ? "rx" : "width";
        svgContent += String.format("\t\t<animate attributeType=\"xml\" "
                        + "begin=\"%.3fms\" dur=\"%.3fms\" "
                        + "attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\"/>\n",
                startTime, duration, w, m.getStartWidth(), m.getEndWidth());
      }
      if (m.getStartHeight() != m.getEndHeight()) {
        String h = (s.getShape() != DifferentShapes.rectangle) ? "ry" : "height";
        svgContent += String.format("\t\t<animate attributeType=\"xml\" "
                        + "begin=\"%.3fms\" dur=\"%.3fms\" "
                        + "attributeName=\"%s\" from=\"%.3f\" to=\"%.3f\" fill=\"freeze\"/>\n",
                startTime, duration, h, m.getStartHeight(), m.getEndHeight());
      }
      if (!m.getStartColor().equals(m.getEndColor())) {
        svgContent += String.format("\t\t<animate attributeType=\"xml\" "
                        + "begin=\"%.3fms\" dur=\"%.3fms\" "
                        + "attributeName=\"fill\" from=\"rgb(%d,%d,%d)\" to=\"rgb(%d,%d,%d)\""
                        + " fill=\"freeze\"/>\n",
                startTime, duration, m.getStartColor().getRed(), m.getStartColor().getGreen(),
                m.getStartColor().getBlue(), m.getEndColor().getRed(), m.getEndColor().getGreen(),
                m.getEndColor().getBlue());
      }
    }
    return svgContent;
  }

  @Override
  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  @Override
  public void setDelay(int delay) {
    this.delay = delay;
  }

}
