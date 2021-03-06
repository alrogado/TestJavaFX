package org.testjavafx.util.apps;

import org.testjavafx.conf.ApplicationSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjavafx.controllers.PreloaderController;

import static org.testjavafx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class PreloaderNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(PreloaderController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, ApplicationSettings.WIDTH, ApplicationSettings.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
