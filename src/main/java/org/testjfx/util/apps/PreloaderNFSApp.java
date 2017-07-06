package org.testjfx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.conf.ApplicationConf;
import org.testjfx.controllers.PreloaderController;

import static org.testjfx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class PreloaderNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(PreloaderController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, ApplicationConf.WIDTH, ApplicationConf.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
