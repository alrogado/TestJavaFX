package com.saphire.util.apps.extra;

import com.saphire.components.Audio;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

/**
 * Created by alrogado on 6/21/17.
 */
public class AudioTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);


        MediaPlayer mediaPlayer = new MediaPlayer(new Media(Audio.class.getResource("/audio/error.wav").toString()));
        //AutoPlay set to false
        mediaPlayer.setAutoPlay(false);
        //mediaPlayer.setVolume();
        mediaPlayer.play();

        //Add a mediaView, to display the media. Its necessary !
        //This mediaView is added to a Pane
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group) scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
