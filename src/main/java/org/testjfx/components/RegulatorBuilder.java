package org.testjfx.components;

import eu.hansolo.fx.regulators.Regulator;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.testjfx.util.GuiColors;

/**
 * Created by alrogado on 6/15/17.
 */
public class RegulatorBuilder {

    public static Regulator createMusicRegulator(String title, String unit){
        return eu.hansolo.fx.regulators.RegulatorBuilder.create()
                .prefSize(400, 400)
                //.barColor(Color.rgb(255, 222, 102))
                .title(title)
                .unit(unit)
                /*.textColor(Color.YELLOW)
                .symbolColor(Color.MAGENTA)*/
                .icon(FontAwesome.MUSIC)
                .iconColor(GuiColors.FRG)
                .color(GuiColors.FRG)
                .build();
    }
}
