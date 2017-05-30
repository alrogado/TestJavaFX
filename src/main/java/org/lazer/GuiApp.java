package org.lazer;

import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.fxmisc.cssfx.CSSFX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Locale;
import java.util.ResourceBundle;

public class GuiApp extends Application {

    private static Logger logger = LoggerFactory.getLogger(GuiApp.class);

    @FXMLViewFlowContext
    private ViewFlowContext ViewFlowContext = null;

    private long epochSeconds;

    private Stage mainStage;

    JFXDecorator decorator;
    ResourceBundle bundle = ResourceBundle.getBundle("lazer", new Locale("fr"));

    BooleanProperty ready = new SimpleBooleanProperty(false);

    public void start(Stage stage) {
        logger.debug("start");
        mainStage = stage;

        configureInitController(configureDataFlow(GuiAppController.class));

        loadSplashAndInitTask();

        ready.addListener((ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) -> {
            if (Boolean.TRUE.equals(t1)) {
                Platform.runLater(() -> {
                    logger.debug("Init Task  completed. Now executes showMainStage");
                    showMainStage();
                });
            }
        });

        epochSeconds = Instant.now().getEpochSecond();
    }

    private void showMainStage() {

        Stop[] stops = new Stop[] { new Stop(0, Color.BLACK), new Stop(1, Color.RED)};
        LinearGradient lg1 = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        int width = 800;
        int height = 850;
        //Scene scene = new Scene(decorator, width, height);
        Scene scene = new Scene(decorator);
        //Scene scene = new Scene(new StackPane(label = new Label()), 800, 850);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(getClass().getResource("/css/jfoenix-fonts.css").toExternalForm(),
                getClass().getResource("/css/jfoenix-design.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());

        //mainStage.setMaxWidth(400.0);

        /*Rectangle r1 = new Rectangle(0, 0, 100, 100);
        r1.setFill(lg1);*/

        scene.setFill(lg1);

        //mainStage.setFullScreen(true);
        //mainStage.setAlwaysOnTop(true);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        /*mainStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - width / 2);
        mainStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - height/ 2);
        mainStage.setMinHeight(height);
        mainStage.setMinWidth(width);
        mainStage.setHeight(height);
        mainStage.setWidth(width);*/
        mainStage.setScene(scene);
        mainStage.setTitle("LAZER App");
        //mainStage.initStyle(StageStyle.UNDECORATED);
        mainStage.setFullScreenExitHint("");
        mainStage.show();

        CSSFX.start(mainStage);
    }

    private DefaultFlowContainer configureDataFlow(Class<GuiAppController> startViewControllerClass) {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(bundle);

        Flow flow = new Flow(startViewControllerClass, viewConfiguration);
        ViewFlowContext flowContext = new ViewFlowContext();
        flowContext.register("Stage", mainStage);

        DefaultFlowContainer container = new DefaultFlowContainer();
        FlowHandler handler = new FlowHandler(flow, flowContext, viewConfiguration);
        try {
            handler.start(container);
        } catch (FlowException e) {
            logger.error("",e);
        }
        return container;
    }

    private void loadSplashAndInitTask() {
        Preloader preloaderFX = new Preloader();
        try {
            preloaderFX.start(mainStage);
        } catch (Exception e) {
            logger.error("Problem launching preloader",e);
        }
        preloaderFX.handleProgressNotification(new javafx.application.Preloader.ProgressNotification(0));

        //simulate long init in background
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                int max = 3;
                for (int i = 1; i <= max; i++) {
                    Thread.sleep(500);
                    logger.debug("loadSplashAndInitTask " + i);
                    // Send progress to preloader
                    preloaderFX.handleProgressNotification(new javafx.application.Preloader.ProgressNotification(((double) i)/max)); //this moves the progress bar of the preloader
                }
                ///Alwways close the dialog because the main can't be closed
                preloaderFX.stop();
                Thread.sleep(500);
                // After init is ready, the app is ready to be shown
                // Do this before hiding the preloader stage to prevent the
                // app from exiting prematurely
                ready.setValue(Boolean.TRUE);
                //preloaderFX.handleStateChangeNotification(new Preloader.StateChangeNotification(Preloader.StateChangeNotification.Type.BEFORE_START));
                return null;
            }
        };
        new Thread(task).start();
    }

    private void configureInitController(DefaultFlowContainer container) {
        decorator = new JFXDecorator(mainStage, container.getView());
        decorator.setCustomMaximize(true);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
