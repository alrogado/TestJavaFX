package com.saphire.components;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.saphire.controllers.components.PasswordAlpahabetController;

public class VirtualReducedKeyBoard {

    private final VBox root;

    PasswordAlpahabetController passwordAlpahabetController;

    /**
     * Creates a Virtual Keyboard.
     *
     * @param passwordAlpahabetController The node that will receive KeyEvents from this keyboard.
     *                                    If target is null, KeyEvents will be dynamically forwarded to the focus owner
     *                                    in the Scene containing this keyboard.
     */
    public VirtualReducedKeyBoard(PasswordAlpahabetController passwordAlpahabetController) {
        this.passwordAlpahabetController = passwordAlpahabetController;
        this.root = new VBox(10);
        root.setPadding(new Insets(10));


        // Data for regular buttons; split into rows
        final String[][] unshifted = new String[][]{
                {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"},
                {"q", "w", "e", "r", "t", "y", "u", "i", "o", "p"},
                {"a", "s", "d", "f", "g", "h", "j", "k", "l",},
                {"z", "x", "c", "v", "b", "n", "m"}};


        final KeyCode[][] codes = new KeyCode[][]{
                {KeyCode.BACK_QUOTE, KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3,
                        KeyCode.DIGIT4, KeyCode.DIGIT5, KeyCode.DIGIT6, KeyCode.DIGIT7,
                        KeyCode.DIGIT8, KeyCode.DIGIT9, KeyCode.DIGIT0, KeyCode.SUBTRACT,
                        KeyCode.EQUALS},
                {KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R, KeyCode.T, KeyCode.Y,
                        KeyCode.U, KeyCode.I, KeyCode.O, KeyCode.P, KeyCode.OPEN_BRACKET,
                        KeyCode.CLOSE_BRACKET, KeyCode.BACK_SLASH},
                {KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F, KeyCode.G, KeyCode.H,
                        KeyCode.J, KeyCode.K, KeyCode.L, KeyCode.SEMICOLON, KeyCode.QUOTE},
                {KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V, KeyCode.B, KeyCode.N,
                        KeyCode.M, KeyCode.COMMA, KeyCode.PERIOD, KeyCode.SLASH}};

        final Button delete = createButton("Del", KeyCode.DELETE, passwordAlpahabetController.getPasswordTextField());
        final Node[][] extraRightButtons = new Node[][]{{}, {}, {}, {delete}};

        // build layout
        for (int row = 0; row < unshifted.length; row++) {
            HBox hbox = new HBox(10);
            hbox.setAlignment(Pos.BASELINE_CENTER);
            root.getChildren().add(hbox);

            for (int k = 0; k < unshifted[row].length; k++) {
                hbox.getChildren().add(createButton(unshifted[row][k].toUpperCase(), codes[row][k], passwordAlpahabetController.getPasswordTextField()));
            }
            hbox.getChildren().addAll(extraRightButtons[row]);
        }
    }

    /**
     * Visual component displaying this keyboard. The returned node has a style class of "virtual-keyboard".
     * Buttons in the view have a style class of "virtual-keyboard-button".
     *
     * @return a view of the keyboard.
     */
    public Node view() {
        return root;
    }

    // Creates a button with fixed text not responding to Shift
    private Button createButton(final String text, final KeyCode code, final JFXPasswordField target) {
        StringProperty textProperty = new SimpleStringProperty(text);
        Button button = createButton(textProperty, code, target);
        return button;
    }

    // Creates a button with mutable text, and registers listener with it
    private Button createButton(final ObservableStringValue text, final KeyCode code, final JFXPasswordField target) {
        JFXButton button = new JFXButton(text.get());
        button.setButtonType(JFXButton.ButtonType.RAISED);
        button.getStyleClass().add("virtual-keyborad-option-button");
        //TODO NO PONEMOS DROP SHADOW HASTA QUE NO VEAMSO EL PERFORMANCE DE LOS EFECTOS EN EL JAVAFX DE LA PLACA
        //button.setEffect(GuiColors.DROPSHADOW_COMP);
        button.textProperty().bind(text);

        // Important not to grab the focus from the target:
        button.setFocusTraversable(false);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                target.setText(target.getText() + text.get());
                passwordAlpahabetController.updatPasswordValue();
            }
        });
        return button;
    }
}
