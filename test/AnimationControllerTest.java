import static org.junit.Assert.assertEquals;

import cs3500.animator.controller.AnimationController;
import cs3500.animator.model.AnimationModel;
import cs3500.animator.model.IModel;
import cs3500.animator.view.EditView;
import cs3500.animator.view.IEditView;
import java.awt.event.ActionEvent;
import org.junit.Test;

/**
 * Animator Controller test will feature tests on the Action Listener events. It will test whether a
 * particular action event's action command can be recognized by actionPerformed(). Also tests
 * whether actionPerformed will give correct reaction to false action command.
 */
public class AnimationControllerTest {

  // Helper function to help setup model controller view objects so tests can
  // manipulate those objects.
  IModel m = new AnimationModel();
  IEditView v = new EditView(m, 1000, 1000, 0, 0);
  AnimationController c = new AnimationController(v, m);

  @Test
  public void testPlayButton() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
        ActionEvent.ACTION_PERFORMED, "Play Button");

    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("Play Button", playButton.getActionCommand());
    assertEquals("Pressed play button", c.getTestString());
  }

  @Test
  public void testPlayButtonWrongCommand() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
        ActionEvent.ACTION_PERFORMED, "Play");
    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("No such action command", c.getTestString());

  }

  @Test
  public void testPlayButtonCommandNull() {
    ActionEvent playButton = new ActionEvent(v.getPlayButton(),
        ActionEvent.ACTION_PERFORMED, null);
    c.playAnimation();
    c.actionPerformed(playButton);
    assertEquals("Action command is null", c.getTestString());
  }


}
