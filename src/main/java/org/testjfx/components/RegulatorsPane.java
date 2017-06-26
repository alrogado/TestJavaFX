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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.testjfx.controllers.components.RegulatorsController;

import static org.testjfx.controllers.components.RegulatorsController.HEIGHTTILE;
import static org.testjfx.controllers.components.RegulatorsController.HEIGTHTEMP;

public class RegulatorsPane extends Region {

    private RegulatorsController regulatorsController;

    Pane depositTilePane;
    Pane tipTilePane;
    Pane frequencyPane;
    Pane fluencyPane;
    Pane startButtonPane;


    public RegulatorsPane(RegulatorsController regulatorsController) {
        this.regulatorsController=regulatorsController;
        frequencyPane = new Pane(regulatorsController.getFrequency());
        fluencyPane = new Pane(regulatorsController.getFluency());
        tipTilePane = new Pane(regulatorsController.getTipTempTile());
        depositTilePane = new Pane(regulatorsController.getDepositTempTile());
        startButtonPane = new Pane(regulatorsController.getButtonStart());
        getChildren().addAll(
                depositTilePane,
                tipTilePane,
                fluencyPane,
                frequencyPane,
                startButtonPane);

        layoutBoundsProperty().addListener((observableValue, oldBounds, newBounds) -> {
            /*double sizeTile = Math.min(HEIGHTTILE, Math.min(newBounds.getWidth(), newBounds.getHeight()));
            tipTilePane.setPrefSize(sizeTile, sizeTile);
            depositTilePane.setPrefSize(sizeTile, sizeTile);
            double sizeTemp = Math.min(HEIGTHTEMP, Math.min(newBounds.getWidth(), newBounds.getHeight()));
            fluencyPane.setPrefSize(sizeTemp, sizeTemp);
            frequencyPane.setPrefSize(sizeTemp, sizeTemp);*/
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

        startButtonPane.relocate(getPadding().getLeft()+ depositTempWidth-startButtonPane.getWidth()/2, getPadding().getTop()+tipTempHeight-startButtonPane.getHeight()/2-10);
        double buttonHeight = startButtonPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        startButtonPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), buttonHeight);

        fluencyPane.relocate(getPadding().getLeft()+ depositTempHeight*0.12, getPadding().getTop() + depositTempHeight  );
        double fluencyHeight = fluencyPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        double fluencyWidth = fluencyPane.prefWidth(getWidth() - getPadding().getLeft() - getPadding().getRight());
        fluencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), fluencyHeight);

        frequencyPane.relocate(getPadding().getLeft()+tipTempHeight+ depositTempHeight*0.12, getPadding().getTop() + depositTempHeight );
        double frequencyHeight = frequencyPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        frequencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), frequencyHeight);
    }

    /*@Override
    protected double computePrefHeight(double width) {
        double widthWithPadding = width - getPadding().getLeft() - getPadding().getRight();
        return getPadding().getTop() + fluencyPane.prefHeight(widthWithPadding) + 2 + getPadding().getBottom();
    }*/
}