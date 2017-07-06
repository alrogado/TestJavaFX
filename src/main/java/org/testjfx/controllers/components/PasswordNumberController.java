package org.testjfx.controllers.components;

import com.fxexperience.javafx.animation.ShakeTransition;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import eu.hansolo.medusa.Fonts;
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
import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.metrizeicons.MetrizeIcons;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.conf.ApplicationConf;
import org.testjfx.util.IkonUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.controllers.MainAppController.flowHandler;
import static org.testjfx.util.GuiColors.DROPSHADOW_COMP;
import static org.testjfx.util.GuiColors.FRG;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_password.fxml")
public class PasswordNumberController {

    @FXMLViewFlowContext
    protected ViewFlowContext context;
    protected EventHandler<ActionEvent> actionHandler;
    protected EventHandler<KeyEvent> keyHandler;
    protected StringProperty passwordValue;
    protected StringBuilder currentPasswordValue;
    protected JFXPasswordField passwordField;
    protected Button one;
    protected Button two;
    protected Button three;
    protected Button four;
    protected Button five;
    protected Button six;
    protected Button seven;
    protected Button eight;
    protected Button nine;
    protected Button zero;
    protected Button ok;
    protected Button del;
    protected VBox mainPane;
    protected int spacing = 15;
    protected Insets value = new Insets(5);
    protected int prefWidthButton = 40;
    protected int prefWidthHeightPassword = 50;
    @FXML
    StackPane root;

    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        actionHandler = EVENT -> handleEvent(EVENT);
        keyHandler = EVENT -> handleEvent(EVENT);

        passwordValue = new SimpleStringProperty(this, "passwordValue", "");
        currentPasswordValue = new StringBuilder();

        passwordField = new JFXPasswordField();
        passwordField.setFont(Fonts.digital(72));
        passwordField.setStyle("-fx-label-float:true;");
        passwordField.textProperty().bind(passwordValue);

        passwordField.setPrefSize(prefWidthHeightPassword, prefWidthHeightPassword);
        passwordField.setMaxSize(prefWidthHeightPassword, prefWidthHeightPassword);

        /*RequiredFieldValidator validatorNotNull = new RequiredFieldValidator();
        validatorNotNull.setMessage("Pass Is empty");
        validatorNotNull.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("24")
                .styleClass("error")
                .build());

        PasswordValidator validatorPssword = new PasswordValidator();
        validatorPssword.setMessage("Not Ok");
        validatorPssword.setIcon(GlyphsBuilder.create(FontAwesomeIconView.class)
                .glyph(FontAwesomeIcon.WARNING)
                .size("24")
                .styleClass("error")
                .build());
        passwordField.setValidators(validatorNotNull,validatorPssword);*/


        one = createPasswordButton("", MetrizeIcons.MET_NUMBER_ONE);
        two = createPasswordButton("", MetrizeIcons.MET_NUMBER_TWO);
        three = createPasswordButton("", MetrizeIcons.MET_NUMBER_THREE);
        four = createPasswordButton("", MetrizeIcons.MET_NUMBER_FOUR);
        five = createPasswordButton("", MetrizeIcons.MET_NUMBER_FIVE);
        six = createPasswordButton("", MetrizeIcons.MET_NUMBER_SIX);
        seven = createPasswordButton("", MetrizeIcons.MET_NUMBER_SEVEN);
        eight = createPasswordButton("", MetrizeIcons.MET_NUMBER_EIGHT);
        nine = createPasswordButton("", MetrizeIcons.MET_NUMBER_NINE);
        zero = createPasswordButton("", MetrizeIcons.MET_NUMBER_ZERO);
        ok = createPasswordButton("", Elusive.OK_CIRCLE);
        del = createPasswordButton("", Feather.FTH_DELETE);

