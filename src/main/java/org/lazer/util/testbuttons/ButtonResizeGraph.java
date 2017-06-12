package org.lazer.util.testbuttons;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import org.kordamp.ikonli.fontelico.Fontelico;
import org.kordamp.ikonli.javafx.FontIcon;

import static org.lazer.util.IkonUtils.customizeIkon;

/**
 * Created by alvaro.lopez on 09/06/2017.
 */
public class ButtonResizeGraph extends Application {

    private final int MIN_BUTTON_SIZE = 10;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
        SVGPath svg = new SVGPath();
        svg.setContent("M87.5,50.002C87.5,29.293,70.712,12.5,50,12.5c-20.712,0-37.5,16.793-37.5,37.502C12.5,70.712,29.288,87.5,50,87.5" +
                "c6.668,0,12.918-1.756,18.342-4.809c0.61-0.22,1.049-0.799,1.049-1.486c0-0.622-0.361-1.153-0.882-1.413l0.003-0.004l-6.529-4.002" +
                "L61.98,75.79c-0.274-0.227-0.621-0.369-1.005-0.369c-0.238,0-0.461,0.056-0.663,0.149l-0.014-0.012" +
                "C57.115,76.847,53.64,77.561,50,77.561c-15.199,0-27.56-12.362-27.56-27.559c0-15.195,12.362-27.562,27.56-27.562" +
                "c14.322,0,26.121,10.984,27.434,24.967C77.428,57.419,73.059,63,69.631,63c-1.847,0-3.254-1.23-3.254-3.957" +
                "c0-0.527,0.176-1.672,0.264-2.111l4.163-19.918l-0.018,0c0.012-0.071,0.042-0.136,0.042-0.21c0-0.734-0.596-1.33-1.33-1.33h-7.23" +
                "c-0.657,0-1.178,0.485-1.286,1.112l-0.025-0.001l-0.737,3.549c-1.847-3.342-5.629-5.893-10.994-5.893" +
                "c-10.202,0-19.877,9.764-19.877,21.549c0,8.531,5.101,14.775,13.632,14.775c4.75,0,9.587-2.727,12.665-7.035l0.088,0.527" +
                "c0.615,3.342,9.843,7.576,15.121,7.576c7.651,0,16.617-5.156,16.617-19.932l-0.022-0.009C87.477,51.13,87.5,50.569,87.5,50.002z" +
                "M56.615,56.844c-1.935,2.727-5.101,5.805-9.763,5.805c-4.486,0-7.212-3.166-7.212-7.738c0-6.422,5.013-12.754,12.049-12.754" +
                "c3.958,0,6.245,2.551,7.124,4.486L56.615,56.844z");

        Button buttonWithGraphics = new Button();

        FontIcon fontIcon = new FontIcon(Fontelico.EMO_SHOOT);
        buttonWithGraphics.setGraphic(fontIcon);

        // Bind the Image scale property to the buttons size
        svg.scaleXProperty().bind(buttonWithGraphics.widthProperty().divide(100));
        svg.scaleYProperty().bind(buttonWithGraphics.heightProperty().divide(100));

        fontIcon.scaleXProperty().bind(buttonWithGraphics.widthProperty().divide(50));
        fontIcon.scaleYProperty().bind(buttonWithGraphics.heightProperty().divide(50));

        // Declare a minimum size for the button
        buttonWithGraphics.setMinSize(MIN_BUTTON_SIZE, MIN_BUTTON_SIZE);

        root.getChildren().addAll(buttonWithGraphics);
        root.layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
                    double size = Math.max(MIN_BUTTON_SIZE, Math.min(newBounds.getWidth(), newBounds.getHeight()));
                    buttonWithGraphics.setPrefSize(size, size);
                }
        );

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}