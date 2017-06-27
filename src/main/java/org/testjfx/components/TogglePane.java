package org.testjfx.components;

import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.conf.Configuration;
import org.testjfx.util.GuiColors;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;
import static org.testjfx.util.GuiColors.FRG_SHADOW;

public class TogglePane extends Pane {

    private final Label label = new Label();
    ToggleSwitch toggleSwitch;

    public TogglePane(String identifier, boolean value) {
        label.setAlignment(Pos.CENTER_LEFT);
        label.setText(Configuration.getBundleString(identifier));
        label.setTextFill(value ?Color.WHITE:GuiColors.FRG);
        label.setFont(Fonts.latoBold(24));
        toggleSwitch = new ToggleSwitch(identifier, value);
        //HBox hBox = new HBox(5, label, toggleSwitch);

        MigPane rootMP = new MigPane("fill");
        rootMP.add(label, "west");
        rootMP.add(toggleSwitch, "east");
        getChildren().addAll(rootMP);

    }
}