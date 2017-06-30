package org.testjfx.controllers.components;

import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.RegulatorBuilder;
import org.testjfx.conf.Configuration;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Random;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.components.RegulatorsPane.HEIGHTTILE;
import static org.testjfx.components.RegulatorsPane.WIDTHTILE;
import static org.testjfx.controllers.components.RegulatorsController.*;
import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class OperatorSettingsController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;
    private Double height = 400d;
    private Double width= 400d;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        MigPane rootMP = new MigPane("fill");
        rootMP.add(createTempeperatureSparkGauage(),"alignx center, wrap");
        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }

    private Node createTempeperatureSparkGauage() {
        Tile depositTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("deposit.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getDepositMinValue(),
                Configuration.getDepositMaxValue(),
                true, true,
                LEFT);
        Tile tipTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("tip.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getTipMinValue(),
                Configuration.getTipMaxValue(),
                true,true,
                RIGHT);
        Tile diodoTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("diodo.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getDepositMinValue(),
                Configuration.getDepositMaxValue(),
                true,true,
                LEFT);
        Tile machineTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("machine.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getTipMinValue(),
                Configuration.getTipMaxValue(),
                true,true,
                RIGHT);

        FlowGridPane pane = new FlowGridPane(2,2, depositTempTile, tipTempTile, diodoTempTile, machineTempTile);
        pane.setHgap(horizontalGap);
        pane.setPadding(padding);

        Random RDM = new Random();
        final long[] lastTimerCall = {System.nanoTime()};
        AnimationTimer timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (now > lastTimerCall[0] + 3_500_000_000L) {
                    //(0, 32767+32768) then subtract by 32768
                    depositTempTile.setValue((RDM.nextInt(80)-20));
                    tipTempTile.setValue((RDM.nextInt(80)-20));
                    diodoTempTile.setValue((RDM.nextInt(80)-20));
                    machineTempTile.setValue((RDM.nextInt(80)-20));
                    lastTimerCall[0] = now;
                }
            }
        };
        timer.start();
        return pane;
    }
}
