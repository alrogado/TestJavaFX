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

package org.testjavafx.util.apps.extra;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjavafx.GuiApp;


public class PasswordManager extends Application {

    private EventHandler<ActionEvent> actionHandler;
    private EventHandler<KeyEvent> keyHandler;
    private StringProperty passwordValue;
    private StringBuilder currentValue;
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

    public static void main(String[] args) {
        launch(args);
    }

    // ******************** Initialization ************************************
    @Override
    public void init() {
        actionHandler = EVENT -> handleEvent(EVENT);
        keyHandler = EVENT -> handleEvent(EVENT);

        passwordValue = new SimpleStringProperty(this, "passwordValue", "");
        currentValue = new StringBuilder();

        passwordField = new JFXPasswordField();
        passwordField.setMaxWidth(Double.MAX_VALUE);
        passwordField.setMaxHeight(Double.MAX_VALUE);
        passwordField.getStyleClass().add("lcd");
        passwordField.textProperty().bind(passwordValue);

        one = createButton("1", ButtonStyle.WHITE);
        two = createButton("2", ButtonStyle.WHITE);
        three = createButton("3", ButtonStyle.WHITE);
        four = createButton("4", ButtonStyle.WHITE);
        five = createButton("5", ButtonStyle.WHITE);
        six = createButton("6", ButtonStyle.WHITE);
        seven = createButton("7", ButtonStyle.WHITE);
        eight = createButton("8", ButtonStyle.WHITE);
        nine = createButton("9", ButtonStyle.WHITE);
        zero = createButton("0", ButtonStyle.WHITE);
        ok = createButton("Ok", ButtonStyle.GRAY);
        del = createButton("Del", ButtonStyle.RED);
    }


    // ******************** Private Methods ***********************************
    private void updateLcdValue() {
        passwordValue.set(currentValue.toString());
        ok.requestFocus();
    }

    private Button createButton(final String TEXT, final ButtonStyle BUTTON_STYLE) {
        Button button = new JFXButton(TEXT);
        button.getStyleClass().addAll("calculator", BUTTON_STYLE.STYLE_CLASS);
        button.setOnAction(actionHandler);
        button.setOnKeyPressed(keyHandler);
        return button;
    }

    private Pane createLayout() {
        // Create GridPane
        MigPane pane = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        pane.getStyleClass().add("calculator");
        //pane.setPadding(new Insets(10, 10, 10, 10));
        /*pane.setHgap(6);
        pane.setVgap(6);*/

        // Place LCD on top


        // First row (7, 8, 9, /, AC)
        pane.add(seven);
        pane.add(eight);
        pane.add(nine, " wrap");

        // Second row (4, 5, 6, x, C)
        pane.add(four);
        pane.add(five);
        pane.add(six, " wrap");

        // Third row (1, 2, 3, -, DEL)
        pane.add(one);
        pane.add(two);
        pane.add(three, " wrap");

        // Fourth row (0, ., +-, +, =)
        pane.add(del);
        pane.add(zero);
        pane.add(ok, " wrap");

        pane.add(passwordField, "grow, wrap");
        return pane;
    }

    // ******************** Event handling ************************************
    private void handleEvent(final Event EVENT) {
        final Object SOURCE = EVENT.getSource();
        final String TEXT = EVENT instanceof KeyEvent ? ((KeyEvent) EVENT).getText() : "";
        if (SOURCE.equals(one) || TEXT.equals("1")) {
            currentValue.append("1");
            updateLcdValue();
        } else if (SOURCE.equals(two) || TEXT.equals("2")) {
            currentValue.append("2");
            updateLcdValue();
        } else if (SOURCE.equals(three) || TEXT.equals("3")) {
            currentValue.append("3");
            updateLcdValue();
        } else if (SOURCE.equals(four) || TEXT.equals("4")) {
            currentValue.append("4");
            updateLcdValue();
        } else if (SOURCE.equals(five) || TEXT.equals("5")) {
            currentValue.append("5");
            updateLcdValue();
        } else if (SOURCE.equals(six) || TEXT.equals("6")) {
            currentValue.append("6");
            updateLcdValue();
        } else if (SOURCE.equals(seven) || TEXT.equals("7")) {
            currentValue.append("7");
            updateLcdValue();
        } else if (SOURCE.equals(eight) || TEXT.equals("8")) {
            currentValue.append("8");
            updateLcdValue();
        } else if (SOURCE.equals(nine) || TEXT.equals("9")) {
            currentValue.append("9");
            updateLcdValue();
        } else if (SOURCE.equals(zero) || TEXT.equals("0")) {
            currentValue.append("0");
            updateLcdValue();
        } else if (SOURCE.equals(ok)) {
            checkPassword();
        } else if (SOURCE.equals(del)) {
            int length = currentValue.length();
            if (length >= 1) {
                if (length == 1) {
                    currentValue.setLength(0);
                    currentValue.append("");
                } else {
                    currentValue.replace(length - 1, length, "");
                }
                updateLcdValue();
            }
        }
    }

    private void checkPassword() {

    }


    // ******************** Public Methods ************************************
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(createLayout());
        scene.getStylesheets().add(DemoCalculator.class.getResource("/org/testjavafx/css/calculator/calculator.css").toExternalForm());
        //scene.getStylesheets().add(DemoCalculator.class.getResource("/org/testjfx/css/calculator/calculator-alternative.css").toExternalForm());
        //scene.getStylesheets().add(PasswordManager.class.getResource("/org/testjfx/css/calculator/calculator-braun.css").toExternalForm());
        scene.getStylesheets().addAll(
                GuiApp.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                GuiApp.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                GuiApp.class.getResource("/org/testjavafx/css/jfoenix-components.css").toExternalForm(),
                GuiApp.class.getResource("/org/testjavafx/css/jfoenix-main-demo.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Demo Powerful CSS");
        stage.setResizable(true);
        stage.show();
    }

    @Override
    public void stop() {

    }

    private enum ButtonStyle {
        WHITE("button-white"),
        BROWN("button-brown"),
        GRAY("button-gray"),
        RED("button-red"),
        ORANGE("button-orange");

        public final String STYLE_CLASS;

        private ButtonStyle(final String STYLE_CLASS) {
            this.STYLE_CLASS = STYLE_CLASS;
        }
    }
}