package org.testjavafx.components;

import org.testjavafx.conf.ApplicationSettings;
import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.LcdDesign;
import eu.hansolo.medusa.LcdFont;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alrogado on 6/15/17.
 */
public class ClockBuilder {

    public static Clock createClock() {
        return eu.hansolo.medusa.ClockBuilder.create()
                .skinType(Clock.ClockSkinType.TEXT)
                .locale(ApplicationSettings.LOCALE)
                .lcdFont(LcdFont.DIGITAL_BOLD)
                .shadowsEnabled(true)
                .running(true)
                .secondsVisible(true)
                .textColor(Color.WHITE)
                .minuteColor(Color.WHITE)
                .dateColor(Color.WHITE)
                .lcdDesign(LcdDesign.WHITE)
                .build();
    }

    public static List<Clock> createClocks() {
        return new ArrayList<Clock>() {{
            for (Clock.ClockSkinType skinType : Clock.ClockSkinType.values()) {
                add(eu.hansolo.medusa.ClockBuilder.create()
                        .skinType(skinType)
                        .locale(ApplicationSettings.LOCALE)
                        .shadowsEnabled(true)
                        //.discreteSeconds(false)
                        //.discreteMinutes(false)
                        .running(true)
                        //.backgroundPaint(Color.web("#1f1e23"))
                        //.hourColor(Color.web("#dad9db"))
                        .lcdFont(LcdFont.DIGITAL)
                        .secondsVisible(true)
                        .minuteColor(Color.web("#dad9db"))
                        .secondColor(Color.web("#d1222b"))
                        .hourTickMarkColor(Color.web("#9f9fa1"))
                        .minuteTickMarkColor(Color.web("#9f9fa1"))
                        .build());
            }
        }};
    }
}
