package com.saphire.controllers.components.extra;

import com.saphire.components.ClockBuilder;
import com.saphire.util.EffectUtils;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;
import java.util.Objects;

@ViewController(value = "/com/saphire/fxml/ui/main_content_clock.fxml")
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
        EffectUtils.fadeIn(root);
    }
}
