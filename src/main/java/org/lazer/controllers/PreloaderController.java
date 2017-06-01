package org.lazer.controllers;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXProgressBar;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import static org.lazer.GuiApp.*;
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

    BooleanProperty ready = new SimpleBooleanProperty(false);

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {
        context = new ViewFlowContext();
        dialog.setPrefWidth(100);
        dialog.setStyle("-fx-width: 100px;");
        dialog.setTransitionType(JFXDialog.DialogTransition.BOTTOM);
        dialog.show((StackPane) context.getRegisteredObject(CONTENT_PANE));

        simulateTasks();

        ready.addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean readyValue) -> {
            if (Boolean.TRUE.equals(readyValue)) {
                Platform.runLater(() -> {
                    logger.debug("Init Task  completed. Now executes configureAndSetScene");
                    FadeTransition ft = new FadeTransition(Duration.millis(2000), dialog);
                    ft.setFromValue(1.0);
                    ft.setToValue(0);
                    ft.play();
                    //dialog.close(); //this throws an exception
                    try {
                        configureContent(LazerMainController.class, context, drawer);
                    } catch (Exception e) {
                        logger.error("",e);
                    }
                });
            }
        });

        /*// side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));*/
    }

    private void simulateTasks() {
        //simulate long init in background
        String[] textProGressValues = new String[]{
                "menu.title.close",
                "menu.title.save.as",
                "menu.title.revert"
        };
        int max = textProGressValues.length;
        new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < max; i++) {
                    logger.debug("loadSplashAndInitTask " + i);
                    // Send progress to preloader
                    int finalI = i;
                    Platform.runLater(() -> {
                        loadProgress.setProgress(((double) finalI) / max); //this moves the progress bar of the preloader
                        progressText.setText(APP_BUNDLE.getString(textProGressValues[finalI]));
                    });
                    Thread.sleep(100);
                }
                ///Alwways close the dialog because the main can't be closed
                //preloaderFX.stop();
                //dejar sleep pq si no no se ve como desaparece el dialog
                //Thread.sleep(400);
                ready.setValue(Boolean.TRUE);
                //preloaderFX.handleStateChangeNotification(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
                return null;
            }
        }).start();
    }
}
