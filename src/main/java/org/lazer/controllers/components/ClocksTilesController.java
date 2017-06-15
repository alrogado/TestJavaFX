package org.lazer.controllers.components;

import eu.hansolo.tilesfx.Tile;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import org.lazer.components.ClockB;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.lazer.util.EffectUtils.fadeIn;

@ViewController(value = "/org/lazer/fxml/ui/main_content_tiles.fxml", title = "Material Design Example")
public class ClocksTilesController {


    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        root.getChildren().addAll(ClockB.createClocks());
        fadeIn(root);
    }
}
