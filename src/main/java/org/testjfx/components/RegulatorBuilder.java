package org.testjfx.components;

import eu.hansolo.fx.regulators.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikonli;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.testjfx.util.GuiColors;

/**
 * Created by alrogado on 6/15/17.
 */
public class RegulatorBuilder {

    public static ColorRegulator createColorRegulator(String title, String unit, Ikon ikon, Double value, Double minVal, Double maxVal){
        return ColorRegulatorBuilder.create()
                .prefSize(400, 400)
                //.textColor(Color.YELLOW)
                //.textColor(Color.YELLOW)
                //.color(Color.PURPLE)
                .brightness(1)
                .color(GuiColors.FRG)
                .onButtonOnPressed(e -> System.out.println("Light ON"))
                .onButtonOffPressed(e -> System.out.println("Light OFF"))
                .build();
    }

    public static FeedbackRegulator createFeedbackRegulator(String title, String unit, Ikon ikon, Double value, Double minVal, Double maxVal){
        return FeedbackRegulatorBuilder.create()
                .prefSize(400, 400)
                .minValue(0)
                .maxValue(100)
                .targetValue(80)
                .currentValue(25)
                .unit(unit)
                .gradientStops(new Stop(0.0, Color.RED),
                        new Stop(0.5, Color.YELLOW),
                        new Stop(1, Color.GREEN))
                //.symbolColor(Color.CRIMSON)
                .icon(ikon!=null?ikon: Ikonli.NONE)
                .iconColor(Color.WHITE)
                //.textColor(Color.MAGENTA)
                .color(GuiColors.FRG)
                /*.onTargetSet(e -> System.out.println("New target set to " + feedbackRegulator.getTargetValue()))
                .onAdjusted(e -> System.out.println("Battery charge is " + feedbackRegulator.getCurrentValue() + "%"))*/
                .build();
    }

    public static Regulator createRegulator(String title, String unit, Ikon ikon,Double value, Double minVal, Double maxVal){
        final Regulator regulator = eu.hansolo.fx.regulators.RegulatorBuilder.create()
                .prefSize(400, 400)
                //.barColor(Color.rgb(255, 222, 102))
                .title(title)
                .unit(unit)
                .targetValue(value!=null?value:0)
                .minValue(minVal!=null?minVal:0)
                .maxValue(maxVal!=null?maxVal:100)
                /*.textColor(Color.YELLOW)
                .symbolColor(Color.MAGENTA)*/
                .icon(ikon!=null?ikon: Ikonli.NONE)
                .iconColor(GuiColors.FRG)
                .color(GuiColors.FRG)
                .build();
        regulator.setOnTargetSet(e -> System.out.println("New target set to " + regulator.getTargetValue()));
        return regulator;
    }
}
