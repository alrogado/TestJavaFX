package org.lazer;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXDrawer;
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
import org.lazer.controllers.PreloaderController;
import org.lazer.util.ExtendedAnimatedFlowContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.time.Instant;
import java.util.Locale;
import java.util.ResourceBundle;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.lazer.util.GuiColors.ICON_GRAD_FGR_BGR;

public class GuiApp extends Application {

    private static Logger logger = LoggerFactory.getLogger(GuiApp.class);

    private long epochSeconds;

    public static JFXDecorator decorator;

    public static String APP_TITLE = "LAZER App";

    private static Locale LOCALE = new Locale("es");
    public static ResourceBundle APP_BUNDLE = ResourceBundle.getBundle("lazer", LOCALE);

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
                GuiApp.class.getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                GuiApp.class.getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());
        scene.setFill(ICON_GRAD_FGR_BGR);
        stage.setScene(scene);
        CSSFX.start(stage);
    }

    public static void configureFullScreenStage(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setMinHeight(HEIGHT);
        stage.setMinWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setWidth(WIDTH);
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
    public static ViewFlowContext context;
    public static ViewConfiguration viewConfiguration ;


    public static DefaultFlowContainer initFlowConf(Class startViewControllerClass, Stage stage) {
        viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(APP_BUNDLE);

        Flow flow = new Flow(startViewControllerClass, viewConfiguration);
        context = new ViewFlowContext();
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

    static Duration animationDuration = Duration.millis(320);

    public static void configureContent(Class controllerClass, JFXDrawer drawer) throws FlowException {
        // set the content Lazer controller
        Flow flow = new Flow(controllerClass, viewConfiguration);
        FlowHandler handler = new FlowHandler(flow, context, viewConfiguration);
        context.register("ContentFlowHandler", handler);
        context.register("ContentFlow", flow);
        drawer.setContent(handler.start(new ExtendedAnimatedFlowContainer(animationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }

    public static void configureSidePane(Class controllerClass, JFXDrawer drawer) throws FlowException {
        // set the content Lazer controller
        Flow flow = new Flow(controllerClass, viewConfiguration);
        FlowHandler handler = new FlowHandler(flow, context, viewConfiguration);
        context.register("ContentFlowHandler", handler);
        context.register("ContentFlow", flow);
        drawer.setSidePane(handler.start(new ExtendedAnimatedFlowContainer(animationDuration, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));
    }

    public static void createJFXDecorator(Stage stage, DefaultFlowContainer container, boolean maximized) {
        decorator = new JFXDecorator(stage, container.getView());
        //no quitar el set maximized porque hace efecto extrañio de repeticion y no funciona bien el fade in fade out
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
                URL resource = GuiApp.class.getResource("/lazer_" + localeStr + ".properties");
                if (resource == null) {
                    logger.warn("el valor del parametro de pais/idioma '"+localeStr+"' no esta dado de alta como fichero. Se toma el valor por defecto del lenguaje de la aplicacion 'es'.");
                } else {
                    LOCALE = new Locale(localeStr);
                    APP_BUNDLE = ResourceBundle.getBundle("lazer", LOCALE);
                }
            }
        }
    }
}
