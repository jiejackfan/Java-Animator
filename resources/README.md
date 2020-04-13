This program will display a given animation sequence in four different formats: a java swing gui, a web svg, a txt description, and an interactive gui window. (NEW)
This project is using the classic model-controller-view design. The model was designed in a previous assignment, modified in this assignment, and the description is the following:

----------------------------------------------------------------------------------------------------------------------------------------
Model Description:
This is a pictorial description of the animation model that we are trying to build. (image is in the resources folder if it won't open)

The main logic of this model will be handled in Animation Model class. This class can create a shape, remove a shape, add motions to a list of motions of a shape, add and delete keyframes of a list of keyframes, insert a keyframe, modify a keyframes, apply the changes of keyframes also to motions, check if a listOfMotion is valid, and convert current animation to strings. Animation model will implement a interface called IModel class. (NEW)

The shapes that user will create is controlled by a shape class called Shape. This class will handle the field storing of each shape. A shape type of a Shape class can be stored using enum values stored in DifferentShapes Enumeration class. This class will eliminate the need to build concrete class for each new shape.

We define motion as a transaction of state from one time to another. The transition of state means the motion will store the starting states and ending states. The starting states include: starting time, width, height, position, and color. The ending states include: ending time, width, height, position, and color. Motion will store position in a Positon2D class and store color in the awt.color library.

We define keyframe as a state of a shape at a time. The state includes: time, position, width, height, and color. The keyframe will store position in a Positon2D class and store color in the awt.color library. (NEW)

Linking these three big parts together, we utilized 2 map data structures in Animation Model. These 2 maps help us store and access the animation. The first hash map is nameMap with key=custom string name and value=IShape objects. It will help us identify which shape is which. The second hash map is animation with key=IShape object and value=List. This is logicial design because a list of transition of states is in fact an animation.

----------------------------------------------------------------------------------------------------------------------------------------
Changes to the model:
1. Changed the getAnimation() function (how view knows what to display at a particular tick) so a shape will stay on the screen even if it ended.
2. Moved read only functions of IModel to ReadOnlyModel so view does not get access to mutate the model.
3. Removed Abstract and concrete shape class, replaced them with a Enum class to represent different shape types. Using enum will elimate the need to create concrete classes when the user wants to introduce a new shape to the system.
4. Introduced new fields in the model such as canvas paramters, current tick. These fields are added because they are obtained in the builder class within the model class and will act as storage place. Added getter methods to Model. To get canvas parameters and current tick.
5. Added a public method in IModel to get starting motion of each shape. This will be used by the SVG view.
6. Added a public method in IModel to sort each shape's list of motions. This will be called in main after AnimationBuilder finished building a model. 
7. Added getAllShapes() and getAllMotionsOfShape(shape) to IModel. Will be useful for SVG view and per TA feedback.

Changes to the model (NEW):
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

----------------------------------------------------------------------------------------------------------------------------------------
View: 4 different types:

1.Swing Visual View:
  Sets up a panel where the panel can draw animation at each tick. Has refresh and set visible.
  
2.SVG View:
  Sets up svg documents with FileWriter.
  
3.Text View:
  Print toString function to a txt file.

4.Edit View (NEW):
  Sets up a panel where you can interactively play with the animation. You can start, pause, resume, replay, loop, fast forward (x1.2), and slow (x0.8) the animation. You are also able to insert a keyframe to a specific time for a named shape, delete a keyframe by providing the name of the shape and the index of the keyframe, and modify a keyframe by specifying all parameters, including the position, size (width and height), and color of a named shape at a certain time. Save and load function were added in the panel as well. You should be about to load a .txt to this view and display your animation or save your animation (in its last modified state) in either as a text file or as a SVG file.
  
----------------------------------------------------------------------------------------------------------------------------------------
Controller:
The controller handles the initialial linking between model and view, initial setup of clocking, and tells the view to update. The clocking is setup using the Swing. Timer library, where the delay is calculated based on tickPerSecond given by the user. For different views, the playAnimation() will have different response. If playAnimation is called on a visual view or edit view, controller will ask the view to draw the window and starts the timer, everytime timer ticks it invokes refresh() on the panel so it can draw new animation. If playAnimation() is called on text or svg view, then they will output to their output file without any timer. 
