package mp3player;

/**
 * Created by alrogado on 5/24/17.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class MP3Player extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MP3Player.fxml"));
            BorderPane root = loader.load();
            MP3PlayerController controller = loader.getController();
            Scene scene = new Scene(root, 300, controller.getWindowHeight());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}