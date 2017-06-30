package org.testjfx.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.conf.Configuration;
import org.testjfx.controllers.components.PasswordNumberController;

import static org.testjfx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class PasswordNumberNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(PasswordNumberController.class, stage), false);
        configureAndSetScene(stage,new Scene(decorator, Configuration.WIDTH, Configuration.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
