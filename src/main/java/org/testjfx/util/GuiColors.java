package org.testjfx.util;

import javafx.geometry.Insets;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;

/**
 * Created by alrogado on 5/29/17.
 */
public class GuiColors {

    public static Color FRG = Color.web("#0091DC");
    public static Color FRG_1 = Color.web("#006DE5");
    public static Color FRG_2 = Color.web("#0048EE");
    public static Color FRG_3 = Color.web("#001EDC");
    public static Color FRG_4 = Color.web("#001CC5");

    public static Background BACKGROUNDFILL_100 = new Background(new BackgroundFill(FRG, new CornerRadii(100), Insets.EMPTY));
    public static Background BACKGROUNDFILL_DARKER_100 = new Background(new BackgroundFill(FRG.darker(), new CornerRadii(100), Insets.EMPTY));
    public static Background BACKGROUNDFILL_60 = new Background(new BackgroundFill(FRG, new CornerRadii(60), Insets.EMPTY));
    public static Background BACKGROUND_BRIGTHER_FILL_60 = new Background(new BackgroundFill(FRG.brighter(), new CornerRadii(60), Insets.EMPTY));

    public static Border BORDER_WHITE_2_OVER_100 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(100), new BorderWidths(2)));
    public static Border BORDER_WHITE_4_OVER_100 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(100), new BorderWidths(4)));
    public static Border BORDER_WHITE_4_OVER_60 = new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, new CornerRadii(60), new BorderWidths(4)));

    public static Color FRG_SHADOW = new Color(FRG.getRed(), FRG.getGreen(), FRG.getBlue(), 0.15);
    public static Color BKG = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0.0);
    public static Color BKG_5 = Color.web("#00ADA5");

    public static Color FRG_FILL = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0.85);

    public static Stop[] STOPS_BGR_FGR = new Stop[]{new Stop(0, FRG), new Stop(1, BKG)};
    public static Stop[] STOPS_FGR_BGR = new Stop[]{new Stop(0, BKG), new Stop(1, FRG)};

    public static LinearGradient GRAD_FGR_BGR = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, STOPS_BGR_FGR);
    public static LinearGradient GRAD_BGR_FRG = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, STOPS_FGR_BGR);

    public static DropShadow DROPSHADOW_COMP;
    public static DropShadow DROPSHADOW_TEXT;
    public static InnerShadow highlight;
    public static InnerShadow innerShadow;

    static {
        DROPSHADOW_COMP = new DropShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), PREFERRED_WIDTH * 0.016, 0.0, 0, PREFERRED_WIDTH * 0.028);
        DROPSHADOW_TEXT = new DropShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.35), PREFERRED_WIDTH * 0.016, 0.0, 0, PREFERRED_WIDTH * 0.008);
        highlight = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(255, 255, 255, 0.2), PREFERRED_WIDTH * 0.008, 0.0, 0, PREFERRED_WIDTH * 0.008);
        innerShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.2), PREFERRED_WIDTH * 0.008, 0.0, 0, -PREFERRED_WIDTH * 0.008);
        highlight.setInput(innerShadow);
        DROPSHADOW_COMP.setInput(highlight);
        DROPSHADOW_TEXT.setInput(highlight);
    }

}
