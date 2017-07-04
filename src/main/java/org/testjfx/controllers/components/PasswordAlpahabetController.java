package org.testjfx.controllers.components;

import com.jfoenix.controls.JFXPasswordField;
import eu.hansolo.medusa.Fonts;
import io.datafx.controller.ViewController;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.metrizeicons.MetrizeIcons;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.VirtualKeyboard;
import org.testjfx.components.VirtualLimitedBoard;

import javax.annotation.PostConstruct;

import java.util.Objects;

import static eu.hansolo.medusa.FGauge.PREFERRED_WIDTH;
import static org.testjfx.util.GuiColors.FRG;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_password.fxml")
public class PasswordAlpahabetController extends PasswordNumberController{

    @PostConstruct
    public void init() {

        Objects.requireNonNull(context, "context");

        passwordValue = new SimpleStringProperty(this, "passwordValue", "");
        currentPasswordValue = new StringBuilder();

        passwordField = new JFXPasswordField();
        passwordField.setFont(Fonts.digital(72));
        passwordField.setStyle("-fx-label-float:true;");

        passwordField.setPrefSize(prefWidthHeightPassword, prefWidthHeightPassword);
        passwordField.setMaxSize(prefWidthHeightPassword, prefWidthHeightPassword);

        root.getChildren().addAll(createPane());
    }

    
    protected Pane createPane() {
        MigPane buttonsPane = initButtonsPane();
        buttonsPane.add(new VirtualLimitedBoard(this).view(), "alignx center, aligny center, grow,span");

        mainPane = new VBox(10, buttonsPane, passwordField);
        //MAXWIDTH ES EL VALOR A CAMBIAR CUANDO SE MODOFICA EL ESTILO DEL TECLADO
        int maxWidth = 850;
        mainPane.setMaxSize(maxWidth,300);

        MigPane rootMP = new MigPane("fill");
        rootMP.add(mainPane, "alignx center, aligny center, span");
        //rootMP.setBackground(new Background(new BackgroundFill(FRG, CornerRadii.EMPTY, Insets.EMPTY)));
        return mainPane;

    }

    public JFXPasswordField getPasswordTextField() {
        return passwordField;
    }

    protected void resetPassword() {
        currentPasswordValue = new StringBuilder("");
        passwordValue.set("");
        passwordField.deleteText(0,passwordField.getLength());
    }
}