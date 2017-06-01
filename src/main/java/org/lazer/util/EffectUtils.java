package org.lazer.util;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Created by alvaro.lopez on 01/06/2017.
 */
public class EffectUtils {

    static int fadeTransitionsDuration = 2000;
    public static void fadeOut(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(fadeTransitionsDuration), node);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.play();
    }

    public static void fadeIn(Node node) {
        FadeTransition ft = new FadeTransition(Duration.millis(fadeTransitionsDuration), node);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
    }

}
