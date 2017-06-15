package org.lazer.util.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lazer.controllers.MainController;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class FullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(MainController.class, stage), true);
        configureAndSetScene(stage,new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

}
