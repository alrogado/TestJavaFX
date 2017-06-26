package org.testjfx.components;

import eu.hansolo.tilesfx.fonts.Fonts;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.testjfx.GuiApp;
import org.testjfx.util.GuiColors;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;
import static org.testjfx.util.GuiColors.FRG_SHADOW;

public class ToggleSwitch extends Region {

    private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

    private final Label label = new Label();

    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

    public BooleanProperty switchedOnProperty() {
        return switchedOn;
    }


    int width = 120;
    int height = 50;
    int radius = 25;

    public
    ToggleSwitch(boolean value) {
        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.65), PREFERRED_WIDTH * 0.016, 0.0, 0, PREFERRED_WIDTH * 0.028);
        InnerShadow highlight = new InnerShadow(BlurType.THREE_PASS_BOX, FRG_SHADOW, PREFERRED_WIDTH * 0.008, 0.0, 0, PREFERRED_WIDTH * 0.008);
        InnerShadow innerShadow = new InnerShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.2), PREFERRED_WIDTH * 0.008, 0.0, 0, -PREFERRED_WIDTH * 0.008);
        highlight.setInput(innerShadow);
        dropShadow.setInput(highlight);

        Rectangle background = new Rectangle(width, height);
        background.setArcWidth(height);
        background.setArcHeight(height);
        background.setFill(Color.WHITE);
        background.setStroke(GuiColors.FRG);

        Circle trigger = new Circle(radius);
        trigger.setCenterX(radius);
        trigger.setCenterY(radius);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(GuiColors.FRG);
        trigger.setEffect(dropShadow);

        translateAnimation.setNode(trigger);
        fillAnimation.setShape(background);

        label.setAlignment(Pos.CENTER);
        label.prefWidthProperty().bind(widthProperty().divide(2));
        label.prefHeightProperty().bind(heightProperty());
        label.setText(value ?"ON":"OFF");
        label.setTextFill(value ?Color.WHITE:GuiColors.FRG);
        label.setFont(Fonts.latoRegular(24));
        label.setEffect(new DropShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.25), PREFERRED_WIDTH * 0.008, 0.0, 2, PREFERRED_WIDTH * 0.016));

        getChildren().addAll(background, trigger, label);

        switchedOn.addListener((obs, oldState, newState) -> {
            boolean isOn = newState.booleanValue();
            translateAnimation.setToX(isOn ? width - height : 0);
            fillAnimation.setFromValue(isOn ? Color.WHITE : GuiColors.FRG);
            fillAnimation.setToValue(isOn ? GuiColors.FRG : Color.WHITE);
            label.setText(isOn ?"ON":"OFF");
            label.setTextFill(isOn ?Color.WHITE:GuiColors.FRG);
            animation.play();
        });

        setOnMouseClicked(event -> {
            switchedOn.set(!switchedOn.get());
        });

        setEffect(dropShadow);
    }

    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        label.relocate(switchedOn.get()?getPadding().getLeft():getPadding().getLeft()+height, getPadding().getTop()-2);
    }

}