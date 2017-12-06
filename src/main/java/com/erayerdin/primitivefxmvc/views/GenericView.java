package com.erayerdin.primitivefxmvc.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * GenericView's label is the self explanatory. It creates scene and stage on the fly.
 * It uses static and non-static icon properties to be used and title is StringProperty.
 *
 * @author erayerdin
 * @see javafx.beans.property.StringProperty
 * @see javafx.scene.image.Image
 * @see javafx.scene.Scene
 * @see javafx.stage.Stage
 */
public abstract class GenericView {
    private static String baseDir = "views";
    private static Image globalIcon = null;

    private String viewName;
    private StringProperty title;
    private Image icon = null;
    private int width = 0;
    private int height = 0;
    private boolean decorated = true;
    private boolean resizable = true;
    private boolean modal = false;
    private boolean maximized = false;

    public GenericView() {
        this.title = new SimpleStringProperty("JavaFX Application");
    }

    /**
     * Generates a view path basing on viewName (object) and baseDir (static) properties.
     * For example: GenericView(viewName="MainScreen", baseDir="fxml") -> "fxml/MainScreen.fxml"
     *
     * @return
     */
    public String generateViewPath() {
        return getBaseDir()+"/"+this.getViewName()+".fxml";
    }

    /**
     * Creates scene by calling generateViewPath and with properties as width, height.
     * If width or height less than 1, than gives no width/height parameters while
     * creating scene.
     *
     * @see GenericView#generateViewPath()
     * @see javafx.scene.Scene
     * @return
     * @throws IOException
     */
    public Scene createScene() throws IOException {
        Scene scene = null;

        String viewPath = this.generateViewPath();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(viewPath));

        if (this.getWidth() < 1 || this.getHeight() < 1)
            scene = new Scene(root);
        else
            scene = new Scene(root, this.getWidth(), this.getHeight());

        return scene;
    }

    /**
     * Creates stage by calling createScene and with almost every properties of object.
     *
     * @see GenericView#createScene()
     * @see javafx.stage.Stage
     * @return
     * @throws IOException
     */
    public Stage createStage() throws IOException {
        Stage stage = new Stage();

        if (this.getTitle() != null) stage.setTitle(this.getTitle());

        stage.setScene(this.createScene());

        stage.setResizable(this.isResizable());
        stage.setMaximized(this.isMaximized());

        if (!this.isDecorated()) stage.initStyle(StageStyle.UNDECORATED);
        if (this.isModal()) stage.initModality(Modality.APPLICATION_MODAL);

        if (this.getIcon() != null)
            stage.getIcons().add(this.getIcon());

        if (this.getIcon() == null && GenericView.getGlobalIcon() != null)
            stage.getIcons().add(GenericView.getGlobalIcon());

        return stage;
    }

    public static String getBaseDir() {
        return baseDir;
    }

    public static void setBaseDir(String baseDir) {
        GenericView.baseDir = baseDir;
    }

    public static Image getGlobalIcon() {
        return globalIcon;
    }

    public static void setGlobalIcon(Image globalIcon) {
        GenericView.globalIcon = globalIcon;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDecorated() {
        return decorated;
    }

    public void setDecorated(boolean decorated) {
        this.decorated = decorated;
    }

    public boolean isResizable() {
        return resizable;
    }

    public void setResizable(boolean resizable) {
        this.resizable = resizable;
    }

    public boolean isModal() {
        return modal;
    }

    public void setModal(boolean modal) {
        this.modal = modal;
    }

    public boolean isMaximized() {
        return maximized;
    }

    public void setMaximized(boolean maximized) {
        this.maximized = maximized;
    }
}
