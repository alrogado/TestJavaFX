package org.lazer.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lazer.controllers.MainContentTilesController;
import org.lazer.controllers.PreloaderController;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class MainContentTilesControllerNotFullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(MainContentTilesController.class, stage), false);
        configureAndSetScene(stage,new Scene(decorator, WIDTH, HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}