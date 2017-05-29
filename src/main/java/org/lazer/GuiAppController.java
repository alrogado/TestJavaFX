package org.lazer;


import com.jfoenix.controls.JFXDrawer;
import demos.datafx.ExtendedAnimatedFlowContainer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.lazer.controllers.ContentLazerController;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;

@ViewController(value = "fxml/lazer-f.fxml", title = "Lazer Application")
public class GuiAppController {

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
    public void init() throws Exception {

        // create the inner flow and content
        context = new ViewFlowContext();

        // set the default controller
        Flow dialogLazerFlowCtrl = new Flow(ContentLazerController.class);

        final FlowHandler flowHandler = dialogLazerFlowCtrl.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", dialogLazerFlowCtrl);
        final Duration containerAnimationDuration = Duration.millis(3200);
        try {
            drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        }catch(Exception e){
            //e.printStackTrace();
        }
        context.register("ContentPane", drawer.getContent().get(0));

        /*// side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));*/

    }
}
