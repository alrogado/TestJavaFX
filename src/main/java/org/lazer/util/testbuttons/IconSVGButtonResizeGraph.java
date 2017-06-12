package org.lazer.util.testbuttons;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontelico.Fontelico;
import org.kordamp.ikonli.javafx.FontIcon;
import org.lazer.GuiApp;

/**
 * Created by alvaro.lopez on 09/06/2017.
 */
public class IconSVGButtonResizeGraph extends Application {

    private final int MIN_BUTTON_SIZE = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);


        Button btn = new JFXButton();
        btn.getStyleClass().add("icon-button");
        btn.setPickOnBounds(true);

        Region icon = new Region();
        icon.getStyleClass().add("icon");
        btn.setGraphic(icon);
        btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);


        //buttonWithGraphics.setGraphic(fontIcon);

        // Bind the Image scale property to the buttons size
        icon.scaleXProperty().bind(btn.widthProperty().divide(100));
        icon.scaleYProperty().bind(btn.heightProperty().divide(100));


        // Declare a minimum size for the button
        btn.setMinSize(MIN_BUTTON_SIZE, MIN_BUTTON_SIZE);

        root.getChildren().addAll(btn);
        root.layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
                    double size = Math.max(MIN_BUTTON_SIZE, Math.min(newBounds.getWidth(), newBounds.getHeight()));
                    btn.setPrefSize(size, size);
                }
        );

        Scene scene = new Scene(root);

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(GuiApp.class.getResource("/org/lazer/css/button-icon.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
