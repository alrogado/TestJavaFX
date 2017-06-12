package org.lazer.util;

import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

import static org.lazer.util.GuiColors.GRAD_FGR_BGR;

/**
 * Created by alvaro.lopez on 06/06/2017.
 */
public class IkonUtils {

    public static FontIcon customizeIkon(Ikon ikon) {
        FontIcon fontIcon = new FontIcon(ikon);
        fontIcon.setIconSize(48);
        fontIcon.setIconColor(Color.BLUE);
        fontIcon.setFill(GRAD_FGR_BGR);
        return fontIcon;
    }
}
