package org.lazer.util;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Created by alrogado on 5/29/17.
 */
public class GuiColors {

    public static Color FRGCOL = Color.web("#0091DC");

    public static Color BKGCOL = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0.85);
    public static Color FRGCOL_FILL = new Color(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), 0.85);

    public static Stop[] STOPS = new Stop[] { new Stop(0, FRGCOL), new Stop(1, BKGCOL)};
    public static LinearGradient ICON_GRAD_FGR_BGR = new LinearGradient(0, 0, 1, 0, true, CycleMethod.REFLECT, STOPS);
}
