package org.testjavafx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjavafx.conf.ApplicationSettings;
import org.testjavafx.controllers.components.PasswordAlpahabetController;

import static org.testjavafx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class PasswordAlphabetNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(PasswordAlpahabetController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, ApplicationSettings.WIDTH, ApplicationSettings.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
