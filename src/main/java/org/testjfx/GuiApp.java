package org.testjfx;

import com.jfoenix.controls.JFXDecorator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
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
import javafx.util.Duration;
import org.fxmisc.cssfx.CSSFX;
import org.testjfx.components.Audio;
import org.testjfx.conf.Configuration;
import org.testjfx.controllers.PreloaderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Instant;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.testjfx.util.GuiColors.GRAD_FGR_BGR;

public class GuiApp extends Application {

    private static Logger logger = LoggerFactory.getLogger(GuiApp.class);

    private long epochSeconds;

    public static JFXDecorator decorator;

    public static String APPTITLE = "Gui Appplication";

    public static FlowHandler handler;

    public void start(Stage stage) {
        epochSeconds = Instant.now().getEpochSecond();
        logger.debug("start");
        createJFXDecorator(stage, initFlowConf(PreloaderController.class, stage), true);
        configureAndSetScene(stage, new Scene(decorator));
        configureFullScreenStage(stage);
        stage.show();
    }

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
        stage.setMinHeight(Configuration.HEIGHT);
        stage.setMinWidth(Configuration.WIDTH);
        stage.setHeight(Configuration.HEIGHT);
        stage.setWidth(Configuration.WIDTH);
        stage.setTitle(APPTITLE);
        stage.setFullScreenExitHint("");
    }

    public static void configureNotFullScreenStage(Stage stage) {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        stage.setX(bounds.getMinX() + bounds.getWidth() / 2 - Configuration.WIDTH / 2);
        stage.setY(bounds.getMinY() + bounds.getHeight() / 2 - Configuration.HEIGHT / 2);
        stage.setAlwaysOnTop(false);
        stage.setMinHeight(Configuration.HEIGHT);
        stage.setMinWidth(Configuration.WIDTH);
        stage.setHeight(Configuration.HEIGHT);
        stage.setWidth(Configuration.WIDTH);
        stage.setTitle(APPTITLE);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreenExitHint("");
    }

    public static DefaultFlowContainer initFlowConf(Class startViewControllerClass, Stage stage) {
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(Configuration.APPBUNDLE);

        Flow flow = new Flow(startViewControllerClass, viewConfiguration);
        ViewFlowContext context = new ViewFlowContext();
        context.register("Stage", stage);

        DefaultFlowContainer container = new DefaultFlowContainer();
        handler = new FlowHandler(flow, context, viewConfiguration);
        try {
            handler.start(container);
        } catch (FlowException e) {
            logger.error("",e);
        }
        return container;
    }

    public static Duration ANIM_DURATION = Duration.millis(320);

    public static void createJFXDecorator(Stage stage, DefaultFlowContainer container, boolean maximized) {
        decorator = new JFXDecorator(stage, container.getView());
        //todo - no quitar - el set maximized porque hace efecto extraño de repeticion y no funciona bien el fade in fade out
        decorator.setMaximized(maximized);
    }

    public static void main(String[] args) {
        configStaticValues(ConfigFactory.defaultApplication());
        Application.launch(args);
    }

    public static void configStaticValues(Config conf) {
        if(conf!=null) {
            String localeStr = conf.getString("application.locale");
            if (localeStr != null) {
                URL resource = GuiApp.class.getResource("/testjfx_" + localeStr + ".properties");
                if (resource == null) {
                    logger.warn("el valor del parametro de pais/idioma '"+localeStr+"' no esta dado de alta como fichero. Se toma el valor por defecto del lenguaje de la aplicacion 'es'.");
                } else {
                    Configuration.LOCALE = new Locale(localeStr);
                    Configuration.APPBUNDLE = ResourceBundle.getBundle("testjfx", Configuration.LOCALE);
                }
            }
        }
    }
}
