package com.saphire.components;

import com.jfoenix.controls.JFXDecorator;
import com.saphire.conf.ApplicationSettings;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alrogado on 6/20/17.
 */
public class Audio {

    static MediaPlayer PULSE;
    static MediaPlayer TOUCH;
    static MediaPlayer MESSAGE;
    static MediaPlayer INFO;
    static MediaPlayer WARN;
    static MediaPlayer WAIT;
    static MediaPlayer ERROR;
    private static Logger logger = LoggerFactory.getLogger(Audio.class);

    public static void addPlayersToMainScene(Scene scene) {
        PULSE = new MediaPlayer(new Media(Audio.class.getResource("/audio/pulse.wav").toString()));
        TOUCH = new MediaPlayer(new Media(Audio.class.getResource("/audio/touch.wav").toString()));
        MESSAGE = new MediaPlayer(new Media(Audio.class.getResource("/audio/message.wav").toString()));
        INFO = new MediaPlayer(new Media(Audio.class.getResource("/audio/info.wav").toString()));
        WARN = new MediaPlayer(new Media(Audio.class.getResource("/audio/warn.wav").toString()));
        WAIT = new MediaPlayer(new Media(Audio.class.getResource("/audio/wait.wav").toString()));
        ERROR = new MediaPlayer(new Media(Audio.class.getResource("/audio/error.wav").toString()));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(PULSE));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(TOUCH));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(MESSAGE));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(INFO));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(WARN));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(WAIT));
        ((JFXDecorator) scene.getRoot()).getChildren().add(new MediaView(ERROR));

    }

    /**
     * Plays pulse beep tone
     */
    public static void pulse() {
        try {
            PULSE.setVolume(ApplicationSettings.getInstance().getPulseVolume());
            PULSE.play();
        } catch (Exception e) {
            logger.error("playing soung pulse", e);
        }
    }

    /**
     * Plays key press event sound
     */
    public static void keypress() {
        try {
            TOUCH.setVolume(ApplicationSettings.getInstance().getScreenVolume());
            TOUCH.play();
        } catch (Exception e) {
            logger.error("playing soung keypress", e);
        }
    }

    /**
     * Plays message event sound
     */
    public static void message() {
        try {
            MESSAGE.setVolume(ApplicationSettings.getInstance().getScreenVolume());
            MESSAGE.play();
        } catch (Exception e) {
            logger.error("playing soung message", e);
        }
    }

    /**
     * Plays information message event sound
     */
    public static void info() {
        try {
            INFO.setVolume(ApplicationSettings.getInstance().getScreenVolume());
            INFO.play();
        } catch (Exception e) {
            logger.error("playing soung info", e);
        }
    }

    /**
     * Plays warning message event sound
     */
    public static void warn() {
        try {
            WARN.setVolume(ApplicationSettings.getInstance().getScreenVolume());
            WARN.play();
        } catch (Exception e) {
            logger.error("playing soung warn", e);
        }
    }

    /**
     * Plays warning message event sound
     */
    public static void waitS() {
        try {
            WAIT.setVolume(ApplicationSettings.getInstance().getScreenVolume());
            WAIT.play();
        } catch (Exception e) {
            logger.error("playing soung waitS", e);
        }
    }

    /**
     * Plays error message event sound
     */
    public static void error() {
        try {
            ERROR.setVolume(ApplicationSettings.getInstance().getScreenVolume());
            ERROR.play();
        } catch (Exception e) {
            logger.error("playing soung error", e);
        }
    }
}
