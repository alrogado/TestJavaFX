package org.testjavafx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjavafx.controllers.components.SettingsController;

import static org.testjavafx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class MainControllerNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(SettingsController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, 400, 200));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
