package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.metrizeicons.MetrizeIcons;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.util.IkonUtils;

import javax.annotation.PostConstruct;

import static org.testjfx.controllers.components.RegulatorsController.ANIMATED_OPTION_BUTTON;
import static org.testjfx.util.GuiColors.BKG;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class PasswordController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;


    private EventHandler<ActionEvent> actionHandler;
    private EventHandler<KeyEvent> keyHandler;
    private StringProperty passwordValue;
    private StringBuilder currentPasswordValue;
    private JFXPasswordField passwordField;
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button zero;
    private Button ok;
    private Button del;

    @PostConstruct
    public void init() {
        actionHandler   = EVENT -> handleEvent(EVENT);
        keyHandler      = EVENT -> handleEvent(EVENT);

        passwordValue = new SimpleStringProperty(this, "passwordValue", "");
        currentPasswordValue = new StringBuilder();

        passwordField = new JFXPasswordField();
        passwordField.textProperty().bind(passwordValue);

        one       = createButton("", MetrizeIcons.MET_NUMBER_ONE);
        two       = createButton("", MetrizeIcons.MET_NUMBER_TWO);
        three     = createButton("", MetrizeIcons.MET_NUMBER_THREE);
        four      = createButton("", MetrizeIcons.MET_NUMBER_FOUR);
        five      = createButton("", MetrizeIcons.MET_NUMBER_FIVE);
        six       = createButton("", MetrizeIcons.MET_NUMBER_SIX);
        seven     = createButton("", MetrizeIcons.MET_NUMBER_SEVEN);
        eight     = createButton("", MetrizeIcons.MET_NUMBER_EIGHT);
        nine      = createButton("", MetrizeIcons.MET_NUMBER_NINE);
        zero      = createButton("", MetrizeIcons.MET_NUMBER_ZERO);
        ok        = createButton("", Elusive.OK_CIRCLE);
        del       = createButton("", Feather.FTH_DELETE);
        root.getChildren().addAll(createFlowPane());
    }

    private Button createButton(String TEXT, Ikon ikon) {
        Button button = createButton(TEXT);
        button.setGraphic(IkonUtils.customizeIkon(ikon));
        return button;
    }


    // ******************** Private Methods ***********************************
    private void updatPasswordValue() {
        passwordValue.set(currentPasswordValue.toString());
    }

    private Button createButton(final String TEXT) {
        JFXButton button = new JFXButton(TEXT);
        //button.getStyleClass().addAll("calculator", BUTTON_STYLE.STYLE_CLASS);
        button.setOnAction(actionHandler);
        button.setOnKeyPressed(keyHandler);
        /*button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add(ANIMATED_OPTION_BUTTON);*/
        button.setPrefSize(20,20);
        return button;
    }


    private Pane createFlowPane() {

        FlowGridPane buttonsPane = new FlowGridPane(3,4);
        buttonsPane.setPadding(new Insets(5));
        buttonsPane.addRow(1,one,two,three);
        buttonsPane.addRow(2,four,five,six);
        buttonsPane.addRow(3,seven,eight,nine);
        buttonsPane.addRow(4,del,zero,ok);
        buttonsPane.setBackground(new Background(new BackgroundFill(BKG, CornerRadii.EMPTY, Insets.EMPTY)));

        FlowGridPane pane = new FlowGridPane(1,2,buttonsPane,passwordField);

        return pane;
    }


    // ******************** Event handling ************************************
    private void handleEvent(final Event EVENT) {
        final Object  SOURCE = EVENT.getSource();
        if (SOURCE.equals(one)) {
            currentPasswordValue.append("1");
            updatPasswordValue();
        } else if (SOURCE.equals(two)) {
            currentPasswordValue.append("2");
            updatPasswordValue();
        } else if (SOURCE.equals(three) ) {
            currentPasswordValue.append("3");
            updatPasswordValue();
        } else if (SOURCE.equals(four) ) {
            currentPasswordValue.append("4");
            updatPasswordValue();
        } else if (SOURCE.equals(five) ) {
            currentPasswordValue.append("5");
            updatPasswordValue();
        } else if (SOURCE.equals(six) ) {
            currentPasswordValue.append("6");
            updatPasswordValue();
        } else if (SOURCE.equals(seven) ) {
            currentPasswordValue.append("7");
            updatPasswordValue();
        } else if (SOURCE.equals(eight) ) {
            currentPasswordValue.append("8");
            updatPasswordValue();
        } else if (SOURCE.equals(nine) ) {
            currentPasswordValue.append("9");
            updatPasswordValue();
        } else if (SOURCE.equals(zero) ) {
            currentPasswordValue.append("0");
            updatPasswordValue();
        } else if (SOURCE.equals(ok) ) {
            checkPassword();
        } else if (SOURCE.equals(del)) {
            int length = currentPasswordValue.length();
            if (length >= 1) {
                if (length == 1) {
                    currentPasswordValue.setLength(0);
                    currentPasswordValue.append("");
                } else {
                    currentPasswordValue.replace(length - 1, length, "");
                }
                updatPasswordValue();
            }
        }
    }

    private void checkPassword() {

    }
}
