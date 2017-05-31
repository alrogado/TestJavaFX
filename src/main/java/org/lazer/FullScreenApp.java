package org.lazer;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class FullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, configureDataFlow(GuiAppController.class, stage));
        Scene scene = new Scene(decorator);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                getClass().getResource("/css/jfoenix-fonts.css").toExternalForm(),
                getClass().getResource("/css/jfoenix-design.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());

        configureFullScreenStage(stage);
        stage.setScene(scene);
        stage.show();
    }

}
