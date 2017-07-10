package com.saphire.util.apps;

import com.saphire.conf.ApplicationSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.saphire.controllers.PreloaderController;

import static com.saphire.GuiApp.*;

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
