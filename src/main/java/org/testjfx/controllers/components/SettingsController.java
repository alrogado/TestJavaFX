package org.testjfx.controllers.components;

import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.ionicons.Ionicons;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.RegulatorBuilder;
import org.testjfx.components.ToggleSwitch;
import org.testjfx.conf.Configuration;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class SettingsController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;
    private Double height = 400d;
    private Double width= 400d;

    Regulator volumScreen;
    Regulator volumPulse;

    ToggleSwitch tsTrigger;
    ToggleSwitch tsPedal;
    ToggleSwitch tsFillDeposit;

    ChoiceBox choiceBox;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        MigPane rootMP = new MigPane("fill");
        volumScreen = RegulatorBuilder.createRegulator(
                "Pantalla", "","%",
                Elusive.SCREEN_ALT,
                width, height,
                50d, 0d, 100d);
        //use Reactfx to manipulate bindings and values from communications
        //volumScreen.minValueProperty()
        volumPulse = RegulatorBuilder.createRegulator(
                "Pulso", "","%",
                Ionicons.ION_IOS_PULSE_STRONG,
                width, height,
                50d, 0d, 100d);

        FlowGridPane regulatorsPane = new FlowGridPane(2,1, volumScreen,volumPulse);
        regulatorsPane.setHgap(100);
        regulatorsPane.setPadding(new Insets(10));

        rootMP.add(regulatorsPane,"alignx center, wrap");

        VBox optionsPane = new VBox();
        optionsPane.setSpacing(20);
        optionsPane.setPadding(new Insets(10));

        choiceBox = new ChoiceBox(FXCollections.observableArrayList("uno", "dos"));
        optionsPane.getChildren().add(choiceBox);
        tsFillDeposit = new ToggleSwitch("fillDeposit.label",Configuration.getDepositFillEnabled());
        optionsPane.getChildren().add(tsFillDeposit);
        tsPedal = new ToggleSwitch("pedal.label",Configuration.getPedalEnabled());
        optionsPane.getChildren().add(tsPedal);
        tsTrigger = new ToggleSwitch("trigger.label",Configuration.getTriggerEnabled());
        optionsPane.getChildren().add(tsTrigger);
        rootMP.add(optionsPane,"alignx center, wrap");
        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }
}
