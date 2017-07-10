package com.saphire.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.control.Labeled;
import javafx.scene.control.Skin;

/**
 * Created by pedro_000 on 6/15/2014.
 */
public class ToggleSwitch extends Labeled {

    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "toggle-switch";
    private static final PseudoClass PSEUDO_CLASS_SELECTED =
            PseudoClass.getPseudoClass("selected");
    boolean initialValue;
    /**
     * Indicates whether this ToggleSwitch is selected.
     */
    private BooleanProperty selected;

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/
    private StringProperty turnOnText;
    private StringProperty turnOffText;

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    public ToggleSwitch(String text, boolean value) {
        setTurnOnText(text);
        setTurnOffText(text);
        initialize();
        setSelected(value);
        initialValue = value;
    }

    public boolean isInitialValue() {
        return initialValue;
    }

    private void initialize() {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    }

    public final boolean isSelected() {
        return selected == null ? false : selected.get();
    }

    public final void setSelected(boolean value) {
        selectedProperty().set(value);
    }

    public final BooleanProperty selectedProperty() {
        if (selected == null) {
            selected = new BooleanPropertyBase() {
                @Override
                protected void invalidated() {
                    final Boolean v = get();
                    pseudoClassStateChanged(PSEUDO_CLASS_SELECTED, v);
//                    accSendNotification(Attribute.SELECTED);
                }

                @Override
                public Object getBean() {
                    return ToggleSwitch.this;
                }

                @Override
                public String getName() {
                    return "selected";
                }
            };
        }
        return selected;
    }

    /**
     * The text to show when this switch is on. The text may be null.
     */
    public final StringProperty turnOnTextProperty() {
        if (turnOnText == null) {
            turnOnText = new SimpleStringProperty(this, "turnOnText", "");
        }
        return turnOnText;
    }

    public final String getTurnOnText() {
        return turnOnText == null ? "" : turnOnText.getValue();
    }

    public final void setTurnOnText(String value) {
        turnOnTextProperty().setValue(value);
    }

    /**
     * The text to show when this switch is off. The text may be null.
     */
    public final StringProperty turnOffTextProperty() {
        if (turnOffText == null) {
            turnOffText = new SimpleStringProperty(this, "turnOffText", "");
        }
        return turnOffText;
    }

    /***************************************************************************
     *                                                                         *
     * Methods                                                                 *
     *                                                                         *
     **************************************************************************/

    public final String getTurnOffText() {
        return turnOffText == null ? "" : turnOffText.getValue();
    }

    public final void setTurnOffText(String value) {
        turnOffTextProperty().setValue(value);
    }

    /**
     * Toggles the state of the {@code ToggleSwitch}. If allowIndeterminate is
     * true, then each invocation of this function will advance the CheckBox
     * through the states checked, unchecked, and undefined. If
     * allowIndeterminate is false, then the CheckBox will only cycle through
     * the checked and unchecked states, and forcing indeterminate to equal to
     * false.
     */
    public void fire() {
        if (!isDisabled()) {
            setSelected(!isSelected());
            fireEvent(new ActionEvent());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Skin<?> createDefaultSkin() {
        return new ToggleSwitchSkin(this);
    }

    /*@Override
    public String getUserAgentStylesheet() {
        return getClass().getResource("modena.css").toExternalForm();
    }*/

}
