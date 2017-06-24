package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXButton;
import eu.hansolo.fx.regulators.ColorRegulator;
import eu.hansolo.fx.regulators.FeedbackRegulator;
import eu.hansolo.fx.regulators.Fonts;
import eu.hansolo.fx.regulators.Regulator;
import org.testjfx.components.RegulatorsPane;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.testjfx.conf.Configuration;
import org.testjfx.components.RegulatorBuilder;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.util.GuiColors;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Random;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.util.EffectUtils.fadeIn;
import static org.testjfx.util.GuiColors.FRG;
import static org.testjfx.util.IkonUtils.customizeIkon;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class RegulatorsController {


    private static final String FX_TEXT_FILL_WHITE = "-fx-text-fill:WHITE; -fx-background-color:#D63333";
    public static final String ANIMATED_OPTION_BUTTON = "animated-option-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON = "animated-option-sub-button";
    private static final String ANIMATED_OPTION_SUB_BUTTON2 = "animated-option-sub-button2";

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    Tile depositTempTile;
    Tile tipTempTile;

    JFXButton buttonStart;

    Regulator frequency;
    Regulator fluency;


    //String migLayoutConstraints = "w 45sp,h 45sp";
    String migLayoutConstraints = "w 50%,h 40%";
    int horizontalGap = 5;
    Insets padding = new Insets(5);
    public static Double WIDTHTILE = 400d;
    public static Double HEIGHTTILE = 400d;
    public static Double WIDTHTEMP = 320d;
    public static Double HEIGTHTEMP = 320d;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        initComponents();

        MigPane rootMP = new MigPane("fill");
        rootMP.add(createWorkModesNodeList(), "alignx center, wrap");
        rootMP.add(new RegulatorsPane(this), "alignx center, wrap");
        rootMP.add(createLabelsPanel(), "alignx center, wrap");
        root.getChildren().addAll(rootMP);

        Random RDM = new Random();
        final long[] lastTimerCall = {System.nanoTime()};
        AnimationTimer timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall[0] + 3_500_000_000L) {
                    //(0, 32767+32768) then subtract by 32768
                    depositTempTile.setValue((RDM.nextInt(80)-20));
                    tipTempTile.setValue((RDM.nextInt(80)-20));
                    lastTimerCall[0] = now;
                }
            }
        };
        timer.start();
        fadeIn(root);
    }

    public void initComponents() {
        createTempeperatureSparkGauage();
        createFreqFluBox();
    }

    public MigPane createStartButton() {
        MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        buttonStart = new JFXButton(Configuration.getBundleString("buttonStart.label"));
        buttonStart.setTooltip(new Tooltip(""));
        buttonStart.setButtonType(JFXButton.ButtonType.RAISED);
        buttonStart.getStyleClass().add(ANIMATED_OPTION_BUTTON);
        double r=50;
        buttonStart.setShape(new Circle(r));
        buttonStart.setMinSize(2*r, 2*r);
        buttonStart.setMaxSize(2*r, 2*r);
        buttonStart.setBorder(new Border(new BorderStroke(FRG, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        //buttonStart.setPrefSize(40,40);
        rootMP.add(buttonStart, "alignx center");
        return rootMP;
    }

    private Node createLabelsPanel(){
        MigPane rootMP = new MigPane("fill");
        rootMP.add(createPanelShoots(), "alignx left");
        rootMP.add(createPanelTotals(), "alignx right");
        return rootMP;
    }
    private Node createWorkModesNodeList(){
        MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        //rootMP.add(getJfxNodesList(), "alignx center");
        rootMP.add(getHBoxWorkModesList(), "alignx center");
        return rootMP;
    }

    private Node createMig0PanelShoots() {
        MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        Text lblSession = new Text(Configuration.getBundleString("shoots-sesion.label"));
        lblSession.setFill(GuiColors.FRG);
        lblSession.setTextOrigin(VPos.CENTER);
        lblSession.setFont(Fonts.robotoMedium(40));
        lblSession.setTextAlignment(TextAlignment.LEFT);
        Text lblShootsValue = new Text("0");
        lblShootsValue.setFill(GuiColors.FRG);
        lblShootsValue.setTextOrigin(VPos.CENTER);
        lblShootsValue.setTextAlignment(TextAlignment.RIGHT);
        lblShootsValue.setFont(Fonts.robotoMedium(40));
        JFXButton btnReload = new JFXButton();
        btnReload.setGraphic(customizeIkon(MaterialDesign.MDI_RELOAD));
        rootMP.setBackground(new Background(new BackgroundFill(GuiColors.BKG,null,null)));
        rootMP.add(lblSession, "alignx left");
        rootMP.add(lblShootsValue, "alignx center");
        rootMP.add(btnReload, "alignx right, wrap");

        return rootMP;
    }

    private Node createPanelShoots() {
        MigPane rootMP = new MigPane("fill");
        Text lblSession = new Text(Configuration.getBundleString("shoots-sesion.label"));
        lblSession.setFill(GuiColors.FRG);
        lblSession.setTextOrigin(VPos.CENTER);
        lblSession.setFont(Fonts.robotoMedium(40));
        lblSession.setTextAlignment(TextAlignment.LEFT);
        Text lblShootsValue = new Text("0");
        lblShootsValue.setFill(GuiColors.FRG);
        lblShootsValue.setTextOrigin(VPos.CENTER);
        lblShootsValue.setTextAlignment(TextAlignment.RIGHT);
        lblShootsValue.setFont(Fonts.robotoMedium(40));
        lblShootsValue.setWrappingWidth(150);
        JFXButton btnReload = new JFXButton();
        FontIcon fontIcon = customizeIkon(MaterialDesign.MDI_RELOAD);
        fontIcon.setFill(GuiColors.FRG);
        btnReload.setGraphic(fontIcon);
        rootMP.add(lblSession, "west");
        rootMP.add(lblShootsValue, "center");
        rootMP.add(btnReload, "east");

        return rootMP;
    }

    private Node createPanelTotals() {
        MigPane rootMP = new MigPane("fill");
        Text lblSession = new Text(Configuration.getBundleString("shoots-total.label"));
        lblSession.setFill(GuiColors.FRG);
        lblSession.setTextOrigin(VPos.CENTER);
        lblSession.setFont(Fonts.robotoMedium(40));
        lblSession.setTextAlignment(TextAlignment.LEFT);
        Text lblShootsValue = new Text("0");
        lblShootsValue.setFill(GuiColors.FRG);
        lblShootsValue.setTextOrigin(VPos.CENTER);
        lblShootsValue.setTextAlignment(TextAlignment.RIGHT);
        lblShootsValue.setFont(Fonts.robotoMedium(40));
        lblShootsValue.setWrappingWidth(150);
        rootMP.add(lblSession, "west");
        rootMP.add(lblShootsValue, "east");

        return rootMP;
    }

    private Node getHBoxWorkModesList() {
        HBox nodesList = new HBox();
        nodesList.setSpacing(15);
        /*nodesList.addAnimatedNode(btnWorkModes,
                (expanded) -> singletonList(new KeyValue(lblWorkModes.rotateProperty(),
                        expanded ? 15 : 0,
                        Interpolator.EASE_BOTH)));*/

        String[] modeNames = new String[]{"FPD", "15 ms", "30 ms", "100 ms", "400 ms"};
        for(String modeName:modeNames ){
            nodesList.getChildren().add(createInnerWorkModeButton(modeName, modeName.replaceAll(" ", "")+".tooltip"));
        }
        return nodesList;
    }

    private JFXButton createInnerWorkModeButton(String title, String tooltipKey) {
        JFXButton button = new JFXButton(title);
        button.setTooltip(new Tooltip(Configuration.getBundleString(tooltipKey)));
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add(ANIMATED_OPTION_BUTTON);
        button.setPrefSize(20,20);
        return button;
    }

    private Node createFreqFluBox() {
        frequency = RegulatorBuilder.createRegulator(
                Configuration.getBundleString("frecuency.label"),
                "Hz",
                "",null,
                WIDTHTEMP, HEIGTHTEMP,
                50d, 30d, 200d);
        fluency = RegulatorBuilder.createRegulator(
                Configuration.getBundleString("fluencia.label"),
                "J/cm",
                "",
                null,
                WIDTHTEMP, HEIGTHTEMP,
                96d, 20d, 130d);
        FlowGridPane regulatorsPane = new FlowGridPane(2,1, frequency, fluency);
        regulatorsPane.setHgap(horizontalGap);
        regulatorsPane.setPadding(padding);
        return regulatorsPane;
    }

    private MigPane createTempRegulator() {
        FeedbackRegulator depositTemp;
        ColorRegulator tipTemp;
        depositTemp = RegulatorBuilder.createFeedbackRegulator(
                Configuration.getBundleString("deposit.label"),
                "ºC",
                MaterialDesign.MDI_TEMPERATURE_CELSIUS,
                WIDTHTILE, HEIGHTTILE,
                50d, 30d, 200d);
        tipTemp = RegulatorBuilder.createColorRegulator(
                Configuration.getBundleString("tip.label"),
                "ºC",
                MaterialDesign.MDI_OIL_TEMPERATURE,
                WIDTHTILE, HEIGHTTILE,
                96d, 20d, 130d);

        HBox hBox = new HBox(depositTemp);
        hBox.setSpacing(20);
        hBox.setPadding(padding);

        HBox hBox2 = new HBox(tipTemp);
        hBox2.setSpacing(20);
        hBox2.setPadding(padding);

        MigPane tempPane = new MigPane("fill");
        tempPane.add(hBox, migLayoutConstraints);
        tempPane.add(hBox2, migLayoutConstraints);
        return tempPane;
    }

    private Node createTempeperatureSparkGauage() {
        depositTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("deposit.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getDepositMinValue(),
                Configuration.getDepositMaxValue(),
                false,
                LEFT);
        tipTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("tip.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getTipMinValue(),
                Configuration.getTipMaxValue(),
                false,
                RIGHT);

        FlowGridPane pane = new FlowGridPane(2,1, depositTempTile, tipTempTile);
        pane.setHgap(horizontalGap);
        pane.setPadding(padding);
        return pane;
    }

    private MigPane createTempGauage() {
        Tile depositTempTile = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                //.prefSize(WIDTH, TILE_HEIGHT)
                .title(Configuration.getBundleString("deposit.label"))
                .unit("ºC")
                .threshold(75)
                .build();

        Tile tipTempTile = TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE)
                //.prefSize(WIDTH, TILE_HEIGHT)
                .title(Configuration.getBundleString("tip.label"))
                .unit("ºC")
                .threshold(75)
                .build();


        MigPane tempPane = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        //MigPane tempPane = new MigPane("debug", "[grow,fill]", "");
        tempPane.add(depositTempTile, migLayoutConstraints);
        tempPane.add(tipTempTile, migLayoutConstraints);
        /*HBox tempPane = new HBox(depositTempTile, tipTempTile);
        tempPane.setSpacing(20);
        tempPane.setPadding(new Insets(10));*/
        return tempPane;
    }

    public Tile getDepositTempTile() {
        return depositTempTile;
    }

    public Tile getTipTempTile() {
        return tipTempTile;
    }

    public Regulator getFrequency() {
        return frequency;
    }

    public Regulator getFluency() {
        return fluency;
    }
}
