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

import com.jfoenix.controls.JFXButton;
import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import org.testjfx.conf.Configuration;
import org.testjfx.controllers.components.RegulatorsController;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.controllers.components.RegulatorsController.*;
import static org.testjfx.util.GuiColors.FRG;

public class RegulatorsPane extends Region {


    Pane depositTilePane;
    Pane tipTilePane;
    Pane frequencyPane;
    Pane fluencyPane;
    Pane startButtonPane;

    Tile depositTempTile;
    Tile tipTempTile;

    JFXButton buttonStart;

    Regulator frequency;
    Regulator fluency;

    public static Double WIDTHTILE = 380d;
    public static Double HEIGHTTILE = 380d;
    public static Double WIDTHTEMP = 220d;
    public static Double HEIGTHTEMP = 220d;

    public RegulatorsPane() {
        initComponents();
        setPadding(new Insets(0,0,5,5));
        frequencyPane = new Pane(getFrequency());
        fluencyPane = new Pane(getFluency());
        tipTilePane = new Pane(getTipTempTile());
        depositTilePane = new Pane(getDepositTempTile());
        startButtonPane = new Pane(getButtonStart());
        getChildren().addAll(
                depositTilePane,
                tipTilePane,
                frequencyPane,
                fluencyPane,
                startButtonPane);
        //setPrefSize(getTipTempTile().getWidth()+getFluency().getWidth(), getTipTempTile().getHeight()+getFluency().getHeight());
        layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
            double sizeTile = Math.max(HEIGHTTILE, Math.min(newBounds.getWidth()/2, newBounds.getHeight()/2));
            tipTilePane.setPrefSize(sizeTile, sizeTile);
            getTipTempTile().setPrefSize(sizeTile, sizeTile);
            tipTilePane.layout();

            depositTilePane.setPrefSize(sizeTile, sizeTile);
            getDepositTempTile().setPrefSize(sizeTile, sizeTile);
            depositTilePane.layout();

            double sizeTemp = Math.max(HEIGTHTEMP, Math.min(newBounds.getWidth()/2, newBounds.getHeight()/2))+getPadding().getLeft();
            fluencyPane.setPrefSize(sizeTemp, sizeTemp);
            getFrequency().setPrefSize(sizeTemp, sizeTemp);
            fluencyPane.layout();

            frequencyPane.setPrefSize(sizeTemp, sizeTemp);
            getFluency().setPrefSize(sizeTemp, sizeTemp);
            frequencyPane.layout();

            //layoutChildren();
        });
    }


    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        depositTilePane.relocate(getPadding().getLeft(), getPadding().getTop());
        double depositTempHeight = depositTilePane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        double depositTempWidth = depositTilePane.prefWidth(getHeight() - getPadding().getLeft() - getPadding().getRight());
        depositTilePane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), depositTempHeight);

        tipTilePane.relocate(getPadding().getLeft()+ depositTempWidth, getPadding().getTop());
        double tipTempHeight = tipTilePane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        tipTilePane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), tipTempHeight);

        startButtonPane.relocate(getPadding().getLeft()+ depositTempWidth -startButtonPane.getWidth()/2, getPadding().getTop()+tipTempHeight-startButtonPane.getHeight()/2-10);
        double buttonHeight = startButtonPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        startButtonPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), buttonHeight);

        frequencyPane.relocate(getPadding().getLeft() , getPadding().getTop() + depositTempHeight -5);
        double frequencyWidth = frequencyPane.prefWidth(getWidth() - getPadding().getLeft() - getPadding().getRight());
        frequencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), frequencyWidth -15);

        fluencyPane.relocate(getPadding().getLeft() + depositTempHeight +10  , getPadding().getTop() + depositTempHeight -5 );
        double fluencyHeight = fluencyPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        fluencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), fluencyHeight -15);

    }

    @Override
    protected double computePrefHeight(double width) {
        double widthWithPadding = width - getPadding().getLeft() - getPadding().getRight();
        return getPadding().getTop() + fluencyPane.prefHeight(widthWithPadding) + tipTilePane.prefHeight(widthWithPadding) +getPadding().getBottom();
    }

    @Override protected double computePrefWidth(double height) {
        double widthWithPadding = height - getPadding().getLeft() - getPadding().getRight();
        return getPadding().getTop() + fluencyPane.prefWidth(widthWithPadding) + tipTilePane.prefWidth(widthWithPadding) +getPadding().getBottom();
    }

    public void initComponents() {
        createTempeperatureSparkGauage();
        createFreqFluGridPane();
        createStartButton();
    }

    public JFXButton createStartButton() {
        //todo controlar que con la inicializacion con
        // new LC().fillX().fillY().pack(), new AC(), new AC()
        // esto no se va de madre, porque al usar unos con una y otros con otra esto hace que se
        // vaya de madre toda la aplicación, es bastante desconcertante
        //MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        buttonStart = new JFXButton(Configuration.getBundleString("buttonStart.label"));
        buttonStart.setTooltip(new Tooltip(""));
        buttonStart.setButtonType(JFXButton.ButtonType.RAISED);
        buttonStart.getStyleClass().add(WORKMODE_BUTTON);
        double r=50;
        buttonStart.setShape(new Circle(r));
        buttonStart.setMinSize(2*r, 2*r);
        buttonStart.setMaxSize(2*r, 2*r);
        buttonStart.setBorder(new Border(new BorderStroke(FRG, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        return buttonStart;
    }

    private Node createFreqFluGridPane() {
        frequency = RegulatorBuilder.createRegulator(
                Configuration.getBundleString("frecuency.label"),
                "Hz",
                "",null,
                WIDTHTEMP, HEIGTHTEMP,
                50d, 30d, 200d);
        fluency = RegulatorBuilder.createRegulator(
                Configuration.getBundleString("fluencia.label"),
                "J/cm",
                "",
                null,
                WIDTHTEMP, HEIGTHTEMP,
                96d, 20d, 130d);
        FlowGridPane regulatorsPane = new FlowGridPane(2,1, frequency, fluency);
        regulatorsPane.setHgap(horizontalGap);
        regulatorsPane.setPadding(padding);
        return regulatorsPane;
    }

    private Node createTempeperatureSparkGauage() {
        depositTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("deposit.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getDepositMinValue(),
                Configuration.getDepositMaxValue(),
                false,false,
                LEFT);
        tipTempTile = RegulatorBuilder.createTempSparkRegulator(
                Configuration.getBundleString("tip.label"),
                WIDTHTILE, HEIGHTTILE,
                Configuration.getTipMinValue(),
                Configuration.getTipMaxValue(),
                false, false,
                RIGHT);

        FlowGridPane pane = new FlowGridPane(2,1, depositTempTile, tipTempTile);
        pane.setHgap(horizontalGap);
        pane.setPadding(padding);
        return pane;
    }

    public Tile getDepositTempTile() {
        return depositTempTile;
    }

    public Tile getTipTempTile() {
        return tipTempTile;
    }

    public Regulator getFrequency() {
        return frequency;
    }

    public Regulator getFluency() {
        return fluency;
    }

    public JFXButton getButtonStart() {
        return buttonStart;
    }
}