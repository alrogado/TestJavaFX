package org.lazer.components;

import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.ClockBuilder;
import eu.hansolo.medusa.LcdFont;
import javafx.scene.paint.Color;
import org.lazer.GuiApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alrogado on 6/15/17.
 */
public class ClockB {

    public static Clock createClock(){
        return ClockBuilder.create()
                .skinType(Clock.ClockSkinType.TEXT)
                .locale(GuiApp.LOCALE)
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
                .build();
    }

    public static List<Clock> createClocks(){
        return new ArrayList<Clock>(){{
            for(Clock.ClockSkinType skinType : Clock.ClockSkinType.values()) {
                add(ClockBuilder.create()
                        .skinType(skinType)
                        .locale(GuiApp.LOCALE)
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
