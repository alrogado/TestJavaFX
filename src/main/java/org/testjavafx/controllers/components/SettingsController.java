package org.testjavafx.controllers.components;

import org.testjavafx.components.ToggleSwitch;
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
import org.testjavafx.components.regulators.RegulatorBuilder;
import org.testjavafx.conf.ApplicationSettings;
import org.testjavafx.util.EffectUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;

@ViewController(value = "/org/testjavafx/fxml/ui/main_content_regulators.fxml")
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
                50d, 0d, 100d);
        //use Reactfx to manipulate bindings and values from communications
        //volumScreen.minValueProperty()
        volumPulse = RegulatorBuilder.createRegulator(
                "Pulso", "", "%",
                Ionicons.ION_IOS_PULSE_STRONG,
                width, height,
                50d, 0d, 100d);
        //volumPulse.targetValueProperty().
        //GuiApp.setConfigValue("volume.pulse", volumPulse.getValue());

        FlowGridPane regulatorsPane = new FlowGridPane(2, 1, volumScreen, volumPulse);
        regulatorsPane.setHgap(100);
        regulatorsPane.setPadding(new Insets(10));

        rootMP.add(regulatorsPane, "alignx center, wrap");

        VBox optionsPane = new VBox();
        optionsPane.setSpacing(20);
        optionsPane.setPadding(new Insets(10));

        choiceBox = new ChoiceBox(FXCollections.observableArrayList("uno", "dos"));
        optionsPane.getChildren().add(choiceBox);
        tsFillDeposit = new ToggleSwitch("fillDeposit.label",true);
        optionsPane.getChildren().add(tsFillDeposit);
        root.getChildren().addAll(rootMP);
        EffectUtils.fadeIn(root);
    }
}
