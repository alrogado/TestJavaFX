package org.testjfx.controllers.components;

import eu.hansolo.fx.regulators.Regulator;
import eu.hansolo.tilesfx.tools.FlowGridPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.tbee.javafx.scene.layout.MigPane;
import org.testjfx.components.ClockBuilder;
import org.testjfx.components.RegulatorBuilder;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml", title = "Material Design Example")
public class SettingsController {


    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        MigPane rootMP = new MigPane(new LC().fillX().fillY().pack(), new AC(), new AC());
        Regulator volumScreen = RegulatorBuilder.createRegulator("Pantalla", "%", FontAwesome.MUSIC, 50d, 0d, 100d);
        //use Reactfx to manipulate bindings and values from communications
        //volumScreen.minValueProperty()
        Regulator volumPulse = RegulatorBuilder.createRegulator("Pulso", "%", FontAwesome.MUSIC, 50d, 0d, 100d);

        FlowGridPane regulatorsPane = new FlowGridPane(2,1, volumPulse, volumScreen);
        regulatorsPane.setHgap(100);
        regulatorsPane.setPadding(new Insets(10));

        /*HBox regulatorsPane = new HBox(volumScreen, volumPulse);
        regulatorsPane.setSpacing(20);
        regulatorsPane.setPadding(new Insets(10));*/

        /*HBox bottomPane = new HBox(ClockBuilder.createClock());
        bottomPane.setSpacing(20);
        bottomPane.setPadding(new Insets(10));*/

        rootMP.add(regulatorsPane,"alignx center, wrap");
        rootMP.add(ClockBuilder.createClock() ,"alignx center");
        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }
}
