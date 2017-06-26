package org.testjfx.controllers.components;

import org.testjfx.components.RegulatorsPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

import javax.annotation.PostConstruct;
import java.util.Objects;

import static org.testjfx.util.EffectUtils.fadeIn;

@ViewController(value = "/org/testjfx/fxml/ui/main_content_regulators.fxml")
public class RegulatorsCustomLayoutController {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML
    StackPane root;

    int horizontalGap = 5;
    Insets padding = new Insets(5);

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() {
        Objects.requireNonNull(context, "context");
        RegulatorsController regulatorsController = new RegulatorsController();
        regulatorsController.initComponents();
        root.getChildren().addAll(new RegulatorsPane(regulatorsController));
        fadeIn(root);
    }

}
