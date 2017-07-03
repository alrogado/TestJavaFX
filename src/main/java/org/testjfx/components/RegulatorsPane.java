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
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import org.testjfx.conf.Configuration;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.controllers.components.RegulatorsController.*;
import static org.testjfx.util.GuiColors.FRG;

public class RegulatorsPane extends Region {

    
    Tile depositTempTile;
    Tile tipTempTile;

    Tile fluencyTile;
    Tile frequencyTile;

    Pane startButtonPane;

    public static Double WIDTHTILE = 420d;
    public static Double HEIGHTTILE = 420d;

    double widthSizeTile = 0;
    double heigthSizeTile = 0;
    double sizeTile = 0;
    double factor = 1.78;

    public RegulatorsPane() {
        initComponents();
        //setPadding(new Insets(0,0,5,5));
        
        getChildren().addAll(
                getDepositTempTile(),
                getTipTempTile(),
                getFrequencyTile(),
                getFluencyTile(),
                getStartButtonPane());
        /*widthSizeTile = getWidth()/factor;
        heigthSizeTile = getHeight()/factor;*/
        sizeTile = Math.max(HEIGHTTILE, Math.min(getWidth()/factor, getHeight()/factor));
        widthSizeTile = sizeTile;
        heigthSizeTile = sizeTile;

        //setPrefSize(getTipTempTile().getWidth()*2, getTipTempTile().getHeight()*2);
        layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
            //double widthSizeTile = Math.max(newBounds.getWidth(), newBounds.getHeight())/factor;
            /*widthSizeTile = newBounds.getWidth()/factor;
            heigthSizeTile = newBounds.getHeight()/factor;*/
            sizeTile = Math.max(HEIGHTTILE, Math.min(newBounds.getWidth()/factor, newBounds.getHeight()/factor));
            widthSizeTile = sizeTile;
            heigthSizeTile = sizeTile;
            getTipTempTile().setPrefSize(widthSizeTile, heigthSizeTile);
            getDepositTempTile().setPrefSize(widthSizeTile, heigthSizeTile);
            getFluencyTile().setPrefSize(widthSizeTile, heigthSizeTile);
            getFrequencyTile().setPrefSize(widthSizeTile, heigthSizeTile);
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

        getDepositTempTile().relocate(getPadding().getLeft(), getPadding().getTop());
        double depositTempHeight = getDepositTempTile().prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        double depositTempWidth = getDepositTempTile().prefWidth(getHeight() - getPadding().getLeft() - getPadding().getRight());

        getTipTempTile().relocate(getPadding().getLeft()+ depositTempWidth, getPadding().getTop());
        double tipTempHeight = getTipTempTile().prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());

        getStartButtonPane().relocate(getPadding().getLeft()+ depositTempWidth - getStartButtonPane().getWidth()/2, getPadding().getTop()+tipTempHeight- getStartButtonPane().getHeight()/2-10);

        getFrequencyTile().relocate(getPadding().getLeft(), getPadding().getTop() + depositTempHeight -70);
        double frequencyWidth = getFrequencyTile().prefWidth(getWidth() - getPadding().getLeft() - getPadding().getRight());

        getFluencyTile().relocate(getPadding().getLeft() + frequencyWidth, getPadding().getTop() + depositTempHeight-70);

        getTipTempTile().resize(widthSizeTile, heigthSizeTile);
        getDepositTempTile().resize(widthSizeTile, heigthSizeTile);
        getFluencyTile().resize(widthSizeTile, heigthSizeTile);
        getFrequencyTile().resize(widthSizeTile, heigthSizeTile);

    }

    @Override
    protected double computePrefHeight(double width) {
        double widthWithPadding = width - getPadding().getLeft() - getPadding().getRight();
        return getPadding().getTop() + getFluencyTile().prefHeight(widthWithPadding) + getTipTempTile().prefHeight(widthWithPadding) +getPadding().getBottom();
    }

    @Override protected double computePrefWidth(double height) {
        double widthWithPadding = height - getPadding().getTop() - getPadding().getBottom();
        return getPadding().getTop() + getFluencyTile().prefWidth(widthWithPadding) + getTipTempTile().prefWidth(widthWithPadding) +getPadding().getBottom();
    }

    public void initComponents() {
        createTempeperatureSparkGauage();
        createFreqFluGridPane();
        createStartButton();
    }

    public Node createStartButton() {
        //todo controlar que con la inicializacion con
        // new LC().fillX().fillY().pack(), new AC(), new AC()
        // esto no se va de madre, porque al usar unos con una y otros con otra esto hace que se
        // vaya de madre toda la aplicación, es bastante desconcertante
        //MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        JFXButton startButton = new JFXButton(Configuration.getBundleString("buttonStart.label"));
        startButton.setTooltip(new Tooltip(""));
        startButton.setButtonType(JFXButton.ButtonType.RAISED);
        startButton.getStyleClass().add(WORKMODE_BUTTON);
        double r=50;
        startButton.setShape(new Circle(r));
        startButton.setMinSize(2*r, 2*r);
        startButton.setMaxSize(2*r, 2*r);
        startButton.setBorder(new Border(new BorderStroke(FRG, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        startButtonPane = new Pane(startButton);
        return startButton;
    }

    private Node createFreqFluGridPane() {
        Regulator frequency = RegulatorBuilder.createRegulator(
                Configuration.getBundleString("frecuency.label"),
                "Hz",
                "",null,
                WIDTHTILE, HEIGHTTILE,
                50d, 30d, 200d);

        frequencyTile = TileBuilder.create()
                .prefSize(WIDTHTILE, HEIGHTTILE)
                .skinType(Tile.SkinType.CUSTOM)
                //.textVisible(false)
                .graphic(frequency)
                .build();

        Regulator fluency = RegulatorBuilder.createRegulator(
                Configuration.getBundleString("fluencia.label"),
                "J/cm",
                "",
                null,
                WIDTHTILE, HEIGHTTILE,
                96d, 20d, 130d);

        fluencyTile = TileBuilder.create()
                .prefSize(WIDTHTILE, HEIGHTTILE)
                .skinType(Tile.SkinType.CUSTOM)
                .graphic(fluency)
                //.textVisible(false)
                .build();

        FlowGridPane regulatorsPane = new FlowGridPane(2,1, frequencyTile, fluencyTile);
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

    public Pane getStartButtonPane() {
        return startButtonPane;
    }

    public Tile getFluencyTile() {
        return fluencyTile;
    }

    public Tile getFrequencyTile() {
        return frequencyTile;
    }

}