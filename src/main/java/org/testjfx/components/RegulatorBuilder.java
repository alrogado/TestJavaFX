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

    static int MINVALUETEMP = -20;
    static int MAXVALUETEMP = 60;

    public static ColorRegulator createColorRegulator(String title, String unit, Ikon ikon, Double heigth, Double width, Double value, Double minVal, Double maxVal) {
        return ColorRegulatorBuilder.create()
                .prefSize(width, heigth)
                //.textColor(Color.YELLOW)
                //.textColor(Color.YELLOW)
                //.color(Color.PURPLE)
                .brightness(1)
                .color(FRG)
                .onButtonOnPressed(e -> System.out.println("Light ON"))
                .onButtonOffPressed(e -> System.out.println("Light OFF"))
                .build();
    }

    public static FeedbackRegulator createFeedbackRegulator(String title, String unit, Ikon ikon, Double width, Double height, Double value, Double minVal, Double maxVal) {
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
                .icon(ikon != null ? ikon : Ikonli.NONE)
                .iconColor(Color.WHITE)
                //.textColor(Color.MAGENTA)
                .color(FRG)
                /*.onTargetSet(e -> System.out.println("New target set to " + feedbackRegulator.getTargetValue()))
                .onAdjusted(e -> System.out.println("Battery charge is " + feedbackRegulator.getCurrentValue() + "%"))*/
                .build();
    }

    public static Tile createTempSparkRegulator(String title, Double width, Double height, double start, double stop, boolean averageVisible, boolean textVisible, TextAlignment textAllignment) {
        int decimals = 0;
        String format = new StringBuilder("%.").append(Integer.toString(decimals)).append("f").toString();
        String startFormated = String.format(Configuration.LOCALE, format, start);
        String stopFormated = String.format(Configuration.LOCALE, format, stop);

        String text = Configuration.getBundleString("start.label") + ": " + startFormated + " - " + Configuration.getBundleString("stop.label") + ": " + stopFormated;
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
                .textVisible(textVisible)
                .sectionTextVisible(textVisible)
                .sectionIconsVisible(textVisible)
                .textAlignment(textAllignment)
                //.textColor(GuiColors.FRG_2)
                .unit("ºC")
                .averageVisible(averageVisible)
                .minValue(MINVALUETEMP)
                .maxValue(MAXVALUETEMP)
                .locale(Configuration.LOCALE)
                .animated(true)
                .highlightSections(false)
                .averagingPeriod(25)
                .autoReferenceValue(true)
                .autoScale(true)
                //.animationDuration(500)

                //.barColor(FRG)
                //.barBackgroundColor(Color.rgb(255, 255, 255, 0.0))
                .sections(
                        new eu.hansolo.tilesfx.Section(MINVALUETEMP, start, Tile.GREEN),
                        new eu.hansolo.tilesfx.Section(start, stop, FRG),
                        new eu.hansolo.tilesfx.Section(stop, MAXVALUETEMP, Tile.LIGHT_RED))
                .sectionsVisible(true)
                .highlightSections(false) //is not working the default change
                .strokeWithGradient(true)
                .alarmsVisible(true)
                .gradientStops(
                        new Stop(0.0, Tile.GREEN),
                        new Stop(0.33, Tile.GREEN),
                        new Stop(0.33, FRG),
                        new Stop(0.67, FRG),
                        new Stop(0.67, Tile.LIGHT_RED),
                        new Stop(1.0, Tile.LIGHT_RED))
                .build();
    }

    public static Regulator createRegulator(String title, String subtitle, String unit, Ikon ikon, Double width, Double height, Double value, Double minVal, Double maxVal) {
        final Regulator regulator = eu.hansolo.fx.regulators.RegulatorBuilder.create()
                .prefSize(width, height)
                //.barColor(Color.rgb(255, 222, 102))
                .title(title)
                .subtitle(subtitle)
                .unit(unit)
                .targetValue(value != null ? value : 0)
                .minValue(minVal != null ? minVal : 0)
                .maxValue(maxVal != null ? maxVal : 100)
                /*.textColor(Color.YELLOW)
                .symbolColor(Color.MAGENTA)*/
                .icon(ikon != null ? ikon : Ikonli.NONE)
                /*.symbolPath(1, 0.71428571, "M 11.7829 11.7647 L 9.3333 20 L 17.5 8.2353 L 12.7171 " +
                        "8.2353 L 15.1667 0 L 7 11.7647 L 11.7829 11.7647 ZM 1.1667 " +
                        "17.6471 L 8.8138 17.6471 L 9.5156 15.2941 L 2.3333 15.2941 " +
                        "L 2.3333 4.7059 L 10.4749 4.7059 L 12.1087 2.3529 L 1.1667 " +
                        "2.3529 C 0.5218 2.3529 0 2.8791 0 3.5294 L 0 16.4706 C 0 " +
                        "17.1209 0.5218 17.6471 1.1667 17.6471 ZM 26.8333 5.8824 L " +
                        "24.5 5.8824 L 24.5 3.5294 C 24.5 2.8791 23.9782 2.3529 23.3333" +
                        " 2.3529 L 15.6839 2.3529 L 14.9844 4.7059 L 22.1667 4.7059 " +
                        "L 22.1667 15.2941 L 14.0228 15.2941 L 12.3913 17.6471 " +
                        "L 23.3333 17.6471 C 23.9782 17.6471 24.5 17.1209 24.5 16.4706 " +
                        "L 24.5 14.1176 L 26.8333 14.1176 C 27.4782 14.1176 28 13.5915 " +
                        "28 12.9412 L 28 7.0588 C 28 6.4085 27.4782 5.8824 26.8333 5.8824 Z")*/
                .iconColor(FRG)
                .color(FRG)
                .build();
        regulator.setOnTargetSet(e -> System.out.println("New target set to " + regulator.getTargetValue()));
        return regulator;
    }
}
