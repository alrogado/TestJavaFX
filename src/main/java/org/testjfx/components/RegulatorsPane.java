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
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testjfx.conf.ApplicationSettings;
import org.testjfx.conf.PredefinedWorkMode;
import org.testjfx.conf.PredefinedWorkModes;
import org.testjfx.util.GuiColors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javafx.scene.text.TextAlignment.LEFT;
import static javafx.scene.text.TextAlignment.RIGHT;
import static org.testjfx.util.GuiColors.*;

public class RegulatorsPane extends Region {

    public static Double WIDTHTILE = 180d;
    public static Double HEIGHTTILE = 180d;
    static double initialStartButtonRadious = 50;
    static RegulatorsPane instance;
    private static Logger logger = LoggerFactory.getLogger(RegulatorsPane.class);
    protected Set<JFXButton> buttonsSet = new HashSet<>();
    Tile depositTempTile;
    Tile tipTempTile;
    Tile fluencyTile;
    Tile frequencyTile;

    /*public static final String WORKMODE_BUTTON = "workmode-button";
    public static final String START_BUTTON = "start-button";

    public static final String style = "-fx-pref-width: 150px;\n" +
            "    -fx-background-color: #0091DC;\n" +
            "    -fx-background-radius: 100px;\n" +
            "    -fx-pref-height: 10px;\n" +
            "    -fx-border-color: WHITE;\n" +
            "    -fx-border-radius: 100px;\n" +
            "    -fx-border-width: 4px;";*/
    Pane startButtonPane;
    VBox workModebuttonsPaneLeft;
    VBox workModebuttonsPaneRight;
    double widthSizeTile = 0;
    double heigthSizeTile = 0;
    double sizeTile = 0;
    //factor has to be changed when for example text of fluency and freq are changed
    double factor = 1.78;
    double startButtonRadious = initialStartButtonRadious;
    JFXButton startButton;
    SimpleBooleanProperty disabledProperty = new SimpleBooleanProperty(true);
    Text workModeMessage;
    List<PredefinedWorkMode> predefinedWorkModes;
    private FlowGridPane messagePane;
    private PredefinedWorkMode predefinedWorkMode;

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
        getMessagePane().relocate(xforMessagePane, y + tipTempHeight + 65);

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


