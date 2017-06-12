package org.lazer.controllers;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.TimeSection;
import eu.hansolo.tilesfx.TimeSectionBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.AnimationTimer;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static org.lazer.util.EffectUtils.fadeIn;
import static org.lazer.util.GuiColors.*;

@ViewController(value = "/org/lazer/fxml/ui/main_content_tiles.fxml", title = "Material Design Example")
public class MainContentTilesController {


    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    @FXML
    Tile personalTile;

    @FXML
    Tile buttonsTile;

    public MainContentTilesController() {
        this.personalTile=this.personalTile;
    }

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        fadeIn(root);
    }
}
