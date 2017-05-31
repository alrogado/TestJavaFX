package org.lazer.controllers;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.beans.property.DoubleProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.lazer.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;

@ViewController(value = "/org/lazer/fxml/preloader.fxml", title = "Lazer Application")
public class PreloaderController {

    private static Logger logger = LoggerFactory.getLogger(PreloaderController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private StackPane stackPane;
    @FXML
    private JFXDialog dialog;
    @FXML
    private Pane splashLayout;
    @FXML
    private ProgressBar loadProgress;
    @FXML
    private Label progressText;
    @FXML
    private ImageView splash;

    private static final int SPLASH_WIDTH = 275;
    private static final int SPLASH_HEIGHT = 183;

    boolean noLoadingProgress = true;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {

        context = new ViewFlowContext();
        loadProgress.setPrefWidth(SPLASH_WIDTH);

        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 10; "
                        + "-fx-background-color: white; "
                        + "-fx-border-WIDTH:5; "
        );
        splashLayout.setEffect(new DropShadow());
        dialog = new JFXDialog(stackPane, splashLayout, JFXDialog.DialogTransition.CENTER);
        dialog.show();
        initTasks();
    }

    public void initTasks() throws Exception {
        //simulate long init in background
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 3;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(500);
                    logger.debug("loadSplashAndInitTask " + i);
                    // Send progress to preloader
                    //loadProgress.set(((double) i)/max)); //this moves the progress bar of the preloader
                }
                ///Alwways close the dialog because the main can't be closed
                dialog.close();
                Thread.sleep(500);
                // After init is ready, the app is ready to be shown
                // Do this before hiding the preloader stage to prevent the
                // app from exiting prematurely
                setContent();
                //preloaderFX.handleStateChangeNotification(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
                return null;
            }
        };
        new Thread(task).start();
    }

    public void setProgress(double d) {
        loadProgress.setProgress(d);
    }

    public DoubleProperty getProgress() {
        return loadProgress.progressProperty();
    }

    public void setContent() throws Exception {
        // set the content Lazer controller
        Flow contentLazerFlow = new Flow(ContentLazerController.class);
        final FlowHandler contentLazerflowHandler = contentLazerFlow.createHandler(context);
        context.register("ContentFlowHandler", contentLazerflowHandler);
        context.register("ContentFlow", contentLazerFlow);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setContent(contentLazerflowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }
}
