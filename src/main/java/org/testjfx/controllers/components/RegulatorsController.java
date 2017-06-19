package org.testjfx.controllers.components;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
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
        MigPane rootMP = new MigPane(new LC().pack(), new AC(), new AC());
        //MigPane rootMP = new MigPane(new LC().pack().packAlign(0.5f, 1f));
        rootMP.add(RegulatorBuilder.createMusicRegulator("Frecuencia", "Hz"));
        rootMP.add(RegulatorBuilder.createMusicRegulator("Fluencia","J/cm"));
        /*rootMP.add(musicRegulator);
        rootMP.add(musicRegulator);*/
        root.getChildren().addAll(rootMP);
        fadeIn(root);
    }
}
