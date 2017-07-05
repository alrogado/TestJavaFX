package org.testjfx.util.testbuttons;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import org.fxmisc.cssfx.CSSFX;
import org.testjfx.GuiApp;

/**
 * Created by alvaro.lopez on 09/06/2017.
 */
public class IconSVGButtonResizeGraph extends Application {

    private final int MIN_BUTTON_SIZE = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        Button btn = new Button();
        btn.getStyleClass().add("icon-button-logo");
        //btn.setPickOnBounds(true);

        Region icon = new Region();
        icon.getStyleClass().add("icon");

        btn.setGraphic(icon);

        IconButton iconButton = new IconButton();

        hBox.getChildren().addAll(btn, iconButton);
        hBox.layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
                    double size = Math.max(MIN_BUTTON_SIZE, Math.min(newBounds.getWidth(), newBounds.getHeight()));
                    btn.setPrefSize(size, size);
                    //iconButton.setPrefSize(size, size);
                }
        );

        Scene scene = new Scene(hBox, 400, 300);
        scene.getStylesheets().addAll(GuiApp.class.getResource("/org/testjfx/css/button-icon.css").toExternalForm());

        stage.setScene(scene);

        stage.show();

        CSSFX.start(stage);
    }


}
