/*
 * Copyright 2015-2017 Andres Almiray
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.testjfx.util.extraapps;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.devicons.Devicons;
import org.kordamp.ikonli.elusive.Elusive;
import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.fontelico.Fontelico;
import org.kordamp.ikonli.foundation.Foundation;
import org.kordamp.ikonli.hawcons.HawconsFilled;
import org.kordamp.ikonli.hawcons.HawconsStroke;
import org.kordamp.ikonli.icomoon.Icomoon;
import org.kordamp.ikonli.ionicons.Ionicons;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.maki.Maki;
import org.kordamp.ikonli.maki2.Maki2;
import org.kordamp.ikonli.material.Material;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.metrizeicons.MetrizeIcons;
import org.kordamp.ikonli.octicons.Octicons;
import org.kordamp.ikonli.openiconic.Openiconic;
import org.kordamp.ikonli.runestroicons.Runestroicons;
import org.kordamp.ikonli.typicons.Typicons;
import org.kordamp.ikonli.weathericons.WeatherIcons;
import org.kordamp.ikonli.websymbols.Websymbols;

import java.net.URL;
import java.util.*;
import static java.util.EnumSet.allOf;

/**
 * @author Andres Almiray
 */
public class SearchIconsApp extends Application {

    List<Class> allIkonClasses = new ArrayList<Class>(){{
        add(Devicons.class);
        add(Elusive.class);
        add(Feather.class);
        add(FontAwesome.class);
        add(Fontelico.class);
        add(Foundation.class);
        add(HawconsFilled.class);
        add(HawconsStroke.class);
        add(Icomoon.class);
        add(Ionicons.class);
        add(Maki.class);
        add(Maki2.class);
        add(Material.class);
        add(MaterialDesign.class);
        add(MetrizeIcons.class);
        add(Octicons.class);
        add(Openiconic.class);
        add(Runestroicons.class);
        add(Typicons.class);
        add(WeatherIcons.class);
        add(Websymbols.class);
    }};


    public static void main(String[] args) {
        launch(SearchIconsApp.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL location = getClass().getResource("/org/testjfx/fxml/searchIcons.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);
        TabPane tabPane = fxmlLoader.load();

        tabPane.getTabs().add(new SearchTab());
        allIkonClasses.forEach(aClass -> {
            try {
                tabPane.getTabs().add(new DemoTab(aClass, allOf(aClass)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(tabPane);
        scene.getStylesheets().add("org/testjfx/css/searchIcons.css");

        primaryStage.setTitle("Ikonli Sampler");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(1024);
        primaryStage.show();
    }

    private static class DemoTab extends Tab {
        private DemoTab(Class<Ikon> iconFontClass, EnumSet<? extends Ikon> enumSet) throws Exception {
            super(iconFontClass.getSimpleName());
            setClosable(false);

            setContent(createPaneForIcons(iconFontClass.getSimpleName(),enumSet));
        }
    }

    private class SearchTab extends Tab {
        public SearchTab() {
            super("Search by name");
            setClosable(false);

            BorderPane borderPane = new BorderPane();
            HBox controlsBox = new HBox(10);
            TextField textField = new TextField();
            controlsBox.getChildren().add(textField);
            JFXButton jfxButton = new JFXButton("Pincha para buscar");
            controlsBox.getChildren().add(jfxButton);
            Map<String,Set<Ikon>> classAndIkonsSet = new HashMap<>();
            jfxButton.setOnMouseClicked((e) -> {
                Set<Ikon> ikonsSet = new HashSet<>();
                allIkonClasses.forEach(aClass -> {
                    //classAndIkonsSet.put(aClass.getSimpleName())
                    EnumSet<? extends Ikon> icons = allOf(aClass);
                    for (Ikon icon : icons) {
                        if (icon.getDescription().contains(textField.getCharacters())) {
                            ikonsSet.add(icon);
                        }
                    }
                });
                borderPane.setCenter(createPaneForIcons("pepe", ikonsSet));
                setContent(borderPane);
            });
            borderPane.setTop(controlsBox);
            setContent(borderPane);
        }
    }

    public static ScrollPane createPaneForIcons(String simpleName, Set<? extends Ikon> enumSet) {
        GridPane pane = new GridPane();
        ScrollPane scrollPane = new ScrollPane(pane);

        int column = 0;
        int row = 0;
        int index = 0;
        for (Ikon value : enumSet) {
            FontIcon icon = new FontIcon(value);
            icon.getStyleClass().setAll("font-icon");

            Color randomColor = new Color( Math.random(), Math.random(), Math.random(), 1);
            icon.setIconColor(randomColor);

            /*Glyph graphic = Glyph.create( simpleName+"|" + value.getDescription()).sizeFactor(2).color(randomColor).useGradientEffect();
            Button button = new Button(value.getDescription(), graphic);
            //Esto va en la demo
            //Glyph graphic = Glyph.create( "FontAwesome|" + glyph.name()).sizeFactor(2).color(randomColor).useGradientEffect();
            button.setContentDisplay(ContentDisplay.TOP);
            button.setMaxWidth(Double.MAX_VALUE);
            pane.add(button, column++, row);
            GridPane.setMargin(icon, new Insets(10, 10, 10, 10));
            if (++index % 10 == 0) {
                column = 0;
                row++;
            }*/

            pane.add(icon, column++, row);
            GridPane.setMargin(icon, new Insets(10, 10, 10, 10));
            if (++index % 10 == 0) {
                column = 0;
                row++;
            }

            //SelectableText decriptionLabel = new SelectableText(value.getDescription()+ " "+icon.getIconCode()+ " "+ Integer.toHexString(value.getCode() | 0x10000).substring(1));
            SelectableText decriptionLabel = new SelectableText(value.getClass().getSimpleName()+ "."+icon.getIconCode()+ " "+ value.getDescription());
            pane.add(decriptionLabel, column++, row);
            GridPane.setMargin(icon, new Insets(10, 10, 10, 10));
            if (++index % 10 == 0) {
                column = 0;
                row++;
            }
        }
        return scrollPane;
    }

    static class SelectableText extends TextField {
        SelectableText(String text) {
            super(text);
            setEditable(false);
            setPrefColumnCount(20);
        }
    }

}