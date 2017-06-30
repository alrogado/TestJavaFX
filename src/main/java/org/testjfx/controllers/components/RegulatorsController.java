package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXButton;
import eu.hansolo.fx.regulators.Fonts;
import eu.hansolo.fx.regulators.Regulator;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.RegulatorsPane;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import org.testjfx.conf.Configuration;
import org.testjfx.components.RegulatorBuilder;
import org.testjfx.conf.Mode;
import org.testjfx.conf.WorkModes;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.util.EffectUtils.fadeIn;
import static org.testjfx.util.GuiColors.FRG;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class RegulatorsController {

    private static Logger logger = LoggerFactory.getLogger(RegulatorsController.class);


    public static final String WORKMODE_BUTTON = "workmode-button";

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
    //String migLayoutConstraints = "w 50%,h 40%";

    public static int horizontalGap = 5;
    public static Insets padding = new Insets(5);
    public static Double WIDTHTILE = 350d;
    public static Double HEIGHTTILE = 350d;
    public static Double WIDTHTEMP = 220d;
    public static Double HEIGTHTEMP = 220d;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        initComponents();
        MigPane rootMP = new MigPane("fill");
        rootMP.add(createHBoxWorkModesList(), "alignx center, aligny top, wrap");
        rootMP.add(createMessagesBox(), "alignx center, aligny top, wrap");
        rootMP.add(new RegulatorsPane(this), "alignx center, aligny center, wrap");
        //rootMP.add(new RegulatorsPane(this), "aligny bottom, aligny bottom, wrap");
        /*FlowPane rootMP = new FlowPane();
        rootMP.getChildren().addAll(createWorkModesNodeList(),new RegulatorsPane(this));*/

        /*FlowGridPane rootMP = new FlowGridPane(1,2, createHBoxWorkModesList(), new RegulatorsPane(this));
        rootMP.setAlignment(Pos.CENTER);
*/
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

    private Node createMessagesBox() {
        Pane messagesPane = new Pane();
        //messagesPane.();
        return messagesPane;
    }

    public void initComponents() {
        createTempeperatureSparkGauage();
        createFreqFluGridPane();
        createStartButton();
    }

    public JFXButton createStartButton() {
        //todo controlar que con la inicializacion con
        // new LC().fillX().fillY().pack(), new AC(), new AC()
        // esto no se va de madre, porque al usar unos con una y otros con otra esto hace que se
        // vaya de madre toda la aplicación, es bastante desconcertante
        //MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        buttonStart = new JFXButton(Configuration.getBundleString("buttonStart.label"));
        buttonStart.setTooltip(new Tooltip(""));
        buttonStart.setButtonType(JFXButton.ButtonType.RAISED);
        buttonStart.getStyleClass().add(WORKMODE_BUTTON);
        double r=50;
        buttonStart.setShape(new Circle(r));
        buttonStart.setMinSize(2*r, 2*r);
        buttonStart.setMaxSize(2*r, 2*r);
        buttonStart.setBorder(new Border(new BorderStroke(FRG, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        return buttonStart;
    }

    private Node createHBoxWorkModesList() {
        HBox nodesList = new HBox();
        //nodesList.setEffect(new GaussianBlur());
        nodesList.setSpacing(5);
        nodesList.setPadding(new Insets(0,0,0,0));
        /*nodesList.addAnimatedNode(btnWorkModes,
                (expanded) -> singletonList(new KeyValue(lblWorkModes.rotateProperty(),
                        expanded ? 15 : 0,
                        Interpolator.EASE_BOTH)));*/
        try {
            for(Mode modeName: WorkModes.getLoadedWorkModes().getWorkModes()){
                nodesList.getChildren().add(createInnerWorkModeButton(modeName));
            }
        } catch (IOException e) {
            //todo decide what to show in messages panel
            logger.error("problem Loading workModes",e);
        }
        return nodesList;
    }

    private JFXButton createInnerWorkModeButton(Mode mode) {
        JFXButton button = new JFXButton(Configuration.getBundleString(mode.getName()+"_wm.label"));
        button.setTooltip(new Tooltip(Configuration.getBundleString(mode.getName()+"_wm.tooltip")));
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add(WORKMODE_BUTTON);
        button.setFont(Fonts.robotoMedium(50));
        //button.setPrefSize(20,20);
        return button;
    }

    private Node createFreqFluGridPane() {
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

    private Node createTempeperatureSparkGauage() {
        depositTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("deposit.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getDepositMinValue(),
                Configuration.getDepositMaxValue(),
                false,false,
                LEFT);
        tipTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("tip.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getTipMinValue(),
                Configuration.getTipMaxValue(),
                false, false,
                RIGHT);

        FlowGridPane pane = new FlowGridPane(2,1, depositTempTile, tipTempTile);
        pane.setHgap(horizontalGap);
        pane.setPadding(padding);
        return pane;
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

    public JFXButton getButtonStart() {
        return buttonStart;
    }
}
