package org.lazer.apps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.lazer.controllers.effects.ReflectionController;
import org.lazer.controllers.effects.Sprite3dController;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class FullScreenEffectShiftApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(ReflectionController.class, stage), true);
        configureAndSetScene(stage,new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

}
