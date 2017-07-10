package org.testjavafx.controllers.components.extra;

import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import org.testjavafx.util.DemoFxApp;

import javax.annotation.PostConstruct;

@ViewController(value = "/org/testjavafx/fxml/ui/sprite3d.fxml")
public class Sprite3dController extends AbstractEffectsController {

    @FXML
    FlowGridPane flowGridPane;
    @FXML
    VBox vbox;
    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private StackPane root;

    @PostConstruct
    public void init() throws FlowException {
        demoFX = new DemoFxApp().getDemoFX("sprite3d",
                Screen.getPrimary().getBounds().getWidth() - 20,
                Screen.getPrimary().getBounds().getHeight() - 100);
        vbox.getChildren().addAll(((BorderPane) demoFX.getScene().getRoot()).getCenter());
        demoFX.runDemo();
    }

}