        getMessagePane().resize(getWidthForMessagePanel(), getHeigthForMessagePanel());

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
        return 220 + (depositTempTile.getWidth() * 0.2);
    }

    protected double getHeigthForMessagePanel() {
        return heigthSizeTile * 0.6;
    }

    protected void prefSizeStartButton(Bounds newBounds) {
        int factorToResizeStartButton = getFactorToResizeStartButton();
        startButtonRadious = Math.max(initialStartButtonRadious, Math.min(newBounds.getWidth() / factorToResizeStartButton, newBounds.getHeight() / factorToResizeStartButton));
        double size = 2 * startButtonRadious;
        startButton.setPrefSize(size, size);
        startButton.setMinSize(size, size);
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
        for (JFXButton wmButton : buttonsSet) {
            wmButton.setPrefSize(getWidth() / 6, getHeight() / (getWorkModebuttonsPaneLeft().getChildren().size() * 5));
            wmButton.setFont(Fonts.robotoBold(wmButton.getWidth() / 8));
        }
    }

    protected double getXForMessagePane(double x, double depositTempWidth) {
        return getWidth() * 0.439 - 100;
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
        return getPadding().getTop() + getFluencyTile().prefHeight(widthWithPadding) + getTipTempTile().prefHeight(widthWithPadding) + getPadding().getBottom();
    }

    @Override
    protected double computePrefWidth(double height) {
        double widthWithPadding = height - getPadding().getTop() - getPadding().getBottom();
        return getPadding().getTop() + getFluencyTile().prefWidth(widthWithPadding) + getTipTempTile().prefWidth(widthWithPadding) + getPadding().getBottom();
    }

    public void initComponents() {
        createTempeperatureSparkGauage();
        createFreqFluGridPane();
        createStartButton();
        createVBoxWorkModesList();
        createMessagePanel();
    }

    private void createVBoxWorkModesList() {
        predefinedWorkModes = PredefinedWorkModes.getInstance().getPredefinedWorkModes();
        if (predefinedWorkModes != null && predefinedWorkModes.size() > 0) {
            int limit = predefinedWorkModes.size() / 2;
            workModebuttonsPaneLeft = createButtonsList(predefinedWorkModes.subList(0, limit));
            workModebuttonsPaneRight = createButtonsList(predefinedWorkModes.subList(limit, predefinedWorkModes.size()));
        } else {
            workModebuttonsPaneLeft = new VBox();
            workModebuttonsPaneRight = new VBox();
        }
    }

    private VBox createButtonsList(List<PredefinedWorkMode> predefinedWorkModes) {
        VBox buttonsList = new VBox();
        //buttonsList.setEffect(new GaussianBlur());
        //buttonsList.setSpacing(getHeight()/predefinedWorkModes.size());
        buttonsList.setPadding(new Insets(20, 5, 5, 5));
        for (PredefinedWorkMode predefinedWorkModeName : predefinedWorkModes) {
            JFXButton innerWorkModeButton = createInnerWorkModeButton(predefinedWorkModeName);
            buttonsList.getChildren().add(innerWorkModeButton);
            buttonsSet.add(innerWorkModeButton);
        }
        return buttonsList;
    }

    protected JFXButton createInnerWorkModeButton(PredefinedWorkMode predefinedWorkMode) {
        JFXButton workModeButton = new JFXButton(ApplicationSettings.getBundleString(predefinedWorkMode.getName() + "_wm.label"));
        workModeButton.setTooltip(new Tooltip(ApplicationSettings.getBundleString(predefinedWorkMode.getName() + "_wm.tooltip")));
        workModeButton.setButtonType(JFXButton.ButtonType.RAISED);
        workModeButton.setPrefWidth(150);
        workModeButton.setPadding(new Insets(25));
        workModeButton.setPrefHeight(getHeight() / 10);
        workModeButton.setBorder(BORDER_WHITE_2_OVER_100);
        workModeButton.getProperties().put("predefinedWorkMode", predefinedWorkMode);
        workModeButton.setBackground(BACKGROUNDFILL_100);
        workModeButton.setOnMouseClicked(e -> {
            this.predefinedWorkMode = predefinedWorkMode;
            setWorkModeMessage(ApplicationSettings.getBundleString(predefinedWorkMode.getName() + "_wm.help"));
            for (Button button : buttonsSet)
                button.setBackground(BACKGROUNDFILL_100);
            workModeButton.setBackground(BACKGROUNDFILL_DARKER_100);
            ApplicationSettings.getInstance().setWorkModeName(predefinedWorkMode.getName());
        });
        workModeButton.setTextFill(Color.WHITE);
        //workModeButton.setPrefSize(20,20);
        return workModeButton;
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

    public void createStartButton() {
        //todo controlar que con la inicializacion con
        // new LC().fillX().fillY().pack(), new AC(), new AC()
        // esto no se va de madre, porque al usar unos con una y otros con otra esto hace que se
        // vaya de madre toda la aplicación, es bastante desconcertante
        //MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());


        startButton = new JFXButton(ApplicationSettings.getBundleString("buttonStart.label"));
        startButton.setTooltip(new Tooltip(""));
        startButton.setTextFill(Color.WHITE);
        startButton.setEffect(DROPSHADOW_TEXT);
        startButton.setButtonType(JFXButton.ButtonType.RAISED);
        startButton.setBorder(BORDER_WHITE_4_OVER_100);
        startButton.setBackground(BACKGROUNDFILL_100);

        Circle buttonCircle = new Circle(startButtonRadious);
        //startButton.setShape(buttonCircle);
        startButton.setMinSize(2* startButtonRadious, 2* startButtonRadious);
        startButton.setMaxSize(2 * startButtonRadious, 2 * startButtonRadious);
        startButtonPane = new Pane(startButton);
    }

    private void createFreqFluGridPane() {
        Regulator frequency = RegulatorBuilder.createRegulator(
                ApplicationSettings.getBundleString("frecuency.label"),
                "Hz",
                "", null,
                WIDTHTILE, HEIGHTTILE,
                50d, 30d, 200d);

        frequencyTile = TileBuilder.create()
                .prefSize(WIDTHTILE, HEIGHTTILE)
                .skinType(Tile.SkinType.CUSTOM)
                //.textVisible(false)
                .graphic(frequency)
                .build();

        Regulator fluency = RegulatorBuilder.createRegulator(
                ApplicationSettings.getBundleString("fluencia.label"),
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
                ApplicationSettings.getBundleString("deposit.label"),
                WIDTHTILE, HEIGHTTILE,
                ApplicationSettings.getInstance().getSetpointMinTemperature(),
                ApplicationSettings.getInstance().getSetpointMaxTemperature(),
                false, false,
                LEFT);
        tipTempTile = RegulatorBuilder.createTempSparkRegulator(
                ApplicationSettings.getBundleString("tip.label"),
                WIDTHTILE, HEIGHTTILE,
                ApplicationSettings.getInstance().getSetpointMinTemperature(),
                ApplicationSettings.getInstance().getSetpointMaxTemperature(),
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
        workModeMessage = new Text();
        workModeMessage.setFill(Color.WHITE);
        workModeMessage.setEffect(GuiColors.DROPSHADOW_TEXT);
        workModeMessage.setFont(eu.hansolo.tilesfx.fonts.Fonts.latoRegular(24));
        workModeMessage.setPickOnBounds(true);


        //messagePane.getChildren().add(workModeMessage);
        messagePane = new FlowGridPane(1, 1, workModeMessage);
        messagePane.setBackground(BACKGROUND_BRIGTHER_FILL_60);
        messagePane.setBorder(BORDER_WHITE_4_OVER_60);
        messagePane.setEffect(DROPSHADOW_COMP);
        messagePane.setPadding(new Insets(25, 20, 20, 20));

        messagePane.setPrefSize(250, 250);

    }

    public void setWorkModeMessage(String message) {
        workModeMessage.setText(message);
    }

    public Pane getMessagePane() {
        return messagePane;
    }

    public JFXButton getStartButton() {
        return startButton;
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