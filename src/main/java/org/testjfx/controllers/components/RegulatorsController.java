package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import eu.hansolo.fx.regulators.Fonts;
import eu.hansolo.fx.regulators.Regulator;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikonli;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.ionicons.Ionicons;
import org.kordamp.ikonli.javafx.FontIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.GuiApp;
import org.testjfx.components.PopupNotification;
import org.testjfx.components.RegulatorsInnerControlsPane;
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
import org.testjfx.util.GuiColors;
import org.testjfx.util.IkonUtils;

import javax.annotation.PostConstruct;
import java.awt.*;
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

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    private JFXDialog dialog;

    //String migLayoutConstraints = "w 45sp,h 45sp";
    //String migLayoutConstraints = "w 50%,h 40%";

    public static int horizontalGap = 5;
    public static Insets padding = new Insets(5);

    RegulatorsPane regulatorsPane;



    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");

        root.getChildren().remove(dialog);

        MigPane rootMP = new MigPane("fill");
        //rootMP.add(createHBoxWorkModesList(), "alignx center, aligny top, wrap");
        //rootMP.add(createMessagesBox(), "alignx center, aligny top, wrap");
        //regulatorsPane = RegulatorsPane.getInstance();
        regulatorsPane = RegulatorsInnerControlsPane.getInstance();

        rootMP.add(regulatorsPane, "alignx center, aligny top, wrap");

        //rootMP.add(createHBoxWorkModesList(), "alignx center, aligny bottom, wrap");
        //regulatorsPane.layout();
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
                regulatorsPane.layout();
                if (now > lastTimerCall[0] + 3_500_000_000L) {

                    int depositTemp = RDM.nextInt(80) - 20;
                    int tipTemp = RDM.nextInt(80) - 20;

                    regulatorsPane.getDepositTempTile().setValue(depositTemp);
                    regulatorsPane.getTipTempTile().setValue(tipTemp);
                    lastTimerCall[0] = now;

                    if((tipTemp>=regulatorsPane.getTipTempTile().getSections().get(1).getStart()&&tipTemp<=regulatorsPane.getTipTempTile().getSections().get(1).getStop())
                        &&(depositTemp>=regulatorsPane.getDepositTempTile().getSections().get(1).getStart()&&depositTemp<=regulatorsPane.getDepositTempTile().getSections().get(1).getStop()))
                        regulatorsPane.disable(false);
                    else{
                        regulatorsPane.disable(true);
                    }
                    showInfo("Mesaje", "seg "+now, TrayIcon.MessageType.ERROR);
                }
            }
        };
        timer.start();
        regulatorsPane.disable(true);
        fadeIn(root);
    }

    public void showInfo(String title, String message, TrayIcon.MessageType messageType) {

        if(dialog!=null &&dialog.isVisible()){
            dialog.close();
        }
        dialog = new JFXDialog();

        JFXDialogLayout layout = new JFXDialogLayout();
        Text titleText = new Text(title);
        titleText.setFont(new Font("Roboto Bold", 28));
        layout.setHeading(titleText);
        dialog.setOverlayClose(false);
        layout.setPrefSize(650,350);

        layout.setEffect(GuiColors.DROPSHADOW_TEXT);
        VBox vbox = new VBox();
        vbox.setPrefWidth(450);
        vbox.setPrefHeight(450);
        vbox.setSpacing(7);

        Label messageLbl = new Label(message);
        messageLbl.setId("message");
        messageLbl.setFont(new Font("Arial", 30));

        vbox.getChildren().addAll(messageLbl);

        FlowGridPane pane = new FlowGridPane(2,1);
        pane.add(messageLbl, 0,0);

        Color ikonColor =GuiColors.FRG;
        Ikon ikon =Ikonli.NONE;
        switch(messageType){
            case ERROR:
                /** An error message */
                ikon = Elusive.ERROR_ALT;
                ikonColor = Color.RED;
                break;
            case WARNING:
                /** A warning message */
                ikon = Elusive.WARNING_SIGN;
                ikonColor=Color.YELLOW;
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
        pane.add(fontIcon, 1,0);
        layout.setBody(pane);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.setContent(layout);
        dialog.show(root);
    }

}
