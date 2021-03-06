package org.testjavafx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjavafx.controllers.AppController;

import static org.testjavafx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class AppControllerFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(AppController.class, stage), true);
        configureAndSetScene(stage, new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

}
