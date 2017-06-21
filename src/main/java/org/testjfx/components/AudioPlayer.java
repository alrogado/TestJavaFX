package org.testjfx.components;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import static javafx.application.Application.launch;

/**
 * Created by alrogado on 6/20/17.
 */
public class AudioPlayer extends Application {

    @Override
    public void start(Stage primaryStage)
    {
        //Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);


        MediaPlayer mediaPlayer = new MediaPlayer(new Media(AudioPlayer.class.getResource("/audio/error.wav").toString()));
        //AutoPlay set to false
        mediaPlayer.setAutoPlay(false);
        //mediaPlayer.setVolume();
        mediaPlayer.play();

        //Add a mediaView, to display the media. Its necessary !
        //This mediaView is added to a Pane
        MediaView mediaView = new MediaView(mediaPlayer);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static Logger logger = LoggerFactory.getLogger(AudioPlayer.class);


    final static AudioClip PULSE = new AudioClip(AudioPlayer.class.getResource("/audio/pulse.wav").toString());
    final static AudioClip TOUCH = new AudioClip(AudioPlayer.class.getResource("/audio/touch.wav").toString());
    final static AudioClip MESSAGE = new AudioClip(AudioPlayer.class.getResource("/audio/message.wav").toString());
    final static AudioClip INFO = new AudioClip(AudioPlayer.class.getResource("/audio/info.wav").toString());
    final static AudioClip WARN = new AudioClip(AudioPlayer.class.getResource("/audio/warn.wav").toString());
    final static AudioClip WAIT = new AudioClip(AudioPlayer.class.getResource("/audio/wait.wav").toString());
    final static AudioClip ERROR = new AudioClip(AudioPlayer.class.getResource("/audio/error.mp3").toString());
    

    /**
     * Plays pulse beep tone
     */
    public static void pulse() {
        try {
            PULSE.play(Configuration.getPulseVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    /**
     * Plays key press event sound
     */
    public static void keypress() {
        try {
            TOUCH.play(Configuration.getScreenVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    /**
     * Plays message event sound
     */
    public static void message() {
        try {
            MESSAGE.play(Configuration.getScreenVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    /**
     * Plays information message event sound
     */
    public static void info() {
        try {
            INFO.play(Configuration.getScreenVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    /**
     * Plays warning message event sound
     */
    public static void warn() {
        try {
            WARN.play(Configuration.getScreenVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    /**
     * Plays warning message event sound
     */
    public static void waitS() {
        try {
            WAIT.play(Configuration.getScreenVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    /**
     * Plays error message event sound
     */
    public static void error() {
        try {
            ERROR.play(Configuration.getScreenVolume());
        } catch (Exception e) {
            logger.error("playing soung", e);
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}