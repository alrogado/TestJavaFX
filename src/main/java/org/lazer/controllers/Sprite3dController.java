package org.lazer.controllers;

import com.chrisnewland.demofx.DemoFX;
import com.jfoenix.controls.JFXDialog;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.lazer.util.Srpite3dDemo;

import javax.annotation.PostConstruct;
import java.util.Objects;

@ViewController(value = "/org/lazer/fxml/ui/sprite3d.fxml", title = "Material Design Example")
public class Sprite3dController {

    @FXML
    private StackPane root;

    @PostConstruct
    public void init() throws FlowException {
        DemoFX demoFX = new Srpite3dDemo().getDemoFX();
        root.getChildren().add(((BorderPane)demoFX.getScene().getRoot()).getCenter());
        demoFX.runDemo();
    }

}
