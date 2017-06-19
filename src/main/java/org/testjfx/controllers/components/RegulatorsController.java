package org.testjfx.controllers.components;

import eu.hansolo.fx.regulators.Regulator;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import org.testjfx.components.RegulatorBuilder;
import org.tbee.javafx.scene.layout.MigPane;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml", title = "Material Design Example")
public class RegulatorsController {


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
        Regulator frequency = RegulatorBuilder.createRegulator("Frecuencia", "Hz", null, 50d, 30d, 200d);
        Regulator fluency = RegulatorBuilder.createRegulator("Fluencia", "J/cm", null, 96d, 20d, 130d);
        HBox pane = new HBox(frequency, fluency);
        pane.setSpacing(20);
        pane.setPadding(new Insets(10));
        rootMP.add(pane);
        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }
}
