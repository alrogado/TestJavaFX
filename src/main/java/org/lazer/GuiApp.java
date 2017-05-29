package org.lazer;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.svg.SVGGlyphLoader;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.fxmisc.cssfx.CSSFX;
import org.reactfx.EventStreams;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.ResourceBundle;

public class GuiApp extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext ViewFlowContext = null;

    private              long            epochSeconds;

    public void start(Stage stage) {
        epochSeconds = Instant.now().getEpochSecond();
        /*new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(getClass().getResourceAsStream("/fonts/icomoon.svg"),"icomoon.svg");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/

        ViewConfiguration viewConfiguration = new ViewConfiguration();
        viewConfiguration.setResources(ResourceBundle.getBundle("lazer", new Locale("fr")));

        Flow flow = new Flow(GuiAppController.class, viewConfiguration);
        ViewFlowContext flowContext = new ViewFlowContext();
        flowContext.register("Stage", stage);

        DefaultFlowContainer container = new DefaultFlowContainer();
        FlowHandler handler = new FlowHandler(flow, flowContext, viewConfiguration);
        try {
            handler.start(container);
        } catch (FlowException e) {
            e.printStackTrace();
        }

        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);

        Scene scene = new Scene(decorator);
        //Scene scene = new Scene(new StackPane(label = new Label()), 800, 850);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(getClass().getResource("/css/jfoenix-fonts.css").toExternalForm(),
                getClass().getResource("/css/jfoenix-design.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                getClass().getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());
        //stage.setMaxWidth(400.0);
        stage.setMinHeight(400.0);
        stage.setMinWidth(400.0);
        stage.setScene(scene);
        stage.setTitle("LAZER App");
        stage.initStyle(StageStyle.UNDECORATED);
        //stage.setFullScreen(true);
        //stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");
        stage.show();
        CSSFX.start(stage);

        //ObservableExecutor.getDefaultInstance().createProcessChain().addRunnableInPlatformThread(() -> label.setText(new Date() + "")).repeatInfinite(Duration.seconds(1));


        /*FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setResources(ResourceBundle.getBundle("lazer", new Locale("fr")));
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResourceAsStream("fxml/lazer.fxml"));
        }catch(NullPointerException npe){
            System.out.println("No se ha encontrado el fichero con la aplicación. "+ npe.getLocalizedMessage());
        }
        //borderPane.setCenter(pane);

        //primaryStage.setTitle("Hello World");
        stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");
        stage.setScene(new Scene(root, 350, 475));
        stage.show();*/
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
