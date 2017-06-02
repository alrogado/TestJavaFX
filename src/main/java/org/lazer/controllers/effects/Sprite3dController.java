package org.lazer.controllers.effects;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import org.lazer.util.DemoFxApp;

import javax.annotation.PostConstruct;

@ViewController(value = "/org/lazer/fxml/ui/sprite3d.fxml", title = "Material Design Example")
public class Sprite3dController extends AbstractEffectsController {

    @FXML
    private StackPane root;

    @PostConstruct
    public void init() throws FlowException {

        demoFX = new DemoFxApp().getDemoFX("sprite3d",
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        BorderPane pane = (BorderPane) demoFX.getScene().getRoot();
        pane.setStyle("-fx-background-color:white;");
        root.getChildren().add(pane.getCenter());
        demoFX.runDemo();
    }

}
