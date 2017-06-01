package org.lazer.controllers;


import com.jfoenix.controls.JFXDrawer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.lazer.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.lazer.GuiApp.flowContext;
import static org.lazer.GuiApp.viewConfiguration;

@ViewController(value = "/org/lazer/fxml/ui/lazer_main.fxml", title = "Lazer Application")
public class LazerMainController {

    private static Logger logger = LoggerFactory.getLogger(LazerMainController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context = new ViewFlowContext();

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
        configureContent(ContentLazerController.class);

        /*// side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));*/

    }

    public void configureContent(Class controllerClass){
        // set the content Lazer controller
        Flow flow = new Flow(controllerClass, viewConfiguration);
        FlowHandler flowHandler = new FlowHandler(flow, flowContext, viewConfiguration);
        DefaultFlowContainer container = new DefaultFlowContainer();

        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", flow);
        final Duration containerAnimationDuration = Duration.millis(320);
        try {
            drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        } catch (FlowException e) {
            logger.error("",e);
        }
        context.register("ContentPane", drawer.getContent().get(0));
    }
}
