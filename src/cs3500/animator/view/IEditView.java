package cs3500.animator.view;

import cs3500.animator.model.ReadOnlyModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;

/**
 * IEditView is an interface that will extend all methods from IView and implement a view unique
 *  methods that will only be used by EditView. Edit view will need specific methods like getting
 *  a lot of textfield inputs, display message from the controller, mutate it panels based on
 *  controller's command. Having this extended class will make sure this new feature can only be
 *  used by Edit View and not the other previous views with less functionalities.
 */
public interface IEditView extends IView {

  /**
   * This is to force the view to have a method to set up actions for buttons.
   * All the buttons must be given this action listener.
   * Thus our Swing-based implementation of this interface will already have such a
   * method.
   * @param listener the listener for events.
   */
  void addActionListener(ActionListener listener);

  /**
   * Function for controller to get the checkbox state of the repeat checkbox.
   * @return true if "repeat" box is checked, return false otherwise.
   */
  boolean getCheckState();

  JButton getPlayButton();

  /**
   * Helper to change the resume/pause button's color based on controller's command.
   *
   * @param color green color if animation is playing, red color if animation is paused.
   */
  void changeResumeButtonColor(Color color);

  /**
   * Helper to return the text field input for creating and deleting shapes.
   * @return a list of strings that the text box recieved in top down order.
   */
  List<String> getShapePanelInput();

  /**
   * Helper to return text field input for inserting or deleting a keyframe of a particular shape.
   * @return a list of strings that the text boxes recievd in top down order.
   */
  List<String> getInsertPanelInput();

  /**
   * Helper to return text field input user give to modify a shape.
   * @return a list of strings that the text boxes recieved in top down order.
   */
  List<String> getEditPanelInput();

  /**
   * Functions will display the given string argument in a pop up window.
   * @param string an error or warning message to print on pop up window.
   */
  void showErrorMsg(String string);

  /**
   * Helper will return the File/ file path selected by the user for loading a new animation.
   * @return a java File that represents the new animation to be loaded.
   */
  File getLoadLocation();

  /**
   * Return File/file path to a directory where the user wants to save their output file in.
   * @return a java File that represents location where user wants to save their output file.
   */
  File getSaveLocation();

  /**
   * Helper for controller to clear the current animatior JPanel object so load can put a new
   *  JPanel object in.
   */
  void clearAnimatorPanel();

  /**
   * Helper for controller to create a new Animator Panel with the new animation Model and its
   *  different params.
   * @param m a read only model that represents the new animation to be loaded.
   * @param width the new animation's width.
   * @param height the new animation's height.
   */
  void loadNewAnimatorPanel(ReadOnlyModel m, int width, int height);

}
