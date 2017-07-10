package org.testjavafx.util.apps.extra;

import org.testjavafx.conf.ApplicationSettings;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjavafx.controllers.components.extra.TwoTilesTilesController;

import static org.testjavafx.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class MainContentTilesControllerNFSApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, initFlowConf(TwoTilesTilesController.class, stage), false);
        configureAndSetScene(stage, new Scene(decorator, ApplicationSettings.WIDTH, ApplicationSettings.HEIGHT));
        configureNotFullScreenStage(stage);
        stage.show();
    }

}
