package org.lazer.controllers;


import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.lazer.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.lazer.GuiApp.*;

@ViewController(value = "/org/lazer/fxml/ui/lazer_main.fxml", title = "Lazer Application")
public class LazerMainController {

    private static Logger logger = LoggerFactory.getLogger(LazerMainController.class);

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

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {
        // create the inner flow and content
        Objects.requireNonNull(context, "context");
        //context = new ViewFlowContext();
        /*// set the default controller
        Flow innerFlow = new Flow(ContentLazerController.class, viewConfiguration);
        final FlowHandler flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));*/
        configureContent(ContentLazerController.class, drawer);

        // side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class, viewConfiguration);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(Duration.millis(320),SWIPE_LEFT)));


        // init the title hamburger icon
        drawer.setOnDrawerOpening(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(1);
            animation.play();
        });
        drawer.setOnDrawerClosing(e -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(-1);
            animation.play();
        });

        configureSidePane(SideMenuController.class, drawer);
        titleBurgerContainer.setOnMouseClicked(e -> {
            if (drawer.isHidden() ){//|| drawer.isHiding()) {
                drawer.open();
            } else {
                drawer.close();
            }
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/lazer/fxml/ui/main_popup.fxml"));
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
                15));
    }

    public static final class InputController {
        @FXML
        private JFXListView<?> toolbarPopupList;

        LazerMainController lazerMainController;

        public InputController(LazerMainController lazerMainController) {
            this.lazerMainController=lazerMainController;
        }

        // close application
        @FXML
        private void submit() {
            if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 1) {
                Platform.exit();
            }else if (toolbarPopupList.getSelectionModel().getSelectedIndex() == 0) {
                try {
                    configureContent(ContentLazerController.class, lazerMainController.drawer);
                    //esto hace lo mismo y no hace el swipe en el content del drawer como sí lo hace el menu de la izq
                    /*Flow innerFlow = new Flow(ContentLazerController.class);
                    final FlowHandler flowHandler = innerFlow.createHandler(lazerMainController.context);
                    lazerMainController.context.register("ContentFlowHandler", flowHandler);
                    lazerMainController.context.register("ContentFlow", innerFlow);
                    final Duration containerAnimationDuration = Duration.millis(320);
                    lazerMainController.drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
                    lazerMainController.context.register("ContentPane", lazerMainController.drawer.getContent().get(0));*/

                } catch (FlowException e) {
                    logger.error("",e);
                }
            }
        }
    }
}
