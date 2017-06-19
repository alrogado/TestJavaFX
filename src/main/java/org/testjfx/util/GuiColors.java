package org.testjfx.util;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Created by alrogado on 5/29/17.
 */
public class GuiColors {

    public static Color FRG = Color.web("#0091DC");
    public static Color FRG_1 = Color.web("#006DE5");
    public static Color FRG_2 = Color.web("#0048EE");
    public static Color FRG_3 = Color.web("#001EDC");
    public static Color FRG_4 = Color.web("#001CC5");

    public static Color BKG = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0.34);
    public static Color BKG_5 = Color.web("#00ADA5");

    public static Color FRG_FILL = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0.85);

    public static Stop[] STOPS_BGR_FGR = new Stop[]{new Stop(0, FRG), new Stop(1, BKG)};
    public static Stop[] STOPS_FGR_BGR = new Stop[]{new Stop(0, BKG), new Stop(1, FRG)};

    public static LinearGradient GRAD_FGR_BGR = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, STOPS_BGR_FGR);
    public static LinearGradient GRAD_BGR_FRG = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, STOPS_FGR_BGR);
}
