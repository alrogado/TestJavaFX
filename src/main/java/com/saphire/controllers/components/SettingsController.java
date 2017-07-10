package com.saphire.controllers.components;

import com.saphire.components.ToggleSwitch;
import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.ionicons.Ionicons;
import org.tbee.javafx.scene.layout.MigPane;
import com.saphire.components.regulators.RegulatorBuilder;
import com.saphire.conf.ApplicationSettings;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static com.saphire.util.EffectUtils.fadeIn;

@ViewController(value = "/com/saphire/fxml/ui/main_content_regulators.fxml")
public class SettingsController {

    @FXML
    StackPane root;
    Regulator volumScreen;
    Regulator volumPulse;
    ToggleSwitch tsTrigger;
    ToggleSwitch tsPedal;
    ToggleSwitch tsFillDeposit;
    ChoiceBox choiceBox;
    @FXMLViewFlowContext
    private ViewFlowContext context;
    private Double height = 400d;
    private Double width = 400d;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        MigPane rootMP = new MigPane("fill");
        volumScreen = RegulatorBuilder.createRegulator(
                "Pantalla", "", "%",
                Elusive.SCREEN_ALT,
                width, height,
                ApplicationSettings.getInstance().getScreenVolume(), 0d, 100d);
        //use Reactfx to manipulate bindings and values from communications
        //volumScreen.minValueProperty()
        volumScreen.setOnTargetSet(o->
                ApplicationSettings.getInstance().setScreenVolume( volumScreen.getTargetValue()));
        volumPulse = RegulatorBuilder.createRegulator(
                "Pulso", "", "%",
                Ionicons.ION_IOS_PULSE_STRONG,
                width, height,
                (Double) ApplicationSettings.getInstance().getPulseVolume(), 0d, 100d);
        //volumPulse.targetValueProperty().
        //GuiApp.setConfigValue("volume.pulse", volumPulse.getValue());
        volumPulse.setOnTargetSet(o->
                ApplicationSettings.getInstance().setPulseVolume(volumPulse.getTargetValue()));

        FlowGridPane regulatorsPane = new FlowGridPane(2, 1, volumScreen, volumPulse);
        regulatorsPane.setHgap(100);
        regulatorsPane.setPadding(new Insets(10));

        rootMP.add(regulatorsPane, "alignx center, wrap");

        VBox optionsPane = new VBox();
        optionsPane.setSpacing(20);
        optionsPane.setPadding(new Insets(10));

        choiceBox = new ChoiceBox(FXCollections.observableArrayList("uno", "dos"));
        optionsPane.getChildren().add(choiceBox);
        tsFillDeposit = new ToggleSwitch("fillDeposit.label", ApplicationSettings.getInstance().getDepositFillEnabled());
        optionsPane.getChildren().add(tsFillDeposit);
        tsPedal = new ToggleSwitch("pedal.label", ApplicationSettings.getInstance().getPedalEnabled());
        optionsPane.getChildren().add(tsPedal);
        tsTrigger = new ToggleSwitch("trigger.label", ApplicationSettings.getInstance().getTriggerEnabled());
        optionsPane.getChildren().add(tsTrigger);
        rootMP.add(optionsPane, "alignx center, wrap");
        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }
}