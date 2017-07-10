package com.saphire.util.apps;

import com.saphire.controllers.components.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static com.saphire.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class MainControllerNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(MainController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, 400, 200));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}