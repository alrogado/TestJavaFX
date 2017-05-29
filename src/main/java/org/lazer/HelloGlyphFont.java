/**
 * Copyright (c) 2013, 2014 ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lazer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import java.io.InputStream;

public class HelloGlyphFont extends Application {

    static {
        // Register a custom default font
        InputStream resourceAsStream = HelloGlyphFont.class.getResourceAsStream("/fonts/icomoon.ttf");
        GlyphFontRegistry.register("icomoon", resourceAsStream, 16);
        //home/alrogado/.m2/repository/org/kordamp/ikonli/ikonli-devicons-pack/1.9.0/ikonli-devicons-pack-1.9.0.jar!/META-INF/resources/devicons/1.8.0/fonts/devicons.ttf
        GlyphFontRegistry.register("icomoon", resourceAsStream, 16);
        InputStream devicons = HelloGlyphFont.class.getResourceAsStream("/META-INF/resources/devicons/1.8.0/fonts/devicons.ttf");
        GlyphFontRegistry.register("devicons", devicons, 16);
    }


    private GlyphFont fontAwesome = GlyphFontRegistry.font("FontAwesome");
    private GlyphFont icoMoon = GlyphFontRegistry.font("icomoon");
    private GlyphFont devicons = GlyphFontRegistry.font("devicons");

    //	private static char FAW_TRASH = '\uf014';
    private static char FAW_GEAR  = '\uf013';
//	private static char FAW_STAR  = '\uf005';

    private static char IM_BOLD        = '\ue027';
    private static char IM_UNDERSCORED = '\ue02b';
    private static char IM_ITALIC      = '\ue13e';


    public void start(Stage stage) {

        VBox root = new VBox(10);

        root.setPadding(new Insets(10, 10, 10, 10));
        root.setMaxHeight(Double.MAX_VALUE);
        Label title = new Label("Using FontAwesome(CDN)");
        root.getChildren().add(title);
        ToolBar toolbar = new ToolBar(

                // There are many ways how you can define a Glyph:

                new Button("", new Glyph("FontAwesome", "TRASH_ALT")),              // Use the Glyph-class with a icon name
                new Button("", new Glyph("FontAwesome", FontAwesome.Glyph.STAR)),   // Use the Glyph-class with a known enum value
                new Button("", Glyph.create("FontAwesome|BUG")),                    // Use the static Glyph-class create protocol
                new Button("", fontAwesome.create("REBEL")),                        // Use the font-instance with a name
                new Button("", fontAwesome.create(FontAwesome.Glyph.SMILE_ALT)),    // Use the font-instance with a enum
                new Button("", fontAwesome.create(FAW_GEAR).color(Color.RED))       // Use the font-instance with a unicode char
        );
        root.getChildren().add(toolbar);
        title = new Label("Using IcoMoon (Local)");
        root.getChildren().add(title);

        Glyph effectGlyph = icoMoon.create(IM_UNDERSCORED)
                .color(Color.BLUE)
                .size(48)
                .useHoverEffect();

        Glyph effectGlyph2 = icoMoon.create(IM_UNDERSCORED)
                .color(Color.BLUE)
                .size(48)
                .useGradientEffect().useHoverEffect();

        toolbar = new ToolBar(

                // Since we have a custom font without named characters,
                // we have to use unicode character codes for the icons:

                new Button("", icoMoon.create(IM_BOLD).size(16)),
                new Button("", icoMoon.create(IM_UNDERSCORED).color(Color.GREEN).size(32)),
                new Button("", icoMoon.create(IM_ITALIC).size(48)),
                new Button("", effectGlyph),
                new Button("tres", effectGlyph2));
        root.getChildren().add(toolbar);

        Glyph effectGlyph3 = devicons.create('\ue60b')
                .color(Color.BLUE)
                .size(48)
                .useGradientEffect().useHoverEffect();

        toolbar = new ToolBar(
                // Since we have a custom font without named characters,
                // we have to use unicode character codes for the icons:
                new Button("la fuente que se quiera con el color que se quiera", effectGlyph3));
        root.getChildren().add(toolbar);
        
        GridPane fontDemo = new GridPane();
        fontDemo.setHgap(5);
        fontDemo.setVgap(5);
        int maxColumns = 10;
        int col = 0;
        int row = 0;
        
        for ( FontAwesome.Glyph glyph:  FontAwesome.Glyph.values() ){
        	Color randomColor = new Color( Math.random(), Math.random(), Math.random(), 1);
        	Glyph graphic = Glyph.create( "FontAwesome|" + glyph.name()).sizeFactor(2).color(randomColor).useGradientEffect();
        	Button button = new Button(glyph.name(), graphic);
        	button.setContentDisplay(ContentDisplay.TOP);
        	button.setMaxWidth(Double.MAX_VALUE);
        	col = col % maxColumns + 1;
        	if ( col == 1 ) row++;
        	fontDemo.add( button, col, row);
        	GridPane.setFillHeight(button, true);
        	GridPane.setFillWidth(button, true);
        }
        
        ScrollPane scroller = new ScrollPane(fontDemo);
        scroller.setFitToWidth(true);
        
        TabPane tabs = new TabPane();
        Tab tab = new Tab("FontAwesome Glyph Demo");
        tab.setContent(scroller);
        tabs.getTabs().add(tab);

        
        root.getChildren().add(tabs);
        VBox.setVgrow(tabs, Priority.ALWAYS);

        stage.initStyle(StageStyle.UNIFIED);
        stage.setScene(new Scene(root));
        /*stage.setFullScreen(true);
        stage.setAlwaysOnTop(true);
        stage.setFullScreenExitHint("");*/
        stage.show();
        
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}