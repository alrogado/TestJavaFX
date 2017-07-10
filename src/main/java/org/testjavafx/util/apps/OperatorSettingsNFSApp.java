package org.testjavafx.util.apps;

import org.testjavafx.GuiApp;
import org.testjavafx.conf.ApplicationSettings;
import org.testjavafx.controllers.components.OperatorSettingsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by alrogado on 5/31/17.
 */
public class OperatorSettingsNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GuiApp.createJFXDecorator(stage, GuiApp.initFlowConf(OperatorSettingsController.class, stage), false);
        GuiApp.configureAndSetScene(stage, new Scene(GuiApp.decorator, ApplicationSettings.WIDTH, ApplicationSettings.HEIGHT));
        GuiApp.configureFullScreenStage(stage);
        stage.show();
    }

}
