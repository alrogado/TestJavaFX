package org.lazer.controllers;


import com.jfoenix.controls.*;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.lazer.controllers.effects.AbstractEffectsController;
import org.lazer.controllers.effects.EffectRunnable;
import org.lazer.controllers.effects.Sprite3dController;
import org.lazer.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.lazer.GuiApp.*;

@ViewController(value = "/org/lazer/fxml/ui/main.fxml", title = "Lazer Application")
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private StackPane root;
    @FXML
    private StackPane titleBurgerContainer;
    @FXML
    private JFXHamburger titleBurger;
    @FXML
    private StackPane optionsBurger;
    @FXML
    private JFXRippler optionsRippler;
    @FXML
    private JFXDrawer drawer;

    private JFXPopup toolbarPopup;
    @FXML
    private Label labelChecks;
    @FXML
    private Label labelMainContent;

    public MainController() {
        this.labelMainContent=this.labelMainContent;
    }

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {
        // create the inner flow and content
        Objects.requireNonNull(context, "context");
        // set the default controller
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(APP_BUNDLE);
        Flow innerFlow = new Flow(MainContentController.class, viewConfiguration);
        FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(ANIM_DURATION, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
        //configureContent(MainContentController.class, drawer);

        // side controller will add links to the content flow
       /* Flow sideMenuFlow = new Flow(SideMenuController.class, viewConfiguration);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(Duration.millis(320),SWIPE_LEFT)));
        */

        /*// init the title hamburger icon
        drawer.setOnDrawerOpening(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(1);
            animation.play();
        });
        drawer.setOnDrawerClosing(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(-1);
            animation.play();
        });*/

        /*FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/lazer/fxml/ui/main_popup.fxml"));
        loader.setController(new InputController(this));
        loader.setResources(APP_BUNDLE);
        try {
            toolbarPopup = new JFXPopup(loader.load());
        } catch (IOException e) {
            logger.error("",e);
        }

        optionsBurger.setOnMouseClicked(e -> toolbarPopup.show(optionsBurger,
                JFXPopup.PopupVPosition.TOP,
                JFXPopup.PopupHPosition.RIGHT,
                -12,
                15));*/


        /*bindNodeToController(labelChecks, MainContentTilesController.class, innerFlow, flowHandler);
        bindNodeToController(labelMainContent, MainContentController.class, innerFlow, flowHandler);*/
        labelChecks.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mouseEventFlow(event, flowHandler, labelChecks, MainContentTilesController.class));
        labelMainContent.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                flowHandler.navigateToHistoryIndex(0);
            } catch (VetoException e) {
                e.printStackTrace();
            } catch (FlowException e) {
                e.printStackTrace();
            }
        });

    }

    private void mouseEventFlow(MouseEvent event, FlowHandler flowHandler, Node node, Class controllerClass) {
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

    /*@FXML
    private void handleMousePress(MouseEvent event) {
        // code in this method is executed when the mouse is pressed
        // on a node with onMousePressed="#handleMousePress"
        //FlowHandler flowHandler = innerFlow.createHandler(context);
        try {
            flowHandler.handle(labelChecks.getId());
        } catch (VetoException e) {
            e.printStackTrace();
        } catch (FlowException e) {
            e.printStackTrace();
        }
    }*/

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        //flowHandler.getFlowContext().getCurrentViewContext().getConfiguration().getBuilderFactory().getBuilder()
        flow.withGlobalLink(node.getId(), controllerClass);
        flow.withGlobalLink(node.getId(), controllerClass);
    }

    public static final class InputController {
        @FXML
        private JFXListView<?> toolbarPopupList;

        MainController mainController;

        public InputController(MainController mainController) {
            this.mainController = mainController;
        }

        // close application
        @FXML
        private void submit() {
            if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 2) {
                Platform.exit();
            }else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
                try {
                    initController(Sprite3dController.class);
                } catch (FlowException e) {
                    logger.error("",e);
                }
            }else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
                try {
                    //configureContent(MainContentController.class, mainController.drawer);
                    //esto hace lo mismo y no hace el swipe en el content del drawer como sí lo hace el menu de la izq
                    initController(MainContentController.class);
                } catch (FlowException e) {
                    logger.error("",e);
                }
            }
        }

        private void initController(Class controllerClass) throws FlowException {
            Flow innerFlow = new Flow(controllerClass);
            final FlowHandler flowHandler = innerFlow.createHandler(mainController.context);
            if(((FlowHandler) mainController.context.getRegisteredObject("ContentFlowHandler")).getCurrentViewContext().getController() instanceof EffectRunnable){
                ((AbstractEffectsController)((FlowHandler) mainController.context.getRegisteredObject("ContentFlowHandler")).getCurrentViewContext().getController()).stopEffects();
            }
            mainController.context.register("ContentFlowHandler", flowHandler);
            mainController.context.register("ContentFlow", innerFlow);
            final Duration containerAnimationDuration = Duration.millis(320);
            mainController.drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
            mainController.context.register("ContentPane", mainController.drawer.getContent().get(0));
        }
    }
}
