package org.lazer.util;

import com.chrisnewland.demofx.DemoConfig;
import com.chrisnewland.demofx.DemoFX;
import com.chrisnewland.demofx.DemoFXApplication;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Created by alrogado on 6/2/17.
 */
public class Srpite3dDemo extends Application
{
    private static String[] args;

    public Srpite3dDemo() {
    }

    public static void main(String[] args)
    {
		/*				   blur
                           bobs
                           bounce+
                           burst+
                           checkerboard
                           chord
                           chromakey
                           cogs
                           colourbackground
                           concentric
                           credits
                           creditssprite
                           cubefield
                           cyclebackground
                           diamonds
                           equaliser
                           equalisercubes
                           falling
                           feedback
                           fireworks
                           flash
                           glowboard
                           grid
                           hexagons
                           honeycomb
                           hue
                           imagebackground
                           inversechromakey
                           mandala
                           mandelbrot
                           mask
                           maskstack
                           mirrorx
                           mirrory
                           moire
                           pentagons
                           picinpic
                           quadplay
                           rain
                           rainbow
                           rawplayer
                           raytrace
                           rings
                           rotations
                           sea
                           sheet
                           shift
                           sierpinski
                           spin
                           sprite3d
                           spritewave
                           squares
                           starfield
                           starfieldsprite
                           stars
                           texcube
                           texsphere
                           textbounce
                           textlabel
                           textlayers
                           textring
                           textwave
                           textwavesprite
                           tiles
                           triangles
                           tubestack
                           tunnel
                           twister
                           vumeter
                           wordsearch*/

        Srpite3dDemo.args = args;
        Application.launch(args);
    }


    public void start(final Stage stage) throws Exception
    {
        DemoFX demoFX = getDemoFX();

        Scene scene = demoFX.getScene();

        stage.setTitle("DemoFX / JavaFX Demoscene Engine / @chriswhocodes");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest((WindowEvent arg0) -> demoFX.stopDemo());

        // Double-click to interrupt the demo and show the result panel:
        scene.setOnMouseClicked((MouseEvent me) -> {
            if (me.getClickCount() == 2) {
                demoFX.stopDemo();
            }
        });

        demoFX.runDemo();
    }

    public DemoFX getDemoFX() {
        args = new String[]{"-e","sprite3d"};
        DemoConfig config = DemoConfig.buildConfig(args);

        if (config == null)
        {
            System.err.print(DemoConfig.getUsageError());
            System.exit(-1);
        }

        return new DemoFX(config);
    }
}
