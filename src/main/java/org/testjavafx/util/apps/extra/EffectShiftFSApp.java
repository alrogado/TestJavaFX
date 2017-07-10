package org.testjavafx.util.apps.extra;

import org.testjavafx.GuiApp;
import org.testjavafx.controllers.components.extra.ReflectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by alrogado on 5/31/17.
 */
public class EffectShiftFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GuiApp.createJFXDecorator(stage, GuiApp.initFlowConf(ReflectionController.class, stage), true);
        GuiApp.configureAndSetScene(stage, new Scene(GuiApp.decorator));
        GuiApp.configureFullScreenStage(stage);
        stage.show();
    }

}
