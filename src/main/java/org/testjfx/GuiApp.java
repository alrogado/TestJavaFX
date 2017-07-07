package org.testjfx;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXDialog;
import eu.hansolo.enzo.notification.Notification;
import eu.hansolo.enzo.notification.NotifierBuilder;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.fxmisc.cssfx.CSSFX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.components.Audio;
import org.testjfx.conf.ApplicationSettings;
import org.testjfx.controllers.PreloaderController;

import java.time.Instant;

import static org.testjfx.util.GuiColors.GRAD_FGR_BGR;

public class GuiApp extends Application {

    public static JFXDecorator decorator;
    public static String APPTITLE = "Gui Appplication";
    public static FlowHandler handler;
    public static Duration ANIM_DURATION = Duration.millis(320);
    private static Logger logger = LoggerFactory.getLogger(GuiApp.class);
    private static Notification.Notifier notifier;
    private static JFXDialog dialog;
    private long epochSeconds;

    public static void configureAndSetScene(Stage stage, Scene scene) {
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                GuiApp.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                GuiApp.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                GuiApp.class.getResource("/org/testjfx/css/jfoenix-components.css").toExternalForm(),
                GuiApp.class.getResource("/org/testjfx/css/jfoenix-main-demo.css").toExternalForm());
        scene.setFill(GRAD_FGR_BGR);
        stage.setScene(scene);
        //todo cssfx control should
        Audio.addPlayersToMainScene(scene);
        //todo cssfx control should be configured by profile
        CSSFX.start(stage);
    }

    public static void configureFullScreenStage(Stage stage) {
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setAlwaysOnTop(true);
        stage.setMinHeight(ApplicationSettings.HEIGHT);
        stage.setMinWidth(ApplicationSettings.WIDTH);
        stage.setHeight(ApplicationSettings.HEIGHT);
        stage.setWidth(ApplicationSettings.WIDTH);
        stage.setTitle(APPTITLE);
        stage.setFullScreenExitHint("");
    }

    public static void configureNotFullScreenStage(Stage stage) {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - ApplicationSettings.WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - ApplicationSettings.HEIGHT / 2);
        stage.setAlwaysOnTop(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setHeight(ApplicationSettings.HEIGHT);
        stage.setWidth(ApplicationSettings.WIDTH);
        stage.setTitle(APPTITLE);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitHint("");
    }

    public static DefaultFlowContainer initFlowConf(Class startViewControllerClass, Stage stage) {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(ApplicationSettings.APPBUNDLE);

        Flow flow = new Flow(startViewControllerClass, viewConfiguration);
        ViewFlowContext context = new ViewFlowContext();
        context.register("Stage", stage);

        DefaultFlowContainer container = new DefaultFlowContainer();
        handler = new FlowHandler(flow, context, viewConfiguration);
        try {
            handler.start(container);
        } catch (FlowException e) {
            logger.error("", e);
        }
        return container;
    }

    public static void createJFXDecorator(Stage stage, DefaultFlowContainer container, boolean maximized) {
        decorator = new JFXDecorator(stage, container.getView());
        //todo - no quitar - el set maximized porque hace efecto extraño de repeticion y no funciona bien el fade in fade out
        decorator.setMaximized(false);

        notifier = NotifierBuilder.create()
                .owner(stage)
                .popupAnimationTime(Duration.millis(1000))
                .popupLifeTime(Duration.millis(10000))
                .popupLocation(Pos.TOP_CENTER)
                //.popupLifeTime(Duration.millis(10000))
                //.styleSheet(getClass().getResource("mynotification.css").toExternalForm())
                .build();

        dialog = new JFXDialog();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }


    public static void showInfo(String title, String message) {
        notifier.notify(new Notification(title, message, Notification.INFO_ICON));
    }

    public void start(Stage stage) {
        epochSeconds = Instant.now().getEpochSecond();
        logger.debug("start main app");
        createJFXDecorator(stage, initFlowConf(PreloaderController.class, stage), true);
        configureAndSetScene(stage, new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

}
