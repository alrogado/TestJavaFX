package com.saphire.util.apps.extra;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.saphire.controllers.components.extra.Sprite3dController;

import static com.saphire.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class EffectSpriteFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(Sprite3dController.class, stage), true);
        configureAndSetScene(stage, new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

}
