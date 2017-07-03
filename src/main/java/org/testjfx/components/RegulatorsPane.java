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
import eu.hansolo.fx.regulators.Fonts;
import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.conf.Configuration;
import org.testjfx.conf.Mode;
import org.testjfx.conf.WorkModes;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.controllers.components.RegulatorsController.*;
import static org.testjfx.util.GuiColors.*;

public class RegulatorsPane extends Region {

    private static Logger logger = LoggerFactory.getLogger(RegulatorsPane.class);

    Tile depositTempTile;
    Tile tipTempTile;

    Tile fluencyTile;
    Tile frequencyTile;

    Pane startButtonPane;
    VBox workModebuttonsPaneLeft;
    VBox workModebuttonsPaneRight;

    public static Double WIDTHTILE = 180d;
    public static Double HEIGHTTILE = 180d;

    public static final String WORKMODE_BUTTON = "workmode-button";
    public static final String START_BUTTON = "start-button";
    public static final String style = "-fx-pref-width: 150px;\n" +
            "    -fx-background-color: #0091DC;\n" +
            "    -fx-background-radius: 100px;\n" +
            "    -fx-pref-height: 10px;\n" +
            "    -fx-border-color: WHITE;\n" +
            "    -fx-border-radius: 100px;\n" +
            "    -fx-border-width: 4px;";



    double widthSizeTile = 0;
    double heigthSizeTile = 0;
    double sizeTile = 0;
    //factor has to be changed when for example text of fluency and freq are changed
    double factor = 1.78;

    static double initialStartButtonRadious =50;
    double startButtonRadious =initialStartButtonRadious;
    JFXButton startButton;

    SimpleBooleanProperty disabledProperty = new SimpleBooleanProperty(true);
    private Set<JFXButton> buttonsSet = new HashSet<>();

    public RegulatorsPane() {
        initComponents();
        //setPadding(new Insets(0,0,5,5));
        
        getChildren().addAll(
                getDepositTempTile(),
                getTipTempTile(),
                getFrequencyTile(),
                getFluencyTile(),
                getStartButtonPane(),
                getWorkModebuttonsPaneLeft(),
                getWorkModebuttonsPaneRight()
                );
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

            //reposition the distance for workmode buttons
            int factorForSpacingButtons = 40;
            getWorkModebuttonsPaneLeft().setSpacing((getHeight()/2/getWorkModebuttonsPaneLeft().getChildren().size())- factorForSpacingButtons);
            getWorkModebuttonsPaneRight().setSpacing((getHeight()/2/getWorkModebuttonsPaneRight().getChildren().size())- factorForSpacingButtons);

            int factortoResizeStartButton = 10;
            startButtonRadious = Math.max(initialStartButtonRadious, Math.min(newBounds.getWidth()/ factortoResizeStartButton, newBounds.getHeight()/ factortoResizeStartButton));
            double size = 2 * startButtonRadious;
            startButton.setPrefSize(size, size);
            startButton.setMinSize(size, size);
            startButton.setMaxSize(size, size);

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

        getStartButtonPane().relocate(getPadding().getLeft()+ depositTempWidth - getStartButtonPane().getWidth()/2, getPadding().getTop()+tipTempHeight- getStartButtonPane().getHeight()/2-startButtonRadious/2);

        int MOVE_FEREQ_FLU_IN_Y  = 70;
        getFrequencyTile().relocate(getPadding().getLeft(), getPadding().getTop() + depositTempHeight - MOVE_FEREQ_FLU_IN_Y );
        double frequencyWidth = getFrequencyTile().prefWidth(getWidth() - getPadding().getLeft() - getPadding().getRight());

        getFluencyTile().relocate(getPadding().getLeft() + frequencyWidth, getPadding().getTop() + depositTempHeight - MOVE_FEREQ_FLU_IN_Y );

        //todo refactor workmode value pane
        getWorkModebuttonsPaneLeft().relocate(-120, getPadding().getTop());
        getWorkModebuttonsPaneRight().relocate(depositTempHeight*2 -40, getPadding().getTop());

        //todo we need toset resize to the same value in setPrefSize in order to not move controls after resize root pane
        getTipTempTile().resize(widthSizeTile, heigthSizeTile);
        getDepositTempTile().resize(widthSizeTile, heigthSizeTile);
        getFluencyTile().resize(widthSizeTile, heigthSizeTile);
        getFrequencyTile().resize(widthSizeTile, heigthSizeTile);


        for(JFXButton wmButton: buttonsSet){
            wmButton.setFont(Fonts.robotoMedium(startButton.getWidth()/8));
        }
        startButton.setFont(Fonts.robotoMedium(startButton.getWidth()/6));

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
        createVBoxWorkModesList();
        disabledProperty.addListener((o, oldVal, newVal) -> {
            frequencyTile.setDisable(newVal);
            frequency.setDisable(newVal);
            fluencyTile.setDisable(newVal);
            fluency.setDisable(newVal);
            startButtonPane.setDisable(newVal);
        });
    }

