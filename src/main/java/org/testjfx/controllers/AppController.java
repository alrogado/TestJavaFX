package org.testjfx.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToolbar;
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
import org.testjfx.components.ClockBuilder;
import org.testjfx.conf.ApplicationSettings;
import org.testjfx.controllers.components.MainController;
import org.testjfx.controllers.components.PasswordAlpahabetController;
import org.testjfx.controllers.components.SettingsController;
import org.testjfx.util.ExtendedAnimatedFlowContainer;
import org.testjfx.util.GuiColors;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.testjfx.GuiApp.ANIM_DURATION;
import static org.testjfx.util.IkonUtils.customizeIkon;

@ViewController(value = "/org/testjfx/fxml/ui/main.fxml", title = "Application")
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

        Class mainViewControllerClass = MainController.class;

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

        JFXButton operatorsButton = new JFXButton();
        operatorsButton.setGraphic(customizeIkon(Ionicons.ION_SETTINGS));
        operatorsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mouseEventFlow(event, flowHandler, PasswordAlpahabetController.class));

        JFXButton settingsButton = new JFXButton();
        settingsButton.setGraphic(customizeIkon(MaterialDesign.MDI_ACCOUNT_SETTINGS_VARIANT));
        settingsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mouseEventFlow(event, flowHandler, SettingsController.class));

        JFXButton regulatorsButton = new JFXButton();
        regulatorsButton.setGraphic(customizeIkon(Typicons.HOME_OUTLINE));
        regulatorsButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> mouseEventFlow(event, flowHandler, mainViewControllerClass));

        JFXButton turonOffButton = new JFXButton();
        turonOffButton.setGraphic(customizeIkon(Elusive.OFF)); //o MetrizeIcons.MET_OFF
        turonOffButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> System.exit(0));

        toolbar.setRightItems(operatorsButton, settingsButton, regulatorsButton, turonOffButton);

        toolbar.setCenter(ClockBuilder.createClock());

        toolbar.getLeftItems().addAll(createPanelSessionShoots(), createPanelTotals());
    }

    private void mouseEventFlow(MouseEvent event, FlowHandler flowHandler, Class controllerClass) {
        if (event.getClickCount() == 1) {
            try {
                //flowHandler.handle(node.getId());
                flowHandler.navigateTo(controllerClass);
            } catch (FlowException e) {
                e.printStackTrace();
            } catch (VetoException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        //flowHandler.getFlowContext().getCurrentViewContext().getConfiguration().getBuilderFactory().getBuilder()
        flow.withGlobalLink(node.getId(), controllerClass);
        flow.withGlobalLink(node.getId(), controllerClass);
    }

    private Node createPanelSessionShoots() {
        Font robotoMedium = Fonts.robotoMedium(20);

        Text lblSession = new Text(ApplicationSettings.getBundleString("shoots-sesion.label"));
        lblSession.setFill(GuiColors.FRG);
        lblSession.setTextOrigin(VPos.CENTER);
        lblSession.setFont(robotoMedium);
        lblSession.setTextAlignment(TextAlignment.LEFT);

        Text lblShootsValue = new Text("0");
        lblShootsValue.setFill(Color.WHITE);
        lblShootsValue.setTextOrigin(VPos.CENTER);
        lblShootsValue.setTextAlignment(TextAlignment.RIGHT);
        lblShootsValue.setFont(robotoMedium);
        lblShootsValue.setWrappingWidth(wrappingWidth);

        JFXButton btnReload = new JFXButton();
        FontIcon fontIcon = customizeIkon(MaterialDesign.MDI_RELOAD);
        fontIcon.setFill(Color.WHITE);
        btnReload.setGraphic(fontIcon);

        MigPane rootMP = new MigPane("fill");
        rootMP.add(lblSession, "west");
        rootMP.add(lblShootsValue, "center");
        rootMP.add(btnReload, "east");
        return rootMP;
    }

    private Node createPanelTotals() {
        Font robotoMedium = Fonts.robotoMedium(20);

        Text lblSession = new Text(ApplicationSettings.getBundleString("shoots-total.label"));
        lblSession.setFill(GuiColors.FRG);
        lblSession.setTextOrigin(VPos.CENTER);
        lblSession.setFont(robotoMedium);
        lblSession.setTextAlignment(TextAlignment.LEFT);

        Text lblShootsValue = new Text("0");
        lblShootsValue.setFill(Color.WHITE);
        lblShootsValue.setTextOrigin(VPos.CENTER);
        lblShootsValue.setTextAlignment(TextAlignment.RIGHT);
        lblShootsValue.setFont(robotoMedium);
        lblShootsValue.setWrappingWidth(wrappingWidth);

        MigPane rootMP = new MigPane("fill");
        rootMP.add(lblSession, "west");
        rootMP.add(lblShootsValue, "east");
        return rootMP;
    }

}
