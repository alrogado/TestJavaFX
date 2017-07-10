package org.testjavafx.controllers.components.extra;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import org.testjavafx.util.DemoFxApp;

import javax.annotation.PostConstruct;

@ViewController(value = "/org/testjavafx/fxml/ui/sprite3d.fxml")
public class ReflectionController extends AbstractEffectsController {

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private StackPane root;

    @PostConstruct
    public void init() throws FlowException {
        //addEffect(time, 2 * D, new Shift(config, 380, "swans.jpeg"));
        demoFX = new DemoFxApp().getDemoFX("shift",
                Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        BorderPane pane = (BorderPane) demoFX.getScene().getRoot();
        root.getChildren().add(pane.getCenter());
        demoFX.runDemo();
    }

}
