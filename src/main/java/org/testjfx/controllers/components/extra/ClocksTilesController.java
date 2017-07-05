package org.testjfx.controllers.components.extra;

import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.testjfx.components.ClockBuilder;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_clock.fxml")
public class ClocksTilesController {


    @FXML
    StackPane root;
    @FXML
    FlowGridPane flowGridPane;
    @FXMLViewFlowContext
    private ViewFlowContext context;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        flowGridPane.getChildren().addAll(ClockBuilder.createClock());
        HBox hBox = new HBox();
        hBox.getChildren().addAll(ClockBuilder.createClocks());
        flowGridPane.getChildren().addAll(hBox);
        fadeIn(root);
    }
}
