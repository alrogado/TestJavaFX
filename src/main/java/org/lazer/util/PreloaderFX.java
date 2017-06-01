package org.lazer.util;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXProgressBar;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
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
import org.fxmisc.cssfx.CSSFX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.lazer.GuiApp.APP_BUNDLE;
import static org.lazer.GuiApp.APP_TITLE;
import static org.lazer.util.GuiColors.ICON_GRAD_FGR_BGR;

/**
 * Created by alrogado on 5/30/17.
 */
public class PreloaderFX extends javafx.application.Preloader {

    private static Logger logger = LoggerFactory.getLogger(PreloaderFX.class);

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

    public PreloaderFX() {
        init();
    }

    @Override
    public void init() {
        splash = new ImageView(new Image(SPLASH_IMAGE,SPLASH_WIDTH,SPLASH_HEIGHT,false,false));
        splash.setFitWidth(SPLASH_WIDTH);
        loadProgress = new JFXProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        /*loadProgress.setStyle("-fx-background-color: #0091DC;"+
                "-fx-background-color: #0091DC; " +
                "-fx-padding: 3;");*/
        loadProgress.setMaxWidth(SPLASH_WIDTH);
        progressText = new Label(APP_BUNDLE.getString("menu.title.cut"));
        progressText.setStyle("-fx-padding: 6;");
        progressText.setAlignment(Pos.CENTER);
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        splashLayout.setStyle(
            "-fx-padding: 10; " +
            "-fx-background-color: white; " +
            "-fx-border-width:5; " +
            "-fx-width: 100px;"
        );
        splashLayout.setEffect(new DropShadow());
        stackPane = new StackPane();
        dialog = new JFXDialog(stackPane, splashLayout, JFXDialog.DialogTransition.CENTER);
        dialog.setPrefWidth(100);
        dialog.setStyle("-fx-width: 100px;");
    }

    @Override
    public void start(Stage stage) {
        logger.debug("PreloaderFx::start(stage);");
        stage.setTitle(APP_TITLE);
        //stage.getIcons().add(new Image(APPLICATION_ICON));
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        Scene scene = new Scene(stackPane);
        scene.setFill(ICON_GRAD_FGR_BGR);
        stage.setScene(scene);
        CSSFX.start(stage);
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        //this has to be done here
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");
        //stage.initStyle(StageStyle.UNDECORATED);

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                PreloaderFX.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                PreloaderFX.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                //PreloaderFX.class.getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                PreloaderFX.class.getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());

        stage.show();
        dialog.show();
        CSSFX.start(stage);
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
        /*FadeTransition ft = new FadeTransition(Duration.millis(3000), dialog);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(4);
        ft.setAutoReverse(true);

        ft.play();*/
        dialog.close();


    }
}