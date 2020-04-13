import static org.junit.Assert.assertTrue;

import cs3500.animator.Excellence;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * The test class for the svg view. Tests include taking in text files with different contents.
 */
public class SVGViewTest {

  @Test
  public void testEmptyInput() {
    // Generate the command line
    String[] inputArray = new String[6];
    inputArray[0] = "-in";
    inputArray[1] = "Empty.txt";
    inputArray[2] = "-view";
    inputArray[3] = "svg";
    inputArray[4] = "-out";
    inputArray[5] = "EmptyOutput.svg";
    // Create the output
    Excellence.main(inputArray);
    // Compare with the expected output
    File createdFile = new File(inputArray[5]);
    File expectedFile = new File("Expected" + inputArray[5]);
    try {
      assertTrue(FileUtils.contentEquals(createdFile, expectedFile));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Test
  public void testShapesOnly() {
    // Generate the command line
    String[] inputArray = new String[6];
    inputArray[0] = "-in";
    inputArray[1] = "ShapesOnly.txt";
    inputArray[2] = "-view";
    inputArray[3] = "svg";
    inputArray[4] = "-out";
    inputArray[5] = "ShapesOnlyOutput.svg";
    // Create the output
    Excellence.main(inputArray);
    // Compare with the expected output
    File createdFile = new File(inputArray[5]);
    File expectedFile = new File("Expected" + inputArray[5]);
    try {
      assertTrue(FileUtils.contentEquals(createdFile, expectedFile));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  @Test
  public void testAnimation() {
    // Generate the command line
    String[] inputArray = new String[8];
    inputArray[0] = "-in";
    inputArray[1] = "toh-8.txt";
    inputArray[2] = "-view";
    inputArray[3] = "svg";
    inputArray[4] = "-out";
    inputArray[5] = "toh-at-20.svg";
    inputArray[6] = "-speed";
    inputArray[7] = "20";
    // Create the output 
    Excellence.main(inputArray);
    // Compare with the expected output
    File createdFile = new File(inputArray[5]);
    File expectedFile = new File("Expected" + inputArray[5]);
    try {
      assertTrue(FileUtils.contentEquals(createdFile, expectedFile));
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
