package org.lazer.components;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.lazer.GuiApp;
import org.lazer.util.GuiColors;

import static javafx.geometry.VPos.*;

/**
 * Created by alvaro.lopez on 12/06/2017.
 */
public class SaphireColor  extends Application {
    Stage stage;
    Scene scene;

    @Override public void start(Stage stage) {
        stage.show();

        scene = new Scene(new Group());
        ObservableList<Node> content = ((Group)scene.getRoot()).getChildren();

        content.add(lighting());
        content.add(innerShadow());

        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(
                GuiApp.class.getResource("/css/jfoenix-fonts.css").toExternalForm(),
                GuiApp.class.getResource("/css/jfoenix-design.css").toExternalForm(),
                GuiApp.class.getResource("/org/lazer/css/jfoenix-components.css").toExternalForm(),
                GuiApp.class.getResource("/org/lazer/css/jfoenix-main-demo.css").toExternalForm());
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    static String text = "Saphire LS";

    static Node lighting() {
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-15.0f);

        Lighting l = new Lighting();
        l.setLight(light);
        l.setSurfaceScale(5.0f);

        Text t = new Text(text);
        t.setFill(GuiColors.FRG);
        t.setStyle("-fx-font-family: 'Roboto'");
        t.setFont(Font.font("Roboto", FontWeight.BOLD, 80));
        /*t.setX(10.0f);
        t.setY(10.0f);
        t.setTextOrigin(TOP);*/
        t.setEffect(l);
        t.setTranslateX(0);
        t.setTranslateY(320);
        return t;
    }

    static Node innerShadow() {
        InnerShadow is = new InnerShadow();
        is.setOffsetX(2.0f);
        is.setOffsetY(2.0f);

        Text t = new Text(text);
        t.setEffect(is);
        t.setX(20);
        t.setY(100);
        t.setFill(GuiColors.FRG);
        t.setFont(Font.font("Roboto", FontWeight.BOLD, 80));

        t.setTranslateX(300);
        t.setTranslateY(300);

        return t;
    }


}






