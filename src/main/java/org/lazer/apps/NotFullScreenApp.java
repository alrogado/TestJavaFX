package org.lazer.apps;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lazer.controllers.LazerMainController;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class NotFullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, configureDataFlow(LazerMainController.class, stage));
        Scene scene = new Scene(decorator, WIDTH, HEIGHT);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                getClass().getResource("/css/jfoenix-fonts.css").toExternalForm(),
                getClass().getResource("/css/jfoenix-design.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());

        configureNotFullScreenStage(stage);
        stage.setScene(scene);
        stage.show();
    }

}
