package com.erayerdin.primitivefxmvc.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.event.ActionEvent;

/**
 * This is essential controller abstract-class for standard JavaFX controller class,
 * which implements Initializable.
 *
 * @see javafx.fxml.Initializable
 */
public abstract class GenericController implements Initializable {
    /**
     * Gets scene.
     *
     * @see javafx.scene.Scene
     * @param event
     * @return
     */
    public Scene getScene(ActionEvent event) {
        Node source = (Node) event.getSource();
        Scene scene = source.getScene();
        return scene;
    }

    /**
     * Gets window by calling getScene.
     *
     * @see GenericController#getScene(ActionEvent)
     * @see javafx.stage.Window
     * @param event
     * @return
     */
    public Window getWindow(ActionEvent event) {
        return this.getScene(event).getWindow();
    }

    /**
     * Gets stage by calling getWindow.
     *
     * @see GenericController#getWindow(ActionEvent)
     * @see javafx.stage.Stage
     * @param event
     * @return
     */
    public Stage getStage(ActionEvent event) {
        return (Stage) this.getWindow(event);
    }
}
