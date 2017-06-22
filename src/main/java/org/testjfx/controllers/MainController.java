package org.testjfx.controllers;


import com.jfoenix.controls.*;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.typicons.Typicons;
import org.testjfx.conf.Configuration;
import org.testjfx.components.ClockBuilder;
import org.testjfx.controllers.components.RegulatorsController;
import org.testjfx.controllers.components.SettingsController;
import org.testjfx.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.testjfx.GuiApp.*;
import static org.testjfx.util.IkonUtils.customizeIkon;

@ViewController(value = "/org/testjfx/fxml/ui/main.fxml", title = "Application")
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private JFXRippler settingsRippler;
    @FXML
    private JFXRippler houseRippler;
    @FXML
    private JFXToolbar toolbar;
    @FXML
    private JFXDrawer drawer;

    Class startViewControllerClass = RegulatorsController.class;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {
        // create the inner flow and content
        Objects.requireNonNull(context, "context");
        // set the default controller
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(Configuration.APPBUNDLE);
        Flow innerFlow = new Flow(startViewControllerClass, viewConfiguration);
        FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(ANIM_DURATION, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));

        houseRippler.setControl(customizeIkon(Typicons.HOME_OUTLINE));
        houseRippler.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mouseEventFlow(event, flowHandler, startViewControllerClass));

        settingsRippler.setControl(customizeIkon(MaterialDesign.MDI_ACCOUNT_SETTINGS_VARIANT));
        settingsRippler.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mouseEventFlow(event, flowHandler, SettingsController.class));

        toolbar.setCenter(ClockBuilder.createClock());

    }

    private void mouseEventFlow(MouseEvent event, FlowHandler flowHandler,  Class controllerClass) {
        if (event.getClickCount() == 1) {
            try {
                //flowHandler.handle(node.getId());
                flowHandler.navigateTo(controllerClass);
            } catch (FlowException e) {
                e.printStackTrace();
            } catch (VetoException e) {
                e.printStackTrace();
            }
        }
    }



    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        //flowHandler.getFlowContext().getCurrentViewContext().getConfiguration().getBuilderFactory().getBuilder()
        flow.withGlobalLink(node.getId(), controllerClass);
        flow.withGlobalLink(node.getId(), controllerClass);
    }

}
