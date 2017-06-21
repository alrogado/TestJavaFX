package org.testjfx.components;

import eu.hansolo.fx.regulators.*;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikonli;
import org.testjfx.conf.Configuration;

import static org.testjfx.util.GuiColors.FRG;

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
                .color(FRG)
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
                .gradientStops(
                        new Stop(0.0, Color.RED),
                        new Stop(0.5, Color.YELLOW),
                        new Stop(1, Color.GREEN))
                //.symbolColor(Color.CRIMSON)
                .icon(ikon!=null?ikon: Ikonli.NONE)
                .iconColor(Color.WHITE)
                //.textColor(Color.MAGENTA)
                .color(FRG)
                /*.onTargetSet(e -> System.out.println("New target set to " + feedbackRegulator.getTargetValue()))
                .onAdjusted(e -> System.out.println("Battery charge is " + feedbackRegulator.getCurrentValue() + "%"))*/
                .build();
    }

    static int MINVALUETEMP = -20;
    static int MAXVALUETEMP = 60;

    public static Tile createSparkRegulator(String title, double start, double stop){

        return TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE_SPARK_LINE)
                .prefSize(400, 400)
                .title(title)
                .minValue(MINVALUETEMP)
                .maxValue(MAXVALUETEMP)
                .locale(Configuration.LOCALE)
                .animated(true)
                .textVisible(true)
                .sectionTextVisible(true)
                .averagingPeriod(25)
                .autoReferenceValue(true)
                .barColor(FRG)
                .barBackgroundColor(Color.rgb(255, 255, 255, 0.1))
                .sections(
                        new eu.hansolo.tilesfx.Section(MINVALUETEMP, start, Tile.LIGHT_GREEN),
                        new eu.hansolo.tilesfx.Section(start, stop, Tile.YELLOW),
                        new eu.hansolo.tilesfx.Section(stop, MAXVALUETEMP, Tile.LIGHT_RED))
                .sectionsVisible(true)
                .highlightSections(false) //is not working the default change
                .strokeWithGradient(true)
                .gradientStops(
                        new Stop(0.0, Tile.LIGHT_GREEN),
                        new Stop(0.33, Tile.LIGHT_GREEN),
                        new Stop(0.33,Tile.YELLOW),
                        new Stop(0.67, Tile.YELLOW),
                        new Stop(0.67, Tile.LIGHT_RED),
                        new Stop(1.0, Tile.LIGHT_RED))
                .build();
    }
    public static Regulator createRegulator(String title, String subtitle, String unit, Ikon ikon, Double value, Double minVal, Double maxVal){
        final Regulator regulator = eu.hansolo.fx.regulators.RegulatorBuilder.create()
                .prefSize(400, 400)
                //.barColor(Color.rgb(255, 222, 102))
                .title(title)
                .subtitle(subtitle)
                .unit(unit)
                .targetValue(value!=null?value:0)
                .minValue(minVal!=null?minVal:0)
                .maxValue(maxVal!=null?maxVal:100)
                /*.textColor(Color.YELLOW)
                .symbolColor(Color.MAGENTA)*/
                .icon(ikon!=null?ikon: Ikonli.NONE)
                .iconColor(FRG)
                .color(FRG)
                .build();
        regulator.setOnTargetSet(e -> System.out.println("New target set to " + regulator.getTargetValue()));
        return regulator;
    }
}
