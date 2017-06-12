package org.lazer.util.animatedgradients;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Created by alvaro.lopez on 12/06/2017.
 */
public class AnimatedGradients extends Application {
    protected static int baseDuration = 10000;
    protected static Color baseExternalColor = Color.rgb(0, 0, 0);
    protected static Color baseInternalColor = Color.rgb(127, 127, 127);
    protected static double durationDelta = 0.25;
    protected static double externalDelta = 0.1;
    protected static double internalDelta = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox();
        root.setSpacing(1);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 761, 500, Color.BLACK);
        primaryStage.setScene(scene);
        AnimatedIcon ai = new AnimatedIcon(primaryStage);
        primaryStage.getIcons().add(ai);
        ai.startAnimation();
        primaryStage.setTitle("Animated Gradients");
        for (int i = 0; i < 10; i++) {
            FilledRegion gr = new FilledRegion();
            root.getChildren().add(gr);
            gr.startAnimation();
        }
        primaryStage.show();

    }}