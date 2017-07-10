package com.saphire.controllers.components.extra;

import com.saphire.util.EffectUtils;
import eu.hansolo.tilesfx.Tile;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;

import javax.annotation.PostConstruct;
import java.util.Objects;

@ViewController(value = "/com/saphire/fxml/ui/main_content_tiles.fxml")
public class TwoTilesTilesController {


    @FXML
    StackPane root;
    @FXML
    Tile personalTile;
    @FXML
    Tile personalTile2;
    @FXMLViewFlowContext
    private ViewFlowContext context;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");

        EffectUtils.fadeIn(root);
    }
}
