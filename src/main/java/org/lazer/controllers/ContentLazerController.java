package org.lazer.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.fontelico.Fontelico;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.typicons.Typicons;
import org.lazer.LazerTileSkin;
import org.reactfx.EventStreams;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Locale;
import java.util.Random;

import static org.lazer.GuiColors.*;

@ViewController(value = "/org/lazer/fxml/ui/contentLazer.fxml", title = "Material Design Example")
public class ContentLazerController {

    public static final String CONTENT_PANE = "ContentPane";

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private JFXButton centerButton;
    @FXML
    private JFXButton topButton;
    @FXML
    private JFXButton rightButton;
    @FXML
    private JFXButton bottomButton;
    @FXML
    private JFXButton leftButton;
    @FXML
    private JFXButton acceptDialogButton;
    @FXML
    private JFXButton customButton;
    @FXML
    private Label time;

    @FXML
    private StackPane root;

    @FXML
    private FlowGridPane flowGridPane;

    @FXML
    private FlowGridPane flowGridPaneInternal;

    @FXML
    private JFXDialog dialog;

    static {
        InputStream devicons = ContentLazerController.class.getResourceAsStream("/META-INF/resources/devicons/1.8.0/fonts/devicons.ttf");
        GlyphFontRegistry.register("devicons", devicons, 16);
    }

    private GlyphFont devicons = GlyphFontRegistry.font("devicons");

