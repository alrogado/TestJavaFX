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
import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.conf.Configuration;
import org.testjfx.conf.Mode;
import org.testjfx.conf.WorkModes;
import org.testjfx.util.GuiColors;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
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

    /*public static final String WORKMODE_BUTTON = "workmode-button";
    public static final String START_BUTTON = "start-button";

    public static final String style = "-fx-pref-width: 150px;\n" +
            "    -fx-background-color: #0091DC;\n" +
            "    -fx-background-radius: 100px;\n" +
            "    -fx-pref-height: 10px;\n" +
            "    -fx-border-color: WHITE;\n" +
            "    -fx-border-radius: 100px;\n" +
            "    -fx-border-width: 4px;";*/



    double widthSizeTile = 0;
    double heigthSizeTile = 0;
    double sizeTile = 0;
    //factor has to be changed when for example text of fluency and freq are changed
    double factor = 1.78;

    static double initialStartButtonRadious =50;

    double startButtonRadious =initialStartButtonRadious;
    JFXButton startButton;

    SimpleBooleanProperty disabledProperty = new SimpleBooleanProperty(true);
    protected Set<JFXButton> buttonsSet = new HashSet<>();
    private FlowGridPane messagePane;
    Text workModeSelectedMessage;

    static RegulatorsPane instance;
    private Mode workMode;
    List<Mode> workModes;

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

            getMessagePane().setPrefSize(getWidthForMessagePanel(),  getHeigthForMessagePanel());

            setSpacingWorkModeButtons();

            prefSizeStartButton(newBounds);

        });
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        double x = getXForComp();
        double y = getPadding().getTop();


        getDepositTempTile().relocate(x, y);
        double depositTempHeight = getDepositTempTile().prefHeight(getWidth() - x - getPadding().getRight());
        double depositTempWidth = getDepositTempTile().prefWidth(getHeight() - x - getPadding().getRight());

        double repositionFactorXForTipTile = getRepositionXForTipTile();
        getTipTempTile().relocate(x + depositTempWidth + repositionFactorXForTipTile, y);
        double tipTempHeight = getTipTempTile().prefHeight(getWidth() - x - getPadding().getRight());

        double xforStartButton = getXForStartButton(x, depositTempWidth);
        getStartButtonPane().relocate(xforStartButton, y + tipTempHeight - getStartButtonPane().getHeight() / 2 - startButtonRadious / 2);

        double xforMessagePane = getXForMessagePane(x, depositTempWidth);
        getMessagePane().relocate(xforMessagePane, y + tipTempHeight +65);

        double repositionFactorYForFreqTile = getRepositionYForFreqTile();
        getFrequencyTile().relocate(x - getRepositionXForFreqTile(), y + depositTempHeight - repositionFactorYForFreqTile);

        double repositionFactorXForFluencyTile = getRepositionXForFluencyTile();
        double frequencyWidth = getFrequencyTile().prefWidth(getWidth() - x - getPadding().getRight());
        getFluencyTile().relocate(x + frequencyWidth + repositionFactorXForFluencyTile, y + depositTempHeight - repositionFactorYForFreqTile);

        //todo refactor workmode value pane
        double repositionFactorXForLeftButtons = getRepositionXForLeftButtons();
        getWorkModebuttonsPaneLeft().relocate(repositionFactorXForLeftButtons, y);
        double respositionMinusFactorXForRightButtons = getRespositionMinusXForRightButtons();
        getWorkModebuttonsPaneRight().relocate(depositTempHeight * 2 - respositionMinusFactorXForRightButtons, y);

        //todo we need to set resize to the same value in setPrefSize in order to not move controls after resize root pane
        getTipTempTile().resize(widthSizeTile, heigthSizeTile);
        getDepositTempTile().resize(widthSizeTile, heigthSizeTile);
        getFluencyTile().resize(widthSizeTile, heigthSizeTile);
        getFrequencyTile().resize(widthSizeTile, heigthSizeTile);


        getMessagePane().resize( getWidthForMessagePanel(), getHeigthForMessagePanel());

        resizeWmButtonsList();

        repositionVmButtons();

        getStartButton().setFont(Fonts.robotoBlack(getStartButton().getWidth() / 6));

        int factorToResizeStartButton = getFactorToResizeStartButton();
        startButtonRadious = Math.max(initialStartButtonRadious, Math.min(getWidth() / factorToResizeStartButton, getHeight() / factorToResizeStartButton));
        double size = 2 * startButtonRadious;
        startButton.resize(size, size);

    }

    protected double getRepositionXForFreqTile() {
        return 120;
    }

    protected double getWidthForMessagePanel() {
        return 220+(depositTempTile.getWidth()*0.2);
    }

    protected double getHeigthForMessagePanel() {
        return heigthSizeTile*0.6;
    }

    protected void prefSizeStartButton(Bounds newBounds) {
        int factorToResizeStartButton = getFactorToResizeStartButton();
        startButtonRadious = Math.max(initialStartButtonRadious, Math.min(newBounds.getWidth() / factorToResizeStartButton, newBounds.getHeight() / factorToResizeStartButton));
        double size = 2 * startButtonRadious;
        startButton.setPrefSize(size, size);
        //startButton.setMinSize(size, size);
        startButton.setMaxSize(size, size);
    }

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



    protected void resizeWmButtonsList() {
        workModebuttonsPaneLeft.resize(widthSizeTile, heigthSizeTile);
        workModebuttonsPaneRight.resize(widthSizeTile, heigthSizeTile);
    }

    protected void repositionVmButtons() {
        for(JFXButton wmButton: buttonsSet){
            wmButton.setPrefSize(getWidth()/6, getHeight()/(getWorkModebuttonsPaneLeft().getChildren().size()*5));
            wmButton.setFont(Fonts.robotoBold(wmButton.getWidth()/8));
        }
    }

    protected double getXForMessagePane(double x, double depositTempWidth) {
        return getWidth()*0.439 - 100;
    }

    protected double getXForStartButton(double x, double depositTempWidth) {
        return x + depositTempWidth - getStartButtonPane().getWidth() / 2;
    }

    protected double getXForComp() {
        return getPadding().getLeft();
    }

    protected double getRespositionMinusXForRightButtons() {
        return 40;
    }

    protected double getRepositionXForLeftButtons() {
        return -120;
    }

    protected double getRepositionXForFluencyTile() {
        return 120;
    }

    protected double getRepositionYForFreqTile() {
        return 70;
    }

    protected double getRepositionXForTipTile() {
        return 0;
    }

    @Override
    protected double computePrefHeight(double width) {
        double widthWithPadding = width - getXForComp() - getPadding().getRight();
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
        createMessagePanel();
    }

    private void createVBoxWorkModesList() {

        /*nodesList.addAnimatedNode(btnWorkModes,
                (expanded) -> singletonList(new KeyValue(lblWorkModes.rotateProperty(),
                        expanded ? 15 : 0,
                        Interpolator.EASE_BOTH)));*/
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
        //buttonsList.setSpacing(getHeight()/workModes.size());
        buttonsList.setPadding(new Insets(20, 5, 5, 5));
        for (Mode modeName : workModes) {
            JFXButton innerWorkModeButton = createInnerWorkModeButton(modeName);
            buttonsList.getChildren().add(innerWorkModeButton);
            buttonsSet.add(innerWorkModeButton);
        }
        return buttonsList;
    }

    protected JFXButton createInnerWorkModeButton(Mode mode) {
        JFXButton workModeButton = new JFXButton(Configuration.getBundleString(mode.getName()+"_wm.label"));
        workModeButton.setTooltip(new Tooltip(Configuration.getBundleString(mode.getName()+"_wm.tooltip")));
        workModeButton.setButtonType(JFXButton.ButtonType.RAISED);
        workModeButton.setPrefWidth(150);
        workModeButton.setPrefHeight(getHeight()/10);
        workModeButton.setBorder(BORDER_WHITE_2_OVER_100);
        workModeButton.setBackground(BACKGROUNDFILL_100);
        workModeButton.setOnMouseClicked(e->{
            this.workMode = mode;
            changeMessageText(Configuration.getBundleString(mode.getName()+"_wm.tooltip"));
        });
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


    public void disable(boolean newVal){
        frequencyTile.setDisable(newVal);
        fluencyTile.setDisable(newVal);
        if(!newVal) {
            new TadaTransition(startButton).play();
            changeMessageText(Configuration.getBundleString(workMode.getName()+"_wm.tooltip"));
        }else {
            changeMessageText("Modo de trabajo desactivado.\n Temperaturas bajas.");
        }
        startButtonPane.setDisable(newVal);
        getMessagePane().setDisable(newVal);
        for(JFXButton wmButton: buttonsSet){
            wmButton.setDisable(newVal);
        }
    }

    public void createStartButton() {
        //todo controlar que con la inicializacion con
        // new LC().fillX().fillY().pack(), new AC(), new AC()
        // esto no se va de madre, porque al usar unos con una y otros con otra esto hace que se
        // vaya de madre toda la aplicación, es bastante desconcertante
        //MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        startButton = new JFXButton(Configuration.getBundleString("buttonStart.label"));
        startButton.setTooltip(new Tooltip(""));
        startButton.setTextFill(Color.WHITE);
        startButton.setEffect(DROPSHADOW_TEXT);
        startButton.setButtonType(JFXButton.ButtonType.RAISED);
        startButton.setBorder(BORDER_WHITE_4_OVER_100);
        startButton.setBackground(BACKGROUNDFILL_100);

        Circle buttonCircle = new Circle(startButtonRadious);
        startButton.setShape(buttonCircle);
        //startButton.setMinSize(2* startButtonRadious, 2* startButtonRadious);
        startButton.setMaxSize(2* startButtonRadious, 2* startButtonRadious);
        startButtonPane = new Pane(startButton);
    }


    private void createFreqFluGridPane() {
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

    }

    private void createTempeperatureSparkGauage() {
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

    public void createMessagePanel() {
        workModeSelectedMessage = new Text();
        workModeSelectedMessage.setFill(Color.WHITE);
        workModeSelectedMessage.setText("Modo de trabajo");
        workModeSelectedMessage.setEffect(GuiColors.DROPSHADOW_TEXT);
        workModeSelectedMessage.setFont(eu.hansolo.tilesfx.fonts.Fonts.latoRegular(24));

        //messagePane.getChildren().add(workModeSelectedMessage);
        messagePane = new FlowGridPane(1,1, workModeSelectedMessage);
        messagePane.setBackground(BACKGROUND_BRIGTHER_FILL_60);
        messagePane.setBorder(BORDER_WHITE_4_OVER_60);
        messagePane.setEffect(DROPSHADOW_COMP);
        messagePane.setPadding(new Insets(25,20,20,20));

        messagePane.setPrefSize(   250,250);
    }

    public void changeMessageText(String message){
        workModeSelectedMessage.setText(message);
    }

    public Pane getMessagePane() {
        return messagePane;
    }

    public static RegulatorsPane getInstance() {
        return instance==null?instance=new RegulatorsPane():instance;
    }

    public JFXButton getStartButton() {
        return startButton;
    }

    public Mode getWorkMode() {
        return workMode;
    }

    public void setWorkMode(String workModeStr) {
        for(Mode mode:workModes){
            if(mode.getName().equals(workModeStr)) {
                workMode = mode;
                changeMessageText(Configuration.getBundleString(workMode.getName()+"_wm.tooltip"));
            }
        }
    }
}