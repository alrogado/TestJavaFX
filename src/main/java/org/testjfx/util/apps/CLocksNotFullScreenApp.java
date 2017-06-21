package org.testjfx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.Configuration;
import org.testjfx.controllers.components.ClocksTilesController;

import static org.testjfx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class CLocksNotFullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(ClocksTilesController.class, stage), false);
        configureAndSetScene(stage,new Scene(decorator, Configuration.WIDTH, Configuration.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
