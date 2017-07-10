package org.testjavafx.util.apps.extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjavafx.controllers.components.extra.Sprite3dController;
import org.testjavafx.GuiApp;

/**
 * Created by alrogado on 5/31/17.
 */
public class EffectSpriteFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GuiApp.createJFXDecorator(stage, GuiApp.initFlowConf(Sprite3dController.class, stage), true);
        GuiApp.configureAndSetScene(stage, new Scene(GuiApp.decorator));
        GuiApp.configureFullScreenStage(stage);
        stage.show();
    }

}
