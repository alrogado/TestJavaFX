package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import eu.hansolo.medusa.Fonts;
import io.datafx.controller.ViewConfiguration;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.metrizeicons.MetrizeIcons;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.PasswordValidator;
import org.testjfx.conf.Configuration;
import org.testjfx.util.ExtendedAnimatedFlowContainer;
import org.testjfx.util.IkonUtils;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;
import static io.datafx.controller.flow.container.ContainerAnimations.SWIPE_LEFT;
import static org.testjfx.GuiApp.ANIM_DURATION;
import static org.testjfx.GuiApp.handler;
import static org.testjfx.util.GuiColors.FRG;

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
        Objects.requireNonNull(context, "context");
        actionHandler   = EVENT -> handleEvent(EVENT);
        keyHandler      = EVENT -> handleEvent(EVENT);

        passwordValue = new SimpleStringProperty(this, "passwordValue", "");
        currentPasswordValue = new StringBuilder();

        passwordField = new JFXPasswordField();
        passwordField.setFont(Fonts.digital(72));
        passwordField.setStyle("-fx-label-float:true;");
        passwordField.textProperty().bind(passwordValue);
        passwordField.setPrefSize(50,50);
        passwordField.setMaxSize(50,50);

        RequiredFieldValidator validatorNotNull = new RequiredFieldValidator();
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
        passwordField.setValidators(validatorNotNull,validatorPssword);


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
        root.getChildren().addAll(createPane());
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
        button.setPrefSize(40,40);
        return button;
    }


    private Pane createPane() {
        MigPane buttonsPane = new MigPane("fill");
        buttonsPane.setPadding(new Insets(5));
        buttonsPane.add(new HBox(15, one, two, three), "grow, span");
        buttonsPane.add(new HBox(15, four, five, six), "grow, span");
        buttonsPane.add(new HBox(15, seven, eight, nine), "grow, span");
        buttonsPane.add(new HBox(15, del, zero, ok), "grow, span");

        buttonsPane.setBackground(new Background(new BackgroundFill(FRG, CornerRadii.EMPTY, Insets.EMPTY)));
        buttonsPane.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

        DropShadow dropShadow = new DropShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.65), PREFERRED_WIDTH * 0.016, 0.0, 0, PREFERRED_WIDTH * 0.028);
        InnerShadow highlight = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(255, 255, 255, 0.2), PREFERRED_WIDTH * 0.008, 0.0, 0, PREFERRED_WIDTH * 0.008);
        InnerShadow innerShadow = new InnerShadow(BlurType.TWO_PASS_BOX, Color.rgb(0, 0, 0, 0.2), PREFERRED_WIDTH * 0.008, 0.0, 0, -PREFERRED_WIDTH * 0.008);
        highlight.setInput(innerShadow);
        dropShadow.setInput(highlight);
        buttonsPane.setEffect(dropShadow);

        VBox pane = new VBox(40, buttonsPane, passwordField);
        pane.setMaxSize(250,300);
        MigPane rootMP = new MigPane("fill");
        rootMP.add(pane, "alignx center, aligny center, span");
        rootMP.setBackground(new Background(new BackgroundFill(FRG, CornerRadii.EMPTY, Insets.EMPTY)));
        return rootMP;

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
        if(passwordField.validate()){
            try {
                handler.navigateTo(SettingsOperatorController.class);
            } catch (VetoException e) {
                e.printStackTrace();
            } catch (FlowException e) {
                e.printStackTrace();
            }
        }
    }
}
