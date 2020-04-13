package cs3500.animator;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.controller.IController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IEditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.ViewCreator;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Main function of this animator that will be converted into the .jar file. Accpets different
 * arguments like input file, speed, output file name, and view type to run an animation.
 */
public class ExcellenceCLI {
  /**
   * The main method that executes the program.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    double tickPerSecond = 1;
    String inputFileName = "";
    String outputFileName = "System.out";
    String viewType = "";

    int i = 0;
    int j = 0;

    String arg;

    while (i < args.length) {
      arg = args[i];

      switch (arg) {
        case "-in":
          arg = args[++i];
          inputFileName = arg;
          break;
        case "-view":
          arg = args[++i];
          viewType = arg;
          break;
        case "-out":
          arg = args[++i];
          outputFileName = arg;
          break;
        case "-speed":
          arg = args[++i];
          tickPerSecond = Double.parseDouble(arg);
          break;
        default:
          throw new IllegalArgumentException("Invalid argument.");
      }
      i++;
    }

    if (inputFileName.equals("")) {
      throw new IllegalArgumentException("No given input file.");
    } else if (viewType.equals("")) {
      throw new IllegalArgumentException("No given view type.");
    }

    AnimationBuilder<IModel> modelBuilder = new AnimationModel.Builder();

    IModel m;
    try {
      Readable inputFileContent = new StringReader(
              new String(Files.readAllBytes(Paths.get(inputFileName))));
      m = AnimationReader.parseFile(inputFileContent, modelBuilder);
    } catch (IOException e) {
      throw new IllegalArgumentException("The input file does not exist.");
    }

    IController c;

    if (viewType.equalsIgnoreCase("edit")) {
      IEditView v = new ViewCreator().createEditView(viewType, m, m.getCanvasWidth(),
          m.getCanvasHeight(), m.getCanvasX(), m.getCanvasY());
      v.setOutputFileName(outputFileName);
      c = new AnimationController(v, m);
    }
    else {
      IView v = new ViewCreator().createViewBasedOnType(viewType, m, m.getCanvasWidth(),
          m.getCanvasHeight(), m.getCanvasX(), m.getCanvasY());
      v.setOutputFileName(outputFileName);
      c = new AnimationController(v, m);
    }

    c.setDelay(tickPerSecond);
    c.playAnimation();
  }
}
