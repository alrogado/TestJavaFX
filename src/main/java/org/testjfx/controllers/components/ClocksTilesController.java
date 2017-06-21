package org.testjfx.controllers.components;

import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.testjfx.components.ClockBuilder;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_clock.fxml")
public class ClocksTilesController {


    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    @FXML
    FlowGridPane flowGridPane;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        flowGridPane.getChildren().addAll(ClockBuilder.createClock());
        fadeIn(root);
    }
}
