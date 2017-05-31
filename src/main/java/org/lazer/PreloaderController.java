package org.lazer;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import demos.datafx.ExtendedAnimatedFlowContainer;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.lazer.controllers.ContentLazerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.lazer.Preloader.SPLASH_IMAGE;

@ViewController(value = "fxml/preloader.fxml", title = "Lazer Application")
public class PreloaderController {

    private static Logger logger = LoggerFactory.getLogger(PreloaderController.class);

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    private StackPane root;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private StackPane stackPane;
    @FXML
    private JFXDialog dialog;
    @FXML
    private Pane splashLayout;
    @FXML
    private ProgressBar loadProgress;
    @FXML
    private Label progressText;
    @FXML
    private ImageView splash;

    private static final int SPLASH_WIDTH = 275;
    private static final int SPLASH_HEIGHT = 183;



    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {

        // create the inner flow and content
        context = new ViewFlowContext();

        splash = new ImageView(new Image(SPLASH_IMAGE,SPLASH_WIDTH,SPLASH_HEIGHT,false,false));
        loadProgress = new ProgressBar();
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

        /*// side controller will add links to the content flow
        Flow sideMenuFlow = new Flow(SideMenuController.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new ExtendedAnimatedFlowContainer(containerAnimationDuration,
                SWIPE_LEFT)));*/

    }
}
