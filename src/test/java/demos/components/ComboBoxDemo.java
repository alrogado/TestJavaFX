package demos.components;

import com.jfoenix.controls.JFXComboBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ComboBoxDemo extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        JFXComboBox<Label> combo = new JFXComboBox<>();
        combo.getItems().add(new Label("Java 1.8"));
        combo.getItems().add(new Label("Java 1.7"));
        combo.getItems().add(new Label("Java 1.6"));
        combo.getItems().add(new Label("Java 1.5"));
        combo.setEditable(true);
        combo.setPromptText("Select Java Version");

        HBox pane = new HBox(100);
        HBox.setMargin(combo, new Insets(20));
        pane.setStyle("-fx-background-color:WHITE");
        pane.getChildren().add(combo);

        final Scene scene = new Scene(pane, 300, 300);
        scene.getStylesheets().add(ComboBoxDemo.class.getResource("/org/testjavafx/css/jfoenix-components.css").toExternalForm());

        primaryStage.setTitle("JFX ComboBox Demo");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
