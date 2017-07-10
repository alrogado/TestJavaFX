package org.testjavafx.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToolbar;
import org.testjavafx.components.ClockBuilder;
import org.testjavafx.conf.ApplicationSettings;
import org.testjavafx.controllers.components.PasswordAlpahabetController;
import org.testjavafx.controllers.components.SettingsController;
import org.testjavafx.util.ExtendedAnimatedFlowContainer;
import org.testjavafx.util.IkonUtils;
import eu.hansolo.fx.regulators.Fonts;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.ionicons.Ionicons;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.typicons.Typicons;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjavafx.util.GuiColors;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.testjavafx.GuiApp.ANIM_DURATION;

@ViewController(value = "/org/testjavafx/fxml/ui/main.fxml", title = "Application")
public class AppController {

    public static FlowHandler flowHandler;
    private static Logger logger = LoggerFactory.getLogger(AppController.class);
    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    private StackPane root;
    @FXML
    private JFXToolbar toolbar;
    @FXML
    private JFXDrawer drawer;
    private int wrappingWidth = 75;


    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws FlowException {

        Class mainViewControllerClass = SettingsController.class;

        // create the inner flow and content
        Objects.requireNonNull(context, "context");
        // set the default controller
        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(ApplicationSettings.APPBUNDLE);
        Flow innerFlow = new Flow(mainViewControllerClass, viewConfiguration);
        flowHandler = innerFlow.createHandler(context);
        context.register("ContentFlowHandler", flowHandler);
        context.register("ContentFlow", innerFlow);
        drawer.setContent(flowHandler.start(new ExtendedAnimatedFlowContainer(ANIM_DURATION, SWIPE_LEFT)));
        context.register("ContentPane", drawer.getContent().get(0));


    }



}