    private void createVBoxWorkModesList() {

        /*nodesList.addAnimatedNode(btnWorkModes,
                (expanded) -> singletonList(new KeyValue(lblWorkModes.rotateProperty(),
                        expanded ? 15 : 0,
                        Interpolator.EASE_BOTH)));*/
        List<Mode> workModes = null;
        try {
            workModes = WorkModes.getLoadedWorkModes().getWorkModes();
        } catch (IOException e) {
            logger.error("Error loading workmodes ", e);
        }
        int limit = workModes.size() / 2;
        workModebuttonsPaneLeft = createButtonsList(workModes.subList(0,limit));
        workModebuttonsPaneRight = createButtonsList(workModes.subList(limit, workModes.size()));

    }

    private VBox createButtonsList(List<Mode> workModes) {
        VBox buttonsList = new VBox();
        //buttonsList.setEffect(new GaussianBlur());
        buttonsList.setSpacing(getHeight()/workModes.size());
        buttonsList.setPadding(new Insets(20, 5, 5, 5));
        for (Mode modeName : workModes) {
            buttonsList.getChildren().add(createInnerWorkModeButton(modeName));
            buttonsSet.add(createInnerWorkModeButton(modeName));
        }
        return buttonsList;
    }

    private JFXButton createInnerWorkModeButton(Mode mode) {
        JFXButton workModeButton = new JFXButton(Configuration.getBundleString(mode.getName()+"_wm.label"));
        workModeButton.setTooltip(new Tooltip(Configuration.getBundleString(mode.getName()+"_wm.tooltip")));
        workModeButton.setButtonType(JFXButton.ButtonType.RAISED);
        workModeButton.setPrefWidth(150);
        workModeButton.setPrefHeight(getHeight()/10);
        workModeButton.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID, new CornerRadii(100), new BorderWidths(2))));
        workModeButton.setBackground(new Background(new BackgroundFill(FRG,new CornerRadii(100),Insets.EMPTY)));
        //-fx-pref-width: 150px;
        //-fx-background-color: #0091DC;
        //-fx-background-radius: 100px;
        //-fx-pref-height: 10px;
        //-fx-border-color: WHITE;
        //-fx-border-radius: 100px;
        //-fx-border-width: 4px;
        //workModeButton.setStyle(style);
        workModeButton.setTextFill(Color.WHITE);
        //workModeButton.setPrefSize(20,20);
        return workModeButton;
    }


    public void disable(){
        disabledProperty.set(true);
    }

    public void enable(){
        disabledProperty.set(false);
    }

    public Node createStartButton() {
        //todo controlar que con la inicializacion con
        // new LC().fillX().fillY().pack(), new AC(), new AC()
        // esto no se va de madre, porque al usar unos con una y otros con otra esto hace que se
        // vaya de madre toda la aplicación, es bastante desconcertante
        //MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        startButton = new JFXButton(Configuration.getBundleString("buttonStart.label"));
        startButton.setTooltip(new Tooltip(""));
        startButton.setTextFill(Color.WHITE);
        startButton.setButtonType(JFXButton.ButtonType.RAISED);
        startButton.setBorder(new Border(new BorderStroke(Color.WHITE,BorderStrokeStyle.SOLID, new CornerRadii(100), new BorderWidths(4))));
        startButton.setBackground(new Background(new BackgroundFill(FRG,new CornerRadii(100),Insets.EMPTY)));

        Circle buttonCircle = new Circle(startButtonRadious);
        startButton.setShape(buttonCircle);
        startButton.setMinSize(2* startButtonRadious, 2* startButtonRadious);
        startButton.setMaxSize(2* startButtonRadious, 2* startButtonRadious);
        startButtonPane = new Pane(startButton);
        return startButton;
    }

    Regulator frequency;
    Regulator fluency;

    private Node createFreqFluGridPane() {
        frequency = RegulatorBuilder.createRegulator(
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

        fluency = RegulatorBuilder.createRegulator(
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

    public VBox getWorkModebuttonsPaneLeft() {
        return workModebuttonsPaneLeft;
    }

    public VBox getWorkModebuttonsPaneRight() {
        return workModebuttonsPaneRight;
    }
}