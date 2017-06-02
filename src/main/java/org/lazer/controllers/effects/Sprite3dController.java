package org.lazer.controllers.effects;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import org.lazer.util.DemoFxApp;

import javax.annotation.PostConstruct;

@ViewController(value = "/org/lazer/fxml/ui/sprite3d.fxml", title = "Material Design Example")
public class Sprite3dController extends AbstractEffectsController {

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private StackPane root;

    @PostConstruct
    public void init() throws FlowException {
        demoFX = new DemoFxApp().getDemoFX("sprite3d",
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight()-180);
        BorderPane pane = (BorderPane) demoFX.getScene().getRoot();
        pane.setStyle("-fx-background-color:white;");
        root.getChildren().add(pane.getCenter());
        demoFX.runDemo();
    }

}
