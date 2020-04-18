package cs3500.animator.controller;

import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import cs3500.animator.view.IView;
import cs3500.animator.view.SVGView;
import cs3500.animator.view.SwingView;
import cs3500.animator.view.TextView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Controller implementation that will be the communication between view and model. This controller
 * will take control of the timing (ticks per second) of our animation. It will tell the model the
 * current tick and tell view to update when a new tick happens.
 */
public class AnimationController implements IController, ActionListener, ChangeListener {

  private IView v;
  protected IModel m;

  protected Timer timer;
  protected int currentTick = 0;


  public static int DELAY;

  private String testStr;
  /**
   * This action listener will be used by the timer initialized in playAnimation(). The
   * taskPerformer will perform an action based on the user's given speed. It will perform the
   * following action: 1. update the current tick in the model. 2. tell the view to repaint the
   * panel based on the model with new tick.
   */
  ActionListener taskPerformer = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (m.getCurrentTick() < m.returnMaxTick()) {
        currentTick = currentTick + 1;
        m.setTick(currentTick);
        if (v instanceof EditView) {
          ((EditView) v).updateSliderPosition(currentTick);
        }
      } else if (m.getCurrentTick() == m.returnMaxTick()) {
        if (v instanceof EditView) {
          if (((EditView) v).getCheckState()) {
            currentTick = 0;
            m.setTick(currentTick);
          }
        }
      }
      v.refresh();
    }
  };

  /**
   * Constructor of the annimation controller. This is where the controller will gain access of the
   * model and the view that will be used for the animation.
   *
   * @param v a view object that will create animation in SVG, text, or swing format.
   * @param m a model object that stored the animations read from the input file.
   */
  public AnimationController(IView v, IModel m) {
    this.v = v;
    this.m = m;
    if (v instanceof EditView) {
      ((EditView) v).addActionListener(this);
      ((EditView) v).addChangeListener(this);
    }
  }

  @Override
  public void playAnimation() {
    // check instanceof
    if (v instanceof SwingView || v instanceof EditView) {
      v.render();
      timer = new Timer(DELAY, taskPerformer);
      timer.start();
    } else if (v instanceof TextView || v instanceof SVGView) {
      v.setDelay(DELAY);
      v.render();
    }
  }

  @Override
  public void setDelay(double tickPerSecond) {
    DELAY = (int) (1000 / tickPerSecond); // convert to ms per tick
  }


  @Override
  public void actionPerformed(ActionEvent e) {

    IEditView v = (IEditView) this.v;
    try {
      switch (Objects.requireNonNull(e.getActionCommand())) {

        case "Play Button":
          timer.start();
          testStr = "Pressed play button";
          break;
        //Resume/Pause button will start stop the animation. Changes the color to green if animation
        // is playing, change color to red if animation is not playing.
        case "Resume Button":
          if (timer.isRunning()) {
            timer.stop();
            v.changeResumeButtonColor(Color.RED);
          } else {
            timer.start();
            v.changeResumeButtonColor(Color.GREEN);
          }
          break;

        // Resetarts the timer.
        case "Restart Button":
          if (currentTick >= m.returnMaxTick()) {
            currentTick = 0;
            m.setTick(currentTick);
            timer.restart();
          } else {
            currentTick = 0;
            timer.restart();
          }
          break;

        case "Speed Up Button":
          int newDelay1 = DELAY - (DELAY / 5);
          DELAY = newDelay1;
          timer.setDelay(newDelay1);
          break;
        case "Slow Down Button":
          int newDelay2 = DELAY + (DELAY / 5);
          DELAY = newDelay2;
          timer.setDelay(newDelay2);
          break;

        //Repeat box actions will be handled in the swing timer actionListener implementation above.
        case "Repeat Box":
          break;

        // Load another AnimatorPanel and replace the current animation.
        case "Load Button":
          File loadLocation = v.getLoadLocation();
          System.out.println("load " + loadLocation.getAbsolutePath());
          Readable inputFileContent = null;
          try {
            inputFileContent = new StringReader(
                new String(Files.readAllBytes(Paths.get(loadLocation.getAbsolutePath()))));
          } catch (IOException ex) {
            System.out.println("open load file failed");
            ex.printStackTrace();
          }
          //reset time
          timer.stop();
          currentTick = 0;
          this.m = AnimationReader.parseFile(inputFileContent, new AnimationModel.Builder());
          v.clearAnimatorPanel();
          v.loadNewAnimatorPanel(m, m.getCanvasWidth(), m.getCanvasHeight());
          timer.start();
          break;

        case "Save SVG Button":
          File saveLocation = v.getSaveLocation();
          System.out.println(saveLocation.getAbsolutePath());
          IView svgView = new SVGView(m, 0, 0, 0, 0);
          svgView.setOutputFileName(saveLocation.getAbsolutePath() + "\\out.svg");
          svgView.setDelay(DELAY);
          IController newController = new AnimationController(svgView, m);
          newController.playAnimation();
          break;
        case "Save Text Button":
          File saveLocation2 = v.getSaveLocation();
          IView txtView = new TextView(m);
          txtView.setOutputFileName(saveLocation2.getAbsolutePath() + "\\out.txt");
          IController newController2 = new AnimationController(txtView, m);
          newController2.playAnimation();
          break;
        case "Modify Keyframe":
          List<String> tmp4 = v.getEditPanelInput();
          int time = 0;
          int x = 0;
          int y = 0;
          int red = 0;
          int green = 0;
          int blue = 0;
          double width = 0.0;
          double height = 0.0;
          try {
            String[] positions = tmp4.get(2).split(" ");
            if (positions.length != 2) {
              throw new IllegalArgumentException("input for position "
                  + "must be 2 integer seperated by space.");
            }

            String[] colors = tmp4.get(5).split(" ");
            if (colors.length != 3) {
              throw new IllegalArgumentException("input for colors must be 3 integers sperated "
                  + "by spaces.");
            }

            time = Integer.parseInt(tmp4.get(1));
            x = Integer.parseInt(positions[0]);
            y = Integer.parseInt(positions[1]);
            width = Integer.parseInt(tmp4.get(3));
            height = Integer.parseInt(tmp4.get(4));
            red = Integer.parseInt(colors[0]);
            green = Integer.parseInt(colors[1]);
            blue = Integer.parseInt(colors[2]);
          } catch (IllegalArgumentException nfe) {
            v.showErrorMsg(nfe.getMessage());
          }
          try {
            m.modifyKeyframe(tmp4.get(0), time, x, y, width, height, red, green, blue);
          } catch (IllegalArgumentException iae) {
            v.showErrorMsg(iae.getMessage());
          }
          break;
        case "Insert Keyframe":
          List<String> tmp2 = v.getInsertPanelInput();
          try {
            int tmpInt = Integer.parseInt(tmp2.get(1));
            m.insertKeyframe(tmp2.get(0), tmpInt);
          } catch (NumberFormatException nfe) {
            v.showErrorMsg("You must enter an int for time");
          } catch (IllegalArgumentException iae) {
            v.showErrorMsg(iae.getMessage());
          }
          break;
        case "Delete Keyframe":
          List<String> tmp3 = v.getInsertPanelInput();
          try {
            int tmpInt = Integer.parseInt(tmp3.get(1));
            m.deleteKeyframe(tmp3.get(0), tmpInt);
          } catch (NumberFormatException nfe) {
            v.showErrorMsg("You must enter an int for index");
          } catch (IllegalArgumentException iae) {
            v.showErrorMsg(iae.getMessage());
          }
          break;
        case "Create Shape":
          List<String> tmp = v.getShapePanelInput();
          try {
            m.createShape(tmp.get(0), tmp.get(1),0);
          } catch (IllegalArgumentException iae) {
            v.showErrorMsg(iae.getMessage());
          }
          break;
        case "Delete Shape":
          List<String> tmp1 = v.getShapePanelInput();
          try {
            m.removeShape(tmp1.get(1));
          } catch (IllegalArgumentException iae) {
            v.showErrorMsg(iae.getMessage());
          }
          break;
        case "Edit Layer Button":
          switch(v.getLayerButtonGroupOption()) {
            case "Add new layer":
              try {
                int tmpInt = Integer.parseInt(v.getLayer1Info());
                m.addNewLayer(tmpInt);
              } catch (NumberFormatException nfe) {
                v.showErrorMsg("You must enter an int for layer");
              } catch (IllegalArgumentException iae) {
                v.showErrorMsg(iae.getMessage());
              }
              break;
            case "Delete a layer":
              try {
                int tmpInt = Integer.parseInt(v.getLayer1Info());
                m.deleteLayer(tmpInt);
              } catch (NumberFormatException nfe) {
                v.showErrorMsg("You must enter an int for layer");
              } catch (IllegalArgumentException iae) {
                v.showErrorMsg(iae.getMessage());
              }
              break;
            case "Reorder two layers":
              try {
                int tmpInt = Integer.parseInt(v.getLayer1Info());
                int tmpInt2 = Integer.parseInt(v.getLayer2Info());
                m.reorderLayer(tmpInt, tmpInt2);
              } catch (IllegalArgumentException iae) {
                v.showErrorMsg(iae.getMessage());
              }
              break;
            case "Add a shape to a layer":
              try {
                int tmpInt = Integer.parseInt(v.getLayer1Info());
                m.addShapeToLayer(v.getLayerShapeName(), tmpInt);
              } catch (IllegalArgumentException iae) {
                v.showErrorMsg(iae.getMessage());
              }
              break;
            default:
              v.showErrorMsg("Invalid command");
              break;
          }
          break;
        default:
          //v.showErrorMsg("Invalid command");
          testStr = "No such action command";
          break;
      }
    } catch (NullPointerException npe) {
      testStr = "Action command is null";
    }
  }

  @Override
  public String getTestString() {
    return this.testStr;
  }

  @Override
  public int getCurrentTick() {
    return this.currentTick;
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    JSlider src = (JSlider) e.getSource();
    currentTick = src.getValue();
    m.setTick(currentTick);
    ((EditView) v).updateSliderPosition(currentTick);

  }
}
