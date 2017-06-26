package org.testjfx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.controllers.components.RegulatorsCustomLayoutController;

import static org.testjfx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class RegulatorsCustomLayoutControllerNotFullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(RegulatorsCustomLayoutController.class, stage), false);
        configureAndSetScene(stage,new Scene(decorator));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