    Tile personalTile;
    LazerTileSkin personalSkin;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {

        root.getChildren().remove(dialog);

        customButton.setGraphic(customizeIcon(Fontelico.EMO_BEER));

        centerButton.setTextFill(FRGCOL_FILL);
        centerButton.setGraphic(customizeIcon(Fontelico.EMO_SHOOT));
        rightButton.setGraphic(customizeIcon(Fontelico.EMO_COFFEE));
        leftButton.setGraphic(customizeIcon(Typicons.CHEVRON_LEFT));

        leftButton.setGraphic(customizeIcon(Fontelico.EMO_DEVIL));
        topButton.setGraphic(customizeIcon(Typicons.BOOK));
        //topButton.setText("fuentes con gradiente");
        topButton.setTextFill(ICON_GRAD_FGR_BGR);
        //topButton.setRipplerFill(ICON_GRAD_FGR_BGR);

        StringProperty timestamp = new SimpleStringProperty();
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        ///eventos para el reloj
        EventStreams.ticks(Duration.ofMillis(1000)).subscribe(tick -> timestamp.setValue(sdf.format(System.currentTimeMillis())));


        time.textProperty().bind(timestamp);
        //dt.format(new Date(timestamp.get()))::toString));

        //root.getChildren().add(e1);

        centerButton.setOnMouseClicked((e) -> {
            dialog.setTransitionType(DialogTransition.CENTER);
            dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));
        });

        topButton.setOnMouseClicked((e) -> {
            dialog.setTransitionType(DialogTransition.TOP);
            dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));
        });

        rightButton.setOnMouseClicked((e) -> {
            dialog.setTransitionType(DialogTransition.RIGHT);
            dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));
        });

        bottomButton.setOnMouseClicked((e) -> {
            dialog.setTransitionType(DialogTransition.BOTTOM);
            dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));
        });

        leftButton.setOnMouseClicked((e) -> {
            dialog.setTransitionType(DialogTransition.LEFT);
            dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));
        });

        acceptDialogButton.setOnMouseClicked((e) -> dialog.close());
        bindingProps();

        Tile clockTile = TileBuilder.create()
                .skinType(Tile.SkinType.CLOCK)
                //.prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("Tile Reloj")
                //.text("Whatever text")
                .dateVisible(true)
                .locale(new Locale("es","ES"))
                .running(true)
                .build();

        Tile gaugeTile = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                //.prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("Tile ")
                .unit("V")
                .threshold(75)
                .build();

        Tile plusMinusTile = TileBuilder.create()
                .skinType(Tile.SkinType.PLUS_MINUS)
                //.prefSize(TILE_WIDTH, TILE_HEIGHT)
                .maxValue(30)
                .minValue(0)
                .title("Suma y resta")
                //.text("Whatever text")
                .description("Test")
                .unit("\u00B0C")
                .build();


        Tile sliderTile = TileBuilder.create()
                .skinType(Tile.SkinType.SLIDER)
                //.prefSize(TILE_WIDTH, TILE_HEIGHT)
                .title("Slider Tile")
                .text("Whatever text")
                .description("Test")
                .unit("\u00B0C")
                .barBackgroundColor(Tile.BACKGROUND)
                .roundedCorners(false)
                .build();


        //FlowGridPane flowGridPaneInternal = new FlowGridPane(1,1);
        flowGridPaneInternal.setNoOfColsAndNoOfRows(1,1);
        configureTilePane(flowGridPaneInternal);
        flowGridPaneInternal.setCenterShape(true);
        flowGridPaneInternal.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        Background whiteBckgr = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
        flowGridPaneInternal.setBackground(whiteBckgr);
        flowGridPaneInternal.backgroundProperty().setValue(Background.EMPTY);
        Tile tile = TileBuilder.create().skinType(Tile.SkinType.CUSTOM)
                .minValue(0)
                .maxValue(30)
                .title("Data")
                .titleAlignment(TextAlignment.RIGHT)
                .text("Test")
                .textVisible(false)
                .averagingPeriod(48)
                .autoReferenceValue(true)
                .tooltipText("Aloha")
                .build();



        personalTile= new Tile()/*{
            protected Skin createDefaultSkin(){
                return (personalSkin = new LazerTileSkin(personalTile));
            }
        }*/;
        tile.setMinValue(0);
        tile.setMaxValue(30);
        tile.setTitle("Data");
        tile.setTitleAlignment(TextAlignment.RIGHT);
        tile.setText("Test");
        tile.setTextVisible(false);
        tile.setAveragingPeriod(48);
        tile.setAutoReferenceValue(true);
        tile.setTooltipText("Aloha");


        tile.setBackground(whiteBckgr);
        flowGridPaneInternal.add(personalTile,0,0);
        flowGridPaneInternal.add(sliderTile,1,0);
        //flowGridPaneInternal.add(clockTile,0,1);



        configureTilePane(flowGridPane);
        flowGridPane.setNoOfColsAndNoOfRows(2,2);
        //flowGridPane.add(flowGridPaneInternal,0,1);
        flowGridPane.add(gaugeTile,1,0);
        flowGridPane.add(plusMinusTile,1,0);

        //flowGridPane.setBackground(new Background(new BackgroundFill(Color.web("#101214"), CornerRadii.EMPTY, Insets.EMPTY)));

        //root.getChildren().add(pane);

        this.value = new SimpleDoubleProperty(0);
        lastTimerCall = System.nanoTime();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now > lastTimerCall + 3_500_000_000L) {
                    gaugeTile.setValue(RND.nextDouble() * gaugeTile.getRange() * 1.5 + gaugeTile.getMinValue());
                    lastTimerCall = now;
                }
            }
        };
        timer.start();
    }

    private FontIcon customizeIcon(Ikon ikon) {
        FontIcon fontIcon = new FontIcon(ikon);
        fontIcon.setIconSize(48);
        fontIcon.setIconColor(Color.BLUE);
        fontIcon.setFill(ICON_GRAD_FGR_BGR);
        return fontIcon;
    }

    private void configureTilePane(FlowGridPane flowGridPane) {
        flowGridPane.setHgap(5);
        flowGridPane.setVgap(5);
        flowGridPane.setAlignment(Pos.CENTER);
        flowGridPane.setCenterShape(true);
        flowGridPane.setPadding(new Insets(5));
    }

    private static final Random RND = new Random();
    private long            lastTimerCall;
    private AnimationTimer  timer;
    private DoubleProperty  value;

    private void bindingProps() {
        //binding properties
        IntegerProperty ip1= new SimpleIntegerProperty(3);
        IntegerProperty ip2= new SimpleIntegerProperty();
        ip2.bind(ip1.multiply(10));

        //También se pueden haver bind de valores de propiedades
        TextField txtF = new TextField("init val");
        Label lblF = new Label();
        lblF.textProperty().bind(txtF.textProperty());
    }

}
