Views
=====
View is the blueprint of generic properties of any *Screen*.

View is exclusively bind with a Screen and inclusively bind with
a controller. See :ref:`architecture` for further details.

Currently, PrimitiveFXMVC only has one *view* and that is called
*GenericView*.

GenericView
------------
GenericView is an abstract class for general purposed views. It has properties as;

 * **Base Directory (private static String baseDir):** *baseDir* holds a directory name to look under in classpath for *Screen* files (FXML files). **Default:** "screens"
 * **Global Icon (private static Image globalIcon):** *globalIcon* is the default icon for any View that is instantiated.
 * **Screen Name (private String screenName):** *screenName* holds the name of view. It automatically adds ".fxml", so ensure not using it. For instance, "MainScreen.fxml" file's *screenName* is "MainScreen".
 * **Title (private StringProperty title):** *title* is the title of the view's window.
 * **Icon (private Image icon):** *icon* is the icon of the view's window. If empty, global icon will be used.
 * **Width/Height (private int width/height):** Width and height of the view's window.
 * **Decoratedness (private boolean decorated):** Is window decorated? **Default:** true
 * **Resizability (private boolean resizable):** Is window resizable? **Default:** true
 * **Modality (private boolean modal):** Is window modal? **Default:** false
 * **Maximizedness (private boolean maximized):** Is window maximized? **Default:** false

*GenericView* has a standard bean definitions, so these properties can be changed or updated with getters and setters.

Examples
^^^^^^^^
Consider the following example:

.. code-block:: java

    public class MainView extends GenericView {
        public MainView() {
            GenericView.setBaseDir("screens"); // this is default. it will look a folder called "screens" under classpath root.
            this.setViewName("MainScreen"); // this will automatically find MainScreen.fxml
            this.setTitle("Welcome");
            this.setWidth(640);
            this.setHeight(480);
            this.setResizable(false);
        }
    }

In this example, we create a view called *MainView* and setting title, width, height, resizability and its *screenName*.
Below, you can see how we instantiate and create *Scene* and *Stage* out of our view:

.. code-block:: java

    GenericView mainView = new MainView();
    Scene mainScene = mainView.createScene();
    Stage mainStage = mainView.createStage();

Highlights
^^^^^^^^^^
 * You can change static *baseDir* property of *GenericView* at any time for it to check any specific directory under the classpath.