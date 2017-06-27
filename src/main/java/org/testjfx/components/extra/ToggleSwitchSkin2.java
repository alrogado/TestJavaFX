package org.testjfx.components.extra;


import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.testjfx.util.GuiColors;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;
import static org.testjfx.util.GuiColors.FRG_SHADOW;

/**
 * Created by pedro_000 on 12/4/13.
 */
public class ToggleSwitchSkin2 extends SkinBase<ToggleSwitch2>{
    StackPane thumb;
    StackPane thumbArea;
    LabeledText label;
    StackPane labelContainer;
    Circle trigger;
    Rectangle background;


    int width = 120;
    int height = 50;
    int radius = 25;
    /*int width = 120;
    int height = 35;
    int radius = 25;*/
    int extraWidth = 50;

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected ToggleSwitchSkin2(ToggleSwitch2 control) {
        super(control);

        DropShadow dropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.65), PREFERRED_WIDTH * 0.016, 0.0, 0, PREFERRED_WIDTH * 0.028);
        InnerShadow highlight = new InnerShadow(BlurType.THREE_PASS_BOX, FRG_SHADOW, PREFERRED_WIDTH * 0.008, 0.0, 0, PREFERRED_WIDTH * 0.008);
        InnerShadow innerShadow = new InnerShadow(BlurType.THREE_PASS_BOX, FRG_SHADOW, PREFERRED_WIDTH * 0.008, 0.0, 0, -PREFERRED_WIDTH * 0.008);
        highlight.setInput(innerShadow);
        dropShadow.setInput(highlight);

        trigger = new Circle(radius);
        trigger.setCenterX(radius);
        trigger.setCenterY(radius);
        trigger.setFill(Color.WHITE);
        trigger.setStroke(GuiColors.FRG);
        trigger.setEffect(dropShadow);

        background = new Rectangle(width, height);
        background.setArcWidth(height);
        background.setArcHeight(height);
        background.setFill(Color.WHITE);
        background.setStroke(GuiColors.FRG);
        background.setEffect(dropShadow);

        thumb = new StackPane(trigger);
        thumbArea = new StackPane(background);
        //thumbArea.setEffect(dropShadow);
        label = new LabeledText(control);
        labelContainer = new StackPane();

        updateLabel(control);
        getChildren().addAll(labelContainer, thumbArea, thumb);
        labelContainer.getChildren().addAll(label);
        StackPane.setAlignment(label, Pos.CENTER_LEFT);

        thumb.getStyleClass().setAll("thumb");
        thumbArea.getStyleClass().setAll("thumb-area");

        thumbArea.setOnMouseReleased(event -> mousePressedOnToggleSwitch(control));
        thumb.setOnMouseReleased(event -> mousePressedOnToggleSwitch(control));
        control.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.booleanValue() != oldValue.booleanValue())
                selectedStateChanged(control);
        });
    }

    private void selectedStateChanged(ToggleSwitch2 control) {
        TranslateTransition transition = new TranslateTransition(Duration.millis(100), thumb);
        double thumbAreaWidth = snapSize(thumbArea.prefWidth(-1));
        double thumbWidth = snapSize(thumb.prefWidth(-1));
        if (!getSkinnable().isSelected())
            transition.setByX(-(thumbAreaWidth - thumbWidth));
        else {
            transition.setByX(thumbAreaWidth - thumbWidth);
        }
        transition.setCycleCount(1);
        transition.play();
        //updateLabel(control);
    }

    private void mousePressedOnToggleSwitch(ToggleSwitch2 toggleSwitch) {
        toggleSwitch.setSelected(!toggleSwitch.isSelected());
        background.setFill(toggleSwitch.isSelected()?GuiColors.FRG:Color.WHITE);
    }

    private void updateLabel(ToggleSwitch2 skinnable) {
        label.setText(skinnable.isSelected() ? skinnable.getTurnOnText() : skinnable.getTurnOffText());
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        ToggleSwitch2 toggleSwitch = getSkinnable();

        double thumbWidth = snapSize(thumb.prefWidth(-1));
        double thumbHeight = snapSize(thumb.prefHeight(-1));
        thumb.resize(thumbWidth, thumbHeight);

        double thumbAreaY = snapPosition(contentY);
        double thumbAreaWidth = snapSize(thumbArea.prefWidth(-1));
        double thumbAreaHeight = snapSize(thumbArea.prefHeight(-1));

        thumbArea.resize(thumbAreaWidth, thumbAreaHeight);
        thumbArea.setLayoutX(contentWidth - thumbAreaWidth);
        thumbArea.setLayoutY(thumbAreaY);

        labelContainer.resize(contentWidth - thumbAreaWidth, thumbAreaHeight);
        labelContainer.setLayoutY(thumbAreaY);

        if (!toggleSwitch.isSelected())
        {
            thumb.setLayoutX(thumbArea.getLayoutX());
            thumb.setLayoutY(thumbAreaY + (thumbAreaHeight - thumbHeight) / 2);
        } else
        {
            thumb.setLayoutX(thumbArea.getLayoutX() + thumbAreaWidth - thumbWidth-68);
            thumb.setLayoutY(thumbAreaY + (thumbAreaHeight - thumbHeight) / 2);
        }
    }


    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final String labelText = label.getText();
        final Font font = label.getFont();
        double textWidth = Utils.computeTextWidth(font, labelText, 0);
        return leftInset + textWidth + thumbArea.prefWidth(-1) + rightInset + extraWidth;
    }

    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Font font = label.getFont();
        final String labelText = label.getText();
        final double textHeight = Utils.computeTextHeight(font, labelText, 0, label.getLineSpacing(), label.getBoundsType());

        return topInset + Math.max(thumb.prefHeight(-1), textHeight) + bottomInset;
    }

    @Override protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final String labelText = label.getText();
        final Font font = label.getFont();
        double textWidth = Utils.computeTextWidth(font, labelText, 0);

        return leftInset + textWidth + 20 + thumbArea.prefWidth(-1) + rightInset;
    }

    @Override protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset)
    {
        final Font font = label.getFont();
        final String labelText = label.getText();
        final double textHeight = Utils.computeTextHeight(font, labelText, 0, label.getLineSpacing(), label.getBoundsType());

        return topInset + Math.max(thumb.prefHeight(-1), textHeight) + bottomInset;
    }
}
