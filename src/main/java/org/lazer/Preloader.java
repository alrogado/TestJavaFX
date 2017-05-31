package org.lazer;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXProgressBar;
import io.datafx.controller.ViewController;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alrogado on 5/30/17.
 */
public class Preloader extends javafx.application.Preloader {

    private static Logger logger = LoggerFactory.getLogger(Preloader.class);

    public static final String APPLICATION_ICON = "http://cdn1.iconfinder.com/data/icons/Copenhagen/PNG/32/people.png";

    public static final String SPLASH_IMAGE = "images/logov.png";

    boolean noLoadingProgress = true;

    private StackPane stackPane;
    private JFXDialog dialog;
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private ImageView splash;

    private static final int SPLASH_WIDTH = 275;
    private static final int SPLASH_HEIGHT = 183;

    public Preloader() {
        init();
    }

    @Override
    public void init() {
        splash = new ImageView(new Image(SPLASH_IMAGE,SPLASH_WIDTH,SPLASH_HEIGHT,false,false));
        loadProgress = new JFXProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        progressText = new Label("%menu.title.cut");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 10; "
                        + "-fx-background-color: white; "
                        + "-fx-border-WIDTH:5; "
        );
        splashLayout.setEffect(new DropShadow());
        stackPane = new StackPane();
        dialog = new JFXDialog(stackPane, splashLayout, JFXDialog.DialogTransition.CENTER);
    }

    @Override
    public void start(Stage stage) {
        logger.debug("PreloaderFx::start(stage);");
        //stage.setTitle("Progress Bar");
        //stage.getIcons().add(new Image(APPLICATION_ICON));
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setScene(new Scene(stackPane));
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        //this has to be done here
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        dialog.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            dialog.close();
        }
    }

    public void setProgress(double d) {
        loadProgress.setProgress(d);
    }

    public DoubleProperty getProgress() {
        return loadProgress.progressProperty();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        logger.debug("PreloaderFx::handleProgressNotification(); progress = " + pn.getProgress());
        //Even if there is nothing to load 0% and 100% events can be delivered
        if (pn.getProgress() != 1.0 || !noLoadingProgress) {
            loadProgress.setProgress(pn.getProgress());
            if (pn.getProgress() > 0) {
                noLoadingProgress = false;
            }
        }
    }

    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
            //expect application to send us progress notifications
            //with progress ranging from 0 to 1.0
            double v = ((javafx.application.Preloader.ProgressNotification) pn).getProgress();
            logger.debug("PreloaderFx::handleApplicationNotification(); progress = " + v);
            //if (!noLoadingProgress) {
            //if we were receiving loading progress notifications
            //then progress is already at 50%.
            //Rescale application progress to start from 50%
            v = 0.5 + v / 2;
            //}
            loadProgress.setProgress(v);
        } else if (pn instanceof StateChangeNotification) {
            logger.debug("PreloaderFx::handleApplicationNotification(); state = " + ((StateChangeNotification) pn).getType());
            //hide after get any state update from application
            dialog.close();
        }
    }

    public void stop() {
        dialog.close();
    }
}
