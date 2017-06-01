package org.lazer;

import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fxmisc.cssfx.CSSFX;
import org.lazer.controllers.PreloaderController;
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
    public static String APP_TITLE = "LAZER App";

    private ViewFlowContext context;

    public void start(Stage stage) {
        epochSeconds = Instant.now().getEpochSecond();
        logger.debug("start");
        createJFXDecorator(stage, configureDataFlow(PreloaderController.class, stage));
        Scene scene = new Scene(decorator);
        configureAndSetScene(stage, scene);
        configureFullScreenStage(stage);
        stage.show();
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
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setTitle(APP_TITLE);
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
        stage.setTitle(APP_TITLE);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitHint("");
    }

    public static FlowHandler handler;
    public static ViewFlowContext flowContext;
    public static ViewConfiguration viewConfiguration ;


    public static DefaultFlowContainer configureDataFlow(Class startViewControllerClass, Stage stage) {
        viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(APP_BUNDLE);

        Flow flow = new Flow(startViewControllerClass, viewConfiguration);
        flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);

        DefaultFlowContainer container = new DefaultFlowContainer();
        handler = new FlowHandler(flow, flowContext, viewConfiguration);

        try {
            handler.start(container);
        } catch (FlowException e) {
            logger.error("",e);
        }
        return container;
    }

    public static void createJFXDecorator(Stage stage, DefaultFlowContainer container) {
        decorator = new JFXDecorator(stage, container.getView());
        //decorator.setMaximized(true);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
