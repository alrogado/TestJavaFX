package com.saphire.components;


import com.sun.javafx.scene.control.skin.LabeledText;
import eu.hansolo.tilesfx.fonts.Fonts;
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
import javafx.scene.text.Text;
import javafx.util.Duration;
import com.saphire.components.extra.Utils;
import com.saphire.util.GuiColors;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;
import static com.saphire.util.GuiColors.FRG_SHADOW;

/**
 * Created by pedro_000 on 12/4/13.
 */
public class ToggleSwitchSkin extends SkinBase<ToggleSwitch> {
    StackPane thumb;
    StackPane thumbArea;
    LabeledText label;
    Text labelOn;
    Text labelOff;
    StackPane labelContainer;
    Circle trigger;
    Rectangle background;


    int width = 120;
    int height = 50;
    int radius = 25;
    int extraWidth = 50;

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected ToggleSwitchSkin(ToggleSwitch control) {
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
        background.setFill(control.isSelected() ? GuiColors.FRG : Color.WHITE);
        background.setStroke(GuiColors.FRG);
        background.setEffect(dropShadow);

        thumb = new StackPane(trigger);
        thumbArea = new StackPane(background);
        thumbArea.setEffect(dropShadow);

        DropShadow onOffDropShadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.25), PREFERRED_WIDTH * 0.008, 0.0, 0, PREFERRED_WIDTH * 0.014);

        labelOn = new Text("ON");
        labelOn.setFont(Fonts.latoBold(22));
        labelOn.setFill(Color.WHITE);
        labelOn.setEffect(onOffDropShadow);
        labelOff = new Text("OFF");
        labelOff.setFill(GuiColors.FRG);
        labelOff.setFont(Fonts.latoBold(22));
        labelOff.setEffect(onOffDropShadow);

        label = new LabeledText(control);
        label.setText(getSkinnable().getTurnOnText());
        //label.setFont(Fonts.latoBold(22));

        labelContainer = new StackPane();
        labelContainer.getChildren().addAll(label);

        updateLabel();
        getChildren().addAll(labelContainer, thumbArea, thumb, labelOn, labelOff);
        StackPane.setAlignment(label, Pos.CENTER_LEFT);

        control.setEffect(onOffDropShadow);

        thumb.getStyleClass().setAll("thumb");
        thumbArea.getStyleClass().setAll("thumb-area");

        thumbArea.setOnMouseReleased(event -> mousePressedOnToggleSwitch());
        thumb.setOnMouseReleased(event -> mousePressedOnToggleSwitch());
        control.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.booleanValue() != oldValue.booleanValue())
                selectedStateChanged();
        });
    }

    public void selectedStateChanged() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(100), thumb);
        double thumbAreaWidth = snapSize(thumbArea.prefWidth(-1));
        double thumbWidth = snapSize(thumb.prefWidth(-1));
        if (getSkinnable().isSelected()) {
            transition.setByX(thumbAreaWidth - thumbWidth * 2.3);
        } else {
            transition.setByX(-(thumbAreaWidth - thumbWidth * 2.3));
        }
        transition.setCycleCount(1);
        transition.play();
        updateLabel();
    }

    private void mousePressedOnToggleSwitch() {
        getSkinnable().setSelected(!getSkinnable().isSelected());
        background.setFill(getSkinnable().isSelected() ? GuiColors.FRG : Color.WHITE);
    }

    private void updateLabel() {
        //label.setText(getSkinnable().isSelected() ? getSkinnable().getTurnOnText() : getSkinnable().getTurnOffText());
        trigger.setFill(getSkinnable().isSelected() ? GuiColors.FRG : Color.WHITE);
        labelOn.setVisible(getSkinnable().isSelected() ? true : false);
        labelOff.setVisible(getSkinnable().isSelected() ? false : true);
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        double thumbWidth = snapSize(thumb.prefWidth(-1));
        double thumbHeight = snapSize(thumb.prefHeight(-1));
        thumb.resize(thumbWidth, thumbHeight);

        labelOn.resize(thumbWidth, thumbHeight);
        labelOff.resize(thumbWidth, thumbHeight);


        double thumbAreaY = snapPosition(contentY);
        double thumbAreaWidth = snapSize(thumbArea.prefWidth(-1));
        double thumbAreaHeight = snapSize(thumbArea.prefHeight(-1));

        thumbArea.resize(thumbAreaWidth, thumbAreaHeight);
        thumbArea.setLayoutX(contentWidth - thumbAreaWidth);
        thumbArea.setLayoutY(thumbAreaY);

        labelOn.setLayoutY(thumbAreaY + radius + 8);
        labelOff.setLayoutY(thumbAreaY + radius + 8);

        double factor = getSkinnable().isInitialValue() ? -1.8 : 2;
        labelOn.setLayoutX(thumbArea.getLayoutX() + thumbAreaWidth - thumbWidth + 10 + factor);
        labelOff.setLayoutX(thumbArea.getLayoutX() + 2 + factor);

        labelContainer.resize(contentWidth - thumbAreaWidth, thumbAreaHeight);
        labelContainer.setLayoutY(thumbAreaY);

        if (getSkinnable().isSelected()) {
            thumb.setLayoutX(thumbArea.getLayoutX() + thumbAreaWidth - thumbWidth);
            thumb.setLayoutY(thumbAreaY + (thumbAreaHeight - thumbHeight) / 2);
        } else {
            thumb.setLayoutX(thumbArea.getLayoutX());
            thumb.setLayoutY(thumbAreaY + (thumbAreaHeight - thumbHeight) / 2);
        }
    }


    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final String labelText = label.getText();
        final Font font = label.getFont();
        double textWidth = Utils.computeTextWidth(font, labelText, 0);
        return leftInset + textWidth + thumbArea.prefWidth(-1) + rightInset + extraWidth;
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Font font = label.getFont();
        final String labelText = label.getText();
        final double textHeight = Utils.computeTextHeight(font, labelText, 0, label.getLineSpacing(), label.getBoundsType());

        return topInset + Math.max(thumb.prefHeight(-1), textHeight) + bottomInset;
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final String labelText = label.getText();
        final Font font = label.getFont();
        double textWidth = Utils.computeTextWidth(font, labelText, 0);

        return leftInset + textWidth + 20 + thumbArea.prefWidth(-1) + rightInset;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Font font = label.getFont();
        final String labelText = label.getText();
        final double textHeight = Utils.computeTextHeight(font, labelText, 0, label.getLineSpacing(), label.getBoundsType());

        return topInset + Math.max(thumb.prefHeight(-1), textHeight) + bottomInset;
    }
}
