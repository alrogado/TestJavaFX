package org.testjfx.controllers.components;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.*;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_tiles.fxml")
public class TwoTilesTilesController {


    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    @FXML
    Tile personalTile;

    @FXML
    Tile personalTile2;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");

        fadeIn(root);
    }
}
