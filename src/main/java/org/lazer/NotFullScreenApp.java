package org.lazer;

import com.sun.org.apache.xalan.internal.xsltc.dom.MultiValuedNodeHeapIterator;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.lazer.GuiApp.*;

/**
 * Created by alrogado on 5/31/17.
 */
public class NotFullScreenApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        createJFXDecorator(stage, configureDataFlow(GuiAppController.class, stage));
        Scene scene = new Scene(decorator, WIDTH, HEIGHT);
        configureNotFullScreenStage(stage);
        stage.setScene(scene);
        stage.show();
    }

}
