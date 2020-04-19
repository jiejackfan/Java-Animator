This program will display a given animation sequence in four different formats: a java swing gui, a web svg, a txt description, and an interactive gui window. This project is mainly focused on designing the Edit GUI, adding in modify capabilities, playback control, insert/delete keyframes, create/remove shapes, and also extra credit for loading/saving files.
This project is using the classic model-controller-view design. The model was designed in a previous assignment, modified in this assignment, and the description is the following:

----------------------------------------------------------------------------------------------------------------------------------------
Model Description:
This is a pictorial description of the animation model that we are trying to build. (image is in the resources folder if it won't open)

The main logic of this model will be handled in Animation Model class. This class can create a shape, remove a shape, add motions to a list of motions of a shape, add and delete keyframes of a list of keyframes, insert a keyframe, modify a keyframes, apply the changes of keyframes also to motions, check if a listOfMotion is valid, and convert current animation to strings. Animation model will implement a interface called IModel class. 

The shapes that user will create is controlled by a shape class called Shape. This class will handle the field storing of each shape. A shape type of a Shape class can be stored using enum values stored in DifferentShapes Enumeration class. This class will eliminate the need to build concrete class for each new shape.

We define motion as a transaction of state from one time to another. The transition of state means the motion will store the starting states and ending states. The starting states include: starting time, width, height, position, and color. The ending states include: ending time, width, height, position, and color. Motion will store position in a Positon2D class and store color in the awt.color library.

We define keyframe as a state of a shape at a time. The state includes: time, position, width, height, and color. The keyframe will store position in a Positon2D class and store color in the awt.color library. (NEW)

Linking these three big parts together, we utilized 2 map data structures in Animation Model. These 2 maps help us store and access the animation. The first hash map is nameMap with key=custom string name and value=IShape objects. It will help us identify which shape is which. The second hash map is animation with key=IShape object and value=List. This is logicial design because a list of transition of states is in fact an animation.

(NEW) For assignment 9, we adjusted our model to handle angle and layer information. In our model implementation, we added a regular hash map to store the layer information, with the key as layer and the value as a list of shapes within the layer. In motion, keyframe, and shape public classes, new fields were added to store the start angle and end angle information of the shapes. According setter and getter functions were included as well. 

----------------------------------------------------------------------------------------------------------------------------------------
Changes to the model - 1:
1. Changed the getAnimation() function (how view knows what to display at a particular tick) so a shape will stay on the screen even if it ended.
2. Moved read only functions of IModel to ReadOnlyModel so view does not get access to mutate the model.
3. Removed Abstract and concrete shape class, replaced them with a Enum class to represent different shape types. Using enum will elimate the need to create concrete classes when the user wants to introduce a new shape to the system.
4. Introduced new fields in the model such as canvas paramters, current tick. These fields are added because they are obtained in the builder class within the model class and will act as storage place. Added getter methods to Model. To get canvas parameters and current tick.
5. Added a public method in IModel to get starting motion of each shape. This will be used by the SVG view.
6. Added a public method in IModel to sort each shape's list of motions. This will be called in main after AnimationBuilder finished building a model. 
7. Added getAllShapes() and getAllMotionsOfShape(shape) to IModel. Will be useful for SVG view and per TA feedback.

Changes to the model - 2:
1. Another linked hashmap was added to store all the shapes and their corresponding keyframes
2. In createShape(String shape, String name) and removeShape(String name), we enhanced the method to support applying changes to both the keyframe map and the motion map to keep the state consistent acrossing two storages.
3. Operations of managing keyframes were added to the IModel, and all these operations also make according changes to the motions:
    - addKeyframe(String name, int time, int x, int y, double width, double height, int colorR, int colorG, int colorB);
    - deleteKeyframe(String name, int index);
    - insertKeyframe(String name, int time);
    - modifyKeyframe(String name, int time, int x, int y, double width, double height, int colorR, int colorG, int colorB);
4. Operation of getting keyframes at a given time was added to the ReadOnlyModel:
    - getFrame(int time);
5. A public class Keyframe was included in the model package, and it has a default constructor and a copy constructor.

(NEW) This is a list of public methods updated for this assignment: 
  In IShape: 
  - int hashCode();
  - boolean equals(Object that);
  In IModel:
  - void createShape(String shape, String name, int layer);
  - void addMotion(String name, int startTime, int startX, int startY, double startWidth,
                 double startHeight, int startColorR, int startColorG, int startColorB,
                 double startAngle, int endTime, int endX, double endY, double endWidth,
                 double endHeight, int endColorR, int endColorG, int endColorB, double endAngle);
  - void addKeyframe(String name, int time, int x, int y, double width,
                   double height, int colorR, int colorG, int colorB, double angle);

This is a list of new public methods added for this assignment: 
  In IShape: 
  - double getAngle();
  In IModel:
  - void addNewLayer(int layer);
  - void deleteLayer(int layer);
  - void reorderLayer(int layer1, int layer2);
  - void addShapeToLayer(String shapeName, int layer);

----------------------------------------------------------------------------------------------------------------------------------------
View: 4 different types:

1.Swing Visual View:
  Sets up a panel where the panel can draw animation at each tick. Has refresh and set visible.
  
2.SVG View:
  Sets up svg documents with FileWriter.
  (NEW) This view now supports rotating shapes in the animation and display according to layers.

3.Text View:
  Print toString function to a txt file.
  (NEW) This view now is able to print start angle, end angle, and layer information. 

4.Edit View:
  Sets up a panel where you can interactively play with the animation. You can start, pause, resume, replay, loop, fast forward (x1.2), and slow (x0.8) the animation. You are also able to insert a keyframe to a specific time for a named shape, delete a keyframe by providing the name of the shape and the index of the keyframe, and modify a keyframe by specifying all parameters (for position, please specify x and y, separated by space; for color, please specify r, g, and b, in order, also separated by space; if you want to leave some parameters unchanged, then please enter their original values), including the position, size (width and height), and color of a named shape at a certain time. If any parameter is incorrect, a warning window will pop up. Save and load function were added in the panel as well. You should be about to load a .txt to this view and display your animation or save your animation (in its last modified state) in either as a text file or as a SVG file. 
  
----------------------------------------------------------------------------------------------------------------------------------------
Controller:
The controller handles the initialial linking between model and view, initial setup of clocking, and tells the view to update. The clocking is setup using the Swing. Timer library, where the delay is calculated based on tickPerSecond given by the user. For different views, the playAnimation() will have different response. If playAnimation is called on a visual view or edit view, controller will ask the view to draw the window and starts the timer, everytime timer ticks it invokes refresh() on the panel so it can draw new animation. If playAnimation() is called on text or svg view, then they will output to their output file without any timer. Controller will not be the actionListener for the button click events. Thus, it overrides the actionPerformed() function. Within the action performed function, there will be a switch statement for each different button. The detailed comments for each button will be in the AnimationController documentation.

----------------------------------------------------------------------------------------------------------------------------------------
(NEW) Untils:
For assignment 9, the animation reader class now supports the feature to parse input file to obtain angle and layer information as an extension functionality. The previous input files are expected to work as before. If the angle information of a motion or a layer information of a shape is not specified, the default values are set to 0. 
The animation builder class was also altered to adapt the changes, the below list contains updated methods:
- AnimationBuilder<Doc> declareShape(String name, String type, int layer);
- AnimationBuilder<Doc> addMotion(String name, int t1, int x1, int y1, int w1, int h1, int r1,
                                  int g1, int b1, int a1, int t2, int x2, int y2, int w2, int h2,
                                  int r2, int g2, int b2, int a2);
-   AnimationBuilder<Doc> addKeyframe(String name,
                                    int t, int x, int y, int w, int h, int r, int g, int b, int a);

----------------------------------------------------------------------------------------------------------------------------------------
Additional features:
We have implemented both load and save in our Edit View.
Load: If the user clicks on the load button, a system file view will pop up. The user can select which file he/she wants to load. The loader will only show txt files and once loading is successful the old AnimatorPanel will disappear and the new AnimatorPanel will show itself.
Save: There are 2 buttons which the user can click, one to save the current model as an SVG and the other is to save as a Text file. The button click will bring up a system file view where the user can pick a directory where the output file will go. The current design will then create that output file with the name "out.svg" or "out.txt".