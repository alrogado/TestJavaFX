package org.lazer.util.apps;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lazer.controllers.PreloaderController;

import java.net.URL;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class PreloaderApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = FXMLLoader.load(getClass().getResource("/org/lazer/fxml/preloader.fxml"));
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
