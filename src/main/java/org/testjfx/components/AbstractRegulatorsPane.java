package org.testjfx.components;

import com.jfoenix.controls.JFXButton;
import eu.hansolo.fx.regulators.Fonts;
import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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

/**
 * Created by alvaro.lopez on 09/07/2017.
 */
public abstract class AbstractRegulatorsPane extends Region {

    public static Double WIDTHTILE = 180d;
    public static Double HEIGHTTILE = 180d;
    static double initialStartButtonRadious = 50;

    protected Set<JFXButton> buttonsSet = new HashSet<>();
    protected FlowGridPane messagePane;
    protected PredefinedWorkMode predefinedWorkMode;
    Tile depositTempTile;
    Tile tipTempTile;
    Tile fluencyTile;
    Tile frequencyTile;
    Regulator frequency;
    Regulator fluency;
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
    double startButtonRadious = initialStartButtonRadious;
    JFXButton startButton;Text workModeMessage;
    List<PredefinedWorkMode> predefinedWorkModes;


    public void initComponents() {
        createTempeperatureSparkGauage();
        createFreqFluGridPane();
        createStartButton();
        createVBoxWorkModesList();
        createMessagePanel();
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

    protected void createVBoxWorkModesList() {
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
        frequency = RegulatorBuilder.createRegulator(
                ApplicationSettings.getBundleString("frecuency.label"),
                "Hz",
                "", null,
                WIDTHTILE, HEIGHTTILE,
                ApplicationSettings.getInstance().getFrequencySelected(), 30d, 200d);

        frequency.setOnTargetSet(e -> ApplicationSettings.getInstance().setFrequencySelected(frequency.getTargetValue()));

        frequencyTile = TileBuilder.create()
                .prefSize(WIDTHTILE, HEIGHTTILE)
                .skinType(Tile.SkinType.CUSTOM)
                //.textVisible(false)
                .graphic(frequency)
                .build();

        fluency = RegulatorBuilder.createRegulator(
                ApplicationSettings.getBundleString("fluencia.label"),
                "J/cm",
                "",
                null,
                WIDTHTILE, HEIGHTTILE,
                ApplicationSettings.getInstance().getFluencySelected(), 20d, 130d);

        fluency.setOnTargetSet(e -> ApplicationSettings.getInstance().setFluencySelected(fluency.getTargetValue()));

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

    public Regulator getFrequency() {
        return frequency;
    }

    public Regulator getFluency() {
        return fluency;
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

    public void setWorkModeMessage(String message) {
        workModeMessage.setText(message);
    }

    public Pane getMessagePane() {
        return messagePane;
    }

    public JFXButton getStartButton() {
        return startButton;
    }



    protected abstract double getRepositionXForFreqTile();

    protected abstract double getWidthForMessagePanel();

    protected abstract double getHeigthForMessagePanel();

    protected abstract void prefSizeStartButton(Bounds newBounds);

    protected abstract int getFactorToResizeStartButton();

    protected abstract void resizeWmButtonsList();

    protected abstract void repositionVmButtons();

    protected abstract double getXForMessagePane(double x, double depositTempWidth);

    protected abstract double getXForStartButton(double x, double depositTempWidth);

    protected abstract double getXForComp();

    protected abstract double getRespositionMinusXForRightButtons();

    protected abstract double getRepositionXForLeftButtons();

    protected abstract double getRepositionXForFluencyTile();

    protected abstract double getRepositionYForFreqTile();

    protected abstract double getRepositionXForTipTile();

}
