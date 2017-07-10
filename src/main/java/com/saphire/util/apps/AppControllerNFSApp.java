package com.saphire.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.saphire.conf.ApplicationSettings;
import com.saphire.controllers.AppController;

import static com.saphire.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class AppControllerNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(AppController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, ApplicationSettings.WIDTH, ApplicationSettings.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
