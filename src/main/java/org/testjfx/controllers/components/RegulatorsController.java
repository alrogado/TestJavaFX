package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXButton;
import eu.hansolo.fx.regulators.Fonts;
import eu.hansolo.fx.regulators.Regulator;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
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



    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

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
        MigPane rootMP = new MigPane("fill");
        //rootMP.add(createHBoxWorkModesList(), "alignx center, aligny top, wrap");
        //rootMP.add(createMessagesBox(), "alignx center, aligny top, wrap");
        regulatorsPane = new RegulatorsPane();
        regulatorsPane.disable();
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
                    //(0, 32767+32768) then subtract by 32768
                    regulatorsPane.getDepositTempTile().setValue((RDM.nextInt(80)-20));
                    regulatorsPane.getTipTempTile().setValue((RDM.nextInt(80)-20));
                    lastTimerCall[0] = now;
                }
            }
        };
        timer.start();
        fadeIn(root);
    }

    private Node createMessagesBox() {
        Pane messagesPane = new Pane();

        messagesPane.setPadding(new Insets(5,5,5,5));
        messagesPane.getChildren().add(new Text("testeando"));
        //messagesPane.();
        return messagesPane;
    }


}
