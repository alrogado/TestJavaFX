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

import javafx.geometry.Orientation;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import jfxtras.scene.layout.CircularPane;
import org.testjfx.controllers.components.RegulatorsController;

import static org.testjfx.controllers.components.RegulatorsController.HEIGHTTILE;
import static org.testjfx.controllers.components.RegulatorsController.HEIGTHTEMP;

public class RegulatorsBorderPane extends Region {

    private RegulatorsController regulatorsController;

    Pane depositTilePane;
    Pane tipTilePane;
    Pane frequencyPane;
    Pane fluencyPane;
    Pane startButtonPane;


    public RegulatorsBorderPane(RegulatorsController regulatorsController) {
        this.regulatorsController=regulatorsController;
        frequencyPane = new BorderPane();
        frequencyPane.getChildren().add(regulatorsController.getFrequency());
        fluencyPane = new BorderPane();
        fluencyPane.getChildren().add(regulatorsController.getFluency());
        tipTilePane = new BorderPane();
        tipTilePane.getChildren().add(regulatorsController.getTipTempTile());
        depositTilePane = new BorderPane();
        depositTilePane.getChildren().add(regulatorsController.getDepositTempTile());
        startButtonPane = new BorderPane();
        startButtonPane.getChildren().add(regulatorsController.getButtonStart());
        getChildren().addAll(
                depositTilePane,
                tipTilePane,
                frequencyPane,
                fluencyPane,
                startButtonPane);

        layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
            double sizeTile = Math.max(HEIGHTTILE, Math.min(newBounds.getWidth()/2, newBounds.getHeight()/2));
            tipTilePane.setPrefSize(sizeTile, sizeTile);
            regulatorsController.getTipTempTile().setPrefSize(sizeTile, sizeTile);
            tipTilePane.layout();

            depositTilePane.setPrefSize(sizeTile, sizeTile);
            regulatorsController.getDepositTempTile().setPrefSize(sizeTile, sizeTile);
            depositTilePane.layout();

            double sizeTemp = Math.max(HEIGTHTEMP, Math.min(newBounds.getWidth()/2, newBounds.getHeight()/2));
            fluencyPane.setPrefSize(sizeTemp, sizeTemp);
            regulatorsController.getFrequency().setPrefSize(sizeTemp, sizeTemp);
            fluencyPane.layout();

            frequencyPane.setPrefSize(sizeTemp, sizeTemp);
            regulatorsController.getFluency().setPrefSize(sizeTemp, sizeTemp);
            frequencyPane.layout();
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

        frequencyPane.relocate(getPadding().getLeft() , getPadding().getTop() + depositTempHeight );
        double frequencyWidth = frequencyPane.prefWidth(getWidth() - getPadding().getLeft() - getPadding().getRight());
        frequencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), frequencyWidth);

        fluencyPane.relocate(getPadding().getLeft() + depositTempHeight  , getPadding().getTop() + depositTempHeight  );
        double fluencyHeight = fluencyPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        fluencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), fluencyHeight);

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
}