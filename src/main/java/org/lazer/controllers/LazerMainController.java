package org.lazer.controllers;


import com.jfoenix.controls.JFXDrawer;
import demos.datafx.ExtendedAnimatedFlowContainer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;

@ViewController(value = "/org/lazer/fxml/lazer_main.fxml", title = "Lazer Application")
public class LazerMainController {

    private static Logger logger = LoggerFactory.getLogger(LazerMainController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label label;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {

        // create the inner flow and content
        context = new ViewFlowContext();

        configureContent(ContentLazerController.class);

        /*// side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));*/

    }

    private void configureContent(Class controllerClass) throws FlowException {
        // set the content Lazer controller
        Flow contentLazerFlow = new Flow(controllerClass);
        final FlowHandler contentLazerflowHandler = contentLazerFlow.createHandler(context);
        context.register("ContentFlowHandler", contentLazerflowHandler);
        context.register("ContentFlow", contentLazerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(contentLazerflowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }
}
