package org.lazer.controllers;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXProgressBar;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.lazer.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.lazer.GuiAppPreloader.APP_BUNDLE;
import static org.lazer.GuiAppPreloader.flowContext;
import static org.lazer.GuiAppPreloader.viewConfiguration;
import static org.lazer.controllers.ContentLazerController.CONTENT_PANE;

@ViewController(value = "/org/lazer/fxml/ui/preloader.fxml", title = "Lazer Application")
public class PreloaderController {

    private static Logger logger = LoggerFactory.getLogger(PreloaderController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXDialog dialog;
    @FXML
    private Pane splashLayout;
    @FXML
    private VBox vBox;
    @FXML
    private ImageView splash;
    @FXML
    private JFXProgressBar loadProgress;
    @FXML
    private Label progressText;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {
        context = new ViewFlowContext();
        dialog.setTransitionType(JFXDialog.DialogTransition.BOTTOM);
        dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));

        Platform.runLater(()->{
            try {
                Thread.sleep(3000);
                dialog.close();
                Thread.sleep(400);
                configureContent(LazerMainController.class);
            } catch (FlowException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        /*// side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));*/
    }

    public void configureContent(Class controllerClass) throws FlowException {
        // set the content Lazer controller
        Flow flow = new Flow(controllerClass, viewConfiguration);
        FlowHandler handler = new FlowHandler(flow, flowContext, viewConfiguration);
        context.register("ContentFlowHandler", handler);
        context.register("ContentFlow", flow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(handler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }

    private void simulateTasks() {
        //simulate long init in background
        new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 3;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(300);
                    logger.debug("loadSplashAndInitTask " + i);
                    // Send progress to preloader
                    //progressProperty().handleProgressNotification(new Preloader.ProgressNotification(((double) i)/max)); //this moves the progress bar of the preloader
                }
                ///Alwways close the dialog because the main can't be closed
                //preloaderFX.stop();
                //dejar sleep pq si no no se ve como desaparece el dialog
                Thread.sleep(400);
                //ready.setValue(Boolean.TRUE);
                //preloaderFX.handleStateChangeNotification(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
                return null;
            }
        }).start();
    }
}
