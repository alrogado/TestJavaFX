package org.testjfx.components;

import eu.hansolo.fx.regulators.*;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikonli;
import org.testjfx.conf.Configuration;
import org.testjfx.util.GuiColors;

import static javafx.scene.text.TextAlignment.CENTER;
import static org.testjfx.util.GuiColors.FRG;

/**
 * Created by alrogado on 6/15/17.
 */
public class RegulatorBuilder {

    public static ColorRegulator createColorRegulator(String title, String unit, Ikon ikon, Double heigth, Double width, Double value, Double minVal, Double maxVal){
        return ColorRegulatorBuilder.create()
                .prefSize(width,heigth)
                //.textColor(Color.YELLOW)
                //.textColor(Color.YELLOW)
                //.color(Color.PURPLE)
                .brightness(1)
                .color(FRG)
                .onButtonOnPressed(e -> System.out.println("Light ON"))
                .onButtonOffPressed(e -> System.out.println("Light OFF"))
                .build();
    }

    public static FeedbackRegulator createFeedbackRegulator(String title, String unit, Ikon ikon, Double width, Double height, Double value, Double minVal, Double maxVal){
        return FeedbackRegulatorBuilder.create()
                .prefSize(width, height)
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

    public static Tile createTempSparkRegulator(String title, Double width, Double height, double start, double stop, boolean averageVisible, TextAlignment textAllignment){
        int decimals = 0;
        String format = new StringBuilder("%.").append(Integer.toString(decimals)).append("f").toString();
        String startFormated = String.format(Configuration.LOCALE, format, start);
        String stopFormated = String.format(Configuration.LOCALE, format, stop );

        String text = Configuration.getBundleString("start.label")+": "+startFormated+" - "+Configuration.getBundleString("stop.label")+": "+stopFormated;
        return TileBuilder.create()
                .skinType(Tile.SkinType.GAUGE_SPARK_LINE)
                .prefSize(width, height)
                .title(title)
                //.titleColor(GuiColors.FRG_4)
                .titleAlignment(CENTER)
                .textSize(Tile.TextSize.BIGGER) //este está mal asignado ya que sale en el Tñítulo
                .foregroundBaseColor(GuiColors.FRG)
                .decimals(decimals)
                .text(text)
                .textVisible(true)
                .textAlignment(textAllignment)
                //.textColor(GuiColors.FRG_2)
                .unit("ºC")
                .averageVisible(averageVisible)
                .minValue(MINVALUETEMP)
                .maxValue(MAXVALUETEMP)
                .locale(Configuration.LOCALE)
                .animated(true)
                .textVisible(true)
                .sectionTextVisible(true)
                .sectionsVisible(true)
                .averagingPeriod(25)
                .autoReferenceValue(true)
                //.barColor(FRG)
                //.barBackgroundColor(Color.rgb(255, 255, 255, 0.0))
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
    public static Regulator createRegulator(String title, String subtitle, String unit, Ikon ikon, Double width, Double height, Double value, Double minVal, Double maxVal){
        final Regulator regulator = eu.hansolo.fx.regulators.RegulatorBuilder.create()
                .prefSize(width, height)
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
