package org.testjfx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.conf.Configuration;
import org.testjfx.controllers.components.RegulatorsController;

import static org.testjfx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class RegulatorsNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(RegulatorsController.class, stage), false);
        configureAndSetScene(stage,new Scene(decorator, 400, 200));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
