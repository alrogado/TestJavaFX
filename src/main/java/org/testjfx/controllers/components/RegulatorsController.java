package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikonli;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.javafx.FontIcon;
import org.reactfx.util.Interpolator;
import org.reactfx.value.Val;
import org.reactfx.value.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.RegulatorsInnerControlsPane;
import org.testjfx.components.RegulatorsPane;
import org.testjfx.conf.ApplicationConf;
import org.testjfx.util.GuiColors;
import org.testjfx.util.IkonUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.time.Duration;
import java.util.Objects;
import java.util.Random;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class RegulatorsController {

    public static int horizontalGap = 5;
    public static Insets padding = new Insets(5);
    private static Logger logger = LoggerFactory.getLogger(RegulatorsController.class);
    @FXML
    StackPane root;

    //String migLayoutConstraints = "w 45sp,h 45sp";
    //String migLayoutConstraints = "w 50%,h 40%";
    RegulatorsPane regulatorsPane;
    @FXMLViewFlowContext
    private ViewFlowContext context;
    private JFXDialog dialog;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");

        regulatorsPane = RegulatorsInnerControlsPane.getInstance();

        regulatorsPane.setPredefinedWorkMode(ApplicationConf.getInstance().getConfigValue("workmode.name"));

        MigPane rootMP = new MigPane("fill");
        rootMP.add(regulatorsPane, "alignx center, aligny top, wrap");
        //regulatorsPane = RegulatorsPane.getInstance();

        /*FlowPane rootMP = new FlowPane();
        rootMP.getChildren().addAll(regulatorsPane);*/

        /*FlowGridPane rootMP = new FlowGridPane(1,2, createHBoxWorkModesList(), new RegulatorsPane(this));
        rootMP.setAlignment(Pos.CENTER);*/
        root.getChildren().addAll(rootMP);

         Var<Integer> obs = Var.newSimpleVar(1);
         Val<Integer> animValue = Val.animate(obs, Duration.ofMillis(500), Interpolator.EASE_IN_INTEGER);
        // No se puede hacer un bind y luego hacer un setvlue
         //regulatorsPane.getDepositTempTile().valueProperty().asObject().addListener();

        Random RDM = new Random();
        final long[] lastTimerCall = {System.nanoTime()};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                regulatorsPane.layout();
                if (now > lastTimerCall[0] + 3_500_000_000L) {

                    int depositTemp = RDM.nextInt(80) - 20;
                    int tipTemp = RDM.nextInt(80) - 20;

                    regulatorsPane.getDepositTempTile().setValue(depositTemp);
                    //obs.setValue(depositTemp);
                    regulatorsPane.getTipTempTile().setValue(tipTemp);
                    lastTimerCall[0] = now;

                    /*if((tipTemp>=regulatorsPane.getTipTempTile().getSections().get(1).getStart()&&tipTemp<=regulatorsPane.getTipTempTile().getSections().get(1).getStop())
                        &&(depositTemp>=regulatorsPane.getDepositTempTile().getSections().get(1).getStart()&&depositTemp<=regulatorsPane.getDepositTempTile().getSections().get(1).getStop()))
                        regulatorsPane.disable(false);
                    else{
                        regulatorsPane.disable(true);
                    }
                    showInfo("Mensaje", "Seg "+now, TrayIcon.MessageType.ERROR);*/
                }
            }
        };
        timer.start();
        fadeIn(root);
    }

    public void showInfo(String title, String message, TrayIcon.MessageType messageType) {
        closeDialog();

        dialog = new JFXDialog();

        JFXDialogLayout layout = new JFXDialogLayout();
        Text titleText = new Text(title);
        //to change the font use .jfx-layout-body in .css
        layout.setHeading(titleText);
        dialog.setOverlayClose(false);
        layout.setPrefSize(650, 350);

        layout.setEffect(GuiColors.DROPSHADOW_TEXT);
        VBox vbox = new VBox();
        vbox.setPrefWidth(450);
        vbox.setPrefHeight(450);
        vbox.setSpacing(7);

        Label messageLbl = new Label(message);
        vbox.getChildren().addAll(messageLbl);

        FlowGridPane pane = new FlowGridPane(2, 1);
        pane.add(messageLbl, 0, 0);

        Color ikonColor = GuiColors.FRG;
        Ikon ikon = Ikonli.NONE;
        switch (messageType) {
            case ERROR:
                /** An error message */
                ikon = Elusive.ERROR_ALT;
                ikonColor = Color.RED;
                break;
            case WARNING:
                /** A warning message */
                ikon = Elusive.WARNING_SIGN;
                ikonColor = Color.YELLOW;
                break;
            case INFO:
                /** An information message */
                ikon = Elusive.DELL;//INFO_SIGN;
                break;
            case NONE:
                /** Simple message */
                ikon = Elusive.DELL;
                break;
        }
        FontIcon fontIcon = IkonUtils.customizeIkon(ikon);
        layout.setBorder(new Border(new BorderStroke(ikonColor, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(2))));
        fontIcon.setIconSize(170);
        fontIcon.setFill(ikonColor);
        fontIcon.setEffect(GuiColors.DROPSHADOW_COMP);
        pane.add(fontIcon, 1, 0);
        layout.setBody(pane);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setContent(layout);
        dialog.show(root);
    }

    public void closeDialog() {
        if (dialog != null && dialog.isVisible()) {
            dialog.close();
        }
    }

}
