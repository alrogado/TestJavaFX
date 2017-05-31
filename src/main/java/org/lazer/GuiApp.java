package org.lazer;

import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fxmisc.cssfx.CSSFX;
import org.lazer.controllers.LazerMainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.lazer.util.GuiColors.ICON_GRAD_FGR_BGR;

public class GuiApp extends Application {

    private static Logger logger = LoggerFactory.getLogger(GuiApp.class);

    private long epochSeconds;

    public static JFXDecorator decorator;
    public static ResourceBundle APP_BUNDLE = ResourceBundle.getBundle("lazer", new Locale("fr"));
    public static String applicationTitle = "LAZER App";

    BooleanProperty ready = new SimpleBooleanProperty(false);

    public void start(Stage stage) {
        logger.debug("start");
        loadSplashAndInitTask(stage);
        ready.addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean readyValue) -> {
            if (Boolean.TRUE.equals(readyValue)) {
                Platform.runLater(() -> {
                    logger.debug("Init Task  completed. Now executes configureAndSetScene");
                    Stage stageI = new Stage();
                    createJFXDecorator(stageI, configureDataFlow(LazerMainController.class, stageI));
                    configureFullScreenStage(stageI);
                    Scene scene = new Scene(decorator);
                    configureAndSetScene(stageI, scene);
                    stageI.show();
                    stage.close();
                });
            }
        });

        epochSeconds = Instant.now().getEpochSecond();
    }

    public static void configureAndSetScene(Stage stage, Scene scene) {
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                GuiApp.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                GuiApp.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                GuiApp.class.getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                GuiApp.class.getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());
        scene.setFill(ICON_GRAD_FGR_BGR);
        stage.setScene(scene);
        CSSFX.start(stage);
    }

    public static void configureFullScreenStage(Stage stage) {
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setTitle(applicationTitle);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitHint("");
    }

    public static int WIDTH = 800;
    public static int HEIGHT = 850;

    public static void configureNotFullScreenStage(Stage stage) {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - HEIGHT / 2);
        stage.setMinHeight(HEIGHT);
        stage.setMinWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
        stage.setTitle(applicationTitle);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitHint("");
    }

    public static DefaultFlowContainer configureDataFlow(Class startViewControllerClass, Stage stage) {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(APP_BUNDLE);

        Flow flow = new Flow(startViewControllerClass, viewConfiguration);
        ViewFlowContext flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);

        DefaultFlowContainer container = new DefaultFlowContainer();
        FlowHandler handler = new FlowHandler(flow, flowContext, viewConfiguration);
        try {
            handler.start(container);
        } catch (FlowException e) {
            logger.error("",e);
        }
        return container;
    }

    private void loadSplashAndInitTask(Stage stage) {
        PreloaderFX preloaderFX = new PreloaderFX();
        try {
            preloaderFX.start(stage);
        } catch (Exception e) {
            logger.error("Problem launching preloader",e);
        }
        preloaderFX.handleProgressNotification(new javafx.application.Preloader.ProgressNotification(0));

        simulateTasks(preloaderFX);
    }

    private void simulateTasks(PreloaderFX preloaderFX) {
        //simulate long init in background
        new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 3;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(300);
                    logger.debug("loadSplashAndInitTask " + i);
                    // Send progress to preloader
                    preloaderFX.handleProgressNotification(new javafx.application.Preloader.ProgressNotification(((double) i)/max)); //this moves the progress bar of the preloader
                }
                ///Alwways close the dialog because the main can't be closed
                preloaderFX.stop();
                //dejar sleep pq si no no se ve como desaparece el dialog
                Thread.sleep(400);
                ready.setValue(Boolean.TRUE);
                //preloaderFX.handleStateChangeNotification(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
                return null;
            }
        }).start();
    }

    public static void createJFXDecorator(Stage stage, DefaultFlowContainer container) {
        decorator = new JFXDecorator(stage, container.getView());
        decorator.setMaximized(true);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
