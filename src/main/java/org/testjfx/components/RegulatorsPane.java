/*
 * Copyright (c) 2016 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.testjfx.components;

import com.fxexperience.javafx.animation.TadaTransition;
import com.jfoenix.controls.JFXButton;
import eu.hansolo.fx.regulators.Fonts;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.conf.ApplicationSettings;
import org.testjfx.conf.PredefinedWorkMode;

import static org.testjfx.util.GuiColors.*;

public class RegulatorsPane extends AbstractRegulatorsPane {

    static RegulatorsPane instance;

    private static Logger logger = LoggerFactory.getLogger(RegulatorsPane.class);

    double sizeTile = 0;
    //factor has to be changed when for example text of fluency and freq are changed
    double factor = 1.78;
    SimpleBooleanProperty disabledProperty = new SimpleBooleanProperty(true);

    protected RegulatorsPane() {
        initComponents();
        //setPadding(new Insets(0,0,5,5));

        getChildren().addAll(
                getDepositTempTile(),
                getTipTempTile(),
                getFrequencyTile(),
                getFluencyTile(),
                getStartButtonPane(),
                getWorkModebuttonsPaneLeft(),
                getWorkModebuttonsPaneRight(),
                getMessagePane()
        );
        /*widthSizeTile = getWidth()/factor;
        heigthSizeTile = getHeight()/factor;*/
        calculateTileSize(Math.min(getWidth() / factor, getHeight() / factor));

        //setPrefSize(getTipTempTile().getWidth()*2, getTipTempTile().getHeight()*2);
        layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
            //double widthSizeTile = Math.max(newBounds.getWidth(), newBounds.getHeight())/factor;
            /*widthSizeTile = newBounds.getWidth()/factor;
            heigthSizeTile = newBounds.getHeight()/factor;*/
            calculateTileSize(Math.min(newBounds.getWidth() / factor, newBounds.getHeight() / factor));

            getTipTempTile().setPrefSize(widthSizeTile, heigthSizeTile);
            getDepositTempTile().setPrefSize(widthSizeTile, heigthSizeTile);
            getFluencyTile().setPrefSize(widthSizeTile, heigthSizeTile);
            getFrequencyTile().setPrefSize(widthSizeTile, heigthSizeTile);

            getMessagePane().setPrefSize(getWidthForMessagePanel(), getHeigthForMessagePanel());
            //in order to adjust pane width for the text
            workModeMessage.setWrappingWidth(getMessagePane().getWidth() - 40);

            setSpacingWorkModeButtons();

            prefSizeStartButton(newBounds);

        });
    }

    public static RegulatorsPane getInstance() {
        return instance == null ? instance = new RegulatorsPane() : instance;
    }

    @Override
    protected double getRepositionXForFreqTile() {
        return 120;
    }

    @Override
    protected double getWidthForMessagePanel() {
        return 220 + (depositTempTile.getWidth() * 0.2);
    }

    @Override
    protected double getHeigthForMessagePanel() {
        return heigthSizeTile * 0.6;
    }

    @Override
    protected void prefSizeStartButton(Bounds newBounds) {
        int factorToResizeStartButton = getFactorToResizeStartButton();
        startButtonRadious = Math.max(initialStartButtonRadious, Math.min(newBounds.getWidth() / factorToResizeStartButton, newBounds.getHeight() / factorToResizeStartButton));
        double size = 2 * startButtonRadious;
        startButton.setPrefSize(size, size);
        startButton.setMinSize(size, size);
        startButton.setMaxSize(size, size);
    }

    @Override
    protected int getFactorToResizeStartButton() {
        return 10;
    }

    protected void setSpacingWorkModeButtons() {
        //reposition the distance for workmode buttons
        int factorForSpacingButtons = 0;
        getWorkModebuttonsPaneLeft().setSpacing((getHeight() / 3 / getWorkModebuttonsPaneLeft().getChildren().size()) - factorForSpacingButtons);
        getWorkModebuttonsPaneRight().setSpacing((getHeight() / 3 / getWorkModebuttonsPaneRight().getChildren().size()) - factorForSpacingButtons);
    }

    private void calculateTileSize(double min) {
        sizeTile = Math.max(HEIGHTTILE, min);
        widthSizeTile = sizeTile;
        heigthSizeTile = sizeTile;
    }

    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected void resizeWmButtonsList() {
        workModebuttonsPaneLeft.resize(widthSizeTile, heigthSizeTile);
        workModebuttonsPaneRight.resize(widthSizeTile, heigthSizeTile);
    }

    @Override
    protected void repositionVmButtons() {
        for (JFXButton wmButton : buttonsSet) {
            wmButton.setPrefSize(getWidth() / 6, getHeight() / (getWorkModebuttonsPaneLeft().getChildren().size() * 5));
            wmButton.setFont(Fonts.robotoBold(wmButton.getWidth() / 8));
        }
    }

    @Override
    protected double getXForMessagePane(double x, double depositTempWidth) {
        return getWidth() * 0.439 - 100;
    }

    @Override
    protected double getXForStartButton(double x, double depositTempWidth) {
        return x + depositTempWidth - getStartButtonPane().getWidth() / 2;
    }

    @Override
    protected double getXForComp() {
        return getPadding().getLeft();
    }

    @Override
    protected double getRespositionMinusXForRightButtons() {
        return 40;
    }

    @Override
    protected double getRepositionXForLeftButtons() {
        return -120;
    }

    @Override
    protected double getRepositionXForFluencyTile() {
        return 120;
    }

    @Override
    protected double getRepositionYForFreqTile() {
        return 70;
    }

    @Override
    protected double getRepositionXForTipTile() {
        return 0;
    }

    @Override
    protected double computePrefHeight(double width) {
        double widthWithPadding = width - getXForComp() - getPadding().getRight();
        return getPadding().getTop() + getFluencyTile().prefHeight(widthWithPadding) + getTipTempTile().prefHeight(widthWithPadding) + getPadding().getBottom();
    }

    @Override
    protected double computePrefWidth(double height) {
        double widthWithPadding = height - getPadding().getTop() - getPadding().getBottom();
        return getPadding().getTop() + getFluencyTile().prefWidth(widthWithPadding) + getTipTempTile().prefWidth(widthWithPadding) + getPadding().getBottom();
    }

    public void disable(boolean newVal) {
        frequencyTile.setDisable(newVal);
        fluencyTile.setDisable(newVal);
        if (!newVal) {
            new TadaTransition(startButton).play();
            if (predefinedWorkMode != null)
                setWorkModeMessage(ApplicationSettings.getBundleString(predefinedWorkMode.getName() + "_wm.help"));
            else
                setWorkModeMessage(ApplicationSettings.getBundleString("workModesMessage.selectOne.label"));
        } else {
            setWorkModeMessage(ApplicationSettings.getBundleString("workModesMessage.lowtemperature.label"));
        }
        startButtonPane.setDisable(newVal);
        getMessagePane().setDisable(newVal);
        if (buttonsSet != null & buttonsSet.size() > 0)
            for (JFXButton wmButton : buttonsSet) {
                wmButton.setDisable(newVal);
            }
    }

    public PredefinedWorkMode getPredefinedWorkMode() {
        return predefinedWorkMode;
    }

    public void setPredefinedWorkMode(String workModeStr) {
        if(predefinedWorkModes!=null)
            for (PredefinedWorkMode predefinedWorkMode : predefinedWorkModes) {
                if (predefinedWorkMode.getName().equals(workModeStr)) {
                    this.predefinedWorkMode = predefinedWorkMode;
                    setWorkModeMessage(ApplicationSettings.getBundleString(this.predefinedWorkMode.getName() + "_wm.help"));
                    for (Button button : buttonsSet)
                        if (((PredefinedWorkMode) button.getProperties().get("predefinedWorkMode")).getName().equals(workModeStr))
                            button.setBackground(BACKGROUNDFILL_DARKER_100);
                        else
                            button.setBackground(BACKGROUNDFILL_100);

                }
            }
    }
}