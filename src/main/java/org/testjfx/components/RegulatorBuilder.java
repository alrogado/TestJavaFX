package org.testjfx.components;

import eu.hansolo.fx.regulators.Regulator;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.Ikonli;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.testjfx.util.GuiColors;

/**
 * Created by alrogado on 6/15/17.
 */
public class RegulatorBuilder {

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