        root.getChildren().addAll(createPane());
    }

    protected Button createPasswordButton(String TEXT, Ikon ikon) {
        Button button = createPasswordButton(TEXT);
        if (ikon != null)
            button.setGraphic(IkonUtils.customizeIkon(ikon));
        return button;
    }


    // ******************** protected Methods ***********************************
    public void updatPasswordValue() {
        passwordValue.set(currentPasswordValue.toString());
        checkPassword();
    }

    protected Button createPasswordButton(final String TEXT) {
        JFXButton button = new JFXButton(TEXT);
        //button.getStyleClass().addAll("calculator", BUTTON_STYLE.STYLE_CLASS);
        button.setOnAction(actionHandler);
        button.setOnKeyPressed(keyHandler);
        if (TEXT != null && !TEXT.trim().equals("")) {
            button.setButtonType(JFXButton.ButtonType.RAISED);
            //button.getStyleClass().add(ANIMATED_OPTION_BUTTON);
        }
        button.setPrefSize(prefWidthButton, prefWidthButton);
        return button;
    }


    protected Pane createPane() {
        MigPane buttonsPane = initButtonsPane();

        buttonsPane.add(new HBox(spacing, one, two, three), "grow, span");
        buttonsPane.add(new HBox(spacing, four, five, six), "grow, span");
        buttonsPane.add(new HBox(spacing, seven, eight, nine), "grow, span");
        buttonsPane.add(new HBox(spacing, del, zero), "grow, span");


        mainPane = new VBox(10, buttonsPane, passwordField);
        mainPane.setMaxSize(250, 300);
        MigPane rootMP = new MigPane("fill");
        rootMP.add(mainPane, "alignx center, aligny center, span");
        //rootMP.setBackground(new Background(new BackgroundFill(FRG, CornerRadii.EMPTY, Insets.EMPTY)));
        return rootMP;

    }

    protected MigPane initButtonsPane() {
        MigPane buttonsPane = new MigPane("fill");
        buttonsPane.setPadding(value);
        buttonsPane.setBackground(new Background(new BackgroundFill(FRG, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonsPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
        buttonsPane.setEffect(DROPSHADOW_COMP);
        return buttonsPane;
    }

    // ******************** Event handling ************************************
    protected void handleEvent(final Event EVENT) {
        final Object SOURCE = EVENT.getSource();
        if (SOURCE.equals(one)) {
            currentPasswordValue.append("1");
            updatPasswordValue();
        } else if (SOURCE.equals(two)) {
            currentPasswordValue.append("2");
            updatPasswordValue();
        } else if (SOURCE.equals(three)) {
            currentPasswordValue.append("3");
            updatPasswordValue();
        } else if (SOURCE.equals(four)) {
            currentPasswordValue.append("4");
            updatPasswordValue();
        } else if (SOURCE.equals(five)) {
            currentPasswordValue.append("5");
            updatPasswordValue();
        } else if (SOURCE.equals(six)) {
            currentPasswordValue.append("6");
            updatPasswordValue();
        } else if (SOURCE.equals(seven)) {
            currentPasswordValue.append("7");
            updatPasswordValue();
        } else if (SOURCE.equals(eight)) {
            currentPasswordValue.append("8");
            updatPasswordValue();
        } else if (SOURCE.equals(nine)) {
            currentPasswordValue.append("9");
            updatPasswordValue();
        } else if (SOURCE.equals(zero)) {
            currentPasswordValue.append("0");
            updatPasswordValue();
        } else if (SOURCE.equals(ok)) {
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

    protected void checkPassword() {
        if (ApplicationConf.getInstance().getPassword().length() == passwordField.getText().length()) {
            if (ApplicationConf.getInstance().getPassword().equals(passwordField.getText())) {
                try {
                    flowHandler.navigateTo(OperatorSettingsController.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    resetPassword();
                }
            } else {
                resetPassword();
                new ShakeTransition(mainPane).play();
            }
        }
    }

    protected void resetPassword() {
        currentPasswordValue = new StringBuilder("");
        passwordValue.set("");
        passwordField.deleteText(0, passwordField.getLength());
    }

}
