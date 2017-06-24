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

package eu.hansolo.tilesfx;

import javafx.geometry.Orientation;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.testjfx.controllers.components.RegulatorsController;

public class RegulatorsPane extends Region {

    private RegulatorsController regulatorsController;

    Pane depositTile;
    Pane tipTile;
    Pane frequencyStackPane;
    Pane fluencyPane;
    Pane startButtonPane;


    public RegulatorsPane(RegulatorsController regulatorsController) {
        this.regulatorsController=regulatorsController;
        frequencyStackPane = new Pane(regulatorsController.getFrequency());
        fluencyPane = new Pane(regulatorsController.getFluency());
        tipTile = new Pane(regulatorsController.getTipTempTile());
        depositTile = new Pane(regulatorsController.getDepositTempTile());
        startButtonPane = new Pane(regulatorsController.createStartButton());
        getChildren().addAll(
                depositTile,
                tipTile,
                fluencyPane,
                frequencyStackPane,
                startButtonPane);
    }


    @Override
    public Orientation getContentBias() {
        return Orientation.HORIZONTAL;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        fluencyPane.relocate(getPadding().getLeft(), getPadding().getTop());
        double depositTempHeight = fluencyPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        double depositTempWidth = fluencyPane.prefWidth(getHeight() - getPadding().getLeft() - getPadding().getRight());
        fluencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), depositTempHeight);

        tipTile.relocate(getPadding().getLeft()+ depositTempWidth, getPadding().getTop());
        double tipTempHeight = tipTile.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        tipTile.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), tipTempHeight);

        startButtonPane.relocate(getPadding().getLeft()+ depositTempWidth-startButtonPane.getWidth()/2, getPadding().getTop()+tipTempHeight-startButtonPane.getHeight()/2);
        double buttonHeight = startButtonPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        startButtonPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), buttonHeight);

        fluencyPane.relocate(getPadding().getLeft(), getPadding().getTop() + depositTempHeight +2 + 6 );
        double fluencyHeight = fluencyPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        double fluencyWidth = fluencyPane.prefWidth(getWidth() - getPadding().getLeft() - getPadding().getRight());
        fluencyPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), fluencyHeight);

        frequencyStackPane.relocate(getPadding().getLeft()+fluencyWidth+ 2 + 6, getPadding().getTop() + depositTempHeight + 2 + 6);
        double frequencyHeight = frequencyStackPane.prefHeight(getWidth() - getPadding().getLeft() - getPadding().getRight());
        frequencyStackPane.resize(getWidth() - getPadding().getLeft() - getPadding().getRight(), frequencyHeight);
    }

    /*@Override
    protected double computePrefHeight(double width) {
        double widthWithPadding = width - getPadding().getLeft() - getPadding().getRight();
        return getPadding().getTop() + fluencyPane.prefHeight(widthWithPadding) + 2 + getPadding().getBottom();
    }*/
}