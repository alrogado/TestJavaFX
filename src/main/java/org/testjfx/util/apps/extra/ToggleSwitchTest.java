package org.testjfx.util.apps.extra;

import eu.hansolo.tilesfx.tools.FlowGridPane;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testjfx.components.ToggleSwitch;
import org.testjfx.components.extra.ToggleSwitch2;

/**
 * Created by alrogado on 6/21/17.
 */
public class ToggleSwitchTest extends Application {

    @Override
    public void start (Stage primaryStage)
    {
        //Add a scene
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);

        ToggleSwitch toggleSwitch = new ToggleSwitch("Hacer cositas", false);
        ToggleSwitch2 toggleSwitch2 = new ToggleSwitch2("ON", "Hacer cositas");
        FlowGridPane flowGridPane = new FlowGridPane(1,2, toggleSwitch2);
        ((Group) scene.getRoot()).getChildren().add(flowGridPane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
