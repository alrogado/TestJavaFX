package org.testjfx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.controllers.MainAppController;

import static org.testjfx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class MainControllerFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(MainAppController.class, stage), true);
        configureAndSetScene(stage,new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

}
